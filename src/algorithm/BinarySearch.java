package algorithm;

public class BinarySearch {

    private BinarySearch() {
    }

    // 如果有target，返回index
    // 如果没有，返回-1
    public static int binarySearchNR(Comparable[] arr, Comparable target) {
        // 循环写法
        // 两个index，左闭右闭方式
        // 在[left, right]中进行寻找
        // 如果left==right，那么搜索结束
        int left = 0;
        int right = arr.length - 1;
        // 因为前闭后闭，所以就算等于还有一个元素！
        while (left <= right) {
            // [0,1]则mid=0, [0,2]则mid=1，偏左
            int mid = left + (right - left) / 2;
            if (arr[mid].compareTo(target) == 0)
                return mid;
            if (arr[mid].compareTo(target) < 0)
                left = mid + 1;
            else
                right = mid - 1;
        }
        return -1;
    }

    // 如果有target，返回index
    // 如果没有，返回-1
    public static int binarySearch(Comparable[] arr, Comparable target) {
        return binarySearchR(arr, target, 0, arr.length - 1);
    }

    // 递归写法
    private static int binarySearchR(Comparable[] arr, Comparable target, int left, int right) {
        if (left > right)
            return -1;

        // [0,1]则mid=0, [0,2]则mid=1，偏左
        int mid = left + (right - left) / 2;
        if (arr[mid].compareTo(target) == 0)
            return mid;
        if (arr[mid].compareTo(target) < 0)
            return binarySearchR(arr, target, mid + 1, right);
        return binarySearchR(arr, target, left, mid - 1);
    }

    // 返回target 第一次出现的 index,或者是前一个元素的最后一个 index
    public static int floor(Comparable[] arr, Comparable target){
        
    }
}