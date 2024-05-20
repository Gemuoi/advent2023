package advent.day3;

import advent.utils.CharacterGrid;
import advent.utils.Pair;
import advent.utils.Vector2;
import io.PuzzleDataIO;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class Day3 {
    public static void main(String[] args) {
        try {
            InputStream inputStream = PuzzleDataIO.readPuzzleData();
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

            List<String> lines = new ArrayList<>();
            String line;
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }

            CharacterGrid characterGrid = new CharacterGrid(lines);

            int sum = calculateSum(characterGrid);
            PuzzleDataIO.writeSolution("Sum of all part numbers: " + sum);
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static List<Pair<Vector2, Character>> getAdjacentSymbols(IntStream xs, int y, CharacterGrid charGrid) {
        List<Integer> xsList = xs.boxed().toList();
        List<Pair<Vector2, Character>> adjacentSymbols = new ArrayList<>();

        for (int yy = y - 1; yy <= y + 1; yy++) {
            for (int x : xsList) {
                if (yy == y && x == x) {
                    continue;
                }
                if (charGrid.isValidPosition(x, yy)) {
                    char c = charGrid.get(x, yy);
                    if (charGrid.isSymbol(c)) {
                        adjacentSymbols.add(new Pair<>(new Vector2(x, yy), c));
                    }
                }
            }
        }

        for (int dx = -1; dx <= 1; dx++) {
            for (int dy = -1; dy <= 1; dy++) {
                if (dx != 0 || dy != 0) {
                    for (int x : xsList) {
                        int neighborX = x + dx;
                        int neighborY = y + dy;
                        if (charGrid.isValidPosition(neighborX, neighborY)) {
                            char c = charGrid.get(neighborX, neighborY);
                            if (charGrid.isSymbol(c)) {
                                adjacentSymbols.add(new Pair<>(new Vector2(neighborX, neighborY), c));
                            }
                        }
                    }
                }
            }
        }

        return adjacentSymbols;
    }

    private static int calculateSum(CharacterGrid characterGrid) {
        int sum = 0;
        int rows = characterGrid.getRowCount();
        int cols = characterGrid.getColumnCount();

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (characterGrid.isSymbol(i, j)) {
                    List<Pair<Vector2, Character>> adjacentSymbols = getAdjacentSymbols(
                            IntStream.rangeClosed(j - 1, j + 1), i, characterGrid);

                    int partSum = adjacentSymbols.stream()
                            .mapToInt(pair -> characterGrid.isPartNumber(pair.first().getY(), pair.first().getX()) ?
                                    pair.second() - '0' : 0)
                            .sum();

                    sum += partSum;
                }
            }
        }
        return sum;
    }
}
