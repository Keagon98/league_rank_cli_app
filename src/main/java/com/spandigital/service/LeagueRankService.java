package com.spandigital.service;

import com.spandigital.dto.TeamNameAndRank;
import com.spandigital.model.TeamRank;

import java.util.*;

public class LeagueRankService {

    /**
     * @description This method iterates through each team name stored in a hashset and
     * then constructs a list of team rank objects
     * @param teamNameAndRank
     * @return List<TeamRank>
     */
    public static List<TeamRank> getFinalLeagueRank(TeamNameAndRank teamNameAndRank) {
        List<TeamRank> teamRanks = new ArrayList<>();

        for (String teamName : teamNameAndRank.teamNames()) {
            TeamRank teamRank = new TeamRank();
            teamRank.setName(teamName);
            var points = 0;

            for (Map<String, Integer> rank : teamNameAndRank.teamRankList()) {
                if (rank.containsKey(teamName)) {
                    teamRank.setPoints(points += rank.get(teamName));
                }
            }

            teamRanks.add(teamRank);
        }

        return teamRanks.stream()
                .sorted(Comparator.comparingInt(TeamRank::getPoints).reversed()
                        .thenComparing(TeamRank::getName))
                .toList();
    }
}
