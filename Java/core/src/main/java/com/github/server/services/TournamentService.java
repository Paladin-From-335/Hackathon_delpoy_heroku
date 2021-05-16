package com.github.server.services;

import com.github.server.entity.Tournament;
import com.github.server.exceptions.PlayerAlreadyInTournament;
import com.github.server.repositories.IRepository;
import com.github.server.utils.HibernateUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class TournamentService implements ITournamentService {

    private final IRepository<Tournament> repository;

    public TournamentService(IRepository<Tournament> repository) {
        this.repository = repository;
    }

    @Override
    public void addPlayer(String tournamentName, String login) {
        Tournament tournament = this.repository.findBy("name", tournamentName, HibernateUtils.getSession());
        String strPlayers = tournament.getPlayers();
        strPlayers = strPlayers.substring(1, strPlayers.length() - 2);
        String[] playersArray = strPlayers.split(",");
        List<String> players = new ArrayList<>(Arrays.asList(playersArray));
        for (String player : players) {
            player = player.trim();
            if (player.equals(login)) {
                throw new PlayerAlreadyInTournament();
            }
        }
        strPlayers = "[" + strPlayers + ", " + login + "]";
        Tournament updatedTournament = new Tournament(
                tournament.getId(),
                tournament.getName(),
                tournament.getDescription(),
                tournament.getMode(),
                tournament.getPlace(),
                tournament.getDateStart(),
                tournament.getDateRegEnd(),
                tournament.getLevel(),
                tournament.getMaxPlayers(),
                tournament.getScenario(),
                strPlayers,
                tournament.getStatus()
        );
        this.repository.update(updatedTournament, HibernateUtils.getSession());
    }

    @Override
    public Collection<Tournament> findAll() {
        return this.repository.findAll(HibernateUtils.getSession());
    }

    @Override
    public Tournament findById(Long id) {
        return this.repository.findBy("id", id, HibernateUtils.getSession());
    }

    @Override
    public Collection<Tournament> findByPlayer(String login) {
        Collection<Tournament> tournaments = this.repository.findAll(HibernateUtils.getSession());
        Collection<Tournament> result = new ArrayList<>();
        for (Tournament tournament : tournaments) {
            String players = tournament.getPlayers();
            players = players.substring(1, players.length() - 2);
            String[] playerArray = players.split(",");
            for (String player : playerArray) {
                player = player.trim();
                if (player.equals(login)) {
                    result.add(tournament);
                }
            }
        }
        return result;
    }

    @Override
    public Tournament findByName(String name) {
        return this.repository.findBy("name", name, HibernateUtils.getSession());
    }

    @Override
    public Collection<Tournament> findByMode(String mode) {
        return this.repository.findAllBy("mode", mode, HibernateUtils.getSession());
    }

    @Override
    public Collection<Tournament> findByStatus(String status) {
        return this.repository.findAllBy("status", status, HibernateUtils.getSession());
    }

    @Override
    public void insert(Tournament tournament) {
        this.repository.save(tournament, HibernateUtils.getSession());
    }

    @Override
    public void update(Tournament tournament) {
        this.repository.update(tournament, HibernateUtils.getSession());
    }
}
