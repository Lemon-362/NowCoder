package NowCoder.Nowcoder.Tags.Array;

import java.util.ArrayList;

public class code41 {
    public static ArrayList<ArrayList<Integer>> findContinuousArr(int target) {
        ArrayList<ArrayList<Integer>> res = new ArrayList<>();

        int i = 1;
        int j = 1;
        int sum = 0;

        while (i <= target / 2){
            if (sum < target){
                sum += j;

                j++;
            }else if (sum > target){
                sum -= i;

                i++;
            }else {
                ArrayList<Integer> list = new ArrayList<>();
                for (int k = i; k < j; k++) {
                    list.add(k);
                }

                res.add(list);

                sum -= i;
                i++;
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
