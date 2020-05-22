package datastructure;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.TreeSet;

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
    private TreeSet<Integer>[] graph;

    public SparseGraph(int vertexes, boolean isDirected) {
        this.vertexes = vertexes;
        this.edges = 0;
        this.isDirected = isDirected;
        this.graph = new TreeSet[vertexes];
        for (int i = 0; i < vertexes; i++)
            graph[i] = new TreeSet<Integer>();
    }

    public SparseGraph(int vertexes) {
        this(vertexes, false);
    }

    public SparseGraph(String filename) {
        this(filename, false);
    }

    public SparseGraph(String filename, boolean directed) {
        File file = new File(filename);
        this.isDirected = directed;
        try (Scanner scanner = new Scanner(file)) {
            vertexes = scanner.nextInt();
            if (vertexes < 0)
                throw new IllegalArgumentException("V must be non-negative!");
            graph = new TreeSet[vertexes];
            for (int i = 0; i < vertexes; i++)
                graph[i] = new TreeSet<>();

            edges = scanner.nextInt();
            if (edges < 0)
                throw new IllegalArgumentException("E must be non-negative!");
            for (int i = 0; i < edges; i++) {
                int v = scanner.nextInt();
                validateVertex(v);
                int w = scanner.nextInt();
                validateVertex(w);

                // 检测自环边和平行边
                if (v == w)
                    throw new IllegalArgumentException("Self Loop is Detected");
                if (graph[v].contains(w))
                    throw new IllegalArgumentException("Parallel Edge is Detected");
                graph[v].add(w);
                if (!isDirected)
                    graph[w].add(v);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

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
        graph[v].add(w);
        // 无向图的话，两边都要修改
        if (v != w && !isDirected)
            graph[w].add(v);

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
        System.out.println(this);

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

        if (graph[v].remove(w))
            edges--;
        if (!isDirected && graph[w].remove(v)) {
            edges--;
        }

    }

    @Override
    public boolean isDirected() {
        return isDirected;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append(String.format("V = %d, E = %d, directed = %b\n", vertexes, edges, isDirected));
        for (int i = 0; i < vertexes; i++) {
            sb.append("vertex " + String.format("%2d", i) + ": ");
            for (int j : adj(i))
                sb.append(String.format("%2d", j) + " ");
            sb.append("\n");
        }
        return sb.toString();
    }

    @Override
    public Object clone() {

        try {
            SparseGraph cloned = (SparseGraph) super.clone();
            cloned.graph = new TreeSet[vertexes];
            for (int i = 0; i < vertexes; i++) {
                cloned.graph[i] = new TreeSet<Integer>();
                for (int v : this.adj(i)) {
                    cloned.graph[i].add(v);
                }
            }
            return cloned;
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }

        return null;

    }

    public static void main(String[] args) {
        SparseGraph sg = new SparseGraph("testfiles\\testG1.txt", true);
        System.out.println(sg);
    }

}