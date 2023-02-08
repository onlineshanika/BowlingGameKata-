package com.returners.kata;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class BowlingGameTest {

    @Test
    public void get12Rolls12StrikesTest() {
        BowlingGame bowlingGame = new BowlingGame();
        int score = bowlingGame.bowlingScore("X X X X X X X X X X X X");
        assertEquals(score, 300);
    }



}
