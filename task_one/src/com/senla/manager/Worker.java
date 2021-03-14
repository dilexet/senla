package com.senla.manager;

import com.senla.entity.Container;
import com.senla.entity.Port;
import com.senla.entity.Ship;
import com.senla.enums.ContainerSize;
import com.senla.enums.ShipState;
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

    public String processCommand(int command) throws Exception {
        return switch (command) {
            case 0 -> "Exit";
            case 1 -> waterVolume();
            case 2 -> listShipsInPort();
            case 3 -> removeShipFromPort();
            case 4 -> createShip();
            case 5 -> informationAboutShipsExpected();
            case 6 -> loadShipIntoPort();
            default -> throw new Exception("incorrect input");
        };
    }

    private void addShip(Ship ship) {
        allShips.add(ship);
    }

    private Ship getRandomShip() throws Exception {
        var ship = allShips.stream().filter(s -> s.getShipState() != ShipState.LOADED).findFirst();
        if (ship.isEmpty()) {
            throw new Exception("Ship not found");
        } else {
            return ship.get();
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
    private String removeShipFromPort() {
        portService.removeShip(port);
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
            if(value == 0){
                break;
            }
            System.out.println("Сколько воды будет в контейнере (литры)?");
            var water = scanner.nextFloat();
            // -----------------------------------------------------------------
            Container container;
            switch (value) {
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
        return ship.getName() + " successfully created";
    }

    // case 5
    private String informationAboutShipsExpected() {
        StringBuilder data = new StringBuilder();
        for (var ship : portService.getExpectedShips(port)) {
            data.append(ship.toString());
        }
        return data.toString();
    }

    // case 6
    private String loadShipIntoPort() throws Exception {
        portService.loadShip(port, getRandomShip());
        return "The ship is successfully loaded into the port";
    }
}
