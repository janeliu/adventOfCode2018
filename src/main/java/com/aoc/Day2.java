package com.aoc;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Day2 {
    private static final String PATH = "/home/jliu/day2.txt";
    public static void main(String[] args){
//        part1();
        part2();
    }

    public static void part1(){
        try {
            List<String> lines = Files.lines(new File(PATH).toPath())
                    .collect(Collectors.toList());

            int doubles = 0;
            int triples = 0;
            for (String l : lines) {
                Map<Character, Integer> map  = new HashMap<>();
                char[] chars = l.trim().toCharArray();
                int twice = 0;
                int threeTimes = 0;
                for ( char c: chars) {
                    if( !map.containsKey(c)) {
                        map.put(c, 1);
                    }else {
                        int value = map.get(c);
                        map.put(c, value + 1);
                        if(value == 2) {
                            threeTimes++;
                            twice--;
                        }else if(value == 1) {
                            twice++;
                        }else {
                            threeTimes--;
                        }
                    }
                }
                if( twice > 0) {
                    doubles++;
                }
                if(threeTimes > 0) {
                    triples++;
                }
            }
            System.out.println(doubles * triples);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void part2(){
        try {
            List<String> lines = Files.lines(new File(PATH).toPath())
                    .collect(Collectors.toList());

            for(int i = 0; i < lines.size(); i++){
                for(int j = i+1; j < lines.size(); j++) {
                    String a = lines.get(i).trim();
                    String b = lines.get(j).trim();
                    int count = 0;
                    StringBuilder newString = new StringBuilder();
                    for(int k = 0; k < a.length(); k++){
                        if(a.charAt(k) == b.charAt(k)) {
                            newString.append(a.charAt(k));
                        }else {
                            if(count > 0) {
                                newString = new StringBuilder();
                                break;
                            }
                            count++;
                        }
                    }
                    if(newString.toString().length() == a.length() - 1) {
                        System.out.println("String " + newString.toString());
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
