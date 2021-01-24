package com.pjw.java.tree;

/**
 * @author pangjiawei
 * @created 2021-01-22 16:30:58
 */
public abstract class BinaryTree {

    protected Node tree;

    public abstract Node find(int data);

    public abstract void insert(int data);

    public abstract void delete(int data);

    public void preOrder() {
        preOrderInner(tree);
    }

    private void preOrderInner(Node node) {
        if (node == null) {
            return;
        }
        System.out.println(node.data);
        preOrderInner(node.left);
        preOrderInner(node.right);
    }

    public void inOrder() {
        inOrderInner(tree);
    }

    private void inOrderInner(Node node) {
        if (node == null) {
            return;
        }
        inOrderInner(node.left);
        System.out.println(node.data);
        inOrderInner(node.right);
    }

    public void postOrder() {
        postOrderInner(tree);
    }

    private void postOrderInner(Node node) {
        if (node == null) {
            return;
        }
        preOrderInner(node.left);
        preOrderInner(node.right);
        System.out.println(node.data);
    }


    public static class Node {
        protected int data;
        protected Node left;
        protected Node right;

        public Node(int data) {
            this.data = data;
        }
    }
}
