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
    private Map<Position, Integer> visited;

    public GroundMob(GameBoard gameBoard) {
        // TODO implement this
        this.board = gameBoard;
        visited = new HashMap<>();
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
        visited.put(mobPosition, visited.computeIfAbsent(mobPosition, position -> 0) + 1);

        List<Position> candidatePositions = new ArrayList<>();
        List<Position> reservePositions = new ArrayList<>();
        List<Position> availablePositions = new ArrayList<>(Arrays.asList(
                new Position(mobPosition.x() + 1, mobPosition.y()), new Position(mobPosition.x(), mobPosition.y() + 1),
                new Position(mobPosition.x(), mobPosition.y() - 1), new Position(mobPosition.x() - 1, mobPosition.y())
        )).stream().filter(this.board::isValidPosition).toList();

        for(Position position: availablePositions) {
            Set<Unit> units = this.board.getUnitsAt(position);
            Set<Tower> towers = this.board.getTowers();

            if((int) units.stream().filter(Unit::isGround).count() >= 1) continue;

            if(visited.computeIfAbsent(position, p -> 0) > 5) continue;

            if(position.x() == this.board.getWidth() - 1) return position;

            if(towers.stream().anyMatch(tower -> tower instanceof GroundTower && this.board.getPosition(tower).getDistance(position) <= 1))
                reservePositions.add(position);
            else
                candidatePositions.add(position);
        }

        if((int) candidatePositions.stream().filter(position -> position.x() > mobPosition.x()).count() >= 1)
            candidatePositions = candidatePositions.stream().filter(position -> position.x() > mobPosition.x()).collect(Collectors.toList());
        else if(candidatePositions.size() <= 1)
            candidatePositions.addAll(reservePositions);
        candidatePositions.add(new Position(mobPosition.x(), mobPosition.y()));

        return candidatePositions.get(0);
    }
}
