package NowCoder.Nowcoder.Additional;

/**
 * code40的衍生版：
 *  一个数组中只有一个数字出现一次，其他都出现三次
 *  找出这个数。
 *
 * 思路：
 *  异或不能用了，但是可以用其思想
 *  如果数字出现三次，那么其二进制对应位相加后，对应位是可以被3整除的
 *  那么，再加上这个只出现一次的数，
 *  如果还能被3整除，说明这个数的该位置上是0，否则是1
 *
 *  TODO 借鉴advance_code_28 子数组最大异或和（前缀树）的思路
 *       来求二进制每一位的数
 */
public class code06_FindOneAppearOnce {
    public static int findNumber(int[] arr){
        if (arr == null || arr.length < 2){
            return -1;
        }

        // 计算每个数二进制对应位的和
        int[] counts = new int[32];

        // 遍历每一个数
        for (int i = 0; i < arr.length; i++) {
            // 将该数二进制的每一位放到counts数组的对应位置上
            for (int j = 31; j >= 0; j--) {
                int path = (arr[i] >> j) & 1;
                counts[j] += path;
            }
        }

        // 将counts数组每一个位置都对3取余，其结果就是只出现一次的数的二进制形式
        for (int i = 0; i < counts.length; i++) {
            counts[i] = counts[i] % 3;
        }

        // 将二进制转换为十进制
        int res = 0;
        for (int i = 31; i >= 0; i--) {
            int path = counts[i];

            res |= path << i;
        }

        return res;
    }

    public static void main(String[] args) {
        int[] arr = {9, 1, 7, 9, 7, 9, 7};
        System.out.println(findNumber(arr)); // 1

    }
}
