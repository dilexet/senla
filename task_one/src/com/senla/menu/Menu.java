package com.senla.menu;

import com.senla.entity.Port;
import com.senla.service.Manager;
import com.senla.service.UserInputService;

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
                        5. Посмотреть список кораблей, ожидающих прибытия в порт -> посмотреть инормацию по конкретному кораблю
                        6. Загрузить корабль в порт
                        """);
        try {
            int option;
            Manager manager = new Manager(new Port());
            do {
                option = readOption();
                new UserInputService().processCommand(option, manager);
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
