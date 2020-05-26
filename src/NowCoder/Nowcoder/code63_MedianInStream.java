package NowCoder.Nowcoder;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/*
    数据流中的中位数：
        如果从数据流中读出奇数个数值，那么中位数就是所有数值排序之后位于中间的数值。
        如果从数据流中读出偶数个数值，那么中位数就是所有数值排序之后中间两个数的平均值。
        我们使用Insert()方法读取数据流，使用GetMedian()方法获取当前读取数据的中位数。
 */
public class code63_MedianInStream {

	/*
		TODO 思路：通过大根堆和小根堆实现
			大根堆存较小的n/2个数，小根堆存较大的n/2个数
			1. 如果新加的数 <= 大根堆堆顶，则进大根堆；否则进小根堆
			2. 进入后，如果两个堆的元素个数差值 > 1，则从较多的堆中弹出栈顶，放入另一个堆中
	 */

    public PriorityQueue<Integer> maxHeap = new PriorityQueue<>(new Comparator<Integer>() {
        @Override
        public int compare(Integer o1, Integer o2) {
            return o2 - o1;
        }
    });

    public PriorityQueue<Integer> minHeap = new PriorityQueue<>(new Comparator<Integer>() {
        @Override
        public int compare(Integer o1, Integer o2) {
            return o1 - o2;
        }
    });

    public void modify() {
        if (this.maxHeap.size() - this.minHeap.size() > 1) {
            this.minHeap.add(this.maxHeap.poll());
        }
        if (this.minHeap.size() - this.maxHeap.size() > 1) {
            this.maxHeap.add(this.minHeap.poll());
        }
    }

    public void Insert(Integer num) {
        if (this.maxHeap.isEmpty()) {
            this.maxHeap.add(num);
            return;
        }

        if (num <= this.maxHeap.peek()) {
            this.maxHeap.add(num);
        } else {

            if (this.minHeap.isEmpty()) {
                this.minHeap.add(num);
                return;
            }

            this.minHeap.add(num);
        }

        this.modify();
    }

    public Double GetMedian() {
        int maxHeapSize = this.maxHeap.size();
        int minHeapSize = this.minHeap.size();

        Integer maxHeapPeek = this.maxHeap.peek();
        Integer minHeapPeak = this.minHeap.peek();

        if (maxHeapSize + minHeapSize == 0) {
            return null;
        }

        if ((maxHeapSize + minHeapSize) % 2 == 0) {
            return (maxHeapPeek + minHeapPeak) / 2.0;
        } else {
            return maxHeapSize > minHeapSize ? maxHeapPeek * 1.0 : minHeapPeak * 1.0;
        }
    }

    // for test
    public static int[] getRandomArray(int maxLen, int maxValue) {
        int[] res = new int[(int) (Math.random() * maxLen) + 1];
        for (int i = 0; i != res.length; i++) {
            res[i] = (int) (Math.random() * maxValue);
        }
        return res;
    }

    // for test, this method is ineffective but absolutely right
    public static Double getMedianOfArray(int[] arr) {
        int[] newArr = Arrays.copyOf(arr, arr.length);
        Arrays.sort(newArr);
        int mid = (newArr.length - 1) / 2;
        if ((newArr.length & 1) == 0) {
            return (newArr[mid] + newArr[mid + 1]) / 2.0;
        } else {
            return newArr[mid] * 1.0;
        }
    }

    public static void printArray(int[] arr) {
        for (int i = 0; i != arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        boolean err = false;
        int testTimes = 200000;
        for (int i = 0; i != testTimes; i++) {
            int len = 30;
            int maxValue = 1000;
            int[] arr = getRandomArray(len, maxValue);
            code63_MedianInStream median = new code63_MedianInStream();

            for (int j = 0; j != arr.length; j++) {
                median.Insert(arr[j]);
            }

            if (!median.GetMedian().equals(getMedianOfArray(arr))) {
                err = true;
                printArray(arr);
                break;
            }
        }
        System.out.println(err ? "Oops..what a fuck!" : "Nice!");

    }

}
