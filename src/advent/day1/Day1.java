package advent.day1;

import io.PuzzleDataIO;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day1 {

    public static void main(String[] args) {
        try {
            InputStream puzzleStream = PuzzleDataIO.readPuzzleData();
            BufferedReader reader = new BufferedReader(new InputStreamReader(puzzleStream));

            StringBuilder solutionBuilder = new StringBuilder();
            int totalSum = 0;

            String line;
            while ((line = reader.readLine()) != null) {
                int firstNumber = -1;
                int lastNumber = -1;

                List<String> segments = extractSegments(line);
                for (String segment : segments) {
                    int number = convertToNumber(segment);
                    if (number >= 1 && number <= 9) {
                        if (firstNumber == -1) {
                            firstNumber = number;
                        }
                        lastNumber = number;
                    }
                }

                if (firstNumber != -1) {
                    int combinedNumber;
                    combinedNumber = (firstNumber * 10) + lastNumber;
                    totalSum += combinedNumber;
                    solutionBuilder.append(combinedNumber).append("\n");
                }
            }

            reader.close();
            puzzleStream.close();

            String solution = solutionBuilder.toString();
            PuzzleDataIO.writeSolution(solution);

            PuzzleDataIO.writeSolution("Total sum of combined numbers: " + totalSum);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static int convertToNumber(String word) {
        try {
            int number = Integer.parseInt(word);
            if (number >= 1 && number <= 9) {
                return number;
            }
        } catch (NumberFormatException ignored) {}

        switch (word.toLowerCase()) {
            case "one" -> {return 1;}
            case "two" -> {return 2;}
            case "three" -> {return 3;}
            case "four" -> {return 4;}
            case "five" -> {return 5;}
            case "six" -> {return 6;}
            case "seven" -> {return 7;}
            case "eight" -> {return 8;}
            case "nine" -> {return 9;}
            default -> {return -1;}
        }
    }

    private static List<String> extractSegments(String line) {
        List<String> segments = new ArrayList<>();
        Pattern pattern = Pattern.compile("[a-zA-Z]+|\\d+");
        Matcher matcher = pattern.matcher(line);
        while (matcher.find()) {
            segments.add(matcher.group());
        }
        return segments;
    }

}