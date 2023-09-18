package edu.postech.csed332.homework1;

import java.util.*;
import java.util.stream.Collectors;
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

    private final GameBoard board;

    public GroundMob(GameBoard gameBoard) {
        // TODO implement this
        this.board = gameBoard;
    }

    @Override
    public boolean isGround() {
        // TODO implement this
        return true;
    }

    @Override
    public GameBoard getBoard() {
        // TODO implement this
        return this.board;
    }

    @Override
    public Position move() {
        // TODO implement this
        Position mobPosition = this.board.getPosition(this);
        List<Position> candidatePositions = new ArrayList<>(){{ add(new Position(mobPosition.x(), mobPosition.y())); }};
        List<Position> reservePositions = new ArrayList<>();
        Set<Position> availablePositions = new HashSet<>(Arrays.asList(
                new Position(mobPosition.x() + 1, mobPosition.y()), new Position(mobPosition.x(), mobPosition.y() + 1),
                new Position(mobPosition.x() - 1, mobPosition.y()), new Position(mobPosition.x(), mobPosition.y() - 1)
        )).stream().filter(this.board::isValidPosition).collect(Collectors.toSet());
        for(Position position: availablePositions) {
            Set<Unit> units = this.board.getUnitsAt(position);
            Set<Tower> towers = this.board.getTowers();

            if((int) units.stream().filter(Unit::isGround).count() >= 1) continue;

            if(towers.stream().anyMatch(tower -> this.board.getPosition(tower).getDistance(position) <= 1))
                reservePositions.add(position);
            else
                candidatePositions.add(position);
        }
        if(candidatePositions.size() <= 1)
            candidatePositions.addAll(reservePositions);
        Collections.shuffle(candidatePositions);
        return candidatePositions.get(0);
    }
}
