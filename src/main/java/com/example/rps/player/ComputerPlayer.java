package com.example.rps.player;

import com.example.rps.model.Move;
import com.example.rps.strategy.MoveStrategy;

/**
 * A computer player that delegates its decision to a pluggable
 * MoveStrategy (Strategy design pattern + polymorphism).
 */
public class ComputerPlayer extends Player {
    private final MoveStrategy strategy;

    public ComputerPlayer(String name, MoveStrategy strategy) {
        super(name);
        this.strategy = strategy;
    }

    @Override
    public Move chooseMove() {
        return strategy.nextMove();
    }

    public void observeOpponent(Move opponentMove) {
        strategy.observe(opponentMove);
    }

    public void forget() {
        strategy.reset();
    }

    public String getStrategyName() {
        return strategy.getName();
    }
}
