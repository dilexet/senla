package com.senla.service.implementation;

import com.senla.entity.Port;
import com.senla.entity.Ship;
import com.senla.enums.ShipState;
import com.senla.service.IPortService;

import java.util.ArrayList;
import java.util.Random;

public class PortService implements IPortService {

    public ArrayList<Ship> getShips(Port port) {
        return port.getShips();
    }

    public ArrayList<Ship> getExpectedShips(Port port) {
        return port.getExpectedShips();
    }

    public void loadShip(Port port, Ship ship) throws Exception {
        if ((long) port.getShips().size() >= 10) {
            ship.setShipState(ShipState.WAITING);
            port.getExpectedShips().add(ship);
            throw new Exception("The port is full");
        } else {
            ship.setShipState(ShipState.LOADED);
            port.getShips().add(ship);
            var water = port.getWaterVolume();
            water += new ShipService().unloadShip(ship);
            port.setWaterVolume(water);
        }
    }

    public double seeWater(Port port) {
        return port.getWaterVolume();
    }

    public void removeShip(Port port) {
        Random random = new Random();
        try {
            var ship = getShips(port).get(random.nextInt(getShips(port).size() - 1));
            if (ship == null) {
                throw new Exception("Ship not found");
            } else if (!port.getShips().remove(ship)) {
                throw new Exception("Error when removing a ship from port");
            } else {
                var item = port.getExpectedShips().stream().findFirst();
                if (item.isPresent()) {
                    port.getShips().add(item.get());
                    port.getExpectedShips().remove(item.get());
                }
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
}
