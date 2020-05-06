package NowCoder.advanced_class.class_7;

import java.util.HashMap;

/*
    暴力递归 ->动态规划：
        换钱：
            给定数组arr，全为正数且不重复，每个值代表一种面值，每种面值可以使用任意张，
            再给定一个整数aim代表要找的钱数，求换钱一共有多少种方法？
        例：arr = [5, 10, 25, 1], aim = 15
            一共有6种

 */
public class Code_02_CoinsWay {

    public static int coins1(int[] arr, int aim) {
        if (arr == null || arr.length == 0 || aim < 0) {
            return 0;
        }
        return process1(arr, 0, aim);
    }

    // TODO 尝试版本：递归
    // index：可以任意使用index及其之后所有的钱
    // aim：目标钱数
    // 返回值：方法种数
    public static int process1(int[] arr, int index, int aim) {
        int res = 0;
        if (index == arr.length) { // base case：每个位置都选完之后还剩下钱，说明不是有效的方法
            res = aim == 0 ? 1 : 0;
        } else {
            for (int zhang = 0; arr[index] * zhang <= aim; zhang++) {
                res += process1(arr, index + 1, aim - arr[index] * zhang);
                // 向下递归，已有index处zhang张，剩下的钱从index+1之后随意组合
            }
        }
        return res;
    }

    // TODO 暴力递归的优化1：减少重复过程，index和aim确定，返回值是确定的
    // key: "index_aim"
    // value: f(index, aim)的结果，参数为index和aim的返回值
    public static HashMap<String, Integer> map = new HashMap<>();

    public static int process1_map(int[] arr, int index, int aim) {
        int res = 0;
        if (index == arr.length) { // base case：每个位置都选完之后还剩下钱，说明不是有效的方法
            res = aim == 0 ? 1 : 0;
        } else {
            for (int zhang = 0; arr[index] * zhang <= aim; zhang++) {
                int nextAim = aim - arr[index] * zhang; // 剩余的钱数
                // TODO 这里index+1是因为当前选择的面值就是index处的,要在index+1往后组合处aim
                String key = String.valueOf(index + 1) + "_" + String.valueOf(nextAim); // key：f(index, aim)
                if (map.containsKey(key)) { // 之前算过
                    res += map.get(key); // 直接累加
                } else { // 之前没算过该过程，就递归算一遍
                    res += process1(arr, index + 1, aim - arr[index] * zhang);
                }
            }
        }
        // TODO 该子过程表示的就是从index开始到len结束,可以组合出aim的方法数,所以和f(index, aim)是一样的
        map.put(String.valueOf(index) + "_" + String.valueOf(aim), res); // 将每一个过程都放进map
        return res;
    }

    public static int coinsOther(int[] arr, int aim) {
        if (arr == null || arr.length == 0 || aim < 0) {
            return 0;
        }
        return processOther(arr, arr.length - 1, aim);
    }

    public static int processOther(int[] arr, int index, int aim) {
        int res = 0;
        if (index == -1) {
            res = aim == 0 ? 1 : 0;
        } else {
            for (int i = 0; arr[index] * i <= aim; i++) {
                res += processOther(arr, index - 1, aim - arr[index] * i);
            }
        }
        return res;
    }

    public static int coins2(int[] arr, int aim) {
        if (arr == null || arr.length == 0 || aim < 0) {
            return 0;
        }
        int[][] map = new int[arr.length + 1][aim + 1];
        return process2(arr, 0, aim, map);
    }

    public static int process2(int[] arr, int index, int aim, int[][] map) {
        int res = 0;
        if (index == arr.length) {
            res = aim == 0 ? 1 : 0;
        } else {
            int mapValue = 0;
            for (int i = 0; arr[index] * i <= aim; i++) {
                mapValue = map[index + 1][aim - arr[index] * i];
                if (mapValue != 0) {
                    res += mapValue == -1 ? 0 : mapValue;
                } else {
                    res += process2(arr, index + 1, aim - arr[index] * i, map);
                }
            }
        }
        map[index][aim] = res == 0 ? -1 : res;
        return res;
    }

    // TODO 动态规划：dp表
    public static int coins3(int[] arr, int aim) {
        if (arr == null || arr.length == 0 || aim < 0) {
            return 0;
        }
        int[][] dp = new int[arr.length][aim + 1];
        for (int i = 0; i < arr.length; i++) {
            dp[i][0] = 1;
        }
        for (int j = 1; arr[0] * j <= aim; j++) {
            dp[0][arr[0] * j] = 1;
        }
        int num = 0;
        for (int i = 1; i < arr.length; i++) {
            for (int j = 1; j <= aim; j++) {
                num = 0; // dp[i][j]的值，就是下一行的值，加上往前推的值之和
                for (int k = 0; j - arr[i] * k >= 0; k++) { // k -> zhang
                    num += dp[i - 1][j - arr[i] * k];
                }
                dp[i][j] = num;
            }
        }
        return dp[arr.length - 1][aim];
    }

    // TODO 动态规划：普通位置的计算过程精简版
    public static int coins4(int[] arr, int aim) {
        if (arr == null || arr.length == 0 || aim < 0) {
            return 0;
        }
        int[][] dp = new int[arr.length][aim + 1];
        for (int i = 0; i < arr.length; i++) {
            dp[i][0] = 1;
        }
        for (int j = 1; arr[0] * j <= aim; j++) {
            dp[0][arr[0] * j] = 1;
        }
        for (int i = 1; i < arr.length; i++) {
            for (int j = 1; j <= aim; j++) {
                dp[i][j] = dp[i - 1][j];
                dp[i][j] += j - arr[i] >= 0 ? dp[i][j - arr[i]] : 0; // 如果往左推不越界，就加上
            }
        }
        return dp[arr.length - 1][aim];
    }

    //
    public static int coins5(int[] arr, int aim) {
        if (arr == null || arr.length == 0 || aim < 0) {
            return 0;
        }
        int[] dp = new int[aim + 1];
        for (int j = 0; arr[0] * j <= aim; j++) {
            dp[arr[0] * j] = 1;
        }
        for (int i = 1; i < arr.length; i++) {
            for (int j = 1; j <= aim; j++) {
                dp[j] += j - arr[i] >= 0 ? dp[j - arr[i]] : 0;
            }
        }
        return dp[aim];
    }

    public static void main(String[] args) {
        int[] coins = {10, 5, 1, 25};
        int aim = 2000;

        long start = 0;
        long end = 0;
        start = System.currentTimeMillis();
        System.out.println(coins1(coins, aim)); // 1103021
        end = System.currentTimeMillis();
        System.out.println("cost time : " + (end - start) + "(ms)");

        start = System.currentTimeMillis();
        System.out.println(coinsOther(coins, aim));
        end = System.currentTimeMillis();
        System.out.println("cost time : " + (end - start) + "(ms)");

        aim = 20000;

        start = System.currentTimeMillis();
        System.out.println(coins2(coins, aim)); // 1070270201
        end = System.currentTimeMillis();
        System.out.println("cost time : " + (end - start) + "(ms)");

        start = System.currentTimeMillis();
        System.out.println(coins3(coins, aim));
        end = System.currentTimeMillis();
        System.out.println("cost time : " + (end - start) + "(ms)");

        start = System.currentTimeMillis();
        System.out.println(coins4(coins, aim));
        end = System.currentTimeMillis();
        System.out.println("cost time : " + (end - start) + "(ms)");

        start = System.currentTimeMillis();
        System.out.println(coins5(coins, aim));
        end = System.currentTimeMillis();
        System.out.println("cost time : " + (end - start) + "(ms)");

    }

}
