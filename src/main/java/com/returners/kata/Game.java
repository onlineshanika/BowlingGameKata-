package com.returners.kata;

import com.returners.kata.bowling.BowlingAttempt;

import java.util.ArrayList;
import java.util.List;

public class Game {


//    Each game, or “line” of bowling, includes ten turns, or “frames” for the bowler.
//    In each frame, the bowler gets up to two tries to knock down all the pins.
//    If in two tries, he fails to knock them all down, his score for that frame is the total number of pins knocked down in his two tries.
//    If in two tries he knocks them all down, this is called a “spare” and his score for the frame is ten plus the number of pins knocked down on his next throw (in his next turn).
//    If on his first try in the frame he knocks down all the pins, this is called a “strike”. His turn is over, and his score for the frame is ten plus the simple total of the pins knocked down in his next two rolls.
//    If he gets a spare or strike in the last (tenth) frame, the bowler gets to throw one or two more bonus balls, respectively. These bonus throws are taken as part of the same turn. If the bonus throws knock down all the pins, the process does not repeat: the bonus throws are only used to calculate the score of the final frame.
//    The game score is the total of all frame scores.

//    X X X X X X X X X X X X (12 rolls: 12 strikes) = 10 frames * 30 points = 300
//    9- 9- 9- 9- 9- 9- 9- 9- 9- 9- (20 rolls: 10 pairs of 9 and miss) = 10 frames * 9 points = 90
//    5/ 5/ 5/ 5/ 5/ 5/ 5/ 5/ 5/ 5/5 (21 rolls: 10 pairs of 5 and spare, with a final 5) = 10 frames * 15 points = 150

    private final List<BowlingAttempt> bowlingAttempts = new ArrayList<>();
    private final int TEN = 10 ;
    private final char DASH = '-' ;
    private final char SLASH = '/' ;
    private final char X = 'X' ;


    public int bowlingScore(String frames) {

        String[] arrFrames = frames.split(" ");
        StringBuilder finalFrame = new StringBuilder();
        for (int i = 0; i < arrFrames.length; i++) {
            if (i < 9) {
                bowlingAttempts.add(processAttempts(arrFrames[i]));
            } else {
                finalFrame.append(arrFrames[i]);
            }
        }
        bowlingAttempts.add(processAttempts(finalFrame.toString()));
        for (int i = 0; i < bowlingAttempts.size(); i++) {
            setTotals(i, bowlingAttempts.get(i));
        }
        return bowlingAttempts.get(bowlingAttempts.size() - 1).getScore();
    }

    private BowlingAttempt processAttempts(String frame) {
        BowlingAttempt bowlingAttempt = new BowlingAttempt();
        int firstAttempt = frame.length() > 0 ? returnNumericValue(frame.charAt(0)) : 0;
        int thirdAttempt = frame.length() > 2 ? returnNumericValue(frame.charAt(2)) : 0;

        int secondAttempt = frame.length() > 1 ? returnNumericValue(frame.charAt(1)) : 0;
        secondAttempt = (secondAttempt == -1) ? (TEN - firstAttempt) : secondAttempt;

        bowlingAttempt.setFirstAttempt(firstAttempt);
        bowlingAttempt.setSecondAttempt(secondAttempt);
        bowlingAttempt.setThirdAttempt(thirdAttempt);
        bowlingAttempt.setScore(firstAttempt + secondAttempt + thirdAttempt);

        return bowlingAttempt;
    }

    private int returnNumericValue(char character) {
        switch (character) {
            case DASH:
                return 0;
            case X:
                return TEN;
            case SLASH:
                return -1;
            default:
                return Character.getNumericValue(character);
        }
    }

    private void setTotals(int currentIndex, BowlingAttempt currentAttempt) {

        // Normal case to calculate scores. Add the previous frame score to the current frame score.
        if (currentIndex != 0) {
            BowlingAttempt previousAttempt = bowlingAttempts.get(currentIndex - 1);
            currentAttempt.setScore(currentAttempt.getScore() + previousAttempt.getScore());
        }

        if (currentAttempt.getFirstAttempt() == TEN) {
            // If current frame is a strike
            int nextScore = 0;

            if ((currentIndex + 1) < bowlingAttempts.size()) {
                BowlingAttempt nextAttempt = bowlingAttempts.get(currentIndex + 1);

                if (nextAttempt.getFirstAttempt() == TEN && (currentIndex + 2) < bowlingAttempts.size()) {
                    BowlingAttempt oneAfterNextAttempt = bowlingAttempts.get(currentIndex + 2);
                    nextScore = nextAttempt.getFirstAttempt() + oneAfterNextAttempt.getFirstAttempt();
                } else {
                    nextScore = nextAttempt.getFirstAttempt() + nextAttempt.getSecondAttempt();
                }
            }
            currentAttempt.setScore(currentAttempt.getScore() + nextScore);
        } else if ((currentAttempt.getFirstAttempt() + currentAttempt.getSecondAttempt()) == TEN) {
            // If current frame is a spare
            if ((currentIndex + 1) < bowlingAttempts.size()) {
                BowlingAttempt nextAttempt = bowlingAttempts.get(currentIndex + 1);
                currentAttempt.setScore(currentAttempt.getScore() + nextAttempt.getFirstAttempt());
            }
        }

    }
}