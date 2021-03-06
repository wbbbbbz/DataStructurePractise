package datastructure;

public class PrintableMaxHeap extends MaxHeap<Integer> {

    public PrintableMaxHeap(int capacity) {
        super(capacity);
    }

    public PrintableMaxHeap() {
        super();
    }

    // 以树状打印整个堆结构
    public void treePrint() {

        if (getSize() >= 100) {
            System.out.println("This print function can only work for less than 100 integer");
            return;
        }

        System.out.println("The max heap size is: " + getSize());
        System.out.println("Data in the max heap: ");
        for (int i = 0; i < getSize(); i++) {
            // 我们的print函数要求堆中的所有整数在[0, 100)的范围内
            assert (Integer) get(i) >= 0 && (Integer) get(i) < 100;
            System.out.print(get(i) + " ");
        }
        System.out.println();
        System.out.println();

        int n = getSize();
        int maxLevel = 0;
        int numberPerLevel = 1;
        while (n > 0) {
            maxLevel += 1;
            n -= numberPerLevel;
            numberPerLevel *= 2;
        }

        int maxLevelNumber = (int) Math.pow(2, maxLevel - 1);
        int curTreeMaxLevelNumber = maxLevelNumber;
        int index = 0;
        for (int level = 0; level < maxLevel; level++) {

            String line1 = new String(new char[maxLevelNumber * 3 - 1]).replace('\0', ' ');

            int curLevelNumber = Math.min(getSize() - (int) Math.pow(2, level) + 1, (int) Math.pow(2, level));
            boolean isLeft = true;
            for (int indexCurLevel = 0; indexCurLevel < curLevelNumber; index++, indexCurLevel++) {
                line1 = putNumberInLine((Integer) get(index), line1, indexCurLevel, curTreeMaxLevelNumber * 3 - 1,
                        isLeft);
                isLeft = !isLeft;
            }
            System.out.println(line1);

            if (level == maxLevel - 1)
                break;

            String line2 = new String(new char[maxLevelNumber * 3 - 1]).replace('\0', ' ');
            for (int indexCurLevel = 0; indexCurLevel < curLevelNumber; indexCurLevel++)
                line2 = putBranchInLine(line2, indexCurLevel, curTreeMaxLevelNumber * 3 - 1);
            System.out.println(line2);

            curTreeMaxLevelNumber /= 2;
        }
    }

    private String putNumberInLine(Integer num, String line, int indexCurLevel, int curTreeWidth, boolean isLeft) {

        int subTreeWidth = (curTreeWidth - 1) / 2;
        int offset = indexCurLevel * (curTreeWidth + 1) + subTreeWidth;
        assert offset + 1 < line.length();
        if (num >= 10)
            line = line.substring(0, offset + 0) + num.toString() + line.substring(offset + 2);
        else {
            if (isLeft)
                line = line.substring(0, offset + 0) + num.toString() + line.substring(offset + 1);
            else
                line = line.substring(0, offset + 1) + num.toString() + line.substring(offset + 2);
        }
        return line;
    }

    private String putBranchInLine(String line, int indexCurLevel, int curTreeWidth) {

        int subTreeWidth = (curTreeWidth - 1) / 2;
        int subSubTreeWidth = (subTreeWidth - 1) / 2;
        int offsetLeft = indexCurLevel * (curTreeWidth + 1) + subSubTreeWidth;
        assert offsetLeft + 1 < line.length();
        int offsetRight = indexCurLevel * (curTreeWidth + 1) + subTreeWidth + 1 + subSubTreeWidth;
        assert offsetRight < line.length();

        line = line.substring(0, offsetLeft + 1) + "/" + line.substring(offsetLeft + 2);
        line = line.substring(0, offsetRight) + "\\" + line.substring(offsetRight + 1);

        return line;
    }

    // 测试 PrintableMaxHeap
    public static void main(String[] args) {

        PrintableMaxHeap maxHeap = new PrintableMaxHeap(100);
        int N = 63; // 堆中元素个数
        int M = 100; // 堆中元素取值范围[0, M)
        for (int i = 0; i < N; i++)
            maxHeap.add(Integer.valueOf((int) (Math.random() * M)));
        maxHeap.treePrint();

    }
}