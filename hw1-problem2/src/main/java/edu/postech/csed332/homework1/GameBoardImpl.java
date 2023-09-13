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
    }

    @Override
    public void placeUnit(Unit obj, Position p) {
        // TODO implement this
    }

    @Override
    public void clear() {
        // TODO implement this
    }

    @Override
    public Set<Unit> getUnitsAt(Position p) {
        // TODO implement this
        return Collections.emptySet();
    }

    @Override
    public Position getPosition(Unit obj) {
        // TODO implement this
        return null;
    }

    @Override
    public void step() {
        // TODO implement this
    }

    @Override
    public boolean isValidBoard() {
        // TODO implement this
        return false;
    }

    @Override
    public Set<Monster> getMonsters() {
        // TODO implement this
        return Collections.emptySet();
    }

    @Override
    public Set<Tower> getTowers() {
        // TODO implement this
        return Collections.emptySet();
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
        return 0;
    }

    @Override
    public int getNumMobsEscaped() {
        // TODO implement this
        return 0;
    }
}
