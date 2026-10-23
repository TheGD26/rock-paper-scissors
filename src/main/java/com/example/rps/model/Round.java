package com.example.rps.model;

/** Immutable record of one played round. */
public record Round(int number, Move playerMove, Move computerMove, Outcome outcome) {
}
