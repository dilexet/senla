package com.senla.entity;

public class Ship {

    private static int count = 0;
    private final int id;
    private final Deck deckOne;
    private final Deck deckTwo;

    public Ship() {
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

    @Override
    public String toString(){
        return "\nShip id: " + id + "\nDeck one: \n" + deckOne.toString() +
                "\nDeck two: \n" + deckTwo.toString();
    }
}
