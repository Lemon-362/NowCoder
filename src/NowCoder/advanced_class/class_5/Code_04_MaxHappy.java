package NowCoder.advanced_class.class_5;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/*
    员工关系的活跃值：
        员工关系是一个多叉树
        一个员工的直接上级如果到场，那么这个员工肯定不会来
        每个员工都有一个活跃度
        如何发邀请函，使得活跃值最大
    例：
    arr = {{1, 6}, {1, 5}, {1, 4}}
    第一个表示直接上级为1，第二个表示活跃度
    返回10
 */
public class Code_04_MaxHappy {
    // 员工关系简单描述
    public static class Node {
        public int huo;
        public List<Node> nexts;

        public Node(int huo) {
            this.huo = huo;
            this.nexts = new ArrayList<>();
        }
    }

    public static class ReturnData {
        public int lai_huo;
        public int bu_lai_huo;

        public ReturnData(int lai_huo, int bu_lai_huo) {
            this.lai_huo = lai_huo;
            this.bu_lai_huo = bu_lai_huo;
        }
    }

    // 递归
    public static ReturnData process(Node head) {
        int lai_huo = head.huo;
        int bu_lai_huo = 0;
        // 遍历头节点的每个后代
        for (int i = 0; i < head.nexts.size(); i++) {
            // 得到当前节点的所有后代节点
            Node next = head.nexts.get(i);
            // 黑盒：后代节点的信息
            ReturnData nextData = process(next);
            // 可能1:当前来,后代不来
            lai_huo += nextData.bu_lai_huo; // 1
            // 可能2:当前不来,后代来/不来的max
            bu_lai_huo += Math.max(nextData.lai_huo, nextData.bu_lai_huo); // 2
        }
        // 解黑盒：向上返回
        return new ReturnData(lai_huo, bu_lai_huo);
    }

    public static int getMaxHuo(int[][] arr) {
        Node[] nodes = new Node[arr.length];
        for (int i = 0; i < arr.length; i++) {
            nodes[i] = new Node(arr[i][1]);
        }

        for (int i = 0; i < arr.length; i++) {
            int boss = arr[i][0];
            if (i != arr[i][0]) {
                nodes[boss].nexts.add(nodes[i]);
            }
        }

        int root = 0;
        for (int i = 0; i < arr.length; i++) {
            if (i == arr[i][0]) {
                root = i;
                break;
            }
        }

        // 求头节点开始往下的来/不来
        ReturnData data = process(nodes[root]);

        return Math.max(data.bu_lai_huo, data.lai_huo);
    }

    // 原题目
    public static int maxHappy(int[][] matrix) {
        int[][] dp = new int[matrix.length][2];
        boolean[] visited = new boolean[matrix.length];
        int root = 0;
        for (int i = 0; i < matrix.length; i++) {
            if (i == matrix[i][0]) {
                root = i;
            }
        }
        process(matrix, dp, visited, root);
        return Math.max(dp[root][0], dp[root][1]);
    }

    public static void process(int[][] matrix, int[][] dp, boolean[] visited, int root) {
        visited[root] = true;
        dp[root][1] = matrix[root][1];
        for (int i = 0; i < matrix.length; i++) {
            if (matrix[i][0] == root && !visited[i]) {
                process(matrix, dp, visited, i);
                dp[root][1] += dp[i][0];
                dp[root][0] += Math.max(dp[i][1], dp[i][0]);
            }
        }
    }

    public static void main(String[] args) {
        int[][] arr = {{1, 8}, {1, 9}, {1, 10}};

        System.out.println(getMaxHuo(arr)); // 18

//        System.out.println(maxHappy(matrix));

    }
}
