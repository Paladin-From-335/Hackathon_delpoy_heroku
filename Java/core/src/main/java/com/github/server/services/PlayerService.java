package com.github.server.services;

import com.github.server.entity.Player;
import com.github.server.repositories.IRepository;
import com.github.server.utils.HibernateUtils;

import java.util.Collection;

public class PlayerService implements IPlayerService {

    private final IRepository<Player> repository;

    public PlayerService(IRepository<Player> repository) {
        this.repository = repository;
    }

    @Override
    public Player findById(Long id) {
        return repository.findBy("id", id, HibernateUtils.getSession());
    }

    @Override
    public Collection<Player> findAll() {
        return repository.findAll(HibernateUtils.getSession());
    }
}
