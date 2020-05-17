package NowCoder.basic_class.exercise.exercise02;

import java.util.Comparator;
import java.util.PriorityQueue;

public class code36 {
    public static int LessMoney(int[] arr) {
        if (arr == null || arr.length < 1) {
            return 0;
        }

        PriorityQueue<Integer> pQ = new PriorityQueue<>();
        for (int i = 0; i < arr.length; i++) {
            pQ.add(arr[i]);
        }

        int res = 0;
        int sum = 0;
        while (pQ.size() > 1){
            sum = pQ.poll() + pQ.poll();

            res += sum;

            pQ.add(sum);
        }

        return res;
    }

    public static class MyComparator implements Comparator<Integer> {

        @Override
        public int compare(Integer o1, Integer o2) {
            return o1 - o2;
        }
    }

    public static void main(String[] args) {
        int[] arr = {6, 7, 8, 9};
        System.out.println(LessMoney(arr)); // 60

    }
}
