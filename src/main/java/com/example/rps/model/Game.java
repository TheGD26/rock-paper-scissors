package com.example.rps.model;

import com.example.rps.player.ComputerPlayer;
import com.example.rps.player.HumanPlayer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * A game session between a human and the computer.
 * It is composed of players, a scoreboard and a round history (composition).
 */
public class Game {
    private final HumanPlayer human;
    private final ComputerPlayer computer;
    private final Scoreboard scoreboard = new Scoreboard();
    private final List<Round> history = new ArrayList<>();

    public Game(HumanPlayer human, ComputerPlayer computer) {
        this.human = human;
        this.computer = computer;
    }

    public Round playRound(Move humanMove) {
        human.setNextMove(humanMove);

        Move playerMove = human.chooseMove();
        Move computerMove = computer.chooseMove();
        Outcome outcome = Outcome.decide(playerMove, computerMove);

        Round round = new Round(history.size() + 1, playerMove, computerMove, outcome);
        history.add(round);
        scoreboard.record(outcome);
        computer.observeOpponent(playerMove);
        return round;
    }

    public void reset() {
        history.clear();
        scoreboard.reset();
        computer.forget();
    }

    public HumanPlayer getHuman() { return human; }
    public ComputerPlayer getComputer() { return computer; }
    public Scoreboard getScoreboard() { return scoreboard; }

    public List<Round> getHistory() {
        return Collections.unmodifiableList(history);
    }
}
