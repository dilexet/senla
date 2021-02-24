package com.senla.entity;

import com.senla.enums.ContainerSize;

import java.util.Random;

public class Ship {
    private final int id;
    private final Deck deckOne;
    private final Deck deckTwo;

    public Ship() {
        Random random = new Random();
        id = random.nextInt();
        deckOne = new Deck();
        deckTwo = new Deck();
    }

    public int getId() {
        return id;
    }

    public void AddContainer(Container container) {
        try {
            if(CheckContainer(container)){
                if (ChekFreeSpace(deckOne)) {
                    deckOne.getContainers().add(container);
                } else if (ChekFreeSpace(deckTwo)) {
                    deckTwo.getContainers().add(container);
                } else {
                    throw new Exception("The deck is full");
                }
            }
            else {
                throw new Exception("Error adding container");
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public float UnloadShip() {
        float liters = 0.0f;
        for (var item : deckOne.getContainers()) {
            liters += item.UnloadWater();
        }
        for (var item : deckTwo.getContainers()) {
            liters += item.UnloadWater();
        }
        return liters;
    }

    private boolean CheckContainer(Container container){
        for (var item : deckOne.getContainers()) {
            if(item == container){
                return false;
            }
        }
        for (var item : deckTwo.getContainers()) {
            if(item == container){
                return false;
            }
        }
        return true;
    }

    private boolean ChekFreeSpace(Deck deck) {
        return deck.getContainers().stream().filter(s -> s.getContainerSize() == ContainerSize.Big).count() < 1 &&
                deck.getContainers().stream().filter(s -> s.getContainerSize() == ContainerSize.Small).count() < 2;
    }
}
