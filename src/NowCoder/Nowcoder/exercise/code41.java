package NowCoder.Nowcoder.exercise;

import java.util.ArrayList;

public class code41 {
    public static ArrayList<ArrayList<Integer>> findContinuousArr(int target) {
        ArrayList<ArrayList<Integer>> res = new ArrayList<>();

        int sum = 0;
        int a = 1;
        int b = 1;

        while (a <= target / 2){
            if (sum < target){
                sum += b;
                b++;
            }else if (sum > target){
                sum -= a;
                a++;
            }else {
                ArrayList<Integer> list = new ArrayList<>();
                for (int i = a; i < b; i++) {
                    list.add(i);
                }
                res.add(list);

                sum -= a;
                a++;
            }
        }

        return res;
    }

    public static void main(String[] args) {
        ArrayList<ArrayList<Integer>> res = findContinuousArr(15);
        for (ArrayList<Integer> list : res) {
            for (Integer num : list) {
                System.out.print(num + " ");
            }
            System.out.println();
        }
//        1 2 3 4 5
//        4 5 6
//        7 8
    }
}
