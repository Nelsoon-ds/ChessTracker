package com.nelson.ChessTracker.model.dto;

import java.time.LocalDateTime;

public record BiggestMover (
        String username,
        Integer position,
        int rating,
        LocalDateTime timestamp
) {
}
