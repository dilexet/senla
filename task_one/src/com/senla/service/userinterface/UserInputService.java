package com.senla.service.userinterface;

import com.senla.manager.Worker;

public class UserInputService {
    public String processCommand(int command, Worker worker) throws Exception {
        // TODO: информацию выводить перегрезив toString, и в меню возвращать строку
        switch (command) {
            case 0:
                return "Exit";
            case 1:
                return worker.waterVolume();
            case 2:
                return worker.listShipsInPort();
            case 3:
                // TODO: ввод id корабля
                int id = 2;
                return worker.removeShipFromPort(id);
            case 4:
                return worker.createShip();
            case 5:
                // TODO: вывести инфу по всем кораблям, затем попросить ввод id и вывести инфу об определённом корабле
                // TODO: добавить в модельку кораблика поле ShipState(busy, free, pending)
                int id2 = 2;
                worker.shipInfo(id2);
                return worker.InformationAboutShipsExpected();
            case 6:
                int id3 = 2;
                return worker.loadShipIntoPort(id3);
            default:
                throw new Exception("incorrect input");
        }
    }
}
