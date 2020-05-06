package NowCoder.Nowcoder;

import java.util.ArrayList;
import java.util.LinkedList;

/*
    给定一个数组和滑动窗口的大小，找出所有滑动窗口里数值的最大值。
    例如，如果输入数组{2,3,4,2,6,2,5,1}及滑动窗口的大小3，
    那么一共存在6个滑动窗口，他们的最大值分别为{4,4,6,6,6,5}；
    针对数组{2,3,4,2,6,2,5,1}的滑动窗口有以下6个：
    {[2,3,4],2,6,2,5,1}， {2,[3,4,2],6,2,5,1}，
    {2,3,[4,2,6],2,5,1}， {2,3,4,[2,6,2],5,1}，
    {2,3,4,2,[6,2,5],1}， {2,3,4,2,6,[2,5,1]}。
 */
public class code64_SlidingWindowMaxArray {
    public static ArrayList<Integer> maxInWindows(int[] arr, int w) {
        ArrayList<Integer> res = new ArrayList<>();
        if (arr == null || w < 1 || arr.length < w) {
            return res;
        }
        // LinkedList：双向链表
        LinkedList<Integer> qmax = new LinkedList<Integer>();
        int index = 0;
        // 依次遍历所有数
        for (int i = 0; i < arr.length; i++) { // 窗口（刚才讲的）的R
            // i -> arr[i]

            // 双向链表直接存放数组的索引，不用放具体的值
            // 加数（通用的）
            // arr[qmax.peekLast()] <= arr[i]：双向链表尾部的值是否小于等于当前值
            while (!qmax.isEmpty() && arr[qmax.peekLast()] <= arr[i]) {
                qmax.pollLast(); // 小于等于，则弹
            }
            qmax.addLast(i); // 当前值加到尾部

            // 减数（题目定制的）：因为窗口长度只能是3
            // i-w == 双向链表头部的值时，头部过期
            if (qmax.peekFirst() == i - w) { // i - w  过期的下标
                qmax.pollFirst(); // 头部弹出过期的
            }

            if (i >= w - 1) { // 如果窗口形成了，就将最大值放到res中
                res.add(arr[qmax.peekFirst()]); // 当前最大值放到res中（一定是头部）
            }
        }
        return res;
    }

    public static void main(String[] args) {
        int[] arr = {2, 3, 4, 2, 6, 2, 5, 1};
        int w = 3;
        ArrayList<Integer> list = maxInWindows(arr, w);
        for (Integer num : list) {
            System.out.print(num + " ");
        }
    }
}
