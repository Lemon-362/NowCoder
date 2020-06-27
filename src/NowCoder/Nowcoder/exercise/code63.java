package NowCoder.Nowcoder.exercise;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class code63 {
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

    public void insert(int num){
        if (this.maxHeap.isEmpty()){
            this.maxHeap.add(num);
            return;
        }

        if (num <= this.maxHeap.peek()){
            this.maxHeap.add(num);
        }else {
            if (this.minHeap.isEmpty()){
                this.minHeap.add(num);
                return;
            }

            this.minHeap.add(num);
        }

        this.modify();
    }

    public void modify(){
        if (this.maxHeap.size() - this.minHeap.size() > 1){
            this.minHeap.add(this.maxHeap.poll());
        }else if (this.minHeap.size() - this.maxHeap.size() > 1){
            this.maxHeap.add(this.minHeap.poll());
        }
    }

    public Double getMedian(){
        int maxSize = this.maxHeap.size();
        int minSize = this.minHeap.size();
        Integer maxNum = this.maxHeap.peek();
        Integer minNum = this.minHeap.peek();

        if (maxSize + minSize == 0){
            return null;
        }

        if ((maxSize + minSize) % 2 == 0){
            return (maxNum + minNum) / 2.0;
        }else {
            return maxSize > minSize ? maxNum * 1.0 : minNum * 1.0;
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
            code63 median = new code63();

            for (int j = 0; j != arr.length; j++) {
                median.insert(arr[j]);
            }

            if (!median.getMedian().equals(getMedianOfArray(arr))) {
                err = true;
                printArray(arr);
                break;
            }
        }
        System.out.println(err ? "Oops..what a fuck!" : "Nice!");

    }
}
