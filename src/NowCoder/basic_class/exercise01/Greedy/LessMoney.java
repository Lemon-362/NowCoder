package NowCoder.basic_class.exercise01.Greedy;

import java.util.Comparator;
import java.util.PriorityQueue;

public class LessMoney {
    public static int method(int[] arr) {
        if (arr == null) {
            return 0;
        }

        PriorityQueue<Integer> pQ = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1 - o2;
            }
        });

        for (int i = 0; i < arr.length; i++) {
            pQ.add(arr[i]);
        }

        int res = 0;

        while (pQ.size() > 1){
            int sum = pQ.poll() + pQ.poll();

            res += sum;

            pQ.add(sum);
        }

        return res;
    }

    public static void main(String[] args) {
        // solution
        int[] arr = {6, 7, 8, 9};
        System.out.println(method(arr)); // 60
    }
}
