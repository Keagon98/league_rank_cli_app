package com.spandigital;

import com.spandigital.dto.TeamNameAndRank;
import com.spandigital.model.TeamRank;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.*;

import static com.spandigital.service.FileReaderService.readFile;
import static com.spandigital.service.LeagueRankService.getFinalLeagueRank;
import static com.spandigital.service.MatchResultsService.getMatchResultsData;

public class Application {

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("Enter your name: ");

            var name = scanner.nextLine();
            System.out.println("Hi " + name + " :), please provide the absolute path to the file: ");

            BufferedReader reader = readFile(scanner);

            TeamNameAndRank teamNameAndRank = getMatchResultsData(reader);
            List<TeamRank> teamRanks = getFinalLeagueRank(teamNameAndRank);

            for (int i = 0; i < teamRanks.size(); i++) {
                System.out.printf("%s. %s, %d %s%n", teamRanks.indexOf(teamRanks.get(i)) + 1, teamRanks.get(i).getName(), teamRanks.get(i).getPoints(), teamRanks.get(i).getPoints() == 1 ? "pt" : "pts");
            }

            reader.close();
        } catch (IllegalStateException | IOException e) {
            throw new RuntimeException("Scanner is closed.");
        }
    }
}