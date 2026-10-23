package com.example.rps.strategy;

import com.example.rps.model.Move;

import java.util.Random;

/** Picks a completely random move — the classic fair opponent. */
public class RandomStrategy implements MoveStrategy {
    private final Random random;

    public RandomStrategy() {
        this(new Random());
    }

    public RandomStrategy(Random random) {
        this.random = random;
    }

    @Override
    public Move nextMove() {
        Move[] moves = Move.values();
        return moves[random.nextInt(moves.length)];
    }

    @Override
    public String getName() {
        return "Easy (random)";
    }
}
