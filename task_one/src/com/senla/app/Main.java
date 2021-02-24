package com.senla.app;

import com.senla.entity.Container;
import com.senla.entity.Port;
import com.senla.enums.ContainerSize;
import com.senla.Initializer.Manager;

public class Main {
    public static void main(String[] args) {

        /*
        Menu - showOptions,
        com.senla.service - Manager - performAction
        Initializer - start
        вся логика подсчётов в менеджере
        
        * */
        Manager manager = new Manager(new Port());

        //  4. Создать корабль -> наполнить корабль контейнерами с водой
        var container1 = manager.CreateContainer(ContainerSize.Big, 600);
        var container2 = manager.CreateContainer(ContainerSize.Small, 400);
        var container3 = manager.CreateContainer(ContainerSize.Big, 800);
        var container4 = manager.CreateContainer(ContainerSize.Small, 300);

        var ship1 = manager.CreateShip(new Container[]{container1, container2});
        var ship2 = manager.CreateShip(new Container[] {container3, container4});

        //  6. Загрузить корабль в порт
        manager.LoadShipToPort(ship1);
        manager.LoadShipToPort(ship2);

        //  1. Посмотреть сколько воды в порту
        var waterVolume = manager.ViewWaterPort();

        //  2. Посмотреть список кораблей в порту
        var ships = manager.ViewListShips();

        //  3. Удалить корабль из порта (вода остаётся в порту)
        manager.RemoveShip(ship1);

        //  5. Посмотреть список кораблей, ожидающих прибытия в порт -> посмотреть инормацию по конкретному кораблю
        var expectedShips = manager.ViewListExpectedShips();
        var shipById = manager.ViewShipById(5433);
    }
}