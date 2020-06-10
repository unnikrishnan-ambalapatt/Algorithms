package org.learn;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class DepthFirstSearch {

    /**
     * Depth-first search (DFS) is an algorithm for traversing or searching tree or graph data structures.
     * <p>
     * Reference: https://en.wikipedia.org/wiki/Depth-first_search
     *
     * @param root - Root node to start traversal
     */
    private static void depthFirstSearch(Node root) {

        // Use a first-in last-out data structure
        Deque<Node> stack = new LinkedList();

        // Push the root node to stack
        stack.push(root);
        Node current = null;

        // Loop until the stack is empty
        while (!stack.isEmpty()) {

            // Pop the top-most node
            current = stack.pop();

            // Check if it is visited: This is important in graphs as graphs can have loops
            // If it is guaranteed to be tree, this check and the corresponding state can be avoided
            if (!current.isVisited()) {
                current.setVisited(true);

                // Print the newly visited node
                current.print();
                if (null != current.getChildren()) {
                    // If current node has children, retrieve all of them and push them to stack
                    for (Node child : current.getChildren()) {
                        stack.push(child);
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        int counter = 1;
        Node root = new Node(1);
        Node node11 = new Node(2);
        Node node12 = new Node(3);
        List<Node> children1 = Arrays.asList(node11, node12);
        root.setChildren(children1);

        Node node111 = new Node(4);
        Node node112 = new Node(5);
        List<Node> children11 = Arrays.asList(node111, node112);
        node11.setChildren(children11);

        Node node211 = new Node(6);
        Node node212 = new Node(7);
        List<Node> children12 = Arrays.asList(node211, node212);
        node12.setChildren(children12);

        depthFirstSearch(root);

    }
}

class Node {
    private int value;
    private boolean isVisited;
    private List<Node> children;

    public Node(int value) {
        this.value = value;
        this.setVisited(false);
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public boolean isVisited() {
        return isVisited;
    }

    public void setVisited(boolean visited) {
        isVisited = visited;
    }

    public List<Node> getChildren() {
        return children;
    }

    public void setChildren(List<Node> children) {
        this.children = children;
    }

    public void print() {
        System.out.println("[" + this.value + "]");
    }
}