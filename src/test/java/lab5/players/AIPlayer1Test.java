package lab5.players;

import lab5.game.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

class AIPlayer1Test{

    private AIPlayer1 aiPlayer;
    private Board board;

    @BeforeEach
    void setUp() {
        aiPlayer = new AIPlayer1();
        board = new Board();
    }
    @Test
    void testPickNextMove_WinningMove() {
        // Set up board where AI can win
        board.placeNextToken(new Position(Row.Top, Col.Left));
        board.placeNextToken(new Position(Row.Top, Col.Middle));

        Position move = aiPlayer.pickNextMove(board);

        assertEquals(new Position(Row.Top, Col.Right), move, "AI should pick winning move");
    }

    @Test
    void testPickNextMove_FirstAvailableMove() {
        // Empty board, AI should pick first available move
        Position move = aiPlayer.pickNextMove(board);

        assertNotNull(move, "AI should pick a valid move");
        assertTrue(board.getEmptyCells().contains(move), "Move should be a valid empty position");
    }
}