package com.spandigital.model;

public sealed abstract class Team implements Comparable<Team> permits TeamScore, TeamRank {

    private String name;

    public Team() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Team{" +
                "name='" + name + '\'' +
                '}';
    }
}
