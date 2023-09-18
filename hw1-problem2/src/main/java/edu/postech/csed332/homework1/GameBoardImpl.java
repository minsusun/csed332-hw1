package edu.postech.csed332.homework1;

import java.util.*;
import java.util.stream.Collectors;

/**
 * An implementation of GameBoard that implements the interface GameBoard.
 * <p>
 * TODO: implements all the unimplemented methods (marked with TODO)
 * NOTE: Do not modify the signature of public methods (which are used for GUI
 * and grading). But feel free to add new fields or new private methods as needed.
 */
class GameBoardImpl implements GameBoard {
    private final int width, height;

    // TODO add more fields to implement this class
    private final Map<Position, Set<Unit>> board;
    private final Set<Position> goals;
    private int nMobsKilled;
    private int nMobsEscaped;

    /**
     * Create a game board with a given width and height
     *
     * @param width  of this board
     * @param height of this board
     */
    public GameBoardImpl(int width, int height) {
        this.width = width;
        this.height = height;

        // TODO add more statements if necessary
        this.board = new HashMap<>();
        this.goals = new HashSet<>();
        for(int y = 0; y < this.width; y++) this.goals.add(new Position(this.width - 1, y));

        this.clear();
    }

    @Override
    public void placeUnit(Unit obj, Position p) {
        // TODO implement this
        if(!this.isValidPosition(p))
            throw new IllegalArgumentException("Invalid Position");

        if(!this.board.containsKey(p))
            this.board.put(p, new HashSet<>());
        this.board.get(p).add(obj);
    }

    @Override
    public void clear() {
        // TODO implement this
        this.board.clear();
        this.nMobsKilled = 0;
        this.nMobsEscaped = 0;

        // initiate for goals
        for(Position position: this.goals) this.board.put(position, new HashSet<>());
    }

    @Override
    public Set<Unit> getUnitsAt(Position p) {
        // TODO implement this
        return this.board.getOrDefault(p, Collections.emptySet());
    }

    @Override
    public Position getPosition(Unit obj) {
        // TODO implement this
        for(Map.Entry<Position, Set<Unit>> entry: this.board.entrySet()){
            if(entry.getValue().contains(obj))
                return entry.getKey();
        }
        return null;
    }

    @Override
    public void step() {
        // TODO implement this
        // 1. All monsters on the green tiles at the right end “escape” from the game board.
        for(Position position: this.goals) {
            this.nMobsEscaped += this.board.get(position).size();
            this.board.get(position).clear();
        }

        // 2. Each tower attacks (and removes) all adjacent monsters of its type.
        for(Tower tower: this.getTowers()) tower.attack().forEach(monster -> {
            this.board.get(this.getPosition(monster)).remove(monster);
            nMobsKilled++;
        });

        // 3. The remaining monsters can move to an adjacent tile (or stay put).
        for(Monster monster: this.getMonsters()) {
            Position originalPosition = this.getPosition(monster);
            Position nextPosition = monster.move();
            if(!originalPosition.equals(nextPosition)){
                this.board.computeIfAbsent(nextPosition, position -> new HashSet<>()).add(monster);
                this.board.get(originalPosition).remove(monster);
            }
        }
    }

    @Override
    public boolean isValidBoard() {
        // TODO implement this
        for(Map.Entry<Position, Set<Unit>> entry: this.board.entrySet()) {
            Position position = entry.getKey();
            Set<Unit> units = entry.getValue();

            // (a) All units are in the boundaries.
            if(!units.isEmpty() && !isValidPosition(position)) return false;

            // (b) Different ground units cannot be on the same tile.
            if(units.stream().filter(Unit::isGround).count() > 1) return false;

            // (c) Different air units cannot be on the same tile.
            if(units.stream().filter(unit -> !unit.isGround()).count() > 1) return false;
        }
        return true;
    }

    @Override
    public Set<Monster> getMonsters() {
        // TODO implement this
        return this.board.entrySet().stream().flatMap(entry -> entry.getValue().stream()).filter(unit -> unit instanceof Monster).map(unit -> (Monster) unit).collect(Collectors.toSet());
    }

    @Override
    public Set<Tower> getTowers() {
        // TODO implement this
        return this.board.entrySet().stream().flatMap(entry -> entry.getValue().stream()).filter(unit -> unit instanceof Tower).map(unit -> (Tower) unit).collect(Collectors.toSet());
    }

    @Override
    public int getWidth() {
        return width;
    }

    @Override
    public int getHeight() {
        return height;
    }

    @Override
    public int getNumMobsKilled() {
        // TODO implement this
        return this.nMobsKilled;
    }

    @Override
    public int getNumMobsEscaped() {
        // TODO implement this
        return this.nMobsEscaped;
    }
}
