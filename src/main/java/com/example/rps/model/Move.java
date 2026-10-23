package com.example.rps.model;

/**
 * The three possible moves. Each move knows which move it beats,
 * so the game rules live in one place instead of a big if/else chain.
 */
public enum Move {
    ROCK("✊"),
    PAPER("✋"),
    SCISSORS("✌️");

    private final String emoji;

    Move(String emoji) {
        this.emoji = emoji;
    }

    public String getEmoji() {
        return emoji;
    }

    /** Returns the move that this move defeats. */
    public Move beatsMove() {
        return switch (this) {
            case ROCK -> SCISSORS;
            case PAPER -> ROCK;
            case SCISSORS -> PAPER;
        };
    }

    /** Returns the move that defeats this move. */
    public Move losesTo() {
        return switch (this) {
            case ROCK -> PAPER;
            case PAPER -> SCISSORS;
            case SCISSORS -> ROCK;
        };
    }

    public boolean beats(Move other) {
        return beatsMove() == other;
    }
}
