package com.test;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class DFS {
    public void doDFS(Node root) {
        Stack<Node> s = new Stack<>();
        s.add(root);
        while (!s.isEmpty()) {
            Node x = s.pop();
            if(x.right!=null) s.push(x.right);
            if(x.left!=null) s.add(x.left);
            System.out.print(" " + x.data);
        }
    }
    public void doBFS(Node root) {
        Queue<Node> q = new LinkedList<Node>();
        if (root == null)
            return;
        q.add(root);
        while (!q.isEmpty()) {
            Node n = (Node) q.remove();
            System.out.print(" " + n.data);
            if (n.left != null)
                q.add(n.left);
            if (n.right != null)
                q.add(n.right);
        }
    }

    public static void main(String args[]){
        Node root = new Node(1);
        root.left = new Node(2);
        root.left.left = new Node(4);
        root.left.right = new Node(5);
        root.right = new Node(3);
        root.right.left = new Node(6);
        root.right.right = new Node(7);

        DFS d = new DFS();
        System.out.println("Depth-First-Search : ");
        d.doDFS(root);

        System.out.println("\nBreadth-First-Search : ");
        d.doBFS(root);
    }
}
