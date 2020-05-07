package NowCoder.advanced_class.exercise;

import java.util.ArrayList;
import java.util.List;

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
public class code23_MaxHappy {
    /*
        在多叉树上找总和最大的 --> 找每个子树上最大的
        1. 分析可能性:对于某一子树
            1) 头节点来 --> 下级都不来 --> sum = 头节点
            2) 头节点不来 --> 下级可能来可能不来,要看下级的下级 --> sum = 每个下级的max(来,不来)
        2. 列出信息:
            1) 当前节点来的活跃度
            2) 当前节点不来的活跃度
     */
    public static class Node {
        private int huo;
        private List<Node> next;

        public Node(int huo) {
            this.huo = huo;
            this.next = new ArrayList<>();
        }
    }

    public static class ReturnData {
        private int lai;
        private int bu_lai;

        public ReturnData(int lai, int bu_lai) {
            this.lai = lai;
            this.bu_lai = bu_lai;
        }
    }

    public static ReturnData process(Node head){
        int lai_huo = head.huo;
        int bu_lai_huo = 0;

        for (int i = 0; i < head.next.size(); i++) {
            Node cur = head.next.get(i);
            ReturnData returnData = process(cur);

            lai_huo += returnData.bu_lai;

            bu_lai_huo += Math.max(returnData.lai, returnData.bu_lai);
        }

        return new ReturnData(lai_huo, bu_lai_huo);
    }

    public static int getMaxHappy(int[][] arr){
        Node[] nodes = new Node[arr.length];
        for (int i = 0; i < arr.length; i++) {
            nodes[i] = new Node(arr[i][1]);
        }

        for (int i = 0; i < nodes.length; i++) {
            int boss = arr[i][0];
            if (i != boss){
                nodes[boss].next.add(nodes[i]);
            }
        }

        Node head = null;
        for (int i = 0; i < nodes.length; i++) {
            if (i == arr[i][0]){
                head = nodes[i];
                break;
            }
        }

        int lai = process(head).lai;
        int bu_lai = process(head).bu_lai;

        return Math.max(lai, bu_lai);
    }

    public static void main(String[] args) {
        int[][] arr = {{1, 8}, {1, 9}, {1, 10}};
        System.out.println(getMaxHappy(arr)); // 18
    }
}
