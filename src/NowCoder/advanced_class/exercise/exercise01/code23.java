package NowCoder.advanced_class.exercise.exercise01;

import java.util.ArrayList;
import java.util.List;

public class code23 {
    public static class Node {
        private int huo;
        private List<Node> next;

        public Node(int huo) {
            this.huo = huo;
            this.next = new ArrayList<>();
        }
    }

    public static class ReturnData {
        private int lai_huo;
        private int bu_lai_huo;

        public ReturnData(int lai_huo, int bu_lai_huo) {
            this.lai_huo = lai_huo;
            this.bu_lai_huo = bu_lai_huo;
        }
    }

    public static ReturnData process(Node head){
        int lai = head.huo;
        int bu_lai = 0;

        for (int i = 0; i < head.next.size(); i++) {
            Node cur = head.next.get(i);

            ReturnData returnData = process(cur);

            // 1
            lai += returnData.bu_lai_huo;
            // 2
            bu_lai += Math.max(returnData.lai_huo, returnData.bu_lai_huo);
        }

        return new ReturnData(lai, bu_lai);
    }

    public static int getMaxHappy(int[][] arr){
        Node[] nodes = new Node[arr.length];
        for (int i = 0; i < arr.length; i++) {
            nodes[i] = new Node(arr[i][1]);
        }

        for (int i = 0; i < nodes.length; i++) {
            int boss = arr[i][0];
            if (boss != i){
                nodes[boss].next.add(nodes[i]);
            }
        }

        Node head = null;
        for (int i = 0; i < nodes.length; i++) {
            if (arr[i][0] == i){
                head = nodes[i];
                break;
            }
        }

        int lai_huo = process(head).lai_huo;
        int bu_lai_huo = process(head).bu_lai_huo;

        return Math.max(lai_huo, bu_lai_huo);
    }

    public static void main(String[] args) {
        int[][] arr = {{1, 8}, {1, 9}, {1, 10}};
        System.out.println(getMaxHappy(arr)); // 18
    }
}
