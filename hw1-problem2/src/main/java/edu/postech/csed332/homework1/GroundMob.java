package edu.postech.csed332.homework1;

import java.util.Map;
import java.util.stream.Stream;

/**
 * A ground monster that moves towards the goal position of the board while satisfying
 * the game board invariants. The class GroundMob implements the interface Monster.
 * <p>
 * TODO: implement all unimplemented methods.
 * Feel free to modify this file, e.g. add new fields or methods. If necessary,
 * you can define a new (abstract) superclass that this class inherits. In this case,
 * some methods can be moved to the abstract class.
 *
 * @see GameBoard#isValidBoard
 */
public class GroundMob implements Monster {

    public GroundMob(GameBoard gameBoard) {
        // TODO implement this
    }

    @Override
    public boolean isGround() {
        // TODO implement this
        return false;
    }

    @Override
    public GameBoard getBoard() {
        // TODO implement this
        return null;
    }

    @Override
    public Position move() {
        // TODO implement this
        return null;
    }
}
