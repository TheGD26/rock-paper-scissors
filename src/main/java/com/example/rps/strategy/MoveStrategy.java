package com.example.rps.strategy;

import com.example.rps.model.Move;

/** How a computer player picks its move. */
public interface MoveStrategy {
    Move nextMove();

    String getName();

    /** Lets "learning" strategies see what the opponent played. Optional. */
    default void observe(Move opponentMove) {
    }

    default void reset() {
    }
}
