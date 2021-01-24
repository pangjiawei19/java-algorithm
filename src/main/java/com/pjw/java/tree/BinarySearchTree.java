package com.pjw.java.tree;

/**
 * @author pangjiawei
 * @created 2021-01-22 08:42:56
 */
public class BinarySearchTree extends BinaryTree {

    @Override
    public Node find(int data) {
        Node node = tree;
        while (node != null) {
            if (node.data == data) {
                return node;
            } else if (node.data > data) {
                node = node.left;
            } else {
                node = node.right;
            }
        }
        return null;
    }

    @Override
    public void insert(int data) {
        if (tree == null) {
            tree = new Node(data);
            return;
        }

        Node node = tree;
        while (node != null) {
            if (data >= node.data) {
                if (node.right == null) {
                    node.right = new Node(data);
                    return;
                } else {
                    node = node.right;
                }
            } else {
                if (node.left == null) {
                    node.left = new Node(data);
                    return;
                } else {
                    node = node.left;
                }
            }
        }
    }

    @Override
    public void delete(int data) {
        while (true) {
            boolean findNode = deleteInner(data);
            if (!findNode) {
                break;
            }
        }
    }

    private boolean deleteInner(int data) {
        // 先找到这个节点及其父节点
        Node p = tree;
        Node pp = null;
        while (p != null && p.data != data) {
            pp = p;
            if (data > p.data) {
                p = p.right;
            } else {
                p = p.left;
            }
        }

        if (p == null) {
            // 未找到对应节点
            return false;
        }

        /*
         * 该节点有两个子节点，找到该节点右子树中的最小节点，把这个最小节点的值赋给这个结点，然后删除这个最小节点
         */
        if (p.left != null && p.right != null) {
            Node minP = p.right; // 右子树最小节点
            Node minPP = p; // 右子树最小节点的父节点
            while (minP.left != null) {
                minPP = minP;
                minP = minP.left;
            }
            p.data = minP.data; // 赋值
            p = minP; // 后面要删除最小节点，直接把引用赋值给 p，进行后续处理，因为最小节点一定没有左子节点
            pp = minPP;
        }

        // 该节点没有子节点或只有一个子节点
        Node child = null;
        if (p.left != null) {
            child = p.left;
        } else if (p.right != null) {
            child = p.right;
        }

        if (pp == null) {
            // 删除根节点
            tree = child;
        } else if (pp.left == p) {
            pp.left = child;
        } else {
            pp.right = child;
        }

        return true;
    }

    public static void main(String[] args) {
        BinarySearchTree tree = new BinarySearchTree();
        for (int i = 10; i > 0; i--) {
            tree.insert(i);
        }

        tree.insert(6);
        tree.insert(6);
        tree.insert(4);

        tree.delete(3);
        tree.delete(6);

        tree.inOrder();
    }
}
