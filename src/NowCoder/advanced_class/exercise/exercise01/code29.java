package NowCoder.advanced_class.exercise.exercise01;

import java.util.HashMap;

public class code29 {
    public static int process1(int[] arr, int index, int aim) {
        int res = 0;
        // base case
        if (index == arr.length) {
            res = aim == 0 ? 1 : 0;
        } else {
            for (int zhang = 0; zhang * arr[index] <= aim; zhang++) {
                int leftAim = aim - zhang * arr[index];
                res += process1(arr, index + 1, leftAim);
            }
        }

        return res;
    }

    public static int getWay1(int[] arr, int aim){
        return process1(arr, 0, aim);
    }

    public static HashMap<String, Integer> map = new HashMap<>();

    public static int process2(int[] arr, int index, int aim){
        int res = 0;
        // base case
        if (index == arr.length) {
            res = aim == 0 ? 1 : 0;
        } else {
            for (int zhang = 0; zhang * arr[index] <= aim; zhang++) {
                int leftAim = aim - zhang * arr[index];
                String f = index + 1 + "_" + leftAim;
                if (map.containsKey(f)){
                    res += map.get(f);
                }else {
                    res += process1(arr, index + 1, leftAim);
                }

            }
        }

        map.put(index + "_" + aim, res);

        return res;
    }

    public static int getWay2(int[] arr, int aim){
        return process2(arr, 0, aim);
    }

    public static int getWay3(int[] arr, int aim){
        if (arr == null || arr.length < 1){
            return 0;
        }

        int[][] dp = new int[arr.length + 1][aim + 1];
        dp[arr.length][0] = 1;

        for (int index = arr.length - 1; index >= 0; index--) {
            for (int leftAim = 0; leftAim < dp[0].length; leftAim++) {
                int num = 0;
                for (int zhang = 0; leftAim - zhang * arr[index] >= 0; zhang++) {
                    num += dp[index + 1][leftAim - zhang * arr[index]];
                }
                dp[index][leftAim] = num;
            }
        }

        return dp[0][aim];
    }

    public static int getWay4(int[] arr, int aim){
        if (arr == null || arr.length < 1){
            return 0;
        }

        int[][] dp = new int[arr.length + 1][aim + 1];
        dp[arr.length][0] = 1;

        for (int index = arr.length - 1; index >= 0; index--) {
            for (int leftAim = 0; leftAim < dp[0].length; leftAim++) {
                int num1 = leftAim - arr[index] >= 0 ? dp[index][leftAim - arr[index]] : 0;
                int num2 = dp[index + 1][leftAim];

                dp[index][leftAim] = num1 + num2;
            }
        }

        return dp[0][aim];
    }

    public static void main(String[] args) {
        int[] arr = {10, 5, 1, 25};
        long start = 0;
        long end = 0;

        start = System.currentTimeMillis();
        System.out.println(getWay1(arr, 2000)); // 1103021
        end = System.currentTimeMillis();
        System.out.println("getWay1: " + (end - start) + "ms");

        start = System.currentTimeMillis();
        System.out.println(getWay2(arr, 2000)); // 1103021
        end = System.currentTimeMillis();
        System.out.println("getWay2: " + (end - start) + "ms");

        start = System.currentTimeMillis();
        System.out.println(getWay3(arr, 20000)); // 1070270201
        end = System.currentTimeMillis();
        System.out.println("getWay3: " + (end - start) + "ms");

        start = System.currentTimeMillis();
        System.out.println(getWay4(arr, 20000)); // 1070270201
        end = System.currentTimeMillis();
        System.out.println("getWay4: " + (end - start) + "ms");
    }
}
