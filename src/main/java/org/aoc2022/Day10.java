package org.aoc2022;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Day10 {
    public final String FILE_DIR = "./src/main/resources/day10.txt";
    public File file = new File(FILE_DIR);
    public Scanner scanner;

    public Day10() {
        try {
            scanner = new Scanner(file);
        } catch (Exception ignored) {
        }
    }

    public void partOne() throws FileNotFoundException {
        scanner = new Scanner(file);

        int register = 1;
        List<Integer> instructions = new ArrayList<>();

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();

            String[] tokens = line.split(" ");

            if (tokens.length == 1) {
                instructions.add(null);
            } else {
                int amount = Integer.parseInt(tokens[1]);
                instructions.add(null);
                instructions.add(amount);
            }
        }

        int signalSum = 0;
        int cycle = 1;
        for (Integer value : instructions) {
            switch (cycle) {
                case 20 -> {
                    signalSum += (20 * register);
                }
                case 60 -> {
                    signalSum += (60 * register);
                }
                case 100 -> {
                    signalSum += (100 * register);
                }
                case 140 -> {
                    signalSum += (140 * register);
                }
                case 180 -> {
                    signalSum += (180 * register);
                }
                case 220 -> {
                    signalSum += (220 * register);
                }
            }

            if (value != null) {
                register += value;
            }

            cycle  += 1;
        }

        System.out.println("Signal sum: " + signalSum);

        scanner.close();
    }

    public void partTwo() throws FileNotFoundException {
        scanner = new Scanner(file);

        int register = 1;
        List<Integer> instructions = new ArrayList<>();

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();

            String[] tokens = line.split(" ");

            if (tokens.length == 1) {
                instructions.add(null);
            } else {
                int amount = Integer.parseInt(tokens[1]);
                instructions.add(null);
                instructions.add(amount);
            }
        }

        char[] screenLine = new char[40];

        int cycle = 1;
        int screenPos = 0;
        for (Integer value : instructions) {

            if (Math.abs(screenPos - register) <= 1) {
                screenLine[screenPos] = '@';
            }
            else {
                screenLine[screenPos] = ' ';
            }

            if (cycle % 40 == 0) {
                System.out.println(screenLine);
                screenPos = 0;
            } else {
                screenPos += 1;
            }

            if (value != null) {
                register += value;
            }

            cycle  += 1;
        }

        scanner.close();
    }
}