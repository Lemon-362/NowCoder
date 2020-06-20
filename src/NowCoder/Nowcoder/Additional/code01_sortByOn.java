package NowCoder.Nowcoder.Additional;

/**
 * 要求写出时间复杂度O(n)的排序算法
 * 可以使用常量大小的辅助空间
 * 例子：对公司员工的年龄进行排序
 * <p>
 * 思路：
 * 我们可以申请一个辅助数组，对年龄出现的次数进行统计 TODO 一维数组的下标就是对应的元素
 *  此时索引就是年龄，元素是出现次数
 * 然后对数组重新赋值，出现几次就赋值几次
 *  遍历次数数组，出现几次就赋值几次，赋的值就是次数数组的索引
 */
public class code01_sortByOn {
    public static void sortAges(int[] arr) {
        if (arr == null || arr.length < 1) {
            return;
        }

        // 假设最大年龄99，范围在0-99内
        int maxAge = 99;
        // 该数组的索引就表示年龄的范围
        int[] timesOfAge = new int[maxAge + 1];
        int age = 0;

        for (int i = 0; i < arr.length; i++) {
            // 得到一个年龄
            age = arr[i];
            // 下标就是对应年龄
            timesOfAge[age]++;
        }

        // 重新赋值
        int index = 0;
        // 遍历timesOfAge次数数组
        for (int i = 0; i <= maxAge; i++) {
            // 次数数组中的每一个元素的值，就表示该年龄出现的次数
            // 并且次数数组的索引对应着年龄，是有序的
            for (int j = 0; j < timesOfAge[i]; j++) {
                arr[index++] = i;
            }
        }
    }

    public static void main(String[] args) {
        int[] arr = {5, 56, 4, 20, 65, 46, 56, 14, 20, 46, 20};

        sortAges(arr);

        for (int i : arr) {
            System.out.print(i + " ");
        }
    }
}
