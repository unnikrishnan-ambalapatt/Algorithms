package org.learn;

import java.util.*;

public class AStar {

    private static void aStar(int[][] grid, Cell source, Cell destination) {
        List<Cell> openList = new ArrayList<>();
        List<Cell> closedList = new ArrayList<>();
        openList.add(source);
        Map<Cell, Cell> cameFrom = new HashMap<>();
        source.setG(0d);
        source.setH(heuristics(source, destination));
        source.setF(source.getG() + source.getH());
        Cell current = null;
        while (!openList.isEmpty()) {
            current = findLowestFScoreFromOpenList(openList);
            if (current.equals(destination)) {
                //Reconstruct the path
                String finalPath = printPath(cameFrom, current);
                System.out.println("finalPath: " + finalPath);
                return;
            } else {
                openList.remove(current);
                closedList.add(current);
                //Find all neighbours of 'current'
                List<Cell> neighbours = findUnvisitedNeighbours(current, grid, closedList);
                for (Cell neighbour : neighbours) {
                    Double distance = distanceBetweenNeighbours(current, neighbour);
                    Double tentativeGScore = current.getG() + distance;
                    if (tentativeGScore < neighbour.getG()) {
                        cameFrom.put(neighbour, current);
                        neighbour.setG(tentativeGScore);
                        neighbour.setF(neighbour.getG() + heuristics(neighbour, destination));
                        if (!openList.contains(neighbour)) {
                            openList.add(neighbour);
                        }
                    }
                }
            }
        }
        System.out.println("No path found");
    }

    private static String printPath(Map<Cell, Cell> cameFrom, Cell current) {
        String fullPath = "[" + current.getX() + ", " + current.getY() + "]";
        while (cameFrom.containsKey(current)) {
            current = cameFrom.get(current);
            fullPath = "[" + current.getX() + ", " + current.getY() + "]" + fullPath;
        }
        return fullPath;
    }

    private static Double distanceBetweenNeighbours(Cell current, Cell neighbour) {
        Integer x = Math.abs(current.getX() - neighbour.getX());
        Integer y = Math.abs(current.getY() - neighbour.getY());
        if (x == 1 && y == 1) {
            return 1.4;
        } else {
            return 1d;
        }
    }

    private static List<Cell> findUnvisitedNeighbours(Cell cell, int[][] grid, List<Cell> closedList) {
        List<Cell> neighbours = new ArrayList<>();
        Integer newX = null, newY = null;
        if (cell.getX() + 1 < grid.length) {
            newX = cell.getX() + 1;
        }
        if (cell.getY() + 1 < grid[cell.getX()].length) {
            newY = cell.getY() + 1;
        }
        populateNeighbours(cell, neighbours, cell.getX(), newY, closedList, grid);
        populateNeighbours(cell, neighbours, newX, cell.getY(), closedList, grid);
        populateNeighbours(cell, neighbours, newX, newY, closedList, grid);
        newX = null;
        newY = null;
        if (cell.getX() - 1 >= 0) {
            newX = cell.getX() - 1;
        }
        if (cell.getY() - 1 >= 0) {
            newY = cell.getY() - 1;
        }
        populateNeighbours(cell, neighbours, cell.getX(), newY, closedList, grid);
        populateNeighbours(cell, neighbours, newX, cell.getY(), closedList, grid);
        populateNeighbours(cell, neighbours, newX, newY, closedList, grid);
        newX = null;
        newY = null;
        if (cell.getX() + 1 < grid.length) {
            newX = cell.getX() + 1;
        }
        if (cell.getY() - 1 >= 0) {
            newY = cell.getY() - 1;
        }
        populateNeighbours(cell, neighbours, cell.getX(), newY, closedList, grid);
        populateNeighbours(cell, neighbours, newX, cell.getY(), closedList, grid);
        populateNeighbours(cell, neighbours, newX, newY, closedList, grid);
        newX = null;
        newY = null;
        if (cell.getX() - 1 >= 0) {
            newX = cell.getX() - 1;
        }
        if (cell.getY() + 1 < grid[cell.getX()].length) {
            newY = cell.getY() + 1;
        }
        populateNeighbours(cell, neighbours, cell.getX(), newY, closedList, grid);
        populateNeighbours(cell, neighbours, newX, cell.getY(), closedList, grid);
        populateNeighbours(cell, neighbours, newX, newY, closedList, grid);
        return neighbours;
    }

    private static void populateNeighbours(Cell cell, List<Cell> neighbours, Integer newX, Integer newY, List<Cell> closedList, int[][] grid) {
        if (null != newX) {
            if (null != newY) {
                Cell neighbour = new Cell(newX, newY, getGridValue(grid, newX, newY));
                if (!closedList.contains(neighbour) && neighbour.getValue() == 1) {
                    neighbours.add(neighbour);
                }
            } else {
                Cell neighbour = new Cell(newX, cell.getY(), getGridValue(grid, newX, cell.getY()));
                if (!closedList.contains(neighbour) && neighbour.getValue() == 1) {
                    neighbours.add(neighbour);
                }
            }
        } else {
            if (null != newY) {
                Cell neighbour = new Cell(cell.getX(), newY, getGridValue(grid, cell.getX(), newY));
                if (!closedList.contains(neighbour) && neighbour.getValue() == 1) {
                    neighbours.add(neighbour);
                }
            }
        }
    }

    private static int getGridValue(int[][] grid, Integer x, Integer y) {
        return grid[x][y];
    }

    private static Cell findLowestFScoreFromOpenList(List<Cell> openList) {
        Cell lowestFScoreCell = null;
        Double lowestFScore = Double.MAX_VALUE;
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

    public static void main(String[] args) {
        int grid[][] = {
                {1, 0, 1, 1, 1, 1, 0, 1, 1, 1},
                {1, 1, 1, 0, 1, 1, 1, 0, 1, 1},
                {1, 1, 1, 0, 1, 1, 0, 1, 0, 1},
                {0, 0, 1, 0, 1, 0, 0, 0, 0, 1},
                {1, 1, 1, 0, 1, 1, 1, 0, 1, 0},
                {1, 0, 1, 1, 1, 1, 0, 1, 0, 0},
                {1, 0, 0, 0, 0, 1, 0, 0, 0, 1},
                {1, 0, 1, 1, 1, 1, 1, 1, 1, 1},
                {1, 1, 1, 0, 0, 0, 1, 0, 0, 1}
        };

        Cell source = new Cell(0, 0);
        Cell destination = new Cell(8, 9);
//        Cell destination = new Cell(3, 2);
        aStar(grid, source, destination);
    }
}

class Cell {

    private int x;
    private int y;
    private Double g;
    private int h;
    private Double f;
    private int value;

    public Cell(int x, int y) {
        this.x = x;
        this.y = y;
        this.g = Double.MAX_VALUE;
        this.f = Double.MAX_VALUE;
    }

    public Cell(int x, int y, int value) {
        this.x = x;
        this.y = y;
        this.g = Double.MAX_VALUE;
        this.f = Double.MAX_VALUE;
        this.value = value;
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

    public Double getG() {
        return g;
    }

    public void setG(Double g) {
        this.g = g;
    }

    public int getH() {
        return h;
    }

    public void setH(int h) {
        this.h = h;
    }

    public Double getF() {
        return f;
    }

    public void setF(Double f) {
        this.f = f;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
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