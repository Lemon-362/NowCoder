package NowCoder.basic_class.exercise;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

/*
	哈夫曼编码：
		一块金条切成两半，需要花费和长度数值一样的铜板。求怎么分最省铜板？
		输入一个数组，返回最小代价。

	将数组变为小根堆，每次从堆中取出最小的2个数，求和后放回堆中，再次形成小根堆
 */
public class code36_LessMoney {
    public static int LessMoney(int[] arr) {
        if (arr == null) {
            return 0;
        }
        PriorityQueue<Integer> pQ = new PriorityQueue<>(new minHeapComparator());
        for (int i = 0; i < arr.length; i++) {
            pQ.add(arr[i]);
        }
        int sum = 0;
        int res = 0;
        while (pQ.size() > 1) {
            sum = pQ.poll() + pQ.poll();
            res += sum;
            pQ.add(sum);
        }
        return res;
    }

    public static class minHeapComparator implements Comparator<Integer> {
        public int compare(Integer o1, Integer o2) {
            return o1 - o2;
        }
    }

    public static class maxHeapComparator implements Comparator<Integer> {
        public int compare(Integer o1, Integer o2) {
            return o2 - o1;
        }
    }

    public static void main(String[] args) {
        // solution
        int[] arr = {6, 7, 8, 9};
        System.out.println(LessMoney(arr)); // 60

        int[] arrForHeap = {3, 5, 2, 7, 0, 1, 6, 4};
        // 优先级队列实现小根堆（默认）
        PriorityQueue<Integer> pQ1 = new PriorityQueue<>();
        for (int i = 0; i < arrForHeap.length; i++) {
            pQ1.add(arrForHeap[i]);
        }
        while (!pQ1.isEmpty()) {
            System.out.print(pQ1.poll() + " ");
        }
        System.out.println();

        // 通过自定义比较器作为优先级队列的参数，实现小根堆
        PriorityQueue<Integer> pQ2 = new PriorityQueue<>(new minHeapComparator());
        for (int i = 0; i < arrForHeap.length; i++) {
            pQ2.add(arrForHeap[i]);
        }
        while (!pQ2.isEmpty()) {
            System.out.print(pQ2.poll() + " ");
        }
        System.out.println();

        // 通过自定义比较器作为优先级队列的参数，实现大根堆
        PriorityQueue<Integer> pQ3 = new PriorityQueue<>(new maxHeapComparator());
        for (int i = 0; i < arrForHeap.length; i++) {
            pQ3.add(arrForHeap[i]);
        }
        while (!pQ3.isEmpty()) {
            System.out.print(pQ3.poll() + " ");
        }
        System.out.println();
    }
}
