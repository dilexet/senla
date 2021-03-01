package com.senla.entity;

import java.util.Random;

public class Ship {

    private static int count = 0;
    private final int id;
    private final Deck deckOne;
    private final Deck deckTwo;

    public Ship() {
        Random random = new Random();
        count++;
        id = count;
        deckOne = new Deck();
        deckTwo = new Deck();
    }

    public int getId() {
        return id;
    }

    public Deck getDeckTwo() {
        return deckTwo;
    }

    public Deck getDeckOne() {
        return deckOne;
    }
}
