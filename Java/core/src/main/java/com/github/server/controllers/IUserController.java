package com.github.server.controllers;

import com.github.server.dto.UserAuthDto;
import com.github.server.dto.UserRegDto;

public interface IUserController {

    String authorize(UserAuthDto userAuthDto);

    void register(UserRegDto userRegDto);


}
