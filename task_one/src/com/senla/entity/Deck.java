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

    // TODO: реализовать
    @Override
    public String toString(){
        return "";
    }
}
