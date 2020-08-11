package NowCoder.Nowcoder.Tags.Array;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class code63 {
    public PriorityQueue<Integer> maxQ = new PriorityQueue<>(new Comparator<Integer>() {
        @Override
        public int compare(Integer o1, Integer o2) {
            return o2 - o1;
        }
    });

    public PriorityQueue<Integer> minQ = new PriorityQueue<>(new Comparator<Integer>() {
        @Override
        public int compare(Integer o1, Integer o2) {
            return o1 - o2;
        }
    });

    public void insert(int num){
        if (maxQ.isEmpty()){
            maxQ.add(num);
            return;
        }

        if (num < maxQ.peek()){
            maxQ.add(num);
        }else {
            if (minQ.isEmpty()){
                minQ.add(num);
                return;
            }

            minQ.add(num);
        }

        modify();
    }

    public void modify(){
        if (maxQ.size() - minQ.size() > 1){
            minQ.add(maxQ.poll());
        }else if (minQ.size() - maxQ.size() > 1){
            maxQ.add(minQ.poll());
        }
    }

    public Double getMedian(){
        int maxSize = maxQ.size();
        int minSize = minQ.size();
        Integer max = maxQ.peek();
        Integer min = minQ.peek();

        if ((maxSize + minSize) % 2 == 0){
            return (max + min) / 2.0;
        }else {
            return maxSize > minSize ? max * 1.0 : min * 1.0;
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
