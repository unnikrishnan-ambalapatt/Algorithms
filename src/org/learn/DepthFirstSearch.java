package org.learn;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class DepthFirstSearch {

    private static void depthFirstSearch(Node root) {
        Deque<Node> stack = new LinkedList();
        stack.push(root);
        Node current = null;
        while(!stack.isEmpty()) {
            current = stack.pop();
            if(!current.isVisited()) {
                current.setVisited(true);
                for(Node child : current.getChildren()) {
                    stack.push(child);
                }
            }
        }
    }

    public static void main(String[] args) {

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
}