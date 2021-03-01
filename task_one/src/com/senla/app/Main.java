package com.senla.app;

import com.senla.builder.ApplicationBuilder;
import com.senla.menu.Menu;

public class Main {
    public static void main(String[] args) {

        /*
        Menu - showOptions,
        com.senla.service - Manager - performAction
        Initializer - start
        вся логика подсчётов в менеджере
инициализатор - меню - сервисы - модели
        * */
        new ApplicationBuilder().run(new Menu());
    }
}