package com.example.rps.service;

import com.example.rps.model.Game;
import com.example.rps.model.Move;
import com.example.rps.player.ComputerPlayer;
import com.example.rps.player.HumanPlayer;
import com.example.rps.strategy.Difficulty;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

/** Business layer: creates and runs games. Games are kept in memory. */
@Service
public class GameService {
    private final Map<String, Game> games = new ConcurrentHashMap<>();

    public String createGame(String playerName, Difficulty difficulty) {
        String name = (playerName == null || playerName.isBlank()) ? "Player" : playerName.trim();
        Difficulty level = difficulty == null ? Difficulty.EASY : difficulty;

        Game game = new Game(
                new HumanPlayer(name),
                new ComputerPlayer("Computer", level.createStrategy()));

        String id = UUID.randomUUID().toString();
        games.put(id, game);
        return id;
    }

    public Game getGame(String id) {
        Game game = games.get(id);
        if (game == null) throw new GameNotFoundException(id);
        return game;
    }

    public Game play(String id, Move move) {
        if (move == null) throw new IllegalArgumentException("Move is required");
        Game game = getGame(id);
        synchronized (game) {
            game.playRound(move);
        }
        return game;
    }

    public Game reset(String id) {
        Game game = getGame(id);
        synchronized (game) {
            game.reset();
        }
        return game;
    }
}
