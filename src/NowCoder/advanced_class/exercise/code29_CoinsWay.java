package NowCoder.advanced_class.exercise;

import java.util.HashMap;

/*
    暴力递归 ->动态规划：
        换钱：
            给定数组arr，全为正数且不重复，每个值代表一种面值，每种面值可以使用任意张，
            再给定一个整数aim代表要找的钱数，求换钱一共有多少种方法？
        例：arr = [5, 10, 25, 1], aim = 15
            一共有6种

 */
public class code29_CoinsWay {
    /*
        1. 尝试版本的递归:
            1) 从0位置开始,分别假设使用0位置的面值0,1,2,...张(每种可能),那么只需要在后续1-len上组合出剩余的钱数
            对于每种可能都有多少种方法,加起来就是总的方法数
            2) 用函数表示: f(index, aim) --> index表示当前位置的每种可能, aim表示剩余的钱数
            3) base case: index == len时,此时所有钱都用完了
            如果aim==0,说明是1种组合方法,如果aim!=0,说明不是1种组合方法
     */
    public static int process1(int[] arr, int index, int aim){
        int res = 0;

        if (index == arr.length){
            res = aim == 0 ? 1 : 0;
        }else {
            for (int zhang = 0; arr[index] * zhang <= aim; zhang++) {
                res += process1(arr, index + 1, aim - arr[index] * zhang);
            }
        }

        return res;
    }

    public static int getWay1(int[] arr, int aim){
        return process1(arr, 0, aim);
    }

    /*
        2. 记忆化搜索: 暴力递归的优化, 存储重复状态
            f(index, aim): 表示index-len范围内组合出aim的方法数
     */
    public static HashMap<String, Integer> map = new HashMap<>();

    public static int process2(int[] arr, int index, int aim){
        int res = 0;

        if (index == arr.length){
            res = aim == 0 ? 1 : 0;
        }else {
            for (int zhang = 0; arr[index] * zhang <= aim; zhang++) {
                int leftAim = aim - arr[index] * zhang;
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

    /*
        3. 动态规划: O(N^3)
            1) 找可变参数,确定参数的范围,建立dp表
            2) 看调process递归的主函数,确定目标位置
            3) 看process递归里的base case,确定不依赖其他位置的值,直接填到dp表里
            4) 看process递归里的递归过程,确定普遍位置需要依赖哪些位置的值
     */
    public static int getWay3(int[] arr, int aim){
        if (arr == null || arr.length < 1 || aim < 0){
            return -1;
        }

        int[][] dp = new int[arr.length + 1][aim + 1];

        dp[arr.length][0] = 1;

        for (int index = arr.length - 1; index >= 0; index--) {
            for (int leftAim = 0; leftAim < dp[0].length; leftAim++) {
//                for (int zhang = 0; arr[index] * zhang <= aim; zhang++) {
//                    res += process1(arr, index + 1, aim - arr[index] * zhang);
//                }
                int num = 0;
                for (int zhang = 0; leftAim - arr[index] * zhang >= 0; zhang++) {
                    num += dp[index + 1][leftAim - arr[index] * zhang];
                }
                dp[index][leftAim] = num;
            }
        }

        return dp[0][aim];
    }

    /*
        4. dp表的优化: O(N^2)
            dp表在填值的时候,对于同一行index行来说,都是他们的下一行每次往前减arr[index]长度,
            所以后一个位置一定和前一个位置有重复状态,
            也就是说后一个位置 = 前一个位置(num1) + 当前位置的下一行的值(zhang=0)(num2)
     */
    public static int getWay4(int[] arr, int aim){
        if (arr == null || arr.length < 1 || aim < 0){
            return -1;
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
