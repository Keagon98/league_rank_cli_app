package com.spandigital.service;

import com.spandigital.dto.TeamNameAndRank;
import com.spandigital.model.TeamRank;
import jdk.jfr.Description;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class LeagueRankServiceTest {
    List<TeamRank> result = new ArrayList<>();
    @BeforeEach
    void setup() {
        List<Map<String, Integer>> teamRankList = new ArrayList<>();
        Map<String, Integer> team = new HashMap<>();
        HashSet<String> teamNames = new HashSet<>();

        team.put("Snakes", 1);
        team.put("Lions", 5);
        team.put("Tarantulas", 6);
        team.put("FC Awesome", 1);
        team.put("Grouches", 0);

        teamRankList.add(team);

        teamNames.add("Tarantulas");
        teamNames.add("Lions");
        teamNames.add("Grouches");
        teamNames.add("Snakes");
        teamNames.add("FC Awesome");

        TeamNameAndRank teamNameAndRank = new TeamNameAndRank(teamRankList, teamNames);

        result = LeagueRankService.getFinalLeagueRank(teamNameAndRank);
    }

    @DisplayName("Test rank sorting")
    @Description("Test that rank is first sorted by points then by name")
    @Test
    void testThatRankIsCorrectlySorted() {
        assertEquals(6, result.get(0).getPoints());
        assertEquals(5, result.get(1).getPoints());
        assertEquals(1, result.get(2).getPoints());
        assertEquals(1, result.get(3).getPoints());
        assertEquals(0, result.get(4).getPoints());

        assertEquals("Tarantulas", result.get(0).getName());
        assertEquals("Lions", result.get(1).getName());
        assertEquals("FC Awesome", result.get(2).getName());
        assertEquals("Snakes", result.get(3).getName());
        assertEquals("Grouches", result.get(4).getName());
    }
}