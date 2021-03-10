package com.senla.entity;

import java.util.ArrayList;

public class Port {
    private static Port port = null;
    private final ArrayList<Ship> ships;
    private final ArrayList<Ship> expectedShips;
    private double waterVolume;

    private Port() {
        ships = new ArrayList<>();
        expectedShips = new ArrayList<>();
        waterVolume = 0.0;
    }

    public static Port getPort() {
        if (port == null) {
            port = new Port();
        }
        return port;
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
