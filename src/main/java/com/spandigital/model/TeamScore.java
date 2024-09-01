package com.spandigital.model;

public final class TeamScore extends Team {

    private int score;

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    @Override
    public int compareTo(Team o) {
        return 0;
    }
}
