package com.example.rps.strategy;

import java.util.function.Supplier;

/** Maps a difficulty level to a strategy factory. */
public enum Difficulty {
    EASY(RandomStrategy::new),
    HARD(FrequencyStrategy::new);

    private final Supplier<MoveStrategy> factory;

    Difficulty(Supplier<MoveStrategy> factory) {
        this.factory = factory;
    }

    public MoveStrategy createStrategy() {
        return factory.get();
    }
}
