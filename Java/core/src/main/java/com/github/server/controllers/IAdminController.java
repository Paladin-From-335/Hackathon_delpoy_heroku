package com.github.server.controllers;

import com.github.server.entity.Player;
import com.github.server.entity.Tournament;

import java.util.Collection;

public interface IAdminController {

    void invitePlayer(String login);

    void createTournament(Tournament tournament);

    void updateTournament(Tournament tournament);

    Collection<Tournament> findAllTournament();

    Collection<Tournament> findByMode(String mode);

    Collection<Tournament> findByStatus(String status);

    Collection<Player> findAllPlayer();

}
