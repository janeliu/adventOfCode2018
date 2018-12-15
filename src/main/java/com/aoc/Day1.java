package com.aoc;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Day1 {
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

    public static void part2(){
        try {
            Set<Integer> freqs = new HashSet<>();
            List<Integer> nums = Files.lines(new File(PATH).toPath())
                    .map(s -> Integer.valueOf(s.trim()))
                    .collect(Collectors.toList());
            int freq = getFreq(freqs, nums, 0);
            System.out.println("frequency is " + freq);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static int getFreq(Set<Integer> freqs, List<Integer> list, int sum){
        for ( int freq: list ) {
            sum += freq;
            if( freqs.contains(sum) ){
                return sum;
            }
            freqs.add(sum);
        }
        return getFreq(freqs, list, sum);
    }

}
