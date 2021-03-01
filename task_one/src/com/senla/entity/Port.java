package com.senla.entity;

import java.util.ArrayList;
import java.util.Collection;

public class Port {
    private final ArrayList<Ship> ships;
    private final ArrayList<Ship> expectedShips;
    private double waterVolume;

    public Port() {
        ships = new ArrayList<>();
        expectedShips = new ArrayList<>();
        waterVolume = 0.0;
    }

    public double getWaterVolume() {
        return waterVolume;
    }

    public void setWaterVolume(double waterVolume) {
        this.waterVolume = waterVolume;
    }

    public ArrayList<Ship> getExpectedShips() {
        return expectedShips;
    }

    public ArrayList<Ship> getShips() {
        return ships;
    }
}
