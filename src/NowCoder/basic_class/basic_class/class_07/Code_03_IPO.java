package NowCoder.basic_class.basic_class.class_07;

import java.util.Comparator;
import java.util.PriorityQueue;

/*
	IPO：
		一次一个项目，有初始资金，最多可做k个项目，或者资金不够做项目停止
		costs[]表示项目的花费，profits[]表示项目的收益
		求获得的最多钱数。

	1. 每个项目中包含cost和profit，先根据cost将所有项目形成小根堆（cost小的在上面）
	2. 依次弹出小根堆头部，若cost < 初始资金，则放入大根堆中（profit高的在上面）--> 可解锁的项目
	3. 从大根堆（可解锁的项目）中弹出头部，收益是最高的
	4. 初始资金+profit，重复2.3，直到cost>资金，或者k次
 */
public class Code_03_IPO {
	// 项目：profit，cost
	public static class Node {
		public int p;
		public int c;

		public Node(int p, int c) {
			this.p = p;
			this.c = c;
		}
	}

	// 小根堆：cost
	public static class MinCostComparator implements Comparator<Node> {

		@Override
		public int compare(Node o1, Node o2) {
			return o1.c - o2.c;
		}

	}

	// 大根堆：profit
	public static class MaxProfitComparator implements Comparator<Node> {

		@Override
		public int compare(Node o1, Node o2) {
			return o2.p - o1.p;
		}

	}

	public static int findMaximizedCapital(int k, int W, int[] Profits, int[] Capital) {
		Node[] nodes = new Node[Profits.length];
		for (int i = 0; i < Profits.length; i++) {
			nodes[i] = new Node(Profits[i], Capital[i]);
		}

		PriorityQueue<Node> minCostQ = new PriorityQueue<>(new MinCostComparator());
		PriorityQueue<Node> maxProfitQ = new PriorityQueue<>(new MaxProfitComparator());
		for (int i = 0; i < nodes.length; i++) {
			minCostQ.add(nodes[i]);
		}
		for (int i = 0; i < k; i++) {
			while (!minCostQ.isEmpty() && minCostQ.peek().c <= W) {
				maxProfitQ.add(minCostQ.poll());
			}
			if (maxProfitQ.isEmpty()) { // 停止条件：可解锁的项目都做完了，或者做了k次
				return W;
			}
			W += maxProfitQ.poll().p;
		}
		return W;
	}

	public static void main(String[] args) {
		int k = 10;
		int W = 7;
		int[] Profits = {3, 2, 4, 9};
		int[] Capital = {5, 10, 6, 20};
		System.out.println(findMaximizedCapital(k, W, Profits, Capital)); // 16
	}
}
