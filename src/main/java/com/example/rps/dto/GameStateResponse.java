package com.example.rps.dto;

import com.example.rps.model.Game;
import com.example.rps.model.Round;
import com.example.rps.model.Scoreboard;

import java.util.List;

/** What the UI receives after every action. */
public record GameStateResponse(
        String gameId,
        String playerName,
        String opponent,
        Round lastRound,
        int wins,
        int losses,
        int draws,
        List<Round> history) {

    public static GameStateResponse from(String id, Game game) {
        Scoreboard s = game.getScoreboard();
        List<Round> history = game.getHistory();
        Round last = history.isEmpty() ? null : history.get(history.size() - 1);
        return new GameStateResponse(
                id,
                game.getHuman().getName(),
                game.getComputer().getStrategyName(),
                last,
                s.getWins(), s.getLosses(), s.getDraws(),
                history);
    }
}
