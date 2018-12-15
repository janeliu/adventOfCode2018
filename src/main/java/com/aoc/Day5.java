package com.aoc;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.util.stream.Collectors;

public class Day5 {
    private static final String PATH = "/home/jliu/day5.txt";
    public static void main(String[] args){
        try {
            List<String> lines = Files.lines(new File(PATH).toPath()).collect(Collectors.toList());
            String s = lines.get(0).trim();
            int min = s.length();
            for(int i = 0; i < 26; i++) {
                int ascii = (int)'a' + i;
                min = Math.min( min, part1(s.toCharArray(), (char)ascii));
            }
            System.out.println("Units " + min);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static int part1(char[] letters, char remove){
        if(letters.length < 2) return letters.length;
        int i = 0;
        int j = 1;
        int count = 0;
        while(j < letters.length) {
            if(Character.toLowerCase(letters[i]) == Character.toLowerCase(remove)) {
                letters[i] = '\0';
                count++;
                i++;
                j++;
                continue;
            }
            if(Character.toLowerCase(letters[j]) == Character.toLowerCase(remove)) {
                letters[j] = '\0';
                count++;
                j++;
                continue;
            }
            while (i >=0 && j < letters.length){
                if(Character.toLowerCase(letters[j]) == Character.toLowerCase(remove)) {
                    letters[j] = '\0';
                    count++;
                    j++;
                    continue;
                }
                if(letters[i] == '\0') {
                    i--;
                    continue;
                }
                if(letters[j] == '\0') {
                    j++;
                    continue;
                }
                int diff = Math.abs((int)letters[i] - (int)letters[j]);
                if(diff != 32) break;
                count+=2;
                letters[i] = '\0';
                letters[j] = '\0';
                i--;
                j++;
            }
            i = j;
            j++;
        }
        return letters.length - count;
    }
}
