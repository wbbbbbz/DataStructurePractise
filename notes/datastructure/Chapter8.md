# 第 8 章 优先队列和堆

- 需要比较的数据结构（二叉树，堆等等）都需要 extends Comparable
- 接下来四章都是树的应用
  - 树这种数据结构非常广泛，就是因为只要对数进行一些限制，就可以做出新数据结构
- 优先队列
  - 区别在于出队！
  - 关键是动态。如果是静态那就直接排序即可
  - 因为是动态，（会不断来新的任务）
  - 接口和 queue 一样。只不过出队和队首需要不一样的逻辑！
  - 因为出队需要判断最优先的元素，那么使用有顺序的结构比较好！
  - 线性结构：
    - 普通线性结构：入队 O(1)，出队(拿出最大元素)O(n)
    - 顺序线性结构: 入队 O(1), 出队 O(1)
  - 堆：入出队都是 O(logn)
- 一般来说计算机领域里面出现 logn 可能就是树。就算排序算法里面没有使用树，但是递归里面使用了隐形的树。
- 二叉堆

  - 完全二叉树（节点一定是从左到右存放的！）不是满二叉树！因为元素量不一定满。
  - 堆中某个节点的值总是不大于其父节点（最大堆），当然还有最小堆
  - 但是大小和层次之间没有关系！
  - 因为堆是一个完全二叉树，元素是按顺序排列的，所以底层可以使用数组进行存放
  - parent(i) = i/2，left child(i) = 2\*i, right child(i) = 2\*i +1 (从 1 开始标)
  - parent(i) = (i - 1)/2，left child(i) = 2\*i + 1, right child(i) = 2\*i +2 (从 0 开始标)
  - sift up（上浮）
    - add 的时候，先直接添加到末尾
    - 然后不满足堆的性质的话，就进行 sift up
    - 与父节点进行比较，大的话就交换
  - 取出最大值(Extract Max)
    - 取出最大值（root）之后，将最后一个元素放至堆顶
    - sift down(下沉)
    - 与两个孩子比较，和比较大的进行交换
    - _小心-1/2=0_
  - 因为是一个完全二叉树，所以一定不会退化成链表！

- Replace
  - 取出最大元素后，放入一个新元素
    - 如果 extractMax->add 两次 O(logn)
    - 直接将堆顶替换之后 sift Down，一次 O(logn)
- heapify

  - 将任意数组整理成堆的形状
  - 一个较好的方法：直接将原数组当作 maxHeap
  - 然后对第一个非叶子节点（getParent(getSize())，也就是(最后一个索引-1)/2）
    - 每一个叶子节点就是一个最大堆
  - 直接将 n 个元素逐一插入空堆是 O(nlogn)
  - heapify 的过程算法复杂度是 O(n)
  - 参考[algorithm - How can building a heap be O(n) time complexity? - Stack Overflow](https://stackoverflow.com/questions/9755721/how-can-building-a-heap-be-on-time-complexity)

- 优先队列

  - 基本可以使用队列所有的接口！
  - 数组，顺序线性结构都可以实现优先队列，只是时间不一样！

- d 叉堆

  - 层数少，log 的底变大
  - 但是 siftup 和 siftdown 要考虑的节点变多，所以制衡
  - 可以进行优化

- 索引堆(Index heap)

  - 构建堆的时候需要改变元素的位置，这样有一定的局限性
    - 如果元素结构复杂的话交换的消耗是巨大的
    - 元素索引改变后堆中元素很难进行改变了！因为无法访问了
  - 索引堆把索引和元素分开构建(比如两个数组)。改变的是索引。
    - 索引是 int，交换的效率非常高
    - 可以进行修改
  - 比较的是元素，交换的是 index
  - 但是数据的更改需要进行优化
    - 因为用户传入的 index 实际上是底层 data 的值，这个时候需要在 indexes 中寻找出 indexes[j] = i;
    - O(n)级别的修改
  - 优化：反向查询
    - 再维护一个数组，rev。记录的是反向索引。表示索引 i 在 indexes（堆）中的位置
    - indexes[i] = j, indexes[j] = i
    - indexes[reverse[i]] - i, reverse[indexes[i]] = i
    - 增加和swap的时候需要维护reverse！
      - swap的时候，reverse[indexes[i]] = i reverse[indexes[j]] = j;
    - reverse[i] != -1来判断是否含有该元素！
    - 还有很多地方没有实装完成
      - indexes和reverse的动态扩容
      - 数据的实际出列

- 广义队列

  - 其实只要能入队和出队就是队列
  - 栈也可以理解为一种队列
  - 二分搜索树的非递归前序遍历和层序遍历逻辑一致，数据结构不一样而已

- _面试重点_
  - 在 N 个元素中选出前 M 个元素（M 远小于 N)
  - 排序 NlogN,优先队列 NlogM
  - 最大堆还是可以用于优先队列，因为优先级可以自己订，越小的元素越有优先级别即可

* 课后学习
  - 堆原地排序算法
  - 如何打印成树？
  - 广度优先遍历递归？
  - 最小堆
  - d 叉堆
