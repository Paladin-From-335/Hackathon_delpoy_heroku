package com.github.server.utils;

import com.github.server.entity.Tournament;
import org.junit.Test;

import java.sql.Date;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.Assert.assertEquals;

public class JsonHelperTest {

    private Tournament tournament = new Tournament(0,
            "testTournament",
            "1",
            "hardmode",
            "Kyiv",
            new Date(212121L),
            new Date(912121L),
            "hard",
            32,
            "norm",
            "1,2",
            "ended");

    private String tournamentStr = "{" +
            "\"id\":0," +
            "\"name\":\"testTournament\"," +
            "\"description\":\"1\"," +
            "\"mode\":\"hardmode\"," +
            "\"place\":\"Kyiv\"," +
            "\"dateStart\":212121," +
            "\"dateRegEnd\":912121," +
            "\"level\":\"hard\"," +
            "\"maxPlayers\":32," +
            "\"scenario\":\"norm\"," +
            "\"players\":\"1,2\"," +
            "\"status\":\"ended\"" +
            "}";

    private Tournament tournamentNull = new Tournament(null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null);

    private String tournamentStrNull = "{" +
            "\"id\":null," +
            "\"name\":null," +
            "\"description\":null," +
            "\"mode\":null," +
            "\"place\":null," +
            "\"dateStart\":null," +
            "\"dateRegEnd\":null," +
            "\"level\":null," +
            "\"maxPlayers\":null," +
            "\"scenario\":null," +
            "\"players\":null," +
            "\"status\":null" +
            "}";

    @Test
    public void toJson() {
        String str = String.valueOf(JsonHelper.toJson(this.tournament).orElseThrow());
        assertEquals(this.tournamentStr, str);
    }

    @Test
    public void toJsonAllNull() {
        String str = String.valueOf(JsonHelper
                .toJson(tournamentNull)
                .orElseThrow());
        assertEquals(tournamentStrNull, str);
    }


    @Test
    public void toJson2() {
        String str = String.valueOf(JsonHelper.toJson(null).orElse("s"));
        assertEquals("null", str);
    }

    @Test
    public void fromJson() {
        Optional<Tournament> tournamentTest = JsonHelper.fromJson(this.tournamentStr, Tournament.class);
        Tournament test = tournamentTest.orElseThrow();
        assertEquals(this.tournament, test);
    }

    @Test
    public void fromJson2() {
        Tournament actual = JsonHelper.fromJson(tournamentStrNull, Tournament.class).orElseThrow();
        assertEquals(tournamentNull, actual);
    }

    @Test(expected = NoSuchElementException.class)
    public void fromJsonException() {
        JsonHelper.fromJson("{\"id\":\"321}", Tournament.class).orElseThrow();
    }
}