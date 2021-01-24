package com.pjw.java.tree;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

/**
 * @author pangjiawei
 * @created 2021-01-24 13:11:59
 */
public class BinarySearchTreeTest {

    @Test
    void test() {
        int[] array = {1, 6, 4, 2, 7, 9, 5, 10, 3, 8};
        BinarySearchTree tree = new BinarySearchTree();
        Arrays.stream(array).forEach(tree::insert);

        int data1 = 5;

        BinaryTree.Node node1 = tree.find(data1);
        Assertions.assertNotNull(node1);
        Assertions.assertEquals(node1.data, data1);
        tree.delete(data1);
        node1 = tree.find(data1);
        Assertions.assertNull(node1);

        tree.insert(6);
        tree.insert(6);
        tree.insert(4);

        tree.inOrder();
    }
}
