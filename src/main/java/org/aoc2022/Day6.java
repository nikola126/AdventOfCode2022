package org.aoc2022;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Day6 {
    public final String FILE_DIR = "./src/main/resources/day6.txt";
    public File file = new File(FILE_DIR);
    public Scanner scanner;

    public Day6() {
        try {
            scanner = new Scanner(file);
        } catch (Exception ignored) {
        }
    }

    public void partOne() throws FileNotFoundException {
        scanner = new Scanner(file);
        String line = scanner.nextLine();
        scanner.close();

        Deque<Character> deque = new LinkedList<>();

        int index = 0;
        for (Character c : line.toCharArray()) {
            deque.addLast(c);

            index += 1;

            if (deque.size() == 4) {
                List<Character> characterList = new ArrayList<>(deque);
                Set<Character> set = new HashSet<>(characterList);

                if (set.size() == 4) {
                    System.out.println("Unique 4 characters: " + characterList + " at index " + index);
                    break;
                }

                deque.removeFirst();
            }
        }
    }

    public void partTwo() throws FileNotFoundException {
        scanner = new Scanner(file);
        String line = scanner.nextLine();
        scanner.close();

        Deque<Character> deque = new LinkedList<>();

        int index = 0;
        for (Character c : line.toCharArray()) {
            deque.addLast(c);

            index += 1;

            if (deque.size() == 14) {
                List<Character> characterList = new ArrayList<>(deque);
                Set<Character> set = new HashSet<>(characterList);

                if (set.size() == 14) {
                    System.out.println("Unique 14 characters: " + characterList + " at index " + index);
                    break;
                }

                deque.removeFirst();
            }
        }
    }

}