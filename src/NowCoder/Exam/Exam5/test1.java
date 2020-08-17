package NowCoder.Exam.Exam5;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;
import java.util.concurrent.atomic.AtomicInteger;

public class test1 {

    public static AtomicInteger value = new AtomicInteger(0);

    public static int[] test(int a, int b, int k){

        int[] res = new int[k + 1];

        process(a, b, k, 0, res, 0);

        return res;
    }

    public static TreeSet<Integer> set = new TreeSet<>();

    public static void process(int a, int b, int k, int res, int[] arr, int index){
        // base case
        if (k == 0){
            arr[index] = res;
            return;
        }

        process(a, b, k - 1, res + a, arr, index++);
        process(a, b, k - 1, res + b, arr, index);
    }

    public static void main(String[] args) {

        int[] res = test(1, 2, 6);
        for (int i = 0; i < res.length; i++) {
            System.out.println(res[i]);
        }

    }

}
