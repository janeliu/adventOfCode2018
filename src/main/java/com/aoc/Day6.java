package com.aoc;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class Day6 {
    private static final String PATH = "/home/jliu/input.txt";

    public static void part1(){
        int sum = 0;
        try {
            sum = Files.lines(new File(PATH).toPath())
                    .mapToInt(s -> Integer.valueOf(s.trim()))
                    .sum();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("sum is " + sum);
    }

    private class Cordinate {
        private int x;
        private int y;

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }
    }

}
