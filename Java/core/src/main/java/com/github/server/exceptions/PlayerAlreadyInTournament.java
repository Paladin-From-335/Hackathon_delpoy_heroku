package com.github.server.exceptions;

public class PlayerAlreadyInTournament extends RuntimeException {

    public PlayerAlreadyInTournament() {
    }

    public PlayerAlreadyInTournament(String message) {
        super(message);
    }
}
