package com.senla.entity;

import com.senla.enums.ContainerSize;

public class Container {
    private float litersOfWater;
    private final ContainerSize containerSize;

    public Container(ContainerSize containerSize) {
        this.containerSize = containerSize;
        litersOfWater = 0.0f;
    }

    public ContainerSize getContainerSize() {
        return containerSize;
    }

    public float UnloadWater(){
        var item = litersOfWater;
        litersOfWater = 0.0f;
        return item;
    }

    public void AddWater(float liters) {
        try {
            switch (containerSize) {
                case Big:
                    if (litersOfWater >= 1000) {
                        throw new Exception("The container is full");
                    } else {
                        if (litersOfWater + liters > 1000) {
                            throw new Exception("The container cannot hold that much water");
                        } else {
                            litersOfWater += liters;
                        }
                    }
                    break;
                case Small:
                    if (litersOfWater >= 450) {
                        throw new Exception("The container is full");
                    } else {
                        if (litersOfWater + liters > 450) {
                            throw new Exception("The container cannot hold that much water");
                        } else {
                            litersOfWater += liters;
                        }
                    }
                    break;
                default:
                    throw new IllegalStateException("Container size not specified");
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
}
