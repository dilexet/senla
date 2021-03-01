package com.senla.manager;

import com.senla.entity.Container;
import com.senla.entity.Port;
import com.senla.entity.Ship;
import com.senla.enums.ContainerSize;
import com.senla.service.IContainerService;
import com.senla.service.IPortService;
import com.senla.service.IShipService;

import java.util.ArrayList;
import java.util.Scanner;

public class Worker {
    private final Port port;
    private final ArrayList<Ship> allShips;
    private final IPortService portService;
    private final IShipService shipService;
    private final IContainerService containerService;

    public Worker(Port port, IPortService portService, IShipService shipService, IContainerService containerService) {
        this.port = port;
        allShips = new ArrayList<>();

        this.portService = portService;
        this.shipService = shipService;
        this.containerService = containerService;
    }

    public void addShip(Ship ship) {
        allShips.add(ship);
    }

    public Ship viewShipById(int id) throws Exception {
        var ship = allShips.stream().filter(s -> s.getId() == id).findFirst();
        if (ship.isEmpty()) {
            throw new Exception("Ship not found");
        } else {
            return ship.get();
        }
    }

    // case 1
    public String waterVolume() {
        return Double.toString(portService.seeWater(port));
    }

    // case 2
    public String listShipsInPort() {
        StringBuilder data = new StringBuilder();
        for (var ship : portService.getShips(port)) {
            data.append(ship.toString());
        }
        return data.toString();
    }

    // case 3
    public String removeShipFromPort(int id) {
        portService.RemoveShip(port, id);
        return "The ship left the port";
    }

    // case 4
    public String createShip() throws Exception {
        var ship = shipService.createShip();

        // TODO: вынести логику ввода/вывода в меню
        boolean flag = true;
        while (flag) {
            System.out.println("""
                    Какого размера будет контейнер?
                    0. Exit
                    1. Big;
                    2. Small;
                    """);
            Scanner scanner = new Scanner(System.in);
            var value = scanner.nextInt();
            System.out.println("Сколько воды будет в контейнере (литры)?");
            var water = scanner.nextFloat();
            // -----------------------------------------------------------------
            Container container;
            switch (value) {
                case 0 -> flag = false;
                case 1 -> {
                    container = containerService.createContainer(ContainerSize.Big);
                    containerService.addWater(container, water);
                    flag = shipService.addContainer(ship, container);
                }
                case 2 -> {
                    container = containerService.createContainer(ContainerSize.Small);
                    containerService.addWater(container, water);
                    flag = shipService.addContainer(ship, container);
                }
                default -> throw new Exception("incorrect input");
            }
        }
        addShip(ship);
        return "Ship successfully created";
    }

    // case 5
    public String InformationAboutShipsExpected() {
        StringBuilder data = new StringBuilder();
        for (var ship : portService.getExpectedShips(port)) {
            data.append(ship.toString());
        }
        return data.toString();
    }

    public String shipInfo(int id) throws Exception {
        return viewShipById(id).toString();
    }

    // case 6
    public String loadShipIntoPort(int id) throws Exception {
        portService.TakeShip(port, viewShipById(id));
        return "The ship is successfully loaded into the port";
    }
}
