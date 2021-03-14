package com.senla.entity;

import com.senla.enums.ShipState;

public class Ship {

    private static int count = 0;
    private final int id;
    private final String name;
    private final Deck deckOne;
    private final Deck deckTwo;
    private ShipState shipState;

    public Ship() {
        count++;
        id = count;
        name = "Ship № " + id;
        shipState = ShipState.FREE;
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

    public String getName(){
        return name;
    }

    public ShipState getShipState() {
        return shipState;
    }

    public void setShipState(ShipState shipState) {
        this.shipState = shipState;
    }

    @Override
    public String toString(){
        return "\nShip name: " + name +
                "\nDeck one: \n" + deckOne.toString() +
                "\nDeck two: \n" + deckTwo.toString() +
                "\nShip state: " + shipState;
    }
}
