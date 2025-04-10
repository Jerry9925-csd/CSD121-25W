package lab3.game;


import java.util.Arrays;

public class Board {
    private final char[][] grid;
    private static final int SIZE = 3;
    private static final char EMPTY = ' ';

    public Board() {
        grid = new char[SIZE][SIZE];
        for (char[] row : grid) {
            Arrays.fill(row, EMPTY);
        }
    }

    public boolean placeMove(int row, int col, char symbol) {
        if (grid[row][col] == EMPTY) {
            grid[row][col] = symbol;
            return true;
        }
        return false; // Position already occupied
    }

    public char checkWin() {
        for (int i = 0; i < SIZE; i++) {
            if (grid[i][0] != EMPTY && grid[i][0] == grid[i][1] && grid[i][1] == grid[i][2]) return grid[i][0]; // Row check
            if (grid[0][i] != EMPTY && grid[0][i] == grid[1][i] && grid[1][i] == grid[2][i]) return grid[0][i]; // Column check
        }
        if (grid[0][0] != EMPTY && grid[0][0] == grid[1][1] && grid[1][1] == grid[2][2]) return grid[0][0]; // Diagonal check
        if (grid[0][2] != EMPTY && grid[0][2] == grid[1][1] && grid[1][1] == grid[2][0]) return grid[0][2]; // Other diagonal

        return EMPTY; // No winner
    }

    public boolean isDraw() {
        for (char[] row : grid) {
            for (char cell : row) {
                if (cell == EMPTY) return false;
            }
        }
        return checkWin() == EMPTY;
    }

    public boolean isValidMove(int row, int col) {
        return row >= 0 && row < SIZE && col >= 0 && col < SIZE && grid[row][col] == EMPTY;
    }

    public int[] parseMove(String rowInput, String colInput) {
        int row = switch (rowInput.toLowerCase()) {
            case "1", "t" -> 0;
            case "2", "m", "c" -> 1;
            case "3", "b" -> 2;
            default -> -1;
        };
        int col = switch (colInput.toLowerCase()) {
            case "1", "l" -> 0;
            case "2", "m", "c" -> 1;
            case "3", "r" -> 2;
            default -> -1;
        };
        return new int[]{row, col};
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (char[] row : grid) {
            sb.append("| ");
            for (char cell : row) {
                sb.append(cell == EMPTY ? "-" : cell).append(" | ");
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}
