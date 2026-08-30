package com.nelson.ChessTracker.model.dto;

import java.time.LocalDateTime;

public record PlayerResponse (
    String username,
    int rating,
    LocalDateTime timestamp
) {}


