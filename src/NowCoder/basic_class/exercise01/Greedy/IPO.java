package NowCoder.basic_class.exercise01.Greedy;

import java.util.Comparator;
import java.util.PriorityQueue;

public class IPO {
    public static class Node {
        private int cost;
        private int profit;

        public Node(int cost, int profit) {
            this.cost = cost;
            this.profit = profit;
        }
    }

    public static int method(int k, int w, int[] costs, int[] profits) {
        Node[] nodes = new Node[profits.length];
        for (int i = 0; i < profits.length; i++) {
            nodes[i] = new Node(costs[i], profits[i]);
        }

        PriorityQueue<Node> minCostHeap = new PriorityQueue<>(new minCostComparator());
        for (int i = 0; i < profits.length; i++) {
            minCostHeap.add(nodes[i]);
        }

        PriorityQueue<Node> maxProfitHeap = new PriorityQueue<>(new maxProfitComparator());
        for (int i = 0; i < k; i++) {
            while (!minCostHeap.isEmpty() && minCostHeap.peek().cost <= w) {
                maxProfitHeap.add(minCostHeap.poll());
            }
            if (maxProfitHeap.isEmpty()) {
                return w;
            }
            w += maxProfitHeap.poll().profit;
        }
        return w;
    }

    public static class minCostComparator implements Comparator<Node> {
        public int compare(Node o1, Node o2) {
            return o1.cost - o2.cost;
        }
    }

    public static class maxProfitComparator implements Comparator<Node> {
        public int compare(Node o1, Node o2) {
            return o2.profit - o1.profit;
        }
    }

    public static void main(String[] args) {
        int k = 10;
        int w = 7;
        int[] profits = {3, 2, 4, 9};
        int[] costs = {5, 10, 6, 20};
        System.out.println(method(k, w, costs, profits)); // 16
    }

}
