package com.example.rps.strategy;

import com.example.rps.model.Move;

import java.util.EnumMap;
import java.util.Map;

/**
 * "Smart" opponent: remembers which move you play most often and
 * plays the move that beats it. Falls back to random with no data.
 */
public class FrequencyStrategy implements MoveStrategy {
    private final Map<Move, Integer> counts = new EnumMap<>(Move.class);
    private final RandomStrategy fallback;

    public FrequencyStrategy() {
        this(new RandomStrategy());
    }

    public FrequencyStrategy(RandomStrategy fallback) {
        this.fallback = fallback;
    }

    @Override
    public Move nextMove() {
        Move favourite = null;
        int best = 0;
        for (Map.Entry<Move, Integer> e : counts.entrySet()) {
            if (e.getValue() > best) {
                best = e.getValue();
                favourite = e.getKey();
            }
        }
        return favourite == null ? fallback.nextMove() : favourite.losesTo();
    }

    @Override
    public void observe(Move opponentMove) {
        counts.merge(opponentMove, 1, Integer::sum);
    }

    @Override
    public void reset() {
        counts.clear();
    }

    @Override
    public String getName() {
        return "Hard (learns your habits)";
    }
}
