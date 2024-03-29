package com.senla.service;

import com.senla.entity.Port;
import com.senla.entity.Ship;

import java.util.Collection;

public interface IPortService {
    Collection<Ship> getShips(Port port);
    Collection<Ship> getExpectedShips(Port port);
    void loadShip(Port port, Ship ship) throws Exception;
    double seeWater(Port port);
    void removeShip(Port port);
}
