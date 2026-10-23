package com.example.rps.player;

import com.example.rps.model.Move;

/** A human player: the move comes from the UI. */
public class HumanPlayer extends Player {
    private Move nextMove;

    public HumanPlayer(String name) {
        super(name);
    }

    public void setNextMove(Move move) {
        this.nextMove = move;
    }

    @Override
    public Move chooseMove() {
        if (nextMove == null) {
            throw new IllegalStateException("Human player has not selected a move");
        }
        return nextMove;
    }
}
