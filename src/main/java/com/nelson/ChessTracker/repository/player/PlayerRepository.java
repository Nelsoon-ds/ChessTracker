package com.nelson.ChessTracker.repository.player;

import com.nelson.ChessTracker.model.player.Player;
import com.nelson.ChessTracker.model.rating.RatingSnapshot;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PlayerRepository extends JpaRepository<Player, Integer> {

    Optional<RatingSnapshot> findTopByPlayerIdOrderByTimestampDesc(Long playerId);

    Player findByUserName(String userName);
}
