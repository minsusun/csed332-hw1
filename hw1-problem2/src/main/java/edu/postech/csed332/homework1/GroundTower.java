package edu.postech.csed332.homework1;

import java.util.Set;
import java.util.stream.Collectors;

/**
 * A ground tower that can attack nearby ground monsters within 1 tile. For example,
 * a ground tower at (x,y) can attack all ground monsters at (x-1, y), (x+1, y),
 * (x, y-1), and (x, y+1). The class GroundTower implements the interface Tower.
 * <p>
 * TODO: implement all unimplemented methods.
 * Feel free to modify this file, e.g. add new fields or methods. If necessary,
 * you can define a new (abstract) superclass that this class inherits. In this case,
 * some methods can be moved to the abstract class.
 */
public class GroundTower implements Tower {

    private final GameBoard board;

    public GroundTower(GameBoard gameBoard) {
        // TODO implement this
        this.board = gameBoard;
    }

    @Override
    public Set<Monster> attack() {
        // TODO implement this
        return this.board.getMonsters().stream().filter(monster -> monster.isGround() && this.board.getPosition(monster).getDistance(this.board.getPosition(this)) <= 1).collect(Collectors.toSet());
    }

    @Override
    public GameBoard getBoard() {
        // TODO implement this
        return this.board;
    }
}
