package com.senla.service.implementation;

import com.senla.entity.Port;
import com.senla.entity.Ship;
import com.senla.service.IPortService;

import java.util.Collection;

public class PortService implements IPortService {

    public Collection<Ship> getShips(Port port) {
        return port.getShips();
    }

    public Collection<Ship> getExpectedShips(Port port) {
        return port.getExpectedShips();
    }

    public void TakeShip(Port port, Ship ship) throws Exception {
        if ((long) port.getShips().size() >= 10) {
            port.getExpectedShips().add(ship);
            throw new Exception("The port is full");
        } else {
            port.getShips().add(ship);
            var water = port.getWaterVolume();
            water += new ShipService().unloadShip(ship);
            port.setWaterVolume(water);
        }
    }

    public double seeWater(Port port) {
        return port.getWaterVolume();
    }

    public void RemoveShip(Port port, int id) {
        try {
            var ship = getShips(port).stream().filter(x -> x.getId() == id).findFirst();
            if (ship.isEmpty()) {
                throw new Exception("Ship not found");
            } else if (!port.getShips().remove(ship.get())) {
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
