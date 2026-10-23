package com.example.rps.player;

import com.example.rps.model.Move;

/**
 * Abstract base class for anyone who can play (abstraction).
 * Subclasses decide HOW a move is chosen.
 */
public abstract class Player {
    private final String name;

    protected Player(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public abstract Move chooseMove();
}
