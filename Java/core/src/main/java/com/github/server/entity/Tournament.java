package com.github.server.entity;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "tournament", schema = "public")
public class Tournament {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "mode")
    private String mode;

    @Column(name = "place")
    private String place;

    @Column(name = "dateStart")
    private Date dateStart;

    @Column(name = "dateRegEnd")
    private Date dateRegEnd;

    @Column(name = "level")
    private String level;

    @Column(name = "maxPlayers")
    private Integer maxPlayers;

    @Column(name = "scenario")
    private String scenario;

    @Column(name = "players")
    private String players;

    @Column(name = "status")
    private String status;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public Date getDateStart() {
        return dateStart;
    }

    public void setDateStart(Date startDate) {
        this.dateStart = startDate;
    }

    public Date getDateRegEnd() {
        return dateRegEnd;
    }

    public void setDateRegEnd(Date lastDayReg) {
        this.dateRegEnd = lastDayReg;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public Integer getMaxPlayers() {
        return maxPlayers;
    }

    public void setMaxPlayers(Integer maxPlayers) {
        this.maxPlayers = maxPlayers;
    }

    public String getScenario() {
        return scenario;
    }

    public void setScenario(String scenario) {
        this.scenario = scenario;
    }

    public String getPlayers() {
        return players;
    }

    public void setPlayers(String listPlayers) {
        this.players = listPlayers;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Tournament() {
    }

    public Tournament(Integer id,
                      String nameTournament,
                      String description,
                      String mode,
                      String place,
                      Date startDate,
                      Date lastDayReg,
                      String difficult,
                      Integer maxPlayers,
                      String scenario,
                      String listPlayers,
                      String status) {
        this.id = id;
        this.name = nameTournament;
        this.description = description;
        this.mode = mode;
        this.place = place;
        this.dateStart = startDate;
        this.dateRegEnd = lastDayReg;
        this.level = difficult;
        this.maxPlayers = maxPlayers;
        this.scenario = scenario;
        this.players = listPlayers;
        this.status = status;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tournament that = (Tournament) o;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name) && Objects.equals(description, that.description) && Objects.equals(mode, that.mode) && Objects.equals(place, that.place) && Objects.equals(dateStart, that.dateStart) && Objects.equals(dateRegEnd, that.dateRegEnd) && Objects.equals(level, that.level) && Objects.equals(maxPlayers, that.maxPlayers) && Objects.equals(scenario, that.scenario) && Objects.equals(players, that.players) && Objects.equals(status, that.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, mode, place, dateStart, dateRegEnd, level, maxPlayers, scenario, players, status);
    }

    @Override
    public String
    toString() {
        return "Tournament{" +
                "id=" + id +
                ", nameTournament='" + name + '\'' +
                ", description='" + description + '\'' +
                ", mode='" + mode + '\'' +
                ", place='" + place + '\'' +
                ", startDate=" + dateStart +
                ", lastDayReg=" + dateRegEnd +
                ", difficult='" + level + '\'' +
                ", maxPlayers=" + maxPlayers +
                ", scenario='" + scenario + '\'' +
                ", listPlayers='" + players + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
