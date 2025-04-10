package lab3;

import lab3.game.Board;
import lab3.ui.Console;

public class Main {

    public static void main(String[] args) {
        Board board = new Board();
        Console console = new Console();
        char currentPlayer = 'X';

        while (true) {
            console.displayBoard(board);
            int[] move = console.getMove(board);

            if (board.placeMove(move[0], move[1], currentPlayer)) {
                if (board.checkWin() != ' ') {
                    console.displayBoard(board);
                    System.out.println(currentPlayer + " wins!");
                    break;
                } else if (board.isDraw()) {
                    console.displayBoard(board);
                    System.out.println("It's a draw!");
                    break;
                }
                currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
            } else {
                System.out.println("Cell occupied. Try again.");
            }
        }
    }
}
