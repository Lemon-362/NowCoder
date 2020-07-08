package NowCoder.Hard;

import java.util.HashMap;

/*
    一个整型数组里除了两个数字之外，其他的数字都出现了两次。请写程序找出这两个只出现一次的数字。
 */
public class No_code40_FindNumAppearOnce {
    // 法一：HashMap：O(N)
    public void FindNumAppearOnce(int[] array, int[] num1, int[] num2) {
        // HashMap：key是数字，value是出现次数 O(N)
        // HashMap的key一定不同，value可以相同
        if (array.length == 0) {
            return;
        }
        HashMap<Integer, Integer> map = new HashMap<>();
        // 遍历数组，将 数字-出现次数存入map中
        for (int i = 0; i < array.length; i++) {
            // 因为数字只可能出现一次或者两次，所以只需要改变value
            if (map.containsKey(array[i])) { // map中包含该key
                map.put(array[i], 2); // 放入map中
            } else { // map不包含该key
                map.put(array[i], 1); // 放入map中
            }
        }
        // 再次遍历数组，在map中找到value为1的key
        int count = 0;
        for (int i = 0; i < array.length; i++) {
            if (map.get(array[i]) == 1) {
                if (count == 0) {
                    num1[0] = array[i];
                    count++;
                } else {
                    num2[0] = array[i];
                }
            }
        }
    }

    /**
     * 法二：异或运算
     *  TODO 简化版：数组中只有一个数字出现一次，其他都出现两次，找出这个数？
     *      异或的性质：相同为0，不同为1
     *      因此，只需要将数组求异或和，因为重复数字出现两次，它们异或后结果为0
     *          而有一个只出现一次，那么异或和的结果就是该数字
     *
     *  现在，数组中有两个只出现一次的数字，那么可以想办法将这两个数分开，
     *  将数组划分为两部分，每一部分都只包含一个只出现一次的数，那么就可以用简化版的方法求解
     *
     *  TODO 如何划分数组为两部分，使得每一部分都只包含一个只出现一次的数？
     *
     *  由于只有两个数只出现一次，那么求数组的异或和后，其结果一定不为0
     *   TODO 一个不为0的数，其二进制一定至少包含一个1
     *  又因为异或的性质是：相同为0，不同为1
     *  那么对于异或和的结果的二进制中最右边的1来说
     *  在两个数的对应的位置上也一定是：一个为0，一个为1，这样才能异或出1
     *  所以可以根据这个区别将这两个数分开
     *  又因为其他重复的数的二进制是相同的，所以一定会划分到一个子数组中，不会将重复的数分开
     *
     *  TODO 划分规则：
     *      根据数组异或和的结果中最右边的1的位置
     *      以该位置是不是1为标准，将数组元素划分为两部分
     *   ==>即，元素 & 该位置 == 0
     *      最终对于那两个只出现一次的数来说，
     *      该位置为1的在一个子数组中，该位置为0的在另一个子数组中
     *
     *  TODO 在寻找二进制最右边的1的位置时，因为定义的index是二进制转换为十进制的结果
     *      所以不能以 两数与运算 后为1 作为判断条件
     *      要以 (index & xor) == 0 为判断条件
     *      后面的划分的条件也是如此
     */
    public static int[] FindNumAppearOnce2(int[] arr) {
        if (arr == null || arr.length < 2){
            return null;
        }

        int xor = 0;
        // 求数组的异或和
        for (int i = 0; i < arr.length; i++) {
            xor ^= arr[i];
        }

        // 求异或和最右边的1的二进制位置
        int index = 1;
        while ((index & xor) == 0){
            index = index << 1;
        }

        int result1 = 0;
        int result2 = 0;
        // 遍历数组，按照 元素 & index == 0划分
        for (int i = 0; i < arr.length; i++) {
            if ((arr[i] & index) == 0){
                result1 ^= arr[i];
            }else {
                result2 ^= arr[i];
            }
        }

        return new int[]{result1, result2};
    }

    public static int FindNumAppearOnceSimple(int[] arr){
        if (arr == null || arr.length < 1){
            return -1;
        }

        int res = 0;
        for (int i = 0; i < arr.length; i++) {
            res ^= arr[i];
        }

        return res;
    }

    public static void main(String[] args) {
        int[] arr = {2, 4, 3, 6, 3, 2, 5, 5};

        int[] res = FindNumAppearOnce2(arr);
        for (int num : res) {
            System.out.println(num); // 4 6
        }

        System.out.println("*****************");

        int[] arr1 = {2, 4, 6, 4, 2, 5, 5};
        System.out.println(FindNumAppearOnceSimple(arr1)); // 6

    }
}
