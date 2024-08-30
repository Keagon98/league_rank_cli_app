package com.spandigital.models;

public final class TeamRank extends Team {

    private int points;

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    @Override
    public int compareTo(Team o) {
        return 0;
    }
}
