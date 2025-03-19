package lab5.players;

import lab5.game.Board;
import lab5.game.Position;
import lab5.game.Row;
import lab5.game.Col;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class AIPlayer2Test {
    private AIPlayer2 aiPlayer;
    private Board board;

    @BeforeEach
    void setUp() {
        aiPlayer = new AIPlayer2();
        board = new Board();
    }

    @Test
    void testPickNextMove_PrioritizesCenter() {
        Position move = aiPlayer.pickNextMove(board);
        assertEquals(new Position(Row.Middle, Col.Middle), move, "AI should prioritize center position");
    }

    @Test
    void testPickNextMove_FirstAvailableMove() {
        // Mark center as occupied
        board.placeNextToken(new Position(Row.Middle, Col.Middle));

        Position move = aiPlayer.pickNextMove(board);

        assertNotNull(move, "AI should pick a valid move");
        assertTrue(board.getEmptyCells().contains(move), "Move should be a valid empty position");
    }

    @Test
    void testPickNextMove_NoAvailableMoves() {
        // Fill the board completely
        for (Position pos : board.getEmptyCells()) {
            board.placeNextToken(pos);
        }

        Position move = aiPlayer.pickNextMove(board);

        assertNull(move, "AI should return null when no moves are available");
    }

}