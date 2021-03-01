package com.senla.service;

import com.senla.entity.Port;
import com.senla.entity.Ship;

import java.util.ArrayList;

public class Manager {
    private Port port;
    private ArrayList<Ship> allShips;

    public Manager(Port port) {
        this.port = port;
        allShips = new ArrayList<>();
    }

    public Port getPort() {
        return port;
    }

    public ArrayList<Ship> getAllShips() {
        return allShips;
    }

    public void addShip(Ship ship){
        allShips.add(ship);
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


}
