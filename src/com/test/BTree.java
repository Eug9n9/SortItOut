package com.test;

public class BTree {
    Node root;

    boolean isBST(Node node, int min, int max) {
        if (node == null) {
            return true;
        }
        if (node.data < min || node.data > max) {
            return false;
        }
        return isBST(node.left, min, node.data - 1) && isBST(node.right, node.data + 1, max);
    }

    public static void main(String args[]) {
        BTree t = new BTree();
        t.root = new Node(4);
        t.root.left = new Node(2);
        t.root.right = new Node(5);
        t.root.left.left = new Node(1);
        t.root.left.right = new Node(3);

        System.out.println("BST " + t.isBST(t.root, Integer.MIN_VALUE, Integer.MAX_VALUE));
    }
}
