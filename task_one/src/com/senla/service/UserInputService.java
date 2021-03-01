package com.senla.service;

import com.senla.entity.Container;
import com.senla.enums.ContainerSize;

import java.util.Scanner;

public class UserInputService {
    public void processCommand(int command, Manager manager) throws Exception {
        switch (command){
            case 0:
                break;
            case 1:
                double waterVolume = new PortService().seeWater(manager.getPort());
                break;
            case 2:
                new PortService().getShips(manager.getPort());
                break;
            case 3:
                // TODO: ввод id корабля
                int id = 2;
                new PortService().RemoveShip(manager.getPort(), id);
                break;
            case 4:
                ShipService shipService = new ShipService();
                var ship = shipService.createShip();

                // TODO: вынести логику ввода/вывода в меню
                boolean flag = true;
                while (flag){
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

                    ContainerService containerService = new ContainerService();
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
                manager.addShip(ship);
                break;
            case 5:
                // TODO: сделать вывод инфы о корабле/кораблях
                var expectedShips = new PortService().getExpectedShips(manager.getPort());
                // TODO: ввод id, добавить в модельку кораблика поле ShipState(busy, free, pending)
                int id2 = 2;
                var ship2 = manager.ViewShipById(id2);
                break;
            case 6:
                int id3 = 2;
                new PortService().TakeShip(manager.getPort(), manager.ViewShipById(id3));
                break;
            default:
                throw new Exception("incorrect input");
        }
    }
}
