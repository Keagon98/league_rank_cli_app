package com.spandigital.service;

import com.spandigital.dto.TeamNameAndRank;
import com.spandigital.dto.TeamResults;
import com.spandigital.model.TeamScore;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.*;

import static com.spandigital.util.StringUtilities.removeCharsFromString;
import static com.spandigital.util.StringUtilities.removeNumbersFromString;

public class MatchResultsService {
    private static final int WIN_POINTS = 3;
    private static final int LOSE_POINTS = 0;
    private static final int DRAW_POINTS = 1;

    /**
     * @description This method takes the match results for each team and
     * then created a rank list for all the teams based on their points
     * @param reader
     * @return TeamNameAndRank
     */
    public static TeamNameAndRank getMatchResultsData(BufferedReader reader) {
        try {
            String line;
            List<Map<String, Integer>> teamRankList = new ArrayList<>();
            HashSet<String> teamNames = new HashSet<>();

            while ((line = reader.readLine()) != null) {
                var teamScore = line.split(",");
                TeamScore teamOneResults = getResultsForBothTeams(teamScore, teamNames).teamOneResults();
                TeamScore teamTwoResults = getResultsForBothTeams(teamScore, teamNames).teamTwoResults();
                boolean teamOneWins = teamOneResults.getScore() > teamTwoResults.getScore();
                boolean teamTwoWins = teamOneResults.getScore() < teamTwoResults.getScore();

                if (teamOneWins) {
                    Map<String, Integer> winner = new HashMap<>();
                    winner.put(teamOneResults.getName(), WIN_POINTS);

                    Map<String, Integer> loser = new HashMap<>();
                    loser.put(teamTwoResults.getName(), LOSE_POINTS);

                    teamRankList.add(winner);
                    teamRankList.add(loser);

                } else if (teamTwoWins) {
                    Map<String, Integer> winner = new HashMap<>();
                    winner.put(teamTwoResults.getName(), WIN_POINTS);

                    Map<String, Integer> loser = new HashMap<>();
                    loser.put(teamOneResults.getName(), LOSE_POINTS);

                    teamRankList.add(winner);
                    teamRankList.add(loser);

                } else {
                    Map<String, Integer> drawOne = new HashMap<>();
                    drawOne.put(teamTwoResults.getName(), DRAW_POINTS);

                    Map<String, Integer> drawTwo = new HashMap<>();
                    drawTwo.put(teamOneResults.getName(), DRAW_POINTS);

                    teamRankList.add(drawOne);
                    teamRankList.add(drawTwo);
                }
            }

            return new TeamNameAndRank(teamRankList, teamNames);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * @description This method takes the match scores in text file and converts them into objects
     * @param teamScore
     * @param teamNames
     * @return TeamResults
     */
    public static TeamResults getResultsForBothTeams(String[] teamScore, HashSet<String> teamNames) {
        TeamScore teamOneResults = new TeamScore();
        TeamScore teamTwoResults = new TeamScore();
        var teamOne = teamScore[0].trim();
        var teamTwo = teamScore[1].trim();
        var teamOneName = removeNumbersFromString(teamOne);
        var teamTwoName = removeNumbersFromString(teamTwo);
        var teamOneScore = Integer.parseInt(removeCharsFromString(teamOne));
        var teamTwoScore = Integer.parseInt(removeCharsFromString(teamTwo));

        teamNames.add(teamOneName);
        teamNames.add(teamTwoName);

        teamOneResults.setName(teamOneName);
        teamOneResults.setScore(teamOneScore);

        teamTwoResults.setName(teamTwoName);
        teamTwoResults.setScore(teamTwoScore);

        return new TeamResults(teamOneResults, teamTwoResults);
    }
}
