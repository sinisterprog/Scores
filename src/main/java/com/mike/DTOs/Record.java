package com.mike.DTOs;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.sql.Timestamp;

@Entity
public class Record {

    @Id
    @GeneratedValue
    private Long id;
    private String player;
    private int score;
    private Timestamp scoreDate;

    public Record() {
    }

    public Record(Long id, String player, int score, Timestamp scoreDate) {
        this.id = id;
        this.player = player.toLowerCase();
        this.score = score;
        this.scoreDate = scoreDate;
    }

    public Record(String player, int score, String scoreDate) {
        this.player = player;
        this.score = score;
        this.scoreDate = Timestamp.valueOf(scoreDate);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public void setPlayer(String player) {
        this.player = player;
    }

    public String getPlayer() {
        return player;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getScoreDate() {
        return scoreDate.toString();
    }

}
