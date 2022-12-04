package org.aoc2022;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Day3 {
    public final String FILE_DIR = "./src/main/resources/day3.txt";
    public File file = new File(FILE_DIR);
    public Scanner scanner;

    public Day3() {
        try {
            scanner = new Scanner(file);
        } catch (Exception ignored) {
        }
    }

    public int decode(Character c) {
        if (Character.isUpperCase(c)) {
            return Integer.valueOf(c) - 38;
        } else {
            return Integer.valueOf(c) - 96;
        }
    }

    public void partOne() throws FileNotFoundException {
        scanner = new Scanner(file);

        int sum = 0;

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            int N = line.length();

            String firstItem = line.substring(0, N / 2);
            String secondItem = line.substring(N / 2, N);

            Character common = null;
            for (Character c : firstItem.toCharArray()) {
                if (secondItem.contains(String.valueOf(c))) {
                    common = c;
                    break;
                }
            }

            sum += decode(common);
        }

        System.out.println("Part One Sum: " + sum);

        scanner.close();
    }

    public void partTwo() throws FileNotFoundException {
        scanner = new Scanner(file);

        int sum = 0;

        List<String> group = new ArrayList<>();

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            group.add(line);

            if (group.size() == 3) {
                Character common = null;

                for (Character c : group.get(0).toCharArray()) {
                    if (group.get(1).contains(String.valueOf(c)) && group.get(2).contains(String.valueOf(c))) {
                        common = c;
                        break;
                    }
                }

                sum += decode(common);
                group = new ArrayList<>();
            }

        }

        System.out.println("Part Two Sum: " + sum);

        scanner.close();
    }

}