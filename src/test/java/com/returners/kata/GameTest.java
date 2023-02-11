package com.returners.kata;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GameTest {

    @Test
    public void get12Rolls12StrikesTest() {
       Game bowlingGame = new Game();
        int score = bowlingGame.bowlingScore("X X X X X X X X X X X X");
        assertEquals(score, 300);
    }


    @Test
    public void testing() {
       Game bowlingGame = new Game();
        int score = bowlingGame.bowlingScore("-- -- -- -- -- -- -- -- 2/ X X X");
        assertEquals(score, 50);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/testData.csv")
    public void testWithCsvFileSourceFromClasspath(String frame, int score)  {

        Game bowlingGame = new Game();
        assertEquals(bowlingGame.bowlingScore (frame), score);

    }
}
