package com.senla.entity;

import java.util.ArrayList;

public class Deck {

    private final ArrayList<Container> containers;

    public Deck() {
        containers = new ArrayList<>();
    }

    public ArrayList<Container> getContainers() {
        return containers;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (var container : containers) {
            stringBuilder.append(container.toString());
        }
        return stringBuilder.toString();
    }
}
