package com.aoc;

import com.aoc.model.Coordinate;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Day3 {
    private static final String PATH = "resources/day3.txt";
    public static void main(String[] args) throws IOException {
        Day3 day3 = new Day3();
        Fabric fabric = day3.drawFabric();
        List<List<String>> lists = day3.part1(fabric);
        System.out.println("area is " + lists.size());
        day3.part2(fabric, lists.stream().flatMap(v -> v.stream()).collect(Collectors.toSet()));
    }

    public List<List<String>> part1(Fabric fabric) {
        return fabric.getFabric().values().stream().filter(list -> list.size() > 1).collect(Collectors.toList());
    }

    public void part2(Fabric fabric, Set<String> ids) {
        List<List<String>> collect = fabric.getFabric().values().stream().filter(list -> list.size() == 1).filter(l -> !ids.contains(l.get(0))).collect(Collectors.toList());
        System.out.println("id is " + collect.get(0).get(0));
    }

    private Fabric drawFabric() throws IOException {
        Fabric fabric = new Fabric();
        Files.lines(new File(PATH).toPath()).forEach(
                l -> {
                    int startOfCor = l.indexOf("@");
                    int startOfArea = l.indexOf(":");
                    String id = l.substring(1, startOfCor).trim();
                    String xy = l.substring(startOfCor + 1, startOfArea).trim();
                    int xStart = Integer.valueOf(xy.split(",")[0]);
                    int yStart = Integer.valueOf(xy.split(",")[1]);
                    String area = l.substring(startOfArea + 1, l.length()).trim();
                    int xLength = Integer.valueOf(area.split("x")[0]);
                    int yLength = Integer.valueOf(area.split("x")[1]);
                    drawCoordinates(fabric, xStart, xLength, yStart, yLength, id);
                }
        );
        return fabric;
    }
    private void drawCoordinates(Fabric fabric, int xStart, int xLength, int yStart, int yLength, String id) {
        IntStream.rangeClosed(xStart, xStart + xLength - 1).forEach(
                x -> IntStream.rangeClosed(yStart, yStart + yLength - 1).forEach(y -> fabric.markId(new Coordinate(x, y), id))
        );
    }

    class Fabric {
        private Map<Coordinate, List<String>> fabric;

        protected Fabric() {
            this.fabric = new HashMap<>();
        }

        void markId(Coordinate coordinate, String id) {
            if(fabric.containsKey(coordinate)) {
                List<String> ids = fabric.get(coordinate);
                ids.add(id);
            } else {
                fabric.put(coordinate, new ArrayList<>(Arrays.asList(id)));
            }
        }

        Map<Coordinate, List<String>> getFabric() {
            return fabric;
        }

    }
}
