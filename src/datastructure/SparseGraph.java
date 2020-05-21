package datastructure;

import java.util.LinkedList;

// 不考虑自环边
// 允许平行边
public class SparseGraph implements Graph {

    public static final boolean DIRECTED = true;
    public static final boolean UNDIRECTED = false;
    // vertexes: 点数，edges: 边数
    private int vertexes, edges;
    // true: 有向图
    private boolean isDirected;
    // 邻接矩阵: true代表连接
    private LinkedList[] graph;

    public SparseGraph(int vertexes, boolean isDirected) {
        this.vertexes = vertexes;
        this.edges = 0;
        this.isDirected = isDirected;
        this.graph = new LinkedList[vertexes];
        for (int i = 0; i < vertexes; i++)
            graph[i] = new LinkedList<Integer>();
    }

    public int getVertexes() {
        return this.vertexes;
    }

    public int getEdges() {
        return this.edges;
    }

    // 传入v和w两个顶点
    public void addEdge(int v, int w) {
        validateVertex(v);
        validateVertex(w);

        // 允许平行边
        graph[v].addFirst(w);
        // 无向图的话，两边都要修改
        if (v != w && !isDirected)
            graph[w].addFirst(v);

        edges++;
    }

    public boolean hasEdge(int v, int w) {
        validateVertex(v);
        validateVertex(w);
        return graph[v].contains(w);
    }

    public void validateVertex(int v) {
        if (v < 0 || v >= vertexes)
            throw new IllegalArgumentException("vertex " + v + " is invalid");
    }

    // 返回图中一个顶点的所有邻边
    // 由于java使用引用机制，返回一个Vector不会带来额外开销,
    public Iterable<Integer> adj(int v) {
        validateVertex(v);
        return graph[v];
    }

    @Override
    public int V() {
        return vertexes;
    }

    @Override
    public int E() {
        return edges;
    }

    @Override
    public void show() {
        for (int i = 0; i < vertexes; i++) {
            System.out.print("vertex " + String.format("%02d", i) + ": ");
            for (int j = 0; j < graph[i].size(); j++)
                System.out.print(String.format("%02d", graph[i].get(j)) + " ");
            System.out.println();
        }

    }

    @Override
    public int degree(int v) {
        validateVertex(v);
        return graph[v].size();
    }

    @Override
    public void removeEdge(int v, int w) {
        validateVertex(v);
        validateVertex(w);

        if (graph[v].contains(w))
            graph[v].remove(w);
        if (graph[w].contains(v))
            graph[w].remove(v);

    }

}