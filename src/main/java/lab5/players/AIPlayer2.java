package lab5.players;

/*
circe
 */
import lab5.game.Board;
import lab5.game.Position;
import java.util.List;
import lab5.game.Row;
import lab5.game.Col;

public class AIPlayer2 extends Player {
    public AIPlayer2() {
        super("AIPlayer2");
    }

    @Override
    public Position pickNextMove(Board board) {
        List<Position> emptyCells = board.getEmptyCells();
        if (emptyCells.isEmpty()) return null;

        Position center = new Position(Row.Middle, Col.Middle);

        if (emptyCells.contains(center)) return center;

        return emptyCells.get(0); // Fallback: First available move
    }
}
