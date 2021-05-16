package com.github.server.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "player", schema = "public")
public class Player {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "login")
    private String login;

    @Column(name = "email")
    private String email;

    @Column(name = "games")
    private Integer games;

    @Column(name = "wins")
    private Integer wins;

    @Column(name = "draws")
    private Integer draws;

    @Column(name = "loses")
    private Integer loses;

    @Column(name = "cupWin")
    private Integer cupWin;

    public Player() {
    }

    public Player(Long id, String login, String email, Integer games, Integer wins, Integer draws, Integer loses, Integer cupWin) {
        this.id = id;
        this.login = login;
        this.email = email;
        this.games = games;
        this.wins = wins;
        this.draws = draws;
        this.loses = loses;
        this.cupWin = cupWin;
    }

    public Long getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public String getEmail() {
        return email;
    }

    public Integer getGames() {
        return games;
    }

    public Integer getWins() {
        return wins;
    }

    public Integer getDraws() {
        return draws;
    }

    public Integer getLoses() {
        return loses;
    }

    public Integer getCupWin() {
        return cupWin;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Player player = (Player) o;
        return Objects.equals(id, player.id) && Objects.equals(login, player.login) && Objects.equals(email, player.email) && Objects.equals(games, player.games) && Objects.equals(wins, player.wins) && Objects.equals(draws, player.draws) && Objects.equals(loses, player.loses) && Objects.equals(cupWin, player.cupWin);
    }

    @Override
    public String toString() {
        return "Player{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", email='" + email + '\'' +
                ", games=" + games +
                ", wins=" + wins +
                ", draws=" + draws +
                ", loses=" + loses +
                ", cupWin=" + cupWin +
                '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, login, email, games, wins, draws, loses, cupWin);
    }
}
