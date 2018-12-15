package com.aoc;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.*;
import java.util.stream.Collectors;

public class Day7 {
    private static final String PATH = "/home/jliu/day7.txt";

    public static void main(String[] args){
        part1();
    }

    public static void part1(){
        try {
            Map<Character, List<Character>> map = new HashMap<>();
            mapLetters(map);
            Queue<Character> queue = buildRootQueue(map);
            StringBuilder sb = new StringBuilder();
            while (queue.peek() != null) {
                Character key = queue.poll();
                sb.append(key);
                List<Character> values = map.get(key);
                map.remove(key);
                if(values == null) continue;
                Set<Character> allValues = getValueSet(map);
                values.stream().forEach(v -> {
                    if (!allValues.contains(v)) {
                        queue.add(v);
                    }
                });
            }


            System.out.println(sb.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static Set<Character> getValueSet(Map<Character, List<Character>> map) {
        return map.values().stream().flatMap(v -> v.stream()).collect(Collectors.toSet());
    }

    private static Queue<Character> buildRootQueue(Map<Character, List<Character>> map) {
        Queue<Character> queue = new PriorityQueue<>();
        Set<Character> valueSet = getValueSet(map);
        map.keySet().stream().forEach( k -> {
            if( !valueSet.contains(k) ) {
                queue.add(k);
            }
        });
        return queue;
    }

    private static void mapLetters(Map<Character, List<Character>> map) throws IOException {
        Files.lines(new File(PATH).toPath())
                .forEach( line -> {
                    Character key = line.charAt(("Step ").length());
                    Character value = line.charAt(line.length() - (" can begin.").length()-1);
                    if(map.containsKey(key)) {
                        map.get(key).add(value);
                    }else {
                        map.put(key, new ArrayList<>(Arrays.asList(value)));
                    }
                });
    }


}
