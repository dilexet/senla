package com.senla.service;

import com.senla.entity.Container;
import com.senla.entity.Ship;

public interface IShipService {
    Ship createShip();
    boolean addContainer(Ship ship, Container container);
    float unloadShip(Ship ship);
}
