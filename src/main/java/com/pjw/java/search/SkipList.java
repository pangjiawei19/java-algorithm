package com.pjw.java.search;

/**
 * @author pangjiawei
 * @created 2021-01-11 22:41:06
 */
public class SkipList {

    private static final float SKIPLIST_P = 0.5f;
    private static final int MAX_LEVEL = 16;

    private int levelCount = 1;

    private Node head = new Node();  // 带头链表

    public Node find(int value) {
        Node p = head;
        for (int i = levelCount - 1; i >= 0; --i) {
            while (p.forwards[i] != null && p.forwards[i].data < value) {
                p = p.forwards[i];
            }
        }

        if (p.forwards[0] != null && p.forwards[0].data == value) {
            return p.forwards[0];
        } else {
            return null;
        }
    }

    public void insert(int value) {
        int level = randomLevel();
        Node newNode = new Node();
        newNode.data = value;
        newNode.maxLevel = level;
        Node update[] = new Node[level];
        for (int i = 0; i < level; ++i) {
            update[i] = head;
        }

        // 把每一层比给定 value 值小的哪些节点中值最大的那个节点给找出来，放在 update[] 对应的位置
        Node p = head;
        for (int i = level - 1; i >= 0; --i) {
            while (p.forwards[i] != null && p.forwards[i].data < value) { // 每一层从 p（最高层时是 head）开始往后找，直到其后继节点值不存在或者值大于等于给定 value 才停止
                p = p.forwards[i];
            }
            update[i] = p;// 注意这个 for 循环是从高层往底层遍历，即高层找到了指定的 node，再找底层时不需要从头开始遍历，低层仍然从这个 node 继续往后找
        }

        // update[] 中的值都是每层中找出来的节点，这个节点的后继节点不存在或者值大于等于给定 value，因此 newNode 要作为这个节点的后继节点
        for (int i = 0; i < level; ++i) {
            newNode.forwards[i] = update[i].forwards[i];
            update[i].forwards[i] = newNode;
        }

        // 更新层数最大值
        if (levelCount < level) {
            levelCount = level;
        }
    }

    public void delete(int value) {
        Node[] update = new Node[levelCount];
        Node p = head;
        for (int i = levelCount - 1; i >= 0; --i) {
            while (p.forwards[i] != null && p.forwards[i].data < value) {
                p = p.forwards[i];
            }
            update[i] = p;
        }

        if (p.forwards[0] != null && p.forwards[0].data == value) {
            for (int i = levelCount - 1; i >= 0; --i) {
                if (update[i].forwards[i] != null && update[i].forwards[i].data == value) {
                    update[i].forwards[i] = update[i].forwards[i].forwards[i];
                }
            }
        }

        while (levelCount > 1 && head.forwards[levelCount] == null) {
            levelCount--;
        }

    }

    // 理论来讲，一级索引中元素个数应该占原始数据的 50%，二级索引中元素个数占 25%，三级索引12.5% ，一直到最顶层。
    // 因为这里每一层的晋升概率是 50%。对于每一个新插入的节点，都需要调用 randomLevel 生成一个合理的层数。
    // 该 randomLevel 方法会随机生成 1~MAX_LEVEL 之间的数，且 ：
    //        50%的概率返回 1
    //        25%的概率返回 2
    //      12.5%的概率返回 3 ...
    private int randomLevel() {
        int level = 1;

        while (Math.random() < SKIPLIST_P && level < MAX_LEVEL) {
            level += 1;
        }
        return level;
    }

    public void printAll() {
        Node p = head;
        while (p.forwards[0] != null) {
            System.out.print(p.forwards[0] + " ");
            p = p.forwards[0];
        }
        System.out.println();
    }

    public class Node {
        private int data = -1;
        private Node forwards[] = new Node[MAX_LEVEL]; // 链表，记录每一层的下一个节点（比自己值大）
        private int maxLevel = 0;

        @Override
        public String toString() {
            return "{ data: " + data + "; levels: " + maxLevel + " }";
        }
    }

    public static void main(String[] args) {
        SkipList list = new SkipList();
        for (int i = 5; i > 0; i--) {
            list.insert(i);
        }
        for (int i = 6; i <= 10; i++) {
            list.insert(i);
        }

        list.printAll();

        System.out.println(list.find(4));;
    }
}
