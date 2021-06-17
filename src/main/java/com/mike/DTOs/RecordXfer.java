package com.mike.DTOs;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

@JsonAutoDetect
public class RecordXfer {
    private String player;
    private int score;
    private String scoreDate;

    public RecordXfer() {
    }

    public void setPlayer(String player) {
        this.player = player;
    }

    public void setScoreDate(String scoreDate) {
        this.scoreDate = scoreDate;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getName() {
        return player;
    }

    public String getDateTime() {
        return String.valueOf(scoreDate);
    }
}
