package com.nelson.ChessTracker.repository.rating;

import com.nelson.ChessTracker.model.dto.BiggestMover;
import com.nelson.ChessTracker.model.dto.PlayerResponse;
import com.nelson.ChessTracker.model.rating.RatingSnapshot;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SnapshotRepository extends JpaRepository<RatingSnapshot, Integer> {
    List<RatingSnapshot> findTop10ByOrderByRatingDesc();

    RatingSnapshot findTopByOrderByProgress();

    List<RatingSnapshot> findTop10ByOrderByProgressDesc();

    RatingSnapshot findTopByOrderByProgressDesc();

    List<RatingSnapshot> findByPlayerUserName(String userName);
}
