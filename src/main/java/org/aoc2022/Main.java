package org.aoc2022;

import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        System.out.println("Hello world!");
        Day1 day1 = new Day1();
        day1.partOne();
        day1.partTwo();

        Day2 day2 = new Day2();
        day2.partOne();
        day2.partTwo();

        Day3 day3 = new Day3();
        day3.partOne();
        day3.partTwo();
    }
}