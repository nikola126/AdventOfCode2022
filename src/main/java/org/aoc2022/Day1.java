package org.aoc2022;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Day1 {
    public final String FILE_DIR = "./src/main/resources/day1.txt";
    public File file = new File(FILE_DIR);
    public Scanner scanner;

    public Day1() {
        try {
            scanner = new Scanner(file);
        } catch (Exception ignored) {
        }
    }

    public void partOne() throws FileNotFoundException {
        scanner = new Scanner(file);

        int maxWeight = -1;
        int currentWeight = 0;

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();

            if (line.isBlank()) {
                maxWeight = Math.max(maxWeight, currentWeight);
                currentWeight = 0;
            } else {
                try {
                    currentWeight += Integer.parseInt(line);
                } catch (Exception ignored) {}
            }
        }

        System.out.println("Max Elf: " + maxWeight);
        scanner.close();
    }

    public void partTwo() throws FileNotFoundException {
        scanner = new Scanner(file);

        List<Integer> weights = new ArrayList<>();
        int currentWeight = 0;

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();

            if (line.isBlank()) {
                weights.add(currentWeight);
                currentWeight = 0;
            } else {
                try {
                    currentWeight += Integer.parseInt(line);
                } catch (Exception ignored) {}
            }
        }

        int sumTopThree = 0;
        weights.sort((o1, o2) -> o2 - o1);

        for (int i = 0; i < 3; i++) {
            sumTopThree += weights.get(i);
        }

        System.out.println("Sum Top 3: " + sumTopThree);

        scanner.close();
    }
}