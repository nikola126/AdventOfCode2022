package org.aoc2022;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Objects;
import java.util.Scanner;

public class Day2 {
    public final String FILE_DIR = "./src/main/resources/day2.txt";
    public File file = new File(FILE_DIR);
    public Scanner scanner;

    public Day2() {
        try {
            scanner = new Scanner(file);
        } catch (Exception ignored) {
        }
    }

    enum Guide {
        A(Shape.ROCK),
        B(Shape.PAPER),
        C(Shape.SCISSORS),
        X(Shape.ROCK),
        Y(Shape.PAPER),
        Z(Shape.SCISSORS);

        public final Shape shape;

        private Guide(Shape shape) {
            this.shape = shape;
        }
    }

    enum Shape {
        ROCK(1),
        PAPER(2),
        SCISSORS(3);

        public final int points;

        private Shape(int points) {
            this.points = points;
        }

        private Shape beats() {
            return switch (this) {
                case ROCK -> SCISSORS;
                case PAPER -> ROCK;
                case SCISSORS -> PAPER;
            };
        }
    }

    private int playGame(String c1, String c2) {
        Guide opponent = Guide.valueOf(c1);
        Guide player = Guide.valueOf(c2);

        if (opponent.shape == player.shape) {
            return 3 + player.shape.points;
        }
        if (opponent.shape.beats() == player.shape) {
            return player.shape.points;
        }
        return 6 + player.shape.points;
    }

    private int playGame2(String c1, String outcome) {
        Guide opponent = Guide.valueOf(c1);

        if (Objects.equals(outcome, Guide.X.toString())) {
            return opponent.shape.beats().points; // X -> end in loss
        } else if (Objects.equals(outcome, Guide.Y.toString())) {
            return 3 + opponent.shape.points; // Y -> end in draw
        } else {
            return 6 + opponent.shape.beats().beats().points; // Z -> end in win (beat what the opponent beats)
        }
    }

    public void partOne() throws FileNotFoundException {
        scanner = new Scanner(file);

        int score = 0;

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();

            String p1 = line.split(" ")[0].trim();
            String p2 = line.split(" ")[1].trim();

            score += playGame(p1, p2);
        }

        System.out.println("Part One Score: " + score);

        scanner.close();
    }

    public void partTwo() throws FileNotFoundException {
        scanner = new Scanner(file);

        int score = 0;

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();

            String p1 = line.split(" ")[0].trim();
            String p2 = line.split(" ")[1].trim();

            score += playGame2(p1, p2);
        }

        System.out.println("Part Two Score: " + score);

        scanner.close();
    }
}