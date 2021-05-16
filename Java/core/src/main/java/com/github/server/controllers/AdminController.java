package com.github.server.controllers;

import com.github.server.entity.Player;
import com.github.server.entity.Tournament;
import com.github.server.services.IPlayerService;
import com.github.server.services.ITournamentService;

import java.util.Collection;

public class AdminController implements IAdminController {

    private final IPlayerService playerService;

    private final ITournamentService tournamentService;

    public AdminController(IPlayerService playerService, ITournamentService tournamentService) {
        this.playerService = playerService;
        this.tournamentService = tournamentService;
    }

    @Override
    public void invitePlayer(String login) {

    }

    @Override
    public void createTournament(Tournament tournament) {
        tournamentService.insert(tournament);
    }

    @Override
    public void updateTournament(Tournament tournament) {
        tournamentService.update(tournament);
    }

    @Override
    public Collection<Tournament> findAllTournament() {
        return tournamentService.findAll();
    }

    @Override
    public Collection<Tournament> findByMode(String mode) {
        return tournamentService.findByMode(mode);
    }

    @Override
    public Collection<Tournament> findByStatus(String status) {
        return tournamentService.findByStatus(status);
    }

    @Override
    public Collection<Player> findAllPlayer() {
        return playerService.findAll();
    }
}
