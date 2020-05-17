package NowCoder.basic_class.exercise.exercise02;

import java.util.Comparator;
import java.util.PriorityQueue;

public class code37 {
    public static class Node {
        private int cost;
        private int profit;

        public Node(int cost, int profit) {
            this.cost = cost;
            this.profit = profit;
        }
    }

    public static int IPO(int k, int w, int[] costs, int[] profits){
        Node[] nodes = new Node[costs.length];
        for (int i = 0; i < nodes.length; i++) {
            nodes[i] = new Node(costs[i], profits[i]);
        }

        PriorityQueue<Node> minCost = new PriorityQueue<>(new minCostComparator());
        PriorityQueue<Node> maxProfit = new PriorityQueue<>(new maxProfitComparator());

        for (int i = 0; i < nodes.length; i++) {
            minCost.add(nodes[i]);
        }

        for (int i = 0; i < k; i++) {
            while (!minCost.isEmpty() && minCost.peek().cost <= w){
                maxProfit.add(minCost.poll());
            }

            if (maxProfit.isEmpty()){
                break;
            }

            w += maxProfit.poll().profit;
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
