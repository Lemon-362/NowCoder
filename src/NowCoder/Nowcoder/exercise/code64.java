package NowCoder.Nowcoder.exercise;

import java.util.ArrayList;
import java.util.LinkedList;

public class code64 {
    public static ArrayList<Integer> maxInWindows(int[] arr, int w){
        ArrayList<Integer> res = new ArrayList<>();

        if (arr == null || arr.length < w || w < 1){
            return res;
        }

        LinkedList<Integer> list = new LinkedList<>();

        for (int i = 0; i < arr.length; i++) {

            while (!list.isEmpty() && arr[list.peekLast()]<= arr[i]){
                list.pollLast();
            }

            list.addLast(i);

            if (list.peekFirst() == i - w){
                list.pollFirst();
            }

            if (i >= w - 1){
                res.add(arr[list.peekFirst()]);
            }
        }
        
        return res;
    }

    public static void main(String[] args) {
        int[] arr = {2, 3, 4, 2, 6, 2, 5, 1};
        int w = 3;
        ArrayList<Integer> list = maxInWindows(arr, w);
        for (Integer num : list) {
            System.out.print(num + " "); // 4 4 6 6 6 5
        }
    }
}
