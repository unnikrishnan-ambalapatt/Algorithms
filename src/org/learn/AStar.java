package org.learn;

import java.util.*;

public class AStar {

    /**
     * A* is an informed search algorithm, or a best-first search used for path-finding problems.
     * <p>
     * Reference: https://en.wikipedia.org/wiki/A*_search_algorithm
     *
     * @param grid        - Grid of nodes to be traversed
     * @param source      - Start node
     * @param destination - End node
     */
    private static void aStar(int[][] grid, Cell source, Cell destination) {

        // Maintain an open list to process the currently found nodes
        List<Cell> openList = new ArrayList<>();

        // The closed list maintains the visited nodes
        List<Cell> closedList = new ArrayList<>();

        // The came-from list maintains a mapping between a node and the node from which this node was reached
        Map<Cell, Cell> cameFrom = new HashMap<>();

        // G value is the distance travelled from source to this node
        source.setG(0d);

        // H value is the estimated distance to destination from this node
        source.setH(heuristics(source, destination));

        // F score is the sum of G and H, which will be the estimated total distance
        source.setF(source.getG() + source.getH());

        // Start with adding the source to open list to be processed
        openList.add(source);

        Cell current = null;

        // Loop until we have some node in open list
        while (!openList.isEmpty()) {

            // From all nodes in open list, find the one which has the lowest F score and move to that node
            current = findLowestFScoreFromOpenList(openList);

            if (current.equals(destination)) {

                // If the current node is the destination, reconstruct the travelled path
                String finalPath = buildPath(cameFrom, current);
                System.out.println("Path from " + source + " to " + destination + ": " + finalPath);
                return;
            } else {

                // If current node is not the destination, remove it from open list and add to closed list
                openList.remove(current);
                closedList.add(current);

                // Find all unvisited (not present in closed list) neighbours of current node
                List<Cell> neighbours = findUnvisitedNeighbours(current, grid, closedList);
                for (Cell neighbour : neighbours) {

                    // Find the distance from current to each neighbour
                    Double distance = distanceBetweenNeighbours(current, neighbour);

                    // Choose the neighbours whose new G score is less than existing G score
                    Double tentativeGScore = current.getG() + distance;
                    if (tentativeGScore < neighbour.getG()) {
                        cameFrom.put(neighbour, current);
                        neighbour.setG(tentativeGScore);
                        neighbour.setF(neighbour.getG() + heuristics(neighbour, destination));
                        if (!openList.contains(neighbour)) {

                            // Add the chosen neighbour to open list
                            openList.add(neighbour);
                        }
                    }
                }
            }
        }
        System.out.println("No path found");
    }

    private static String buildPath(Map<Cell, Cell> cameFrom, Cell current) {
        StringBuilder pathBuilder = new StringBuilder("[" + current.getX() + ", " + current.getY() + "]");

        // Traverse the path from current to source using came-from map
        while (cameFrom.containsKey(current)) {
            current = cameFrom.get(current);

            // Prepend each new value
            pathBuilder.insert(0, "[" + current.getX() + ", " + current.getY() + "]");
        }
        return pathBuilder.toString();
    }

    private static Double distanceBetweenNeighbours(Cell current, Cell neighbour) {
        int x = Math.abs(current.getX() - neighbour.getX());
        int y = Math.abs(current.getY() - neighbour.getY());
        if (x == 1 && y == 1) {
            // Diagonal distance using Pythagoras' theorem considering 1 each for horizontal and vertical distances
            return 1.4;
        } else {
            return 1d;
        }
    }

    private static List<Cell> findUnvisitedNeighbours(Cell cell, int[][] grid, List<Cell> closedList) {
        List<Cell> neighbours = new ArrayList<>();
        Integer newX = null, newY = null;
        if (cell.getX() + 1 < grid.length) {
            // Proceed one node towards right
            newX = cell.getX() + 1;
        }
        if (cell.getY() + 1 < grid[cell.getX()].length) {
            // Proceed one node towards bottom
            newY = cell.getY() + 1;
        }
        populateNeighbours(cell, neighbours, cell.getX(), newY, closedList, grid);
        populateNeighbours(cell, neighbours, newX, cell.getY(), closedList, grid);
        populateNeighbours(cell, neighbours, newX, newY, closedList, grid);
        newX = null;
        newY = null;
        if (cell.getX() - 1 >= 0) {
            // Proceed one node towards left
            newX = cell.getX() - 1;
        }
        if (cell.getY() - 1 >= 0) {
            // Proceed one node towards top
            newY = cell.getY() - 1;
        }
        populateNeighbours(cell, neighbours, cell.getX(), newY, closedList, grid);
        populateNeighbours(cell, neighbours, newX, cell.getY(), closedList, grid);
        populateNeighbours(cell, neighbours, newX, newY, closedList, grid);
        newX = null;
        newY = null;
        // Proceed one node towards right-top
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
        // Proceed one node towards bottom-left
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

    private static void populateNeighbours(Cell cell, List<Cell> neighbours,
                                           Integer newX, Integer newY, List<Cell> closedList, int[][] grid) {
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
        // Heuristics using Manhattan distance
        return (destination.getX() - source.getX()) + (destination.getY() - source.getY());
    }

    public static void main(String[] args) {
        int[][] grid = {
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

    Cell(int x, int y) {
        this.x = x;
        this.y = y;
        // Initialize each node with maximum G and F scores so that the first calculated scores will be a better value
        this.g = Double.MAX_VALUE;
        this.f = Double.MAX_VALUE;
    }

    Cell(int x, int y, int value) {
        this.x = x;
        this.y = y;
        this.g = Double.MAX_VALUE;
        this.f = Double.MAX_VALUE;
        this.value = value;
    }

    int getX() {
        return x;
    }

    void setX(int x) {
        this.x = x;
    }

    int getY() {
        return y;
    }

    void setY(int y) {
        this.y = y;
    }

    Double getG() {
        return g;
    }

    void setG(Double g) {
        this.g = g;
    }

    int getH() {
        return h;
    }

    void setH(int h) {
        this.h = h;
    }

    Double getF() {
        return f;
    }

    void setF(Double f) {
        this.f = f;
    }

    int getValue() {
        return value;
    }

    void setValue(int value) {
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

    @Override
    public String toString() {
        return "{" + x + ", " + y + "}";
    }
}