package NowCoder.advanced_class.exercise;

import java.util.LinkedList;

/*
	滑动窗口内最大值、最小值的更新结构：（双端队列）
		有一个整形数组arr和一个大小为w的窗口从数组的最左边滑到最右边，窗口每次向右滑动一个位置
		如果数组长度为n，窗口大小为w，则一共产生n-w+1个窗口的最大值
		请实现一个函数，输入：整形数组arr，窗口大小w
		输出：一个长度为n-w+1的数组res，res[i]表示每一种窗口状态下的最大值
	例：
		arr=[4, 3, 5, 4, 3, 3, 6, 7]  w=3
		res=[5, 5, 5, 4, 6, 7]
	解法：
	    双端队列，最大值更新结构-->从头到尾是严格从大到小的，相等也不行
	    尾部满足条件进数据，头部到达L时出数据
	    头部始终是窗口内的最大值
	        例如此时窗口R是i，L是i-w
 */
public class code07_SlidingWindowMaxArray {
    public static int[] slidingWindow(int[] arr, int w) {
        if (arr == null || arr.length < 1) {
            return null;
        }

        LinkedList<Integer> list = new LinkedList<>();
        int[] res = new int[arr.length - w + 1];
        int index = 0;

        for (int i = 0; i < arr.length; i++) {
            while (!list.isEmpty() && arr[i] >= arr[list.peekLast()]){
                list.pollLast();
            }
            list.addLast(i);

            if (list.peekFirst() == i - w){
                list.pollFirst();
            }

            if (i >= w - 1){
                res[index++] = arr[list.peekFirst()];
            }
        }

        return res;
    }

    // for test
    public static void printArray(int[] arr) {
        for (int i = 0; i != arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int[] arr = {4, 3, 5, 4, 3, 3, 6, 7};
        int w = 3;
        printArray(slidingWindow(arr, w)); // 5 5 5 4 6 7
    }
}
