# 第 3 章 高级排序算法

> ## 参考[常见的排序算法——常见的 10 种排序 - sc_FlyingDreams - 博客园](https://www.cnblogs.com/flyingdreams/p/11161157.html)

- 归并排序 Merge Sort
  - 不断分半，分到最小单位之后进行归并！
  - 关键就是归并的时候如何做到 n 级别！
    - 归并排序的归并的时候，额外使用一个 O(n)级别的存储空间会比较好
  - 所以归并排序会用掉 O(n)级别的存储空间！
    - 先将需要归并的元素存档，然后对其排序也行，对原来的数组排序也行
    - 因为有多个 index，每一个都要好好判断数组越界的可能性！
  - 归并排序的优化：
    - 如果归并时左半部分的最大值已经小于右半部分的最小值，那么就不需要进行 merge 操作了！
      - 在数组近乎有序时非常有用
    - 递归到比较小的元素的情况，可以直接使用插入排序！
      - 元素数据小的时候近乎有序的概率会变大
      - 插入排序的常数系数要比归并排序小！
      - 这个边界值可以优化！
  - 递归是自顶到底
    - 可以自底到顶，通过迭代方式即可！
    - 第一层循环增加 size
    - 第二层循环每一次增加 2 个 size，进行判断！
      - 并且 merge 的时候必须保证有两个部分，也就是 mid+1"<"length
      - 同时右边界需要判断和最后一个元素的大小！min(right, length-1)
    - _自底到顶虽然统计意义上效率底，但是因为没有使用随机访问，所以可以对链表进行排序！_
- 弄清楚每一个索引的定义有助于更清晰的写出逻辑！
- 前闭后闭，还是开，需要定义好！
- 高级排序的递归都有优化的空间，就是递归到底的情况使用插入排序
- 快速排序

  - 非常优秀！
  - 核心就是 partition，将一个元素移到相应位置并且左右两边大小关系正确！
  - 但是不进行优化的话，对近乎有序的数据效率不好
    - 因为 partition 出来的结果可能倾斜
    - 可以进行随机选取 pivot 来避免这个问题
  - 如果存在的键值有大量重复的话，会导致 partition 不平衡！
    - 解决方案 1：partition 分成两边，左边<=v, 右边>=v
      - 但是边界条件不要使用<=或者>=，会导致不平衡，相等就交换也行
    - 解决方案 2: partition 直接分成三部分，<=>
      - Quick sort 3 ways
  - 一般系统级别快速排序都是用 quick sort 3 ways
    - 因为重复元素的优势很大，而且一般元素的时候也不慢

- merge sort 和 quick sort 都是分治算法(divide and conquer)

  - merge sort 是分的简单，和的复杂
  - quick sort 是分的复杂，和的简单

- 逆序对问题：随意抽取一个数对，看是顺序还是逆序

  - 有序数列逆序对为 0，逆序数列逆序对为最大值
  - 暴力解法是 O(n^2)，Merge Sort 思路可以达到 O(nlogn)
    - 关键是归并部分，在排序的同时进行逆序对计数就好！
    - 从右半部分提取的元素就包含逆序对
      - 注意不要重复计算。如果右边算了一次左边就不要算了

- 取第 n 大元素

  - 还是 quick sort，只不过每一次有一半的部分不需要进行 quick sort 了！
  - 可以达到 O(n)

- 课后学习
  - Shell Sort 虽然慢于高级的排序方式, 但仍然是非常有竞争力的一种排序算法
    - 其所花费的时间完全在可以容忍的范围内, 远不像 O(n^2)的排序算法, 在数据量较大的时候无法忍受
    - 同时, Shell Sort 实现简单, 只使用循环的方式解决排序问题, 不需要实现递归, 不占用系统占空间, 也不依赖随机数
    - 所以, 如果算法实现所使用的环境不利于实现复杂的排序算法, 或者在项目工程的测试阶段, 完全可以暂时使用 Shell Sort 来进行排序任务:)
