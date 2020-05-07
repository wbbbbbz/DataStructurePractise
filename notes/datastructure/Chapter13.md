# 第 13 章 红黑树

- 红黑树每一个节点有红色或者黑色

  - 二分搜索树，保证平衡
  - 根节点是黑色的(因为 3 节点分解的时候一定是分解到子节点)
  - 最后的空节点是黑色的(这是定义！定义 null 是黑色的)
  - 节点是红色的话，孩子节点都是黑色的(只是孩子，不是所有子树)
    - 黑色节点的右孩子一定是黑色节点
  - 任意一个节点到叶子节点经过的黑色节点数是一样的
    - 因为 2-3 树是绝对平衡的。也就是节点是一样多的。
    - 因为 2-3 树的节点一定会生成一个黑色节点。
    - “黑平衡”的二叉树。严格意义上不是平衡二叉树。黑色节点高度保持绝对平衡
    - 最大高度：2logn。每一个黑节点与红节点相间。O(logn)
    - 最大高度比 AVL 高，所以查询比 AVL 慢
    - 但是红黑树添加和删除比较快。如果数据经常更新：使用红黑树。只用查询：AVL 树
  - 不会退化成链表

- 算法 4 教材作者就是红黑树创始人
- TAOCP

- 红黑树和 2-3 树是等价的

- 2-3 树满足二分搜索书的基本性质，但是不是普通二叉树

  - 有两种节点，每一个节点可以存放一个或两个元素
  - 两个元素的节点:left < l, l < mid < r, right > r
  - 2 个孩子是 2 节点，3 个孩子是 3 节点
  - 2-3 树是一棵绝对平衡的树
    - 到任意一个叶子经过的路径树相等
    - 是由插入的性质得来的

- 2-3 树是如何维持绝对平衡的

  - 添加节点永远不会添加到空节点中
    - 融合到找到的最后一个叶子节点上
    - 如果已经是 3 节点了，那就先暂时融合到 4 节点，再进行分裂成 2 分搜索树
    - 如果叶子节点已经是 4 节点的话，分裂之后不一定平衡
      - 这个时候该节点向上融合，左右孩子直接带上去，变成中间和左或右节点。
      - 如果向上融合之后父节点成为了 4 节点的话，父节点也进行分裂即可。
    - 如果插入 2 节点
      - 融合成 3 节点
    - 插入 3 节点
      - 如果父节点为 2 节点
        - 融合到父节点
      - 如果父节点为 3 节点
        - 融合到父节点
        - 父节点拆分

- 红黑树

  - 黑节点就是 2-3 树的 2 节点
  - 3 节点用的是两个节点，子节点是红色
    - 3 节点的左 l,右 r 可以写作 r.left = l
    - 这样 l 是红色
    - 因为正常二叉树每一个节点只有一个父节点，所以这样两个节点之间的特殊关系可以放入节点本身中
    - 定义所有红色节点都是向左倾斜的(3 节点的处理方法)
  - _添加节点_

    - 默认创建节点是红色的！
    - 因为 2-3 树中添加一定添加到非空节点，进行融合。所以红黑树中新插入节点默认是红的！
    - 如果添加第一个节点的时候，根节点是红色的，需要把根节点变成黑色
      - 这个步骤以后也需要，因为融合到子节点的时候，向上融合，再拆分的话父节点就会保留红色，以此类推。知道没有父节点，此时就是根节点。所以根节点还是会变成红色的。这个时候就要把根节点变成黑色
    - 空节点 null 默认为黑色
    - 插入节点,插入红色到黑色左边 OK
    - 插入红色(x)到黑色(y)右边
      - 经过左旋转使红色节点的左节点是黑色节点(x.left = y)
      - 左旋转的颜色需要维护!(x = y.color, y.color = RED)),因为 y 现在是 2-3 树中对应的节点了
      - 左旋转不需要考虑维持红黑树性质,还需要后续操作!
    - 如果相当于向 2-3 树中三节点中添加新元素应该怎么办?

      - 向(black.left = red1)的两个节点里面插入 red2 > black 怎么办
      - 只需要改变颜色,子节点为黑,根节点为红.因为根节点需要向上融合，所以为红
      - 所有节点的颜色都反转了.颜色翻转 flipColors
      - 红色根节点需要向上融合

      - 向(black.left = red1)的两个节点里面插入 red2 < red1 怎么办
      - black 为根进行右旋转
      - 旋转后 red1 为根，颜色和 black 一样。
      - 旋转后 black 因为是类似于 2-3 树中从父节点分裂出来的，所以变红
      - 最终是根黑，子红
      - 最后进行颜色翻转

    - 一条逻辑链条可以串起所有更新的操作(视频)
      - [30 张图带你彻底理解红黑树 - 简书](https://www.jianshu.com/p/e136ec79235c)
      - 维护时机和 AVL 树一样。向上回溯进行维护

```java
        if (isRed(node.right) && !isRed(node.left))
            node = leftRotate(node);
        if (isRed(node.left) && isRed(node.left.left))
            node = rightRotate(node);
        if (isRed(node.left) && isRed(node.right))
            flipColors(node);
```

- 红黑树最大可能是 2logN，所以只是查询的话并不必 AVL 优势大
  - 红黑树的优势是添加或者删除，所以查询，添加加上删除一起的话稍稍占优势

- 性能:
  - 完全随机的数据的话普通二分搜索书就很好(因为没有平衡等操作)
    - 极端情况退化成链表
  - 查询较多的话AVL树很好
  - 红黑树牺牲了平衡新(2logn的高度)
    - 但是统计性能更优(综合增删改查所有操作)，平均情况更好
      - 所以一般语言内部的有序映射底层都是红黑树


- _面试_

  - 红黑树基本数概念问题。性能，优缺点，原理。

- 课后学习

  - 添加节点的逻辑
  - 删除节点
  - 现在实现的是左倾红黑树，还有右倾红黑树
  - 另一种统计性能优秀的树结构: Splay Tree(伸展树)
    - 局部性原理: 刚被访问的内容下次高概率被再次访问
  - java的TreeMap和TreeSet基于红黑树