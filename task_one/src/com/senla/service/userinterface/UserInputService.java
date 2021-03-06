package com.senla.service.userinterface;

import com.senla.manager.Worker;

import java.util.Scanner;

public class UserInputService {
    public String processCommand(int command, Worker worker) throws Exception {
        Scanner scanner = new Scanner(System.in);
        switch (command) {
            case 0:
                return "Exit";
            case 1:
                return worker.waterVolume();
            case 2:
                return worker.listShipsInPort();
            case 3:
                System.out.println("Введите id корабля: ");
                int id = scanner.nextInt();
                return worker.removeShipFromPort(id);
            case 4:
                return worker.createShip();
            case 5:
                return worker.informationAboutShipsExpected();
            case 6:
                System.out.println("Введите id корабля: ");
                int id3 = scanner.nextInt();
                return worker.loadShipIntoPort(id3);
            case 7:
                System.out.println("Введите id корабля: ");
                int id2 = scanner.nextInt();
                return worker.shipInfo(id2);
            default:
                throw new Exception("incorrect input");
        }
    }
}
