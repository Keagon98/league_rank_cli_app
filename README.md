## League Ranking CLI Application

### Problem Statement

> We want you to create a production ready, maintainable, testable command-line application that
will calculate the ranking table for a league.

### Application Requirements

- The input and output will be text. Either using stdin/stdout or taking filenames on the command
  line
- Automated unit tests
- Application should be able to run on any OS with no issue

### League Ranking Rules

> In this league, a draw (tie) is worth 1 point and a win is worth 3 points. A loss is worth 0 points.
If two or more teams have the same number of points, they should have the same rank and be
printed in alphabetical order

### How to set up / run Application
> This application only takes in the file path as input. Create a .txt file on your machine and add the following:

```
Lions 3, Snakes 3
Tarantulas 1, FC Awesome 0
Lions 1, FC Awesome 1
Tarantulas 3, Snakes 1
Lions 4, Grouches 0
```

1. Clone repo by running: ```git clone https://github.com/Keagon98/league_rank_cli_app.git```
    #### OR
   By downloading the zip folder
   > Click on the `Code` dropdown to find the above
2. To run the Application on the CLI, do the following:
   > First navigate to the project folder on your machine using the terminal, then run...
   ```
   mvn clean package
   java -jar target/league_ranking_cli_app-1.0-SNAPSHOT.jar
   ```
3. To run the unit tests in the terminal make sure you're in the project directory, then run the following:
   ```
   mvn test
   ```
### Sample Input
```
Lions 3, Snakes 3
Tarantulas 1, FC Awesome 0
Lions 1, FC Awesome 1
Tarantulas 3, Snakes 1
Lions 4, Grouches 0
```

### Expected output:
```
1. Tarantulas, 6 pts
2. Lions, 5 pts
3. FC Awesome, 1 pt
3. Snakes, 1 pt
5. Grouches, 0 pts
```