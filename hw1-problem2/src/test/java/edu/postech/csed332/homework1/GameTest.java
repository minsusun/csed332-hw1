package edu.postech.csed332.homework1;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class GameTest {

    @Test
    void testGameBoard() {
        var board = new GameBoardImpl(5, 3);
        Assertions.assertEquals(5, board.getWidth());
        Assertions.assertEquals(3, board.getHeight());
    }

    @Test
    void testPlaceUnit() {
        var board = new GameBoardImpl(5, 3);
        var mob = new GroundMob(board);
        var pos = new Position(0, 0);

        board.placeUnit(mob, pos);
        Assertions.assertTrue(board.getUnitsAt(pos).contains(mob));
        Assertions.assertEquals(1, board.getNumMobs());
    }

    @Test
    void testPlaceUnitInvalid() {
        var board = new GameBoardImpl(5, 3);
        var mob = new GroundMob(board);
        var pos = new Position(5, 0);

        Assertions.assertThrows(IllegalArgumentException.class, () -> board.placeUnit(mob, pos));
    }

    @Test
    void testPlaceTwoUnits() {
        var board = new GameBoardImpl(5, 3);
        var mob = new AirMob(board);
        var tower = new GroundTower(board);
        var position = new Position(0, 0);

        board.placeUnit(mob, position);
        board.placeUnit(tower, position);
        Assertions.assertTrue(board.getUnitsAt(position).contains(mob));
        Assertions.assertTrue(board.getUnitsAt(position).contains(tower));
        Assertions.assertEquals(1, board.getNumMobs());
        Assertions.assertEquals(1, board.getNumTowers());
    }

    @Test
    void testGroundAttack() {
        var board = new GameBoardImpl(5, 3);
        var tower = new GroundTower(board);
        var mob = new GroundMob(board);
        var pos1 = new Position(1, 1);
        var pos2 = new Position(1, 2);

        board.placeUnit(tower, pos1);
        board.placeUnit(mob, pos2);
        Assertions.assertTrue(tower.attack().contains(mob));
    }
}
