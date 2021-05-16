package com.github.server.services;

import com.github.server.entity.Player;

import java.util.Collection;

public interface IPlayerService {

    Player findById(Long id);

    Collection<Player> findAll();


}
