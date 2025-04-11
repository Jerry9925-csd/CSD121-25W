package lab4test;

import lab4.game.Board;
import lab4.game.Col;
import lab4.game.Position;
import lab4.game.Row;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class boardtest {

    @Test
    void boardIsInitiallyEmpty() {
        Board board = new Board();
        assertFalse(board.isOccupiedAt(new Position(Row.Top, Col.Middle)));
    }

    @Test
    void placingXOccupiesPosition() {
        Board board = new Board();
        Position pos = new Position(Row.Top, Col.Middle);
        board.placeX(pos);
        assertTrue(board.isOccupiedAt(pos));
    }

    @Test
    void placingOOccupiesPosition() {
        Board board = new Board();
        Position pos = new Position(Row.Bottom, Col.Right);
        board.placeO(pos);
        assertTrue(board.isOccupiedAt(pos));
    }

    @Test
    void occupiedPositionThrowsException() {
        Board board = new Board();
        Position pos = new Position(Row.Top, Col.Middle);
        board.placeX(pos);
        assertThrows(IllegalArgumentException.class, () -> {
            board.placeO(pos);
        });
    }

    @Test
    void boardDetectsDraw() {
        Board board = new Board();
        board.placeX(new Position(Row.Top, Col.Left));
        board.placeO(new Position(Row.Top, Col.Middle));
        board.placeX(new Position(Row.Top, Col.Right));
        board.placeO(new Position(Row.Middle, Col.Left));
        board.placeX(new Position(Row.Middle, Col.Middle));
        board.placeO(new Position(Row.Middle, Col.Right));
        board.placeX(new Position(Row.Bottom, Col.Left));
        board.placeO(new Position(Row.Bottom, Col.Middle));
        board.placeX(new Position(Row.Bottom, Col.Right));
        assertEquals(Board.Status.Draw, board.getStatus());
    }

}
