package advent.utils;

import java.util.List;

public class CharacterGrid {
    private final char[][] grid;

    public CharacterGrid(List<String> lines) {
        int rows = lines.size();
        int cols = lines.get(0).length();
        grid = new char[rows][cols];

        for (int i = 0; i < rows; i++) {
            char[] row = lines.get(i).toCharArray();
            System.arraycopy(row, 0, grid[i], 0, cols);
        }
    }

    public char getChar(int row, int col) {
        return grid[row][col];
    }

    public int getRowCount() {
        return grid.length;
    }

    public int getColumnCount() {
        return grid[0].length;
    }

    public boolean isValidPosition(int row, int col) {
        return row >= 0 && row < getRowCount() && col >= 0 && col < getColumnCount();
    }

    public boolean isSymbol(int row, int col) {
        return isValidPosition(row, col) && grid[row][col] != '.' && !Character.isDigit(grid[row][col]);
    }

    public boolean isPartNumber(int row, int col) {
        return isValidPosition(row, col) && Character.isDigit(grid[row][col]);
    }
}