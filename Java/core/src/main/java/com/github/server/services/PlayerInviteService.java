package com.github.server.services;

import com.github.server.entity.PlayerInvite;
import com.github.server.repositories.IRepository;
import com.github.server.utils.HibernateUtils;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

public class PlayerInviteService implements IPlayerInviteService {

    private final IRepository<PlayerInvite> repository;

    public PlayerInviteService(IRepository<PlayerInvite> repository) {
        this.repository = repository;
    }

    @Override
    public PlayerInvite findByTournament(String tournamentName) {
        return this.repository.findBy("nameTournament", tournamentName, HibernateUtils.getSession());
    }

    @Override
    public Collection<PlayerInvite> findByPlayer(String user) {
        return this.repository.findAllBy("user", user, HibernateUtils.getSession());
    }
}
