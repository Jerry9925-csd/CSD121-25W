package lab3.ui;

import lab3.game.Board;
import java.util.Scanner;

public class Console {
    private final Scanner scanner = new Scanner(System.in);

    public int[] getMove(Board board) {
        while (true) {
            System.out.print("Enter your move (row column): ");
            String rowInput = scanner.next();
            String colInput = scanner.next();

            int[] move = board.parseMove(rowInput, colInput);
            if (move[0] != -1 && move[1] != -1 && board.isValidMove(move[0], move[1])) {
                return move;
            }
            System.out.println("Invalid move. Try again.");
        }
    }

    public void displayBoard(Board board) {
        System.out.println(board);
    }
}