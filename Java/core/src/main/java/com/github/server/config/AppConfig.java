package com.github.server.config;

import com.github.server.controllers.AdminController;
import com.github.server.controllers.IAdminController;
import com.github.server.controllers.IUserController;
import com.github.server.controllers.UserController;
import com.github.server.entity.Player;
import com.github.server.entity.Tournament;
import com.github.server.entity.User;
import com.github.server.handlers.HttpHandler;
import com.github.server.proxy.UserControllerProxy;
import com.github.server.proxy.UserServiceProxy;
import com.github.server.repositories.IRepository;
import com.github.server.repositories.Repository;
import com.github.server.services.*;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

public class AppConfig {

    private static final IRepository<User> userRepository = new Repository<>(User.class);

    private static final IRepository<Player> playerRepository = new Repository<>(Player.class);

    private static final IRepository<Tournament> tournamentRepository = new Repository<>(Tournament.class);

    private static final IUserService userService = new UserService(getUserRepository());

    private static final IPlayerService playerService = new PlayerService(getPlayerRepository());

    private static final ITournamentService tournamentService = new TournamentService(getTournamentRepository());

    private static final IUserController userController = new UserController(getUserService());

    private static final IAdminController adminController = new AdminController(getPlayerService(), getTournamentService());

    private static final HttpHandler handler = new HttpHandler(getUserController(), getAdminController());

    public static IRepository<User> getUserRepository() {
        return userRepository;
    }

    public static IRepository<Player> getPlayerRepository() {
        return playerRepository;
    }

    public static IRepository<Tournament> getTournamentRepository() {
        return tournamentRepository;
    }


    public static IUserService getUserService() {
        InvocationHandler handler = new UserServiceProxy(userService);
        return (IUserService) Proxy.newProxyInstance(
                userService.getClass().getClassLoader(),
                new Class[]{IUserService.class},
                handler
        );
    }

    public static IPlayerService getPlayerService() {
        return playerService;
    }

    public static ITournamentService getTournamentService() {
        return tournamentService;
    }

    public static IUserController getUserController() {
        return userController;
    }

    public static IAdminController getAdminController() {
        return adminController;
    }

    public static HttpHandler getHandler() {
        return handler;
    }
}
