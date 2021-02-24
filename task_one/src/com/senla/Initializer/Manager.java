package com.senla.Initializer;

import com.senla.entity.Container;
import com.senla.entity.Port;
import com.senla.entity.Ship;
import com.senla.enums.ContainerSize;

import java.util.Collection;

public class Manager {
    private final Port port;

    public Manager(Port port) {
        this.port = port;
    }

    public double ViewWaterPort() {
        return port.getWaterVolume();
    }

    public Collection<Ship> ViewListShips() {
        return port.getShips();
    }

    public Collection<Ship> ViewListExpectedShips() {
        return port.getExpectedShips();
    }

    public Ship ViewShipById(int id){
        var ship = port.getExpectedShips().stream().filter(s -> s.getId() == id).findFirst();

        if(ship.isEmpty()){
            ship = port.getShips().stream().filter(s-> s.getId() == id).findFirst();
        }

        if(ship.isPresent()) {
            return ship.get();
        }

        return ship.orElse(null);
    }

    public void LoadShipToPort(Ship ship){
        port.TakeShip(ship);
    }

    public void RemoveShip(Ship ship) {
        try {
            if (!port.getShips().remove(ship)) {
                throw new Exception("Error when removing a ship from port");
            } else {
                var item = port.getExpectedShips().stream().findFirst();
                if(item.isPresent()) {
                    port.getShips().add(item.get());
                    port.getExpectedShips().remove(item.get());
                }
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public Container CreateContainer(ContainerSize containerSize, float litersWater) {
        Container container = new Container(containerSize);
        container.AddWater(litersWater);
        return container;
    }

    public Ship CreateShip(Container[] containers) {
        Ship ship = new Ship();
        for (var container : containers) {
            ship.AddContainer(container);
        }
        return ship;
    }
}
