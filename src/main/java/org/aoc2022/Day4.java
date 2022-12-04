package org.aoc2022;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Day4 {
    public final String FILE_DIR = "./src/main/resources/day4.txt";
    public File file = new File(FILE_DIR);
    public Scanner scanner;

    public Day4() {
        try {
            scanner = new Scanner(file);
        } catch (Exception ignored) {
        }
    }

    public void partOne() throws FileNotFoundException {
        scanner = new Scanner(file);

        int countOverlap = 0;

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();

            String[] pairs = line.split(",");

            int l1 = Integer.parseInt(pairs[0].split("-")[0]);
            int r1 = Integer.parseInt(pairs[0].split("-")[1]);

            int l2 = Integer.parseInt(pairs[1].split("-")[0]);
            int r2 = Integer.parseInt(pairs[1].split("-")[1]);

            if (l1 <= l2 && r2 <= r1) {
                countOverlap += 1;
            } else if (l2 <= l1 && r1 <= r2) {
                countOverlap += 1;
            }
        }

        System.out.println("Part One Overlapping: " + countOverlap);

        scanner.close();
    }

    public void partTwo() throws FileNotFoundException {
        scanner = new Scanner(file);

        int countOverlap = 0;

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();

            String[] pairs = line.split(",");

            int l1 = Integer.parseInt(pairs[0].split("-")[0]);
            int r1 = Integer.parseInt(pairs[0].split("-")[1]);

            int l2 = Integer.parseInt(pairs[1].split("-")[0]);
            int r2 = Integer.parseInt(pairs[1].split("-")[1]);

            if (r1 < l2 || r2 < l1) {
                continue;
            } else {
                countOverlap += 1;
            }
        }

        System.out.println("Part Two Overlapping: " + countOverlap);

        scanner.close();
    }

}