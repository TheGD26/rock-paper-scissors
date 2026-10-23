package com.example.rps.dto;

import com.example.rps.strategy.Difficulty;

public record NewGameRequest(String playerName, Difficulty difficulty) {
}
