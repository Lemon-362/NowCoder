package NowCoder.Nowcoder;


import java.util.HashSet;

/*
    扑克牌中的顺子:
        从扑克牌中随机抽5张牌，判断是不是一个顺子，即这5张牌是不是连续的。
        2～10为数字本身，A为1，J为11，Q为12，K为13，
        而大、小王为 0 ，可以看成任意数字。
        A 不能视为 14。

    TODO 给定的数组长度只有5，找规律：
        1. 要求数组元素不重复
        2. 要求数组中 max-min < 5，最多有两个0，那么即使是两个0（如：1 0 0 4 5），其 max-min < 5 仍满足
 */
public class code45_isContinuous {
    public static boolean isContinuous(int[] arr) {
        if (arr == null || arr.length < 1) {
            return false;
        }

        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;
        HashSet<Integer> set = new HashSet<>();

        // 遍历每个元素，找最值，并判断是否重复
        for (int i = 0; i < arr.length; i++) {
            // 首先如果是0，直接跳过
            if (arr[i] == 0) {
                continue;
            }

            max = Math.max(max, arr[i]);
            min = Math.min(min, arr[i]);

            // 判断元素是否重复，如果重复直接返回false
            if (set.contains(arr[i])) {
                return false;
            } else {
                set.add(arr[i]);
            }
        }

        // 找到全局最大值和全局最小值后，判断差值是否小于5
        return max - min < 5;
    }

    public static void main(String[] args) {
        int[] arr1 = {1, 2, 3, 4, 5};
        int[] arr2 = {0, 0, 1, 2, 5};

        System.out.println(isContinuous(arr1)); // true
        System.out.println(isContinuous(arr2)); // true
    }
}
