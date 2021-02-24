package com.senla.entity;

import java.util.ArrayList;
import java.util.Collection;

public class Port {
    private final ArrayList<Ship> ships;
    private final ArrayList<Ship> ExpectedShips;
    private double waterVolume;

    public Port() {
        ships = new ArrayList<>();
        ExpectedShips = new ArrayList<>();
        waterVolume = 0.0;
    }

    public double getWaterVolume(){
        return waterVolume;
    }

    public Collection<Ship> getShips(){
        return ships;
    }

    public Collection<Ship> getExpectedShips(){
        return ExpectedShips;
    }

    public void TakeShip(Ship ship) {
        try {
            if ((long) ships.size() >= 10) {
                ExpectedShips.add(ship);
                throw new Exception("The port is full");
            } else {
                ships.add(ship);
                waterVolume += ship.UnloadShip();
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
}
