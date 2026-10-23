package com.example.rps.model;

/**
 * Keeps the running score. Fields are private and can only change
 * through record(), which keeps the score consistent (encapsulation).
 */
public class Scoreboard {
    private int wins;
    private int losses;
    private int draws;

    public void record(Outcome outcome) {
        switch (outcome) {
            case WIN -> wins++;
            case LOSE -> losses++;
            case DRAW -> draws++;
        }
    }

    public void reset() {
        wins = losses = draws = 0;
    }

    public int getWins() { return wins; }
    public int getLosses() { return losses; }
    public int getDraws() { return draws; }
    public int getTotal() { return wins + losses + draws; }
}
