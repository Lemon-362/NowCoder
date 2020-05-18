package NowCoder.Nowcoder;

import java.util.HashMap;

/*
    一个整型数组里除了两个数字之外，其他的数字都出现了两次。请写程序找出这两个只出现一次的数字。
 */
public class code40_FindNumAppearOnce {
    // 法二：位运算


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

    public static int[] FindNumAppearOnce2(int[] arr) {
        int xor1 = 0;
        for (int i = 0; i < arr.length; i++)
            xor1 = xor1 ^ arr[i];
        //在xor1中找到第一个不同的位对数据进行分类，分类为两个队列对数据进行异或求和找到我们想要的结果
        int index = 1;
        while ((index & xor1) == 0) {
            index = index << 1;//因为可能有多个位为1所以需要求一下位置
        }

        int result1 = 0;
        int result2 = 0;
        for (int i = 0; i < arr.length; i++) {
            if ((index & arr[i]) == 0)
                result1 = result1 ^ arr[i];
            else
                result2 = result2 ^ arr[i];
        }

        return new int[]{result1, result2};
    }

    public static void main(String[] args) {
        int[] arr = {1, 4, 1, 4, 3, 3, 10, 2};

        int[] res = FindNumAppearOnce2(arr);
        for (int num : res) {
            System.out.println(num);
        }
    }
}
