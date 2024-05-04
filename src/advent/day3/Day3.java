package advent.day3;

import data.Stream;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Day3 {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(Stream.readPuzzleData()));
        String line;
        StringBuilder sb = new StringBuilder();
        while ((line = reader.readLine()) != null) {
            sb.append(line).append("\n");
        }
        reader.close();
        String[] schematic = sb.toString().split("\n");
        int sum = sumPartNumbers(schematic);
        Stream.writeSolution("Sum of all part numbers: " + sum);
    }

    public static int sumPartNumbers(String[] schematic) {
        int sum = 0;
        int rows = schematic.length;
        int cols = schematic[0].length();

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (Character.isDigit(schematic[i].charAt(j))) {
                    if (isAdjacentToSymbol(schematic, i, j)) {
                        sum += Character.getNumericValue(schematic[i].charAt(j));
                    }
                }
            }
        }

        return sum;
    }

    public static boolean isAdjacentToSymbol(String[] schematic, int row, int col) {
        int[] dr = {-1, -1, -1, 0, 0, 1, 1, 1};
        int[] dc = {-1, 0, 1, -1, 1, -1, 0, 1};
        int rows = schematic.length;
        int cols = schematic[0].length();

        for (int k = 0; k < 8; k++) {
            int newRow = row + dr[k];
            int newCol = col + dc[k];
            if (newRow >= 0 && newRow < rows && newCol >= 0 && newCol < cols) {
                if (schematic[newRow].charAt(newCol) != '.') {
                    return true;
                }
            }
        }

        return false;
    }
}
