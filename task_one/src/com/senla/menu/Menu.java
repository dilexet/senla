package com.senla.menu;

import com.senla.entity.Port;
import com.senla.manager.Worker;
import com.senla.service.implementation.ContainerService;
import com.senla.service.implementation.PortService;
import com.senla.service.implementation.ShipService;

import java.util.Scanner;

public class Menu {
    public void showOptions() {
        System.out.println(
                """
                        0. Exit;\s
                        1. Посмотреть сколько воды в порту;
                        2. Посмотреть список кораблей в порту;
                        3. Удалить корабль из порта (вода остаётся в порту);
                        4. Создать корабль -> наполнить корабль контейнерами с водой
                        5. Посмотреть список кораблей, ожидающих прибытия в порт
                        6. Загрузить корабль в порт
                        7. Посмотреть инормацию по конкретному кораблю
                        """);
        try {
            int option;
            var port = Port.getPort();
            Worker worker = new Worker(port, new PortService(), new ShipService(), new ContainerService());
            do {
                option = readOption();
                System.out.println(worker.processCommand(option));
            } while (option != 0);
        } catch (Exception ex) {
            System.out.println(ex.toString());
        }
    }

    private int readOption() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextInt();
    }
}
