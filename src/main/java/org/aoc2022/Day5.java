package org.aoc2022;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Day5 {
    public final String FILE_DIR = "./src/main/resources/day5.txt";
    public File file = new File(FILE_DIR);
    public Scanner scanner;

    public Day5() {
        try {
            scanner = new Scanner(file);
        } catch (Exception ignored) {
        }
    }

    private int determineColumn(File file) throws FileNotFoundException {
        scanner = new Scanner(file);

        String lastLine = "";
        // determine number of columns
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();

            if (line.isBlank()) {
                scanner.close();
                return lastLine.split("   ").length;
            } else {
                lastLine = line;
            }
        }

        scanner.close();
        return -1;
    }

    private void fillDequeues(File file, List<Deque<Character>> dequeList) throws FileNotFoundException {
        scanner = new Scanner(file);

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();

            if (line.charAt(1) == '1')
                break;

            StringBuilder sb = new StringBuilder();
            int i = 0;
            int j = 0;
            for (Character c : line.toCharArray()) {
                sb.append(c);
                if ((i + 1) % 4 == 0 || i == line.length() - 1) {
                    if (!sb.toString().isBlank()) {
                        for (Character cx : sb.toString().toCharArray()) {
                            if (Character.isAlphabetic(cx)) {
                                dequeList.get(j).add(cx);
                            }
                        }
                    }
                    j += 1;
                    sb.setLength(0);
                }
                i++;
            }
        }

        scanner.close();
    }

    private void moverPartOne(File file, List<Deque<Character>> dequeList) throws FileNotFoundException {
        scanner = new Scanner(file);

        boolean instructionsReached = false;
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();

            if (line.isEmpty()) {
                instructionsReached = true;
                continue;
            }

            if (instructionsReached) {
                String[] tokens = line.split(" ");
                int amount = Integer.parseInt(tokens[1]);
                int start = Integer.parseInt(tokens[3]);
                int end = Integer.parseInt(tokens[5]);

                for (int i = 0; i < amount; i++) {
                    dequeList.get(end - 1).addFirst(dequeList.get(start - 1).poll());
                }
            }
        }

        scanner.close();

        StringBuilder sb = new StringBuilder();

        for (Deque<Character> deque : dequeList) {
            sb.append(deque.peekFirst());
        }

        System.out.println("Part One: " + sb);
    }

    private void moverPartTwo(File file, List<Deque<Character>> dequeList) throws FileNotFoundException {
        scanner = new Scanner(file);

        boolean instructionsReached = false;
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();

            if (line.isEmpty()) {
                instructionsReached = true;
                continue;
            }

            if (instructionsReached) {
                String[] tokens = line.split(" ");
                int amount = Integer.parseInt(tokens[1]);
                int start = Integer.parseInt(tokens[3]);
                int end = Integer.parseInt(tokens[5]);

                Deque<Character> buffer = new LinkedList<>();
                for (int i = 0; i < amount; i++) {
                    buffer.addFirst(dequeList.get(start - 1).poll());
                }

                for (int i = 0; i < amount; i++) {
                    dequeList.get(end - 1).addFirst(buffer.pollFirst());
                }
            }
        }

        scanner.close();

        StringBuilder sb = new StringBuilder();

        for (Deque<Character> deque : dequeList) {
            sb.append(deque.peekFirst());
        }

        System.out.println("Part Two: " + sb);
    }

    public void partOne() throws FileNotFoundException {
        scanner = new Scanner(file);

        List<Deque<Character>> dequeList = new ArrayList<>();

        int N = determineColumn(file);

        for (int i = 0; i < N; i++)
            dequeList.add(new LinkedList<>());

        fillDequeues(file, dequeList);

        moverPartOne(file, dequeList);
    }

    public void partTwo() throws FileNotFoundException {
        scanner = new Scanner(file);

        List<Deque<Character>> dequeList = new ArrayList<>();

        int N = determineColumn(file);

        for (int i = 0; i < N; i++)
            dequeList.add(new LinkedList<>());

        fillDequeues(file, dequeList);

        moverPartTwo(file, dequeList);
    }

}