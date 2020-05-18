package NowCoder.Nowcoder.exercise;

import java.util.HashMap;

public class code40 {
    public static int[] FindNumAppearOnce(int[] arr){
        if (arr == null || arr.length < 1){
            return null;
        }

        HashMap<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < arr.length; i++) {
            if (!map.containsKey(arr[i])){
                map.put(arr[i], 1);
            }else {
                map.put(arr[i], map.get(arr[i]) + 1);
            }
        }

        int[] res = new int[2];
        int index = 0;

        for (int i = 0; i < arr.length; i++) {
            if (map.get(arr[i]) == 1){
                res[index++] = arr[i];
            }
        }

        return res;
    }

    public static void main(String[] args) {
        int[] arr = {1, 4, 1, 4, 3, 3, 10, 2};

        int[] res = FindNumAppearOnce(arr);
        for (int num : res) {
            System.out.println(num);
        }
    }
}
