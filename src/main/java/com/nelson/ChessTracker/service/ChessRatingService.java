package com.nelson.ChessTracker.service;

import com.nelson.ChessTracker.model.dto.LichessUser;
import com.nelson.ChessTracker.model.dto.LichessUserList;
import com.nelson.ChessTracker.model.player.Player;
import com.nelson.ChessTracker.model.rating.RatingSnapshot;
import com.nelson.ChessTracker.repository.player.PlayerRepository;
import com.nelson.ChessTracker.repository.rating.SnapshotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

@Service
public class ChessRatingService {
    @Autowired
    PlayerRepository playerRepository;
    @Autowired
    SnapshotRepository snapshotRepository;

    public ChessRatingService(PlayerRepository playerRepository, SnapshotRepository snapshotRepository, RestClient client) {
        this.client = client;
        this.playerRepository = playerRepository;
        this.snapshotRepository = snapshotRepository;
    }

    private final RestClient client;

    public void findPlayer(String username) {
        LichessUser result = client
                .get()
                .uri("https://lichess.org/api/user/{username}", username)
                .retrieve()
                .body(LichessUser.class);
        System.out.println("text");
        System.out.println(result);
    }

    //@Scheduled(fixedDelay = 3_600_000)
    public void getTop100() {
        LichessUserList result = client.get()
                .uri("https://lichess.org/api/player/top/100/blitz")
                .retrieve()
                .body(LichessUserList.class);
        List<LichessUser> players = result.users();
        for (LichessUser dto : players) {
            Player player;
            LocalDateTime now = LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES);
            Optional<Player> existing = playerRepository.findByUserName(dto.username());

            if (existing.isPresent()){
                player = existing.get();
            }
            // If the player is not in the repo means we need to create him
            else {
                player = new Player(dto.username(), now);
                playerRepository.save(player);
            }
            // Declare variables
            int rating = dto.perfs().blitz().rating();
            Integer progress = dto.perfs().blitz().progress();
            // Save the result
            snapshotRepository.save(new RatingSnapshot(rating, progress, now, player));
        }
    }
}
