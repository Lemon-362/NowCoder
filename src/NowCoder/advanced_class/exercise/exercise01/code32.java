package NowCoder.advanced_class.exercise.exercise01;

import java.util.HashMap;

public class code32 {
    public static int getMaxLength1(int[] arr, int aim) {
        if (arr == null || arr.length < 1) {
            return 0;
        }

        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(0, -1);
        int sum = 0;
        int res = 0;

        for (int i = 0; i < arr.length; i++) {
            sum += arr[i];

            if (map.containsKey(sum - aim)){
                int right = i;
                int left = map.get(sum - aim) + 1;
                res = Math.max(res, right - left + 1);
            }

            if (!map.containsKey(sum)){
                map.put(sum, i);
            }
        }

        return res;
    }

    public static int getMaxLength2(int[] arr, int aim) {
        if (arr == null || arr.length < 1) {
            return 0;
        }

        int res = 0;
        int L = 0;
        int R = 0;
        int sum = 0;

        while (L < arr.length && R < arr.length){
            if (sum < aim){
                sum += arr[R];
                R++;
            }else if (sum > aim){
                sum -= arr[L];
                L++;
            }else {
                res = Math.max(res, R - L + 1);

                sum -= arr[L];
                L++;
            }
        }

        return res;
    }

    public static int[] generatePositiveArray(int size) {
        int[] result = new int[size];
        for (int i = 0; i != size; i++) {
            result[i] = (int) (Math.random() * 10) + 1;
        }
        return result;
    }

    public static void printArray(int[] arr) {
        for (int i = 0; i != arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 1, 1, 1};
        System.out.println(getMaxLength1(arr, 3)); // 3
        System.out.println(getMaxLength2(arr, 3));

        int len = 20;
        int k = 15;
        int[] arr1 = generatePositiveArray(len);
        printArray(arr1);
        System.out.println(getMaxLength2(arr1, k));
    }
}
