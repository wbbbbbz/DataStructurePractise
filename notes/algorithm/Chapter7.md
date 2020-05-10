# 第 7 章 图的基础

- 节点(Vertex)和边(Edge)进行表达
- 图可以分成无向图(Undirected Graph)和有向图(Directed Graph)
  - 有向图因为不对称,算法比较困难
  - 无向图是一种特殊的有向图
    - 无向图可以认为是双向图
- 图还可以分成无权图(Unweighted Graph)和有权图(Weighted Graph)
  - 边有没有权(数值)
- 图的连通性
  - 不一定图中所有的结点都连接在一起
  - 可以看作单独的图,也可以看作合起来的一张图
- 简单图(Simple Graph),没有自环边和平行边

  - 自环边(self-loop)结点连接自身的边
  - 平行边(parallel-edges)两个节点间有多个边
  - 自环边和平行边会使算法难度增加,但是有时候需要考虑的问题不一定涉及这两种边(比如连通性)

- 图的表示
  - 图的核心是节点和边!
  - 根据边的表示方式分成两种
- 邻接矩阵(Adjacency Matrix)
  - 二维矩阵(n 个节点的话 n\*n 的矩阵)
  - 无向图的话对角线左右对称
  - 有向图也很容易表达
  - 适合表示稠密图(Dense Graph),也就是边比较多
    - 完全图:图中所有的点间都有边
- 邻接表(Adjacency Lists)
  - 每一行存储对应结点相连的节点
  - 每一行都是一个链表
  - 有向图也很容易
  - 存储空间小
  - 适合表示稀疏图(Sparse Graph),也就是边比较少
  - 边的多少应该由现有边数和最大变数的比来判断
  - 遇到平行边的时候需要一定的处理，会消耗性能
    - 添加的时候每一次判断最差是 O(n)
    - 或者添加完所有边之后综合判断
- 遍历邻边-图算法中最常见操作
  - 邻接矩阵: O(V)
  - 邻接表: O(E)
  - 大部分图都是稀疏图，所以邻接表更好
  - 存储的图需要保持 private，开放遍历可以通过迭代器
    - 传出去一个 iterable 的对象，比如 vector
    - iterable 还是能修改？
  - 稀疏图不处理的话平行边可能存在
  - 稀疏图是 O(E)，稠密图是 O(V^2)
    - 所以使用稀疏图还是稠密图取决于Edges和Vertexes的平方的大小关系