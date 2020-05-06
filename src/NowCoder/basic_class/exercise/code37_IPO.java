package NowCoder.basic_class.exercise;

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
public class code37_IPO {
    public static class Node{
        private int cost;
        private int profit;

        public Node(int cost, int profit) {
            this.cost = cost;
            this.profit = profit;
        }
    }

    public static int IPO(int k, int w, int[] costs, int[] profits){
        // 产生含有两个参数的项目数组
        Node[] nodes = new Node[profits.length];
        for (int i = 0; i < profits.length; i++) {
            nodes[i] = new Node(costs[i], profits[i]);
        }
        PriorityQueue<Node> minCostQ = new PriorityQueue<>(new minCostComparator());
        PriorityQueue<Node> maxProfitQ = new PriorityQueue<>(new maxProfitComparator());

        for (int i = 0; i < profits.length; i++) {
            minCostQ.add(nodes[i]);
        }

        for (int i = 0; i < k; i++) {
            while (!minCostQ.isEmpty() && minCostQ.peek().cost <= w){
                maxProfitQ.add(minCostQ.poll());
            }
            if (maxProfitQ.isEmpty()){
                return w;
            }
            w += maxProfitQ.poll().profit;
        }
        return w;
    }

    public static class minCostComparator implements Comparator<Node> {
        public int compare(Node o1, Node o2){
            return o1.cost - o2.cost;
        }
    }

    public static class maxProfitComparator implements Comparator<Node> {
        public int compare(Node o1, Node o2){
            return o2.profit - o1.profit;
        }
    }


    public static void main(String[] args) {
        int k = 10;
        int w = 7;
        int[] profits = {3, 2, 4, 9};
        int[] costs = {5, 10, 6, 20};
        System.out.println(IPO(k, w, costs, profits)); // 16
    }
}
