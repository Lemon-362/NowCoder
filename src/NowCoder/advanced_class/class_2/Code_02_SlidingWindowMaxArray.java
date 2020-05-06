package NowCoder.advanced_class.class_2;

import java.util.LinkedList;

/*
	窗口内最大值、最小值的更新结构：
		有一个整形数组arr和一个大小为w的窗口从数组的最左边滑到最右边，窗口每次向右滑动一个位置
		如果数组长度为n，窗口大小为w，则一共产生n-w+1个窗口的最大值
		请实现一个函数，输入：整形数组arr，窗口大小w
		输出：一个长度为n-w+1的数组res，res[i]表示每一种窗口状态下的最大值
	例：
		arr=[4, 3, 5, 4, 3, 3, 6, 7]  w=3
		res=[5, 5, 5, 4, 6, 7]
 */
public class Code_02_SlidingWindowMaxArray {
	public static int[] getMaxWindow(int[] arr, int w) {
		if (arr == null || w < 1 || arr.length < w) {
			return null;
		}
		// LinkedList：双向链表
		LinkedList<Integer> qmax = new LinkedList<Integer>();
		int[] res = new int[arr.length - w + 1];
		int index = 0;
		// 依次遍历所有数，窗口进行滑动
		for (int i = 0; i < arr.length; i++) { // 窗口（刚才讲的）的R
			// i -> arr[i]

			// 双向链表直接存放数组的索引，不用放具体的值
			// 加数（通用的）
			// arr[qmax.peekLast()] <= arr[i]：双向链表尾部的值是否小于等于当前值
			while (!qmax.isEmpty() && arr[qmax.peekLast()] <= arr[i]) { // 保证双向链表中头到尾始终是从大到小的
				qmax.pollLast(); // 小于等于，则弹
			}
			qmax.addLast(i); // 当前值加到尾部

			// 减数（题目定制的）：因为窗口长度只能是3，形成窗口后每次加一个数并减一个数，此时窗口大小为 [i-w+1, i]
			// i-w == 双向链表头部的值时，头部过期
			// 窗口没有形成长度3的时候，不会弹出；但是如果形成窗口后，就要判断头部是否过期
			// TODO 因为固定了窗口长度为3，所以每次出去的是i-w位置的数，只要看双向链表中是否还存在这个数。
			if (qmax.peekFirst() == i - w) { // i - w  双向链表中过期的下标，保证窗口始终长度为3
				// 因为先从尾加数，然后要看头部的值是否是过期（使得窗口长度超过3）
				// 如果双向链表中有过期的下标，要将其弹出，也就相当于窗口减数
				qmax.pollFirst(); // 头部弹出过期的
			}

			if (i >= w - 1) { // 如果窗口形成了，就将最大值放到res中
				res[index++] = arr[qmax.peekFirst()]; // 当前最大值放到res中（一定是头部）
			}
		}
		return res;
	}

	// for test
	public static void printArray(int[] arr) {
		for (int i = 0; i != arr.length; i++) {
			System.out.print(arr[i] + " ");
		}
		System.out.println();
	}

	public static void main(String[] args) {
		int[] arr = { 4, 3, 5, 4, 3, 3, 6, 7 };
		int w = 3;
		printArray(getMaxWindow(arr, w)); // 5 5 5 4 6 7

	}

}
