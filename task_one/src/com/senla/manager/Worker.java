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

    private void addShip(Ship ship) {
        allShips.add(ship);
    }

    private Ship viewShipById(int id) throws Exception {
        var ship = allShips.stream().filter(s -> s.getId() == id).findFirst();
        if (ship.isEmpty()) {
            throw new Exception("Ship not found");
        } else {
            return ship.get();
        }
    }

    public String processCommand(int command) throws Exception {
        Scanner scanner = new Scanner(System.in);
        switch (command) {
            case 0:
                return "Exit";
            case 1:
                return waterVolume();
            case 2:
                return listShipsInPort();
            case 3:
                System.out.println("Введите id корабля: ");
                int id = scanner.nextInt();
                return removeShipFromPort(id);
            case 4:
                return createShip();
            case 5:
                return informationAboutShipsExpected();
            case 6:
                System.out.println("Введите id корабля: ");
                int id3 = scanner.nextInt();
                return loadShipIntoPort(id3);
            case 7:
                System.out.println("Введите id корабля: ");
                int id2 = scanner.nextInt();
                return shipInfo(id2);
            default:
                throw new Exception("incorrect input");
        }
    }

    // case 1
    private String waterVolume() {
        return Double.toString(portService.seeWater(port));
    }

    // case 2
    private String listShipsInPort() {
        StringBuilder data = new StringBuilder();
        for (var ship : portService.getShips(port)) {
            data.append(ship.toString());
        }
        return data.toString();
    }

    // case 3
    private String removeShipFromPort(int id) {
        portService.RemoveShip(port, id);
        return "The ship left the port";
    }

    // case 4
    private String createShip() throws Exception {
        var ship = shipService.createShip();

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
                    container = containerService.createContainer(ContainerSize.BIG);
                    containerService.addWater(container, water);
                    flag = shipService.addContainer(ship, container);
                }
                case 2 -> {
                    container = containerService.createContainer(ContainerSize.SMALL);
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
    private String informationAboutShipsExpected() {
        StringBuilder data = new StringBuilder();
        for (var ship : portService.getExpectedShips(port)) {
            data.append(ship.toString());
        }
        return data.toString();
    }

    private String shipInfo(int id) throws Exception {
        return viewShipById(id).toString();
    }

    // case 6
    private String loadShipIntoPort(int id) throws Exception {
        portService.TakeShip(port, viewShipById(id));
        return "The ship is successfully loaded into the port";
    }
}
