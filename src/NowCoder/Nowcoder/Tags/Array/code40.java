package NowCoder.Nowcoder.Tags.Array;

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
        for(Integer num : map.keySet()){
            if (map.get(num) == 1){
                res[index++] = num;
            }
        }

        return res;
    }

    public static int[] FindNumAppearOnce1(int[] arr){
        if (arr == null || arr.length < 2){
            return null;
        }

        int xor = 0;
        for (int i = 0; i < arr.length; i++) {
            xor ^= arr[i];
        }

        int index = 1;
        while ((index & xor) == 0){
            index = index << 1;
        }

        int xor1 = 0;
        int xor2 = 0;
        for (int i = 0; i < arr.length; i++) {
            if ((arr[i] & index) == 0){
                xor1 ^= arr[i];
            }else {
                xor2 ^= arr[i];
            }
        }

        return new int[]{xor1, xor2};
    }

    public static void main(String[] args) {
        int[] arr = {2, 4, 3, 6, 3, 2, 5, 5};

        int[] res = FindNumAppearOnce1(arr);
        for (int num : res) {
            System.out.println(num); // 4 6
        }
    }
}
