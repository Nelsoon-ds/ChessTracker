package com.nelson.ChessTracker.model.player;


import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class Player {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long playerId;
    private String userName;
    private LocalDateTime timestamp;

    public Player(String username, LocalDateTime timestamp) {
        this.userName = username;
        this.timestamp = timestamp;
    }

    public Player() {

    }

    public Long getId() {
        return playerId;
    }

    public void setId(Long id) {
        this.playerId = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }



    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
}
