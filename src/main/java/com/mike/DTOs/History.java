package com.mike.DTOs;

import java.util.ArrayList;
import java.util.List;
public class History {
    private final List<Record> allScores;
    private final int highScore;
    private final int lowScore;
    private final String lowScoreDate;
    private final String highScoreDate;
    private final double average;
    public History() {
        allScores = new ArrayList<>();
        highScore = 0;
        lowScore = 0;
        average = 0.0;
        highScoreDate = "n/a";
        lowScoreDate = "n/a";

    }
    public History(List<Record> allScores, int highScore, int lowScore, String highScoreDate, String lowScoreDate, double average) {

        this.allScores = allScores;
        this.highScore = highScore;
        this.lowScore = lowScore;
        this.average = average;
        this.highScoreDate = highScoreDate;
        this.lowScoreDate = lowScoreDate;
    }

    public String getLowScoreDate() {
        return lowScoreDate;
    }

    public String getHighScoreDate() {
        return highScoreDate;
    }

    public List<Record> getAllScores() {
        return allScores;
    }

    public int getHighScore() {
        return highScore;
    }

    public int getLowScore() {
        return lowScore;
    }

    public double getAverage() {
        return average;
    }
}
