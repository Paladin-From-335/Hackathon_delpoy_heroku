package com.github.server.controllers;

import com.github.server.dto.UserAuthDto;
import com.github.server.dto.UserRegDto;
import com.github.server.entity.User;
import com.github.server.exceptions.*;
import com.github.server.payload.Envelope;
import com.github.server.payload.PrivateToken;
import com.github.server.services.IUserService;
import com.github.server.utils.JsonHelper;
import com.github.server.utils.PattenMatcher;
import com.github.server.utils.TokenProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UserController implements IUserController {

    private static final Logger log = LoggerFactory.getLogger(UserController.class);

    private final IUserService userService;

    public UserController(IUserService userService) {
        this.userService = userService;
    }

    @Override
    public String authorize(UserAuthDto userAuthDto) {
        User user;
        if (PattenMatcher.isValidEmail(userAuthDto.getLogin())) {
            user = this.userService.findByEmail(userAuthDto.getLogin());
        } else {
            user = this.userService.findByLogin(userAuthDto.getLogin());
        }
        if(user == null) {
            throw new ForbiddenException();
        }
        PrivateToken token = new PrivateToken(user);
        String encodedToken = TokenProvider.encode(token);
        Envelope env = new Envelope(user.getRole(), JsonHelper.toJson(token).orElseThrow(InternalServerError::new));
        return JsonHelper.toJson(env).orElseThrow(InternalServerError::new);
    }

    @Override
    public void register(UserRegDto userRegDto) {
        System.out.println("before");
        userService.insert(userRegDto.toUser());
        System.out.println("after");
    }

}
