package com.pjw.java.graph;

import java.util.LinkedList;

/**
 * 有项有权图 - 用于 Astar 寻路
 *
 * @author pangjiawei
 * @created 2021-08-08 13:59:00
 */
public class AstarGraph {

    private LinkedList<Edge> adj[]; // 邻接表
    private int v; // 顶点个数
    private Vertex[] vertexes;

    public AstarGraph(int v) {
        this.v = v;
        this.adj = new LinkedList[v];
        this.vertexes = new Vertex[v];
        for (int i = 0; i < v; i++) {
            this.adj[i] = new LinkedList<>();
        }
    }

    public void addEdge(int s, int t, int w) {
        this.adj[s].add(new Edge(s, t, w));
    }

    /**
     * 边
     */
    private class Edge {
        public int sid; // 边的起始顶点编号
        public int tid; // 边的终止顶点编号
        public int w; // 权重

        public Edge(int sid, int tid, int w) {
            this.sid = sid;
            this.tid = tid;
            this.w = w;
        }
    }

    /**
     * 顶点
     */
    private class Vertex {
        public int id; // 顶点编号
        public int dist; // 起始顶点到这个顶点的距离，也就是g(i)
        public int f; // f(i)=g(i)+h(i)
        public int x; // 地图坐标 x
        public int y; // 地图坐标 y

        public Vertex(int id, int x, int y) {
            this.id = id;
            this.x = x;
            this.y = y;
            this.f = Integer.MAX_VALUE;
            this.dist = Integer.MAX_VALUE;
        }
    }

    /**
     * 优先级队列
     */
    private class PriorityQueue { // 根据vertex.f构建小顶堆
        private Vertex[] nodes;
        private int count;

        public PriorityQueue(int v) {
            this.nodes = new Vertex[v + 1];
            this.count = 0;
        }

        public Vertex poll() {
            Vertex top = nodes[1];

            nodes[1] = nodes[count]; // 将堆尾数据放到堆顶
            this.count--;

            int i = 1; // 从堆顶节点开始
            while (true) {
                int minIndex = i;

                // 先和左子节点比较
                if (i * 2 <= count && nodes[minIndex].f > nodes[i * 2].f) {
                    minIndex = i * 2;
                }

                // 再和右子节点比较
                if (i * 2 + 1 <= count && nodes[minIndex].f > nodes[i * 2 + 1].f) {
                    minIndex = i * 2 + 1;
                }

                // 如果没有子节点比自己小，退出循环
                if (minIndex == i) {
                    break;
                }

                // 和比自己小的子节点交换
                this.swap(i, minIndex);

                i = minIndex; // 从比自己小的子节点开始继续判断
            }
//            System.out.println("pool get -> " + top.id);
            return top;
        }

        public void add(Vertex vertex) {
            System.out.println("pool add -> " + vertex.id);
            // 将数据插到堆尾
            this.count++;
            this.nodes[count] = vertex;

            // 自下向上进行堆化
            this.heapify(count);
        }

        // 更新结点的值，并且从下往上堆化，重新符合堆的定义。时间复杂度O(logn)。
        public void update(Vertex vertex) {
            int index = 0;
            for (int i = 1; i < nodes.length; i++) {
                Vertex node = nodes[i];
                if (node.id == vertex.id) {
                    node.dist = vertex.dist;
                    node.f = vertex.f;
                    index = i;
                    break;
                }
            }

            if (index > 0) {
                this.heapify(index);
            }
        }

        public boolean isEmpty() {
            return count < 1;
        }

        public void clear() {
            this.count = 0;
        }

        /**
         * 自下向上进行堆化
         */
        private void heapify(int index) {
            int i = index;
            while (i / 2 > 0 && nodes[i].f < nodes[i / 2].f) {
                this.swap(i, i / 2);
                i = i / 2;
            }
        }

        /**
         * 交换节点
         */
        private void swap(int index1, int index2) {
            Vertex tmp = nodes[index1];
            nodes[index1] = nodes[index2];
            nodes[index2] = tmp;
        }
    }

    public void addVertex(int id, int x, int y) {
        vertexes[id] = new Vertex(id, x, y);
    }


    private int hManhattan(Vertex v1, Vertex v2) { // Vertex表示顶点，后面有定义
        return Math.abs(v1.x - v2.x) + Math.abs(v1.y - v2.y);
    }

    public void astar(int s, int t) { // 从顶点s到顶点t的路径
        int[] predecessor = new int[this.v]; // 用来还原路径
        // 按照vertex的f值构建的小顶堆，而不是按照dist
        PriorityQueue queue = new PriorityQueue(this.v);
        boolean[] inQueue = new boolean[this.v]; // 标记是否进入过队列
        vertexes[s].dist = 0;
        vertexes[s].f = 0;
        queue.add(vertexes[s]);
        inQueue[s] = true;
        while (!queue.isEmpty()) {
            Vertex minVertex = queue.poll(); // 取堆顶元素并删除
            for (int i = 0; i < adj[minVertex.id].size(); ++i) {
                Edge e = adj[minVertex.id].get(i); // 取出一条minVertex相连的边
                Vertex nextVertex = vertexes[e.tid]; // minVertex-->nextVertex
                if (minVertex.dist + e.w < nextVertex.dist) { // 更新next的dist,f
                    nextVertex.dist = minVertex.dist + e.w;
                    nextVertex.f = nextVertex.dist + hManhattan(nextVertex, vertexes[t]);
                    predecessor[nextVertex.id] = minVertex.id;
                    if (inQueue[nextVertex.id]) {
                        queue.update(nextVertex);
                    } else {
                        queue.add(nextVertex);
                        inQueue[nextVertex.id] = true;
                    }
                }
                if (nextVertex.id == t) { // 只要到达t就可以结束while了
                    queue.clear(); // 清空queue，才能推出while循环
                    break;
                }
            }
        }
        // 输出路径
        System.out.print(s);
        print(s, t, predecessor);
    }

    private void print(int s, int t, int[] predecessor) {
        if (s == t) return;
        print(s, predecessor[t], predecessor);
        System.out.print("->" + t);
    }

    public static void main(String[] args) {
        /*
         *   10   10   10
         * 0 -- 1 -- 2 -- 3
         * |10            |10
         * 4 -- 8 -- 5 -- 6 -- 7 -- 9
         *   10   10   10   10   10
         */

        AstarGraph graph = new AstarGraph(10);
        graph.addVertex(0,0,0);
        graph.addVertex(1,10,0);
        graph.addVertex(2,20,0);
        graph.addVertex(3,30,0);
        graph.addVertex(4,0,10);
        graph.addVertex(5,20,10);
        graph.addVertex(6,30,10);
        graph.addVertex(7,40,10);
        graph.addVertex(8,10,10);
        graph.addVertex(9,50,10);

        graph.addEdge(0, 1, 1);
        graph.addEdge(1, 2, 1);
        graph.addEdge(2, 3, 1);
        graph.addEdge(3, 6, 1);
        graph.addEdge(4, 0, 1);
        graph.addEdge(8, 4, 1);
        graph.addEdge(5, 8, 1);
        graph.addEdge(5, 6, 1);
        graph.addEdge(6, 7, 1);
        graph.addEdge(7, 9, 1);

        graph.astar(5, 9);
    }
}
