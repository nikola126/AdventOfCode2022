package org.aoc2022;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Day8 {
    public final String FILE_DIR = "./src/main/resources/day8.txt";
    public File file = new File(FILE_DIR);
    public Scanner scanner;

    public Day8() {
        try {
            scanner = new Scanner(file);
        } catch (Exception ignored) {
        }
    }

    public void partOne() throws FileNotFoundException {
        scanner = new Scanner(file);

        int ROWS = 0, COLS = 0;
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            COLS = line.length();
            ROWS += 1;
        }

        int[][] forest = new int[ROWS][COLS];

        scanner.close();
        scanner = new Scanner(file);

        int row = 0;
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            int col = 0;
            for (Character c : line.toCharArray()) {
                int tree = Integer.parseInt(c.toString());

                forest[row][col] = tree;
                col += 1;
            }
            row += 1;
        }

        int visibleTrees = 0;
        int visibleInterior = 0;
        int visibleEdge = 0;

        for (row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLS; col++) {
                int tree = forest[row][col];

                if (isAtEdge(row, col, ROWS, COLS)) {
                    visibleEdge += 1;
                    continue;
                }

                if (isVisibleUp(row, col, ROWS, COLS, tree, forest)) {
                    visibleInterior += 1;
                    continue;
                }

                if (isVisibleRight(row, col, ROWS, COLS, tree, forest)) {
                    visibleInterior += 1;
                    continue;
                }

                if (isVisibleDown(row, col, ROWS, COLS, tree, forest)) {
                    visibleInterior += 1;
                    continue;
                }

                if (isVisibleLeft(row, col, ROWS, COLS, tree, forest)) {
                    visibleInterior += 1;
                    continue;
                }
            }
        }

        visibleTrees = visibleEdge + visibleInterior;
        System.out.println("Visible trees: " + visibleTrees);
    }

    boolean isAtEdge(int row, int col, int ROWS, int COLS) {
        if (row == 0 || row == ROWS - 1)
            return true;
        else if (col == 0 || col == COLS - 1)
            return true;
        return false;
    }

    boolean isVisibleUp(int row, int col, int ROWS, int COLS, int tree, int[][] forest) {
        int currentRow = row - 1;

        while (currentRow >= 0) {
            if (tree <= forest[currentRow][col])
                return false;
            currentRow--;
        }

        return true;
    }

    boolean isVisibleRight(int row, int col, int ROWS, int COLS, int tree, int[][] forest) {
        int currentCol = col + 1;

        while (currentCol < COLS) {
            if (tree <= forest[row][currentCol])
                return false;
            currentCol++;
        }

        return true;
    }

    boolean isVisibleDown(int row, int col, int ROWS, int COLS, int tree, int[][] forest) {
        int currentRow = row + 1;

        while (currentRow < ROWS) {
            if (tree <= forest[currentRow][col])
                return false;
            currentRow++;
        }

        return true;
    }

    boolean isVisibleLeft(int row, int col, int ROWS, int COLS, int tree, int[][] forest) {
        int currentCol = col - 1;

        while (currentCol >= 0) {
            if (tree <= forest[row][currentCol])
                return false;
            currentCol--;
        }

        return true;
    }

    public void partTwo() throws FileNotFoundException {
        scanner = new Scanner(file);

        int ROWS = 0, COLS = 0;
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            COLS = line.length();
            ROWS += 1;
        }

        int[][] forest = new int[ROWS][COLS];

        scanner.close();
        scanner = new Scanner(file);

        int row = 0;
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            int col = 0;
            for (Character c : line.toCharArray()) {
                int tree = Integer.parseInt(c.toString());

                forest[row][col] = tree;
                col += 1;
            }
            row += 1;
        }

        int maxScore = -1;

        for (row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLS; col++) {
                int tree = forest[row][col];

                int up = getScenicScoreUp(row, col, ROWS, COLS, tree, forest);
                int right = getScenicScoreRight(row, col, ROWS, COLS, tree, forest);
                int down = getScenicScoreDown(row, col, ROWS, COLS, tree, forest);
                int left = getScenicScoreLeft(row, col, ROWS, COLS, tree, forest);

                maxScore = Math.max(maxScore, up * right * down * left);
            }
        }

        System.out.println("Max Scenic Score: " + maxScore);
    }

    int getScenicScoreUp(int row, int col, int ROWS, int COLS, int tree, int[][] forest) {
        if (row == 0)
            return 0;

        if (forest[row - 1][col] >= tree)
            return 1;

        return 1 + getScenicScoreUp(row - 1, col, ROWS, COLS, tree, forest);
    }

    int getScenicScoreRight(int row, int col, int ROWS, int COLS, int tree, int[][] forest) {
        if (col == COLS - 1)
            return 0;

        if (forest[row][col + 1] >= tree)
            return 1;

        return 1 + getScenicScoreRight(row, col + 1, ROWS, COLS, tree, forest);
    }

    int getScenicScoreDown(int row, int col, int ROWS, int COLS, int tree, int[][] forest) {
        if (row == ROWS - 1)
            return 0;

        if (forest[row + 1][col] >= tree)
            return 1;

        return 1 + getScenicScoreDown(row + 1, col, ROWS, COLS, tree, forest);
    }

    int getScenicScoreLeft(int row, int col, int ROWS, int COLS, int tree, int[][] forest) {
        if (col == 0)
            return 0;

        if (forest[row][col - 1] >= tree)
            return 1;

        return 1 + getScenicScoreLeft(row, col - 1, ROWS, COLS, tree, forest);
    }

}