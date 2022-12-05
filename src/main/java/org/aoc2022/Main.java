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

        Day4 day4 = new Day4();
        day4.partOne();
        day4.partTwo();

        Day5 day5 = new Day5();
        day5.partOne();
        day5.partTwo();
    }
}