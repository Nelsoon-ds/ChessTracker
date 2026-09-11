package com.nelson.ChessTracker.controller;

import com.nelson.ChessTracker.model.dto.BiggestMover;
import com.nelson.ChessTracker.model.dto.PlayerResponse;
import com.nelson.ChessTracker.model.rating.RatingSnapshot;
import com.nelson.ChessTracker.repository.player.PlayerRepository;
import com.nelson.ChessTracker.repository.rating.SnapshotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PlayerController {


    @Autowired
    private final SnapshotRepository snapshotRepository;
    @Autowired
    private PlayerRepository playerRepository;

    public PlayerController(SnapshotRepository snapshotRepository) {
        this.snapshotRepository = snapshotRepository;
    }


    @GetMapping("/api/players/{userName}")
    public List<PlayerResponse> findPlayer(@PathVariable String userName) {
        System.out.println("Endpoint hit with following input: " + userName);
        return snapshotRepository.findByPlayerUserName(userName).stream()
                .map(snapshot -> new PlayerResponse(
                        userName,
                        snapshot.getRating(),
                        snapshot.getTimestamp()
                ))
                .toList();

    }

    @GetMapping("/api/10biggestmovers")
    public List<BiggestMover> getBiggestMovers() {
        return snapshotRepository.findTop10ByOrderByProgressDesc()
                .stream()
                .map(snapshot -> new BiggestMover(
                        snapshot.getPlayer().getUserName(),
                        snapshot.getProgress(),
                        snapshot.getRating(),
                        snapshot.getTimestamp()
                ))
                .toList();

    }

    @GetMapping("/api/biggestmover")
    public BiggestMover getBiggestMover() {
        RatingSnapshot snapshot = snapshotRepository.findTopByOrderByProgressDesc();
        return new BiggestMover(
                snapshot.getPlayer().getUserName(),
                snapshot.getProgress(),
                snapshot.getRating(),
                snapshot.getTimestamp());

    }

    @GetMapping("/api/top10")
    public List<PlayerResponse> showTop10() {
        return snapshotRepository.findTop10ByOrderByRatingDesc()
                .stream()
                .map(snapshot -> new PlayerResponse(
                        snapshot.getPlayer().getUserName(),
                        snapshot.getRating(),
                        snapshot.getTimestamp()
                ))
                .toList();
    }

    @GetMapping("/api/top100")
    public List<PlayerResponse> showTop100() {
        return snapshotRepository.findAll()
                .stream()
                .map(snapshot -> new PlayerResponse(
                        snapshot.getPlayer().getUserName(),
                        snapshot.getRating(),
                        snapshot.getTimestamp()
                )).toList();
    }
}
