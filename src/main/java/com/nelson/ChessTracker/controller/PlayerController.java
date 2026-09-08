package com.nelson.ChessTracker.controller;

import com.nelson.ChessTracker.model.dto.BiggestMover;
import com.nelson.ChessTracker.model.dto.PlayerResponse;
import com.nelson.ChessTracker.model.rating.RatingSnapshot;
import com.nelson.ChessTracker.repository.rating.SnapshotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PlayerController {


    @Autowired
    private final SnapshotRepository snapshotRepository;

    public PlayerController(SnapshotRepository snapshotRepository) {
        this.snapshotRepository = snapshotRepository;
    }


    @GetMapping("/get/10biggestmovers")
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

    @GetMapping("/get/biggestmover")
    public BiggestMover getBiggestMover() {
        RatingSnapshot snapshot = snapshotRepository.findTopByOrderByProgressDesc();
        return new BiggestMover(
                snapshot.getPlayer().getUserName(),
                snapshot.getProgress(),
                snapshot.getRating(),
                snapshot.getTimestamp());

    }

    @GetMapping("/get/top10")
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

    @GetMapping("/get/top100")
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
