package com.example.rps.model;

/** Result of a round from the human player's point of view. */
public enum Outcome {
    WIN, LOSE, DRAW;

    public static Outcome decide(Move player, Move computer) {
        if (player == computer) return DRAW;
        return player.beats(computer) ? WIN : LOSE;
    }
}
