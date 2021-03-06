package com.senla.entity;

import com.senla.enums.ContainerSize;

public class Container {

    private final ContainerSize containerSize;
    private float litersOfWater;

    public Container(ContainerSize containerSize) {
        this.containerSize = containerSize;
        litersOfWater = 0.0f;
    }

    public ContainerSize getContainerSize() {
        return containerSize;
    }

    public float getLitersOfWater() {
        return litersOfWater;
    }

    public void setLitersOfWater(float litersOfWater) {
        this.litersOfWater = litersOfWater;
    }

    @Override
    public String toString(){
        return "\nContainer size: " + containerSize + ";\n" + "Liters of water: " + litersOfWater + ";\n";
    }
}
