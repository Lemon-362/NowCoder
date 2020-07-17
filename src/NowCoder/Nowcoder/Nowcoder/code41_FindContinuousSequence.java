package NowCoder.Nowcoder.Nowcoder;

import java.util.ArrayList;

/*
    输入一个正整数 target ，输出所有和为 target 的连续正整数序列（至少含有两个数）。
    序列内的数字由小到大排列，不同序列按照首个数字从小到大排列。
    TODO 双指针简化版的滑动窗口：i包含，j不包含，先加上/减去当前位置，然后再移动

 */
public class code41_FindContinuousSequence {
    public static ArrayList<ArrayList<Integer>> finContinuousArr(int sum) {
        ArrayList<ArrayList<Integer>> res = new ArrayList<>();
        int i = 1; // 减数
        int j = 1; // 加数
        int N = 0; // 窗口内的和
        // 滑动窗口法
        // 如果 N < sum，说明窗口仍可以加数，j++
        // 如果 N > sum，说明窗口需要减数，i++
        while (i <= sum / 2) { // 因为窗口最少要有两个数，所以超过一半后就不可能累和=sum，只可能大于sum
            if (N < sum) {
                N += j;
                j++;
            } else if (N > sum) {
                N -= i;
                i++;
            } else {
                ArrayList<Integer> list = new ArrayList<>();
                for (int k = i; k < j; k++) {
                    list.add(k);
                }
                res.add(list);
                // 以i开头的连续序列找到了，i后移，窗口和要减小i
                N -= i;
                i++;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        ArrayList<ArrayList<Integer>> res = finContinuousArr(15);
        for (ArrayList<Integer> list : res) {
            for (Integer num : list) {
                System.out.print(num + " ");
            }
            System.out.println();
        }
//        1 2 3 4 5
//        4 5 6
//        7 8
    }
}
