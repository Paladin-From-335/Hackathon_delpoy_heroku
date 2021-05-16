package com.github.server.dto;

import com.github.server.entity.User;
import com.github.server.payload.Role;

import java.util.Objects;

public class UserRegDto {

    private String login;

    private String email;

    private String password;

    public UserRegDto() {
    }

    public UserRegDto(String login, String email, String password) {
        this.login = login;
        this.email = email;
        this.password = password;
    }

    public UserRegDto(User user) {
        this.login = user.getLogin();
        this.email = user.getEmail();
        this.password = user.getPassword();

    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserRegDto that = (UserRegDto) o;
        return Objects.equals(getLogin(), that.getLogin()) && Objects.equals(getEmail(), that.getEmail()) && Objects.equals(getPassword(), that.getPassword());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getLogin(), getEmail(), getPassword());
    }

    @Override
    public String toString() {
        return "UserRegDto{" +
                "login='" + login + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    public User toUser() {
        return new User(
                null,
                this.login,
                this.email,
                this.password,
                Role.USER
        );
    }

}
