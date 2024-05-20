package advent.day3;

import advent.utils.CharacterGrid;
import data.Stream;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Day3 {
    public static void main(String[] args) {
        try {
            InputStream inputStream = Stream.readPuzzleData();
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

            List<String> lines = new ArrayList<>();
            String line;
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }

            CharacterGrid characterGrid = new CharacterGrid(lines);

            int sum = calculateSum(characterGrid);
            Stream.writeSolution("Sum of all part numbers: " + sum);
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static int calculateSum(CharacterGrid characterGrid) {
        int sum = 0;
        int rows = characterGrid.getRowCount();
        int cols = characterGrid.getColumnCount();

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (characterGrid.isSymbol(i, j)) {
                    int partSum = 0;
                    for (int di = -1; di <= 1; di++) {
                        for (int dj = -1; dj <= 1; dj++) {
                            if (di == 0 && dj == 0) continue;
                            int ni = i + di;
                            int nj = j + dj;
                            if (characterGrid.isPartNumber(ni, nj)) {
                                partSum += characterGrid.getChar(ni, nj) - '0';
                            }
                        }
                    }
                    sum += partSum;
                }
            }
        }
        return sum;
    }
}
