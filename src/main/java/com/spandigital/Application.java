package com.spandigital;

import com.spandigital.models.TeamRank;
import com.spandigital.models.TeamScore;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Application {

    public static void main(String[] args) {

        try (Scanner scanner = new Scanner(System.in)) {

            System.out.println("Enter your name: ");

            var name = scanner.nextLine();
            System.out.println("Hi " + name + " :), please provide the absolute path to the file: ");

            boolean isValidFile = false;
            do {
                try {
                    var filePath = scanner.nextLine();
                    BufferedReader reader = new BufferedReader(new FileReader(filePath));
                    isValidFile = true;
                    readFile(reader);
                } catch (FileNotFoundException e) {
                    System.out.println("File not found: Please enter a valid file path!");
                }
            } while (!isValidFile);

        } catch (IllegalStateException  e) {
            throw new RuntimeException("Scanner is closed.");
        }
    }

    public static void readFile(BufferedReader reader) {
        try {
            int WIN_POINTS = 3;
            int LOSE_POINTS = 0;
            int DRAW_POINTS = 1;

            String line;

            TeamScore teamOneResults = new TeamScore();
            TeamScore teamTwoResults = new TeamScore();

            List<Map<String, Integer>> teamRankList = new ArrayList<>();
            HashSet<String> teamNames = new HashSet<>();

            List<TeamRank> teamRanks = new ArrayList<>();

            while ((line = reader.readLine()) != null) {
                String[] teamScore = line.split(",");

                String teamOne;
                String teamTwo;

                teamOne = teamScore[0].trim();
                teamTwo = teamScore[1].trim();

                var teamOneName = teamOne.replaceAll("[^A-Za-z ]+", "").trim();
                var teamTwoName = teamTwo.replaceAll("[^A-Za-z ]+", "").trim();

                teamNames.add(teamOneName);
                teamNames.add(teamTwoName);

                teamOneResults.setName(teamOne.replaceAll("[^A-Za-z ]+", "").trim());
                teamOneResults.setScore(Integer.parseInt(teamOne.replaceAll("[^0-9]", "")));

                teamTwoResults.setName(teamTwo.replaceAll("[^A-Za-z ]+", "").trim());
                teamTwoResults.setScore(Integer.parseInt(teamTwo.replaceAll("[^0-9]", "")));

                if (teamOneResults.getScore() > teamTwoResults.getScore()) {
                    Map<String, Integer> winner = new HashMap<>();
                    winner.put(teamOneResults.getName(), WIN_POINTS);

                    Map<String, Integer> loser = new HashMap<>();
                    loser.put(teamTwoResults.getName(), LOSE_POINTS);

                    teamRankList.add(winner);
                    teamRankList.add(loser);

                } else if (teamOneResults.getScore() < teamTwoResults.getScore()) {
                    Map<String, Integer> loser = new HashMap<>();
                    loser.put(teamTwoResults.getName(), WIN_POINTS);

                    Map<String, Integer> winner = new HashMap<>();
                    winner.put(teamOneResults.getName(), LOSE_POINTS);

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

            for (String teamName : teamNames) {
                TeamRank teamRank = new TeamRank();
                teamRank.setName(teamName);
                var points = 0;

                for (Map<String, Integer> rank : teamRankList) {
                    if (rank.containsKey(teamName)) {
                        teamRank.setPoints(points += rank.get(teamName));
                    }
                }

                teamRanks.add(teamRank);
            }

            teamRanks = teamRanks.stream()
                    .sorted(Comparator.comparingInt(TeamRank::getPoints).reversed()
                            .thenComparing(TeamRank::getName))
                    .toList();

            for (int i = 0; i < teamRanks.size(); i++) {
                System.out.printf("%s. %s, %d %s%n", teamRanks.indexOf(teamRanks.get(i)) + 1, teamRanks.get(i).getName(), teamRanks.get(i).getPoints(), teamRanks.get(i).getPoints() == 1 ? "pt" : "pts");
            }

            reader.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}