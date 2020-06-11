package org.learn;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class BreadthFirstSearch {

    /**
     * Breadth-first search (BFS) is an algorithm for traversing or searching tree or graph data structures.
     * <p>
     * Reference: https://en.wikipedia.org/wiki/Breadth-first_search
     *
     * @param root - Root node to start traversal
     */
    private static void breadthFirstSearch(Node root) {

        // Use a first-in first-out data structure
        Deque<Node> queue = new LinkedList();

        root.setVisited(true);
        root.print();

        // Push the root node to queue
        queue.push(root);
        Node current = null;

        // Loop until the queue is empty
        while (!queue.isEmpty()) {

            // Pop the top-most node
            current = queue.pollLast();
            if(null != current.getChildren()) {
                for(Node node : current.getChildren()) {
                    if(!node.isVisited()) {
                        node.setVisited(true);
                        node.print();
                        queue.push(node);
                    }
                }
            }
        }
    }

    private static Node populateGraph(int counter) {
        Node root = new Node(counter++);
        Node node11 = new Node(counter++);
        Node node12 = new Node(counter++);
        List<Node> children1 = Arrays.asList(node11, node12);
        root.setChildren(children1);

        Node node111 = new Node(counter++);
        Node node112 = new Node(counter++);
        List<Node> children11 = Arrays.asList(node111, node112);
        node11.setChildren(children11);

        Node node211 = new Node(counter++);
        Node node212 = new Node(counter++);
        List<Node> children12 = Arrays.asList(node211, node212);
        node12.setChildren(children12);
        return root;
    }

    public static void main(String[] args) {
        Node root = populateGraph(1);
        breadthFirstSearch(root);
    }
}
