package org.aoc2022;

import lombok.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Day9 {
    public final String FILE_DIR = "./src/main/resources/day9.txt";
    public File file = new File(FILE_DIR);
    public Scanner scanner;

    public Day9() {
        try {
            scanner = new Scanner(file);
        } catch (Exception ignored) {
        }
    }

    public void partOne() throws FileNotFoundException {
        scanner = new Scanner(file);

        Head head = new Head(new Point());
        Tail tail = new Tail(new Point());

        Set<Point> visitedSet = new HashSet<>();

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();

            String direction = line.split(" ")[0];
            int steps = Integer.parseInt(line.split(" ")[1]);

            for (int i = 0; i < steps; i++) {
                switch (direction) {
                    case "U" -> {
                        head.moveUp();
                    }
                    case "D" -> {
                        head.moveDown();
                    }
                    case "L" -> {
                        head.moveLeft();
                    }
                    case "R" -> {
                        head.moveRight();
                    }
                }

                tail.moveToTarget(head.location);

                visitedSet.add(tail.getLocation());
            }
        }

        scanner.close();

        System.out.println("Visited points: " + visitedSet.size());
    }

    public void partTwo() throws FileNotFoundException {
        scanner = new Scanner(file);

        int ROPE_SIZE = 9;
        Head head = new Head(new Point());

        List<Tail> tails = new ArrayList<>();

        for (int i = 0; i < ROPE_SIZE; i++) {
            tails.add(new Tail(new Point()));
        }

        Set<Point> visitedSet = new HashSet<>();
        visitedSet.add(tails.get(ROPE_SIZE - 1).location);

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();

            String direction = line.split(" ")[0];
            int steps = Integer.parseInt(line.split(" ")[1]);

            for (int i = 0; i < steps; i++) {
                switch (direction) {
                    case "U" -> {
                        head.moveUp();
                    }
                    case "D" -> {
                        head.moveDown();
                    }
                    case "L" -> {
                        head.moveLeft();
                    }
                    case "R" -> {
                        head.moveRight();
                    }
                }

                for (int j = 0; j < ROPE_SIZE; j++) {
                    Tail tail = tails.get(j);

                    if (j == 0) {
                        tail.moveToTarget(head.location);
                    } else {
                        tail.moveToTarget(tails.get(j - 1).location);
                    }

                    if (j == ROPE_SIZE - 1) {
                        visitedSet.add(tail.location);
                    }
                }
            }
        }

        scanner.close();

        System.out.println("Visited points: " + visitedSet.size());
    }

    @NoArgsConstructor
    @Getter
    @Setter
    private class Head {
        Point location;

        public Head(Point location) {
            this.location = location;
        }

        public void moveLeft() {
            location.x--;
        }

        public void moveRight() {
            location.x++;
        }

        public void moveDown() {
            location.y--;
        }

        public void moveUp() {
            location.y++;
        }

        @Override
        public String toString() {
            return "Head " + "[" + location.x + "][" + location.y + "]";
        }
    }

    @NoArgsConstructor
    @Getter
    @Setter
    private class Tail {
        Point location;

        public Tail(Point location) {
            this.location = location;
        }

        public void moveToTarget(Point target) {
            if (!isTargetFar(target))
                return;

            if (location.x > target.x) {
                location.x--;
            } else if (location.x < target.x) {
                location.x++;
            }

            if (location.y > target.y) {
                location.y--;
            } else if (location.y < target.y) {
                location.y++;
            }
        }

        public boolean isTargetFar(Point point) {
            return Math.abs(location.x - point.x) > 1 || Math.abs(location.y - point.y) > 1;
        }

        @Override
        public String toString() {
            return "Tail " + "[" + location.x + "][" + location.y + "]";
        }
    }

    @NoArgsConstructor
    @Getter
    @Setter
    private class Point {
        int x;
        int y;

        @Override
        public boolean equals(Object o) {
            if (!(o instanceof Point other))
                return false;

            return x == other.x && y == other.y;
        }

        @Override
        public int hashCode() {
            // https://stackoverflow.com/a/50228389
            // Other people had it easier :(
            int ax = Math.abs(x);
            int ay = Math.abs(y);
            if (ax > ay && x > 0) return 4 * x * x - 3 * x + y + 1;
            if (ax > ay && x <= 0) return 4 * x * x - x - y + 1;
            if (ax <= ay && y > 0) return 4 * y * y - y - x + 1;
            return 4 * y * y - 3 * y + x + 1;
        }

        @Override
        public String toString() {
            return "[" + x + "][" + y + "]";
        }
    }

}