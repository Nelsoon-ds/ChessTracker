package com.nelson.ChessTracker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class ChessTrackerApplication {

    public static void main(String[] args) {
        SpringApplication.run(com.nelson.ChessTracker.ChessTrackerApplication.class, args);
    }

}
