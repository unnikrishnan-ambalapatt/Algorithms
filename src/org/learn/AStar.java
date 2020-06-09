package org.learn;

import java.time.Year;
import java.util.*;

public class AStar {
    public static void main(String[] args) {
        int grid[][] = {
                {1, 0, 1, 1, 1, 1, 0, 1, 1, 1},
                {1, 1, 1, 0, 1, 1, 1, 0, 1, 1},
                {1, 1, 1, 0, 1, 1, 0, 1, 0, 1},
                {0, 0, 1, 0, 1, 0, 0, 0, 0, 1},
                {1, 1, 1, 0, 1, 1, 1, 0, 1, 0},
                {1, 0, 1, 1, 1, 1, 0, 1, 0, 0},
                {1, 0, 0, 0, 0, 1, 0, 0, 0, 1},
                {1, 0, 1, 1, 1, 1, 0, 1, 1, 1},
                {1, 1, 1, 0, 0, 0, 1, 0, 0, 1}
        };

        Cell source = new Cell(0, 0);
        Cell destination = new Cell(9, 8);
        aStar(grid, source, destination);
    }

    private static void aStar(int[][] grid, Cell source, Cell destination) {
        List<Cell> openList = new ArrayList<>();
        List<Cell> closedList = new ArrayList<>();
        openList.add(source);
        Map<Cell, Cell> cameFrom = new HashMap<>();
        source.setG(0);
        source.setH(heuristics(source, destination));
        source.setF(source.getG() + source.getH());
        Cell current = null;
        while (!openList.isEmpty()) {
            current = findLowestFScoreFromOpenList(openList);
            if (current.equals(destination)) {
                //Reconstruct the path
                break;
            } else {
                openList.remove(current);
                //Find all neighbours of 'current'
                List<Cell> neighbours = findAllNeighbours(current, grid);
                for(Cell neighbour: neighbours) {

                }
            }
        }
    }

    private static List<Cell> findAllNeighbours(Cell cell, int [][] grid) {
        List<Cell> neighbours = new ArrayList<>();
        Integer newX = null, newY = null;
        if(cell.getX() + 1 < grid.length) {
            newX = cell.getX() + 1;
        }
        if(cell.getY() + 1 < grid[cell.getX()].length) {
            newY = cell.getY() + 1;
        }
        populateNeighbours(cell, neighbours, newX, newY);
        newX = null;
        newY = null;
        if(cell.getX() - 1 >= 0) {
            newX = cell.getX() - 1;
        }
        if(cell.getY() - 1 >= 0) {
            newY = cell.getY() - 1;
        }
        populateNeighbours(cell, neighbours, newX, newY);
        newX = null;
        newY = null;
        if(cell.getX() + 1 < grid.length) {
            newX = cell.getX() + 1;
        }
        if(cell.getY() - 1 >= 0) {
            newY = cell.getY() - 1;
        }
        populateNeighbours(cell, neighbours, newX, newY);
        newX = null;
        newY = null;
        if(cell.getX() - 1 >= 0) {
            newX = cell.getX() - 1;
        }
        if(cell.getY() + 1 < grid[cell.getX()].length) {
            newY = cell.getY() + 1;
        }
        populateNeighbours(cell, (List<Cell>) neighbours, newX, newY);
        return neighbours;
    }

    private static void populateNeighbours(Cell cell, List<Cell> neighbours, Integer newX, Integer newY) {
        if (null != newX) {
            if (null != newY) {
                Cell neighbour = new Cell(newX, newY);
                neighbours.add(neighbour);
            } else {
                Cell neighbour = new Cell(newX, cell.getY());
                neighbours.add(neighbour);
            }
        } else {
            if (null != newY) {
                Cell neighbour = new Cell(cell.getX(), newY);
                neighbours.add(neighbour);
            }
        }
    }

    private static Cell findLowestFScoreFromOpenList(List<Cell> openList) {
        Cell lowestFScoreCell = null;
        int lowestFScore = Integer.MAX_VALUE;
        for (Cell cell : openList) {
            if (cell.getF() < lowestFScore) {
                lowestFScore = cell.getF();
                lowestFScoreCell = cell;
            }
        }
        return lowestFScoreCell;
    }

    private static int heuristics(Cell source, Cell destination) {
        return (destination.getX() - source.getX()) + (destination.getY() - destination.getY());
    }
}

class Cell {

    private int x;
    private int y;
    private int g;
    private int h;
    private int f;

    public Cell(int x, int y) {
        this.x = x;
        this.y = y;
        this.g = Integer.MAX_VALUE;
        this.f = Integer.MAX_VALUE;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getG() {
        return g;
    }

    public void setG(int g) {
        this.g = g;
    }

    public int getH() {
        return h;
    }

    public void setH(int h) {
        this.h = h;
    }

    public int getF() {
        return f;
    }

    public void setF(int f) {
        this.f = f;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cell cell = (Cell) o;
        return getX() == cell.getX() &&
                getY() == cell.getY();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getX(), getY());
    }
}