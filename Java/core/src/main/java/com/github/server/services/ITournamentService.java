package com.github.server.services;

import com.github.server.entity.Tournament;

import java.util.Collection;

public interface ITournamentService {

    void addPlayer(String tournamentName, String login);

    Collection<Tournament> findAll();

    Tournament findById(Long id);

    Collection<Tournament> findByPlayer(String login);

    Tournament findByName(String name);

    Collection<Tournament> findByMode(String mode);

    Collection<Tournament> findByStatus(String status);

    void insert(Tournament tournament);

    void update(Tournament tournament);

}
