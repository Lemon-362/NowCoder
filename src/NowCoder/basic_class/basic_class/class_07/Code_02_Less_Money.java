package NowCoder.basic_class.basic_class.class_07;

import java.util.Comparator;
import java.util.PriorityQueue;

/*
	哈夫曼编码：
		一块金条切成两半，需要花费和长度数值一样的铜板。求怎么分最省铜板？
		输入一个数组，返回最小代价。
	例：{10，20，30}，代表三个人，金条长度为10+20+30=60，需要分成10，20，30三部分
	（1）先把长60的分为10和50，花费60，再把长50的分为20和30，花费50。	一共花费60+50=110
	（2）先把长60的分为30和30，花费60，再把长30的分为10和20，花费30。	一共花费60+30=90

	将数组变为小根堆，每次从堆中取出最小的2个数，求和后放回堆中，再次形成小根堆，

	PriorityQueue优先级队列：默认将数组形成小根堆，可以通过自定义比较器形成大根堆

	o1 - o2 ：升序
	o2 - o1 ：降序
 */
public class Code_02_Less_Money {

	public static int lessMoney(int[] arr) {
		PriorityQueue<Integer> pQ = new PriorityQueue<>();
		for (int i = 0; i < arr.length; i++) {
			pQ.add(arr[i]);
		}
		int sum = 0;
		int cur = 0;
		while (pQ.size() > 1) {
			cur = pQ.poll() + pQ.poll(); // 求和
			sum += cur; // 花费
			pQ.add(cur); // 和放入小根堆中
		}
		return sum;
	}

	// 小根堆比较器
	public static class MinheapComparator implements Comparator<Integer> {

		@Override
		public int compare(Integer o1, Integer o2) {
			return o1 - o2; // < 0  o1 < o2  负数
		}

	}

	// 大根堆比较器
	public static class MaxheapComparator implements Comparator<Integer> {

		@Override
		public int compare(Integer o1, Integer o2) {
			return o2 - o1; // <   o2 < o1
		}

	}

	public static void main(String[] args) {
		// solution
		int[] arr = { 6, 7, 8, 9 };
		System.out.println(lessMoney(arr));

		int[] arrForHeap = { 3, 5, 2, 7, 0, 1, 6, 4 };

		// min heap
		PriorityQueue<Integer> minQ1 = new PriorityQueue<>();
		for (int i = 0; i < arrForHeap.length; i++) {
			minQ1.add(arrForHeap[i]);
		}
		while (!minQ1.isEmpty()) {
			System.out.print(minQ1.poll() + " ");
		}
		System.out.println();

		// min heap use Comparator
		PriorityQueue<Integer> minQ2 = new PriorityQueue<>(new MinheapComparator());
		for (int i = 0; i < arrForHeap.length; i++) {
			minQ2.add(arrForHeap[i]);
		}
		while (!minQ2.isEmpty()) {
			System.out.print(minQ2.poll() + " ");
		}
		System.out.println();

		// max heap use Comparator
		PriorityQueue<Integer> maxQ = new PriorityQueue<>(new MaxheapComparator());
		for (int i = 0; i < arrForHeap.length; i++) {
			maxQ.add(arrForHeap[i]);
		}
		while (!maxQ.isEmpty()) {
			System.out.print(maxQ.poll() + " ");
		}

	}

}
