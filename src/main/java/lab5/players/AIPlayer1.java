package lab5.players;

/*
omola
 */

import lab5.game.Board;
import lab5.game.PlayerToken;
import lab5.game.Position;

import java.util.List;

public class AIPlayer1 extends Player {
    public AIPlayer1() {
        super("AiPlayer1");
    }

    @Override
    public Position pickNextMove(Board board) {
        List<Position> emptyCells = board.getEmptyCells();
        PlayerToken userToken = board.getNextTurnToken();
        PlayerToken opponentToken = (userToken == PlayerToken.X) ? PlayerToken.O : PlayerToken.X;

        // Check for a winning move
        for (Position pos : emptyCells) {
            Board copy = new Board(board);
            copy.placeNextToken(pos);
            if (copy.getWinner()==userToken) return pos;
        }

        // Block opponent’s winning move
        for (Position pos : emptyCells) {
            Board copy = new Board(board);
            copy.placeNextToken(pos);
            if (copy.getWinner()==opponentToken) return pos;
        }

        // Default: Pick the first available move
        return emptyCells.get(0);
    }
}