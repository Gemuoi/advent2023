package advent.day2;

import data.Stream;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Day2 {
    public static void main(String[] args) throws IOException {
        List<String> games = new ArrayList<>();
        try (InputStream inputStream = Stream.readPuzzleData();
             BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            while ((line = reader.readLine()) != null) {
                games.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        int totalPower = 0;

        for (String game : games) {
            String[] gameData = game.split(":\\s+");
            String[] sets = gameData[1].split(";\\s+");

            int maxRed = 0;
            int maxGreen = 0;
            int maxBlue = 0;

            for (String set : sets) {
                int redCount = 0;
                int greenCount = 0;
                int blueCount = 0;

                String[] cubes = set.split(",\\s+");
                for (String cube : cubes) {
                    int count = Integer.parseInt(cube.split("\\s+")[0]);
                    String color = cube.split("\\s+")[1];
                    switch (color) {
                        case "red" -> redCount += count;
                        case "green" -> greenCount += count;
                        case "blue" -> blueCount += count;
                    }
                }

                maxRed = Math.max(maxRed, redCount);
                maxGreen = Math.max(maxGreen, greenCount);
                maxBlue = Math.max(maxBlue, blueCount);
            }

            int power = maxRed * maxGreen * maxBlue;
            totalPower += power;
        }

        Stream.writeSolution("Sum of the power of minimum sets: " + totalPower);
    }
}