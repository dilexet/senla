package com.senla.service.implementation;

import com.senla.entity.Container;
import com.senla.entity.Deck;
import com.senla.entity.Ship;
import com.senla.enums.ContainerSize;
import com.senla.service.IShipService;

public class ShipService implements IShipService {

    public Ship createShip() {
        return new Ship();
    }

    public boolean addContainer(Ship ship, Container container) {
        if (checkContainer(ship, container)) {
            if (chekFreeSpace(ship.getDeckOne())) {
                ship.getDeckOne().getContainers().add(container);
            } else if (chekFreeSpace(ship.getDeckTwo())) {
                ship.getDeckTwo().getContainers().add(container);
            } else {
                return false;
            }
        } else {
            return false;
        }
        return true;
    }

    public float unloadShip(Ship ship) {
        float liters = 0.0f;
        for (var item : ship.getDeckOne().getContainers()) {
            var water = item.getLitersOfWater();
            item.setLitersOfWater(0.0f);
            liters += water;
        }
        for (var item : ship.getDeckTwo().getContainers()) {
            var water = item.getLitersOfWater();
            item.setLitersOfWater(0.0f);
            liters += water;
        }
        return liters;
    }

    private boolean checkContainer(Ship ship, Container container) {
        for (var item : ship.getDeckOne().getContainers()) {
            if (item == container) {
                return false;
            }
        }
        for (var item : ship.getDeckTwo().getContainers()) {
            if (item == container) {
                return false;
            }
        }
        return true;
    }

    private boolean chekFreeSpace(Deck deck) {
        return deck.getContainers().stream().filter(s -> s.getContainerSize() == ContainerSize.BIG).count() < 1 &&
                deck.getContainers().stream().filter(s -> s.getContainerSize() == ContainerSize.SMALL).count() < 2;
    }
}
