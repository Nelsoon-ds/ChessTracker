package com.nelson.ChessTracker.model.rating;

import com.nelson.ChessTracker.model.player.Player;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class RatingSnapshot {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int rating;
    private Integer progress;
    private LocalDateTime timestamp;

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    @ManyToOne
    @JoinColumn(name = "player_id")
    private Player player;

    public RatingSnapshot(int rating, Integer progress, LocalDateTime timestamp) {
        this.rating = rating;
        this.progress = progress;
        this.timestamp = timestamp;
    }

    public RatingSnapshot() {

    }

    public RatingSnapshot(int rating, Integer progress, LocalDateTime timestamp, Player player) {
    this.rating = rating;
    this.progress = progress;
    this.timestamp = timestamp;
    this.player = player;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public Integer getProgress() {
        return progress;
    }

    public void setProgress(Integer progress) {
        this.progress = progress;
    }


    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
}
