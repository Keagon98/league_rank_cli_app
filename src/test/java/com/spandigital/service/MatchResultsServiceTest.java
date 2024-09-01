package com.spandigital.service;

import com.spandigital.model.TeamScore;
import jdk.jfr.Description;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.*;

class MatchResultsServiceTest {

    @DisplayName("Test team one score")
    @Description("Test that team one score is correct")
    @Test
    void testTeamOneScore() {
        String[] scores = {
                "Tarantulas 3", "Snakes 1"
        };
        HashSet<String> teamNames = new HashSet<>();
        teamNames.add("Tarantulas");
        teamNames.add("Snakes");

        TeamScore teamOne = MatchResultsService.getResultsForBothTeams(scores, teamNames).teamOneResults();
        assertEquals(3, teamOne.getScore());
    }

    @DisplayName("Test team two score")
    @Description("Test that team two score is correct")
    @Test
    void testTeamTwoScore() {
        String[] scores = {
                "Tarantulas 1", "FC Awesome 0"
        };
        HashSet<String> teamNames = new HashSet<>();
        teamNames.add("Tarantulas");
        teamNames.add("FC Awesome");

        TeamScore teamTwo = MatchResultsService.getResultsForBothTeams(scores, teamNames).teamTwoResults();
        assertEquals(0, teamTwo.getScore());
    }

    @DisplayName("Test team one name")
    @Description("Test that team one name is correct")
    @Test
    void testTeamOneName() {
        String[] scores = {
                "Tarantulas 1", "FC Awesome 0"
        };
        HashSet<String> teamNames = new HashSet<>();
        teamNames.add("Tarantulas");
        teamNames.add("FC Awesome");

        TeamScore teamOne = MatchResultsService.getResultsForBothTeams(scores, teamNames).teamOneResults();
        assertEquals("Tarantulas", teamOne.getName());
    }

    @DisplayName("Test team two name")
    @Description("Test that team two name is correct")
    @Test
    void testTeamTwoName() {
        String[] scores = {
                "Lions 4", "Grouches 0"
        };
        HashSet<String> teamNames = new HashSet<>();
        teamNames.add("Lions");
        teamNames.add("Grouches");

        TeamScore teamTwo = MatchResultsService.getResultsForBothTeams(scores, teamNames).teamTwoResults();
        assertEquals("Grouches", teamTwo.getName());
    }

}