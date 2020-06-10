package org.learn;

import java.util.*;

public class DepthFirstSearch {

    private static void depthFirstSearch(Node root) {
        Deque<Node> stack = new LinkedList();
        stack.push(root);
        Node current = null;
        while (!stack.isEmpty()) {
            current = stack.pop();
            if (!current.isVisited()) {
                current.setVisited(true);
                current.print();
                if(null != current.getChildren()) {
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