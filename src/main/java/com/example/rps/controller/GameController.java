package com.example.rps.controller;

import com.example.rps.dto.GameStateResponse;
import com.example.rps.dto.NewGameRequest;
import com.example.rps.dto.PlayRequest;
import com.example.rps.service.GameService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/** REST API used by the Vue front-end. */
@RestController
@RequestMapping("/api/games")
public class GameController {
    private final GameService service;

    public GameController(GameService service) {
        this.service = service;
    }

    @PostMapping
    public GameStateResponse newGame(@RequestBody NewGameRequest req) {
        String id = service.createGame(req.playerName(), req.difficulty());
        return GameStateResponse.from(id, service.getGame(id));
    }

    @GetMapping("/{id}")
    public GameStateResponse get(@PathVariable String id) {
        return GameStateResponse.from(id, service.getGame(id));
    }

    @PostMapping("/{id}/play")
    public GameStateResponse play(@PathVariable String id, @RequestBody PlayRequest req) {
        return GameStateResponse.from(id, service.play(id, req.move()));
    }

    @PostMapping("/{id}/reset")
    public GameStateResponse reset(@PathVariable String id) {
        return GameStateResponse.from(id, service.reset(id));
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Map<String, String>> badRequest(IllegalArgumentException e) {
        return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
    }
}
