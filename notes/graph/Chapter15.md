# 第 15 章 匹配算法

- 最大匹配和完全匹配
  - 基于无向图
  - 一个二分图(二部图)
  - 匹配问题是选边，选中的边不能有重复的顶点
  - 最大匹配是最大能匹配多少对，但是匹配方式不一定只有一种
  - 匹配所有顶点就是完全匹配，完全匹配一定是最大匹配
  - 求出最大匹配后，只要看看最大匹配数和原图的边数的关系即可得出是否完美匹配

- 使用最大流算法解决匹配问题
  - 新设一个源点，新设一个汇点
  - 源点连接上二分图的一部分，另外一部分连接到汇点
  - 原先无向图的部分全部改为有向边，方向是被源点指向的是起头，指向汇点的是汇点
  - 所有边的容量都定为1
  - 最大流即为最大匹配数
  - 1个流量其实就是一个匹配(因为平衡限制)
  - 把无向图问题转换为有向图问题

- 匈牙利算法
  - 不借助有向图，不借助网络流
  - 左部分中找出未匹配的点，在右部分找，如果没匹配，那就可以算一个匹配
    - 左部分再找未匹配的点，寻找的时候如果发现了已匹配的点，那就顺着匹配边来到另外一个匹配点，最后找到一个未匹配的点。
    - 拆掉原来的匹配边，变成两个匹配边
    - 非匹配-匹配-非匹配变成匹配-非匹配-匹配
      - 中间的匹配组可能不止一个，但是原理是一样的，拆匹配再合成匹配
  - 本质：从左侧的一个非匹配点出发，来到右侧，从右侧向左走永远走匹配边，
    - 匹配边和非匹配边交替出现: 交替路
    - 如果能终止于另外一个非匹配点: 增广路径
      - 一定是偶数个顶点，奇数条边，非匹配-匹配-非匹配-匹配...-非匹配。非匹配 = 匹配+1
        - 然后匹配边和非匹配边交换即可
      - 一个路径，最大匹配数+1
    - 对左侧每一个尚为匹配的点，不断地寻找可以增广的交替路
    - 寻找路径使用BFS和DFS都行
      - 改进：来到右边的点不需要寻路
      - 队列中只存储左边的点即可
  - 时间复杂度是O(V\*E)
  - DFS比较简单
  



- 拓展
  - 力扣中文站LCP 4