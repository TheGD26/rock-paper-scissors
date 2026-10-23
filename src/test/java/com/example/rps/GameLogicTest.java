package com.example.rps;

import com.example.rps.model.Game;
import com.example.rps.model.Move;
import com.example.rps.model.Outcome;
import com.example.rps.model.Round;
import com.example.rps.player.ComputerPlayer;
import com.example.rps.player.HumanPlayer;
import com.example.rps.strategy.FrequencyStrategy;
import com.example.rps.strategy.MoveStrategy;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GameLogicTest {

    @Test
    void rulesAreCorrect() {
        assertEquals(Outcome.WIN, Outcome.decide(Move.ROCK, Move.SCISSORS));
        assertEquals(Outcome.WIN, Outcome.decide(Move.PAPER, Move.ROCK));
        assertEquals(Outcome.WIN, Outcome.decide(Move.SCISSORS, Move.PAPER));
        assertEquals(Outcome.LOSE, Outcome.decide(Move.ROCK, Move.PAPER));
        assertEquals(Outcome.DRAW, Outcome.decide(Move.PAPER, Move.PAPER));
    }

    @Test
    void gameTracksScoreAndHistory() {
        MoveStrategy alwaysRock = new MoveStrategy() {
            public Move nextMove() { return Move.ROCK; }
            public String getName() { return "Rock"; }
        };
        Game game = new Game(new HumanPlayer("Ann"), new ComputerPlayer("CPU", alwaysRock));

        Round r = game.playRound(Move.PAPER);
        game.playRound(Move.SCISSORS);
        game.playRound(Move.ROCK);

        assertEquals(Outcome.WIN, r.outcome());
        assertEquals(1, game.getScoreboard().getWins());
        assertEquals(1, game.getScoreboard().getLosses());
        assertEquals(1, game.getScoreboard().getDraws());
        assertEquals(3, game.getHistory().size());

        game.reset();
        assertEquals(0, game.getScoreboard().getTotal());
    }

    @Test
    void frequencyStrategyCountersFavouriteMove() {
        FrequencyStrategy s = new FrequencyStrategy();
        s.observe(Move.ROCK);
        s.observe(Move.ROCK);
        s.observe(Move.PAPER);
        assertEquals(Move.PAPER, s.nextMove());
    }
}
