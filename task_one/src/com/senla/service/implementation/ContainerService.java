package com.senla.service.implementation;

import com.senla.entity.Container;
import com.senla.enums.ContainerSize;
import com.senla.service.IContainerService;

public class ContainerService implements IContainerService {

    public Container createContainer(ContainerSize containerSize) {
        return new Container(containerSize);
    }

    public void addWater(Container container, float liters) throws Exception {
        switch (container.getContainerSize()) {
            case Big:
                if (container.getLitersOfWater() >= 1000) {
                    throw new Exception("The container is full");
                } else {
                    if (container.getLitersOfWater() + liters > 1000) {
                        throw new Exception("The container cannot hold that much water");
                    } else {
                        container.setLitersOfWater(container.getLitersOfWater() + liters);
                    }
                }
                break;
            case Small:
                if (container.getLitersOfWater() >= 450) {
                    throw new Exception("The container is full");
                } else {
                    if (container.getLitersOfWater() + liters > 450) {
                        throw new Exception("The container cannot hold that much water");
                    } else {
                        container.setLitersOfWater(container.getLitersOfWater() + liters);
                    }
                }
                break;
            default:
                throw new IllegalStateException("Container size not specified");
        }
    }
}
