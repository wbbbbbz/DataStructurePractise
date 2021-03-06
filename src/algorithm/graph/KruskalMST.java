package algorithm.graph;

import java.util.Vector;

import datastructure.*;

public class KruskalMST<Weight extends Number & Comparable> {

    private Vector<Edge<Weight>> mst; // 最小生成树所包含的所有边
    private Number mstWeight; // 最小生成树的权值

    // 构造函数，使用Kruskal算法计算graph的最小生成树
    public KruskalMST(WeightedGraph graph) {
        mst = new Vector<Edge<Weight>>();

        // 将图中的所有边存放到一个最小堆中
        MinHeap<Edge<Weight>> pq = new MinHeap<>(graph.E());
        for (int i = 0; i < graph.V(); i++) {
            for (Object item : graph.adj(i)) {
                Edge<Weight> e = (Edge<Weight>) item;
                // 避免重复加入边
                if (e.v() <= e.w())
                    pq.add(e);
            }
        }

        UnionFind uf = new UnionFindQuickUnionRank(graph.V());
        while (!pq.isEmpty() && mst.size() < graph.V() - 1) {

            // 从最小堆中依次从小到大取出所有的边
            Edge<Weight> e = pq.extractMin();
            // 如果该边的两个端点是联通的, 说明加入这条边将产生环, 扔掉这条边
            // 否则, 将这条边添加进最小生成树, 同时标记边的两个端点联通
            if (!uf.isConnected(e.v(), e.w())) {
                mst.add(e);
                uf.unionElements(e.v(), e.w());
            }
        }

        // 计算最小生成树的权值
        mstWeight = mst.elementAt(0).wt();
        for (int i = 1; i < mst.size(); i++)
            mstWeight = mstWeight.doubleValue() + mst.elementAt(i).wt().doubleValue();
    }

    // 返回最小生成树的所有边
    Vector<Edge<Weight>> mstEdges() {
        return mst;
    }

    // 返回最小生成树的权值
    Number result() {
        return mstWeight;
    }

    // 测试 Kruskal
    public static void main(String[] args) {

        String filename = "testfiles\\testWG1.txt";
        int V = 8;

        SparseWeightedGraph<Double> g = new SparseWeightedGraph<Double>(V, false);
        ReadWeightedGraph readGraph = new ReadWeightedGraph(g, filename);
        g.show();

        // Test Kruskal
        System.out.println("Test Kruskal:");
        KruskalMST<Double> kruskalMST = new KruskalMST<Double>(g);
        Vector<Edge<Double>> mst = kruskalMST.mstEdges();
        for (int i = 0; i < mst.size(); i++)
            System.out.println(mst.elementAt(i));
        System.out.println("The MST weight is: " + kruskalMST.result());

        System.out.println();
    }
}