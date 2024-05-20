package advent.day1;

import advent.utils.CharacterGrid;
import io.PuzzleDataIO;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

public class Day1 {

    public static void main(String[] args) {
        try {
            InputStream puzzleStream = PuzzleDataIO.readPuzzleData();
            BufferedReader reader = new BufferedReader(new InputStreamReader(puzzleStream));

            StringBuilder solutionBuilder = new StringBuilder();
            int totalSum = 0;

            String line;
            while ((line = reader.readLine()) != null) {
                CharacterGrid grid = new CharacterGrid(List.of(line));

                int firstNumber = -1;
                int lastNumber = -1;

                for (int row = 0; row < grid.getRowCount(); row++) {
                    for (int col = 0; col < grid.getColumnCount(); col++) {
                        if (grid.isPartNumber(row, col)) {
                            int number = Character.getNumericValue(grid.getChar(row, col));
                            if (number >= 1 && number <= 9) {
                                if (firstNumber == -1) {
                                    firstNumber = number;
                                }
                                lastNumber = number;
                            }
                        } else if (grid.isSymbol(row, col)) {
                            String word = extractWord(grid, row, col);
                            int number = convertToNumber(word);
                            if (number >= 1 && number <= 9) {
                                if (firstNumber == -1) {
                                    firstNumber = number;
                                }
                                lastNumber = number;
                            }
                        }
                    }
                }

                if (firstNumber != -1) {
                    int combinedNumber = (firstNumber * 10) + lastNumber;
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

    private static String extractWord(CharacterGrid grid, int row, int col) {
        StringBuilder wordBuilder = new StringBuilder();
        while (grid.isSymbol(row, col)) {
            wordBuilder.append(grid.getChar(row, col));
            col++;
        }
        return wordBuilder.toString();
    }

    private static int convertToNumber(String word) {
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
}