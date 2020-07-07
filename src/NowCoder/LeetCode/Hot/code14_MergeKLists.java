package NowCoder.LeetCode.Hot;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 合并K个排序链表：
 *  给定K个已排序的链表，将它们合并成一个新的排序链表
 *
 */
public class code14_MergeKLists {
    public static class Node{
        private int value;
        private Node next;

        public Node(int value) {
            this.value = value;
        }
    }

    /**
     * 方法一：
     *  用合并两个链表（外排）的思想，谁小移谁，使用while(true)来表示每一次的最小值
     *  在一次循环中，遍历nodes数组的每一个当前位置节点，找到最小值节点
     *  TODO 注意
     *      1. 需要在每次循环中都让找到的最小值节点所在的链表往后移动一个
     *      2. 因为K个链表可能不是等长的，所以在遍历nodes数组时需要判断当前链表的当前位置节点是否为null
     *      3. 要在循环中设置退出条件：遍历完nodes数组后也没有找到最小值节点所在的链表时，表示停止
     *
     *  时间复杂度：每次遍历K个链表，一共循环N次 ==> O(K*N)
     */
    public static Node mergeKLists(Node[] nodes){
        if (nodes == null || nodes.length < 1){
            return null;
        }

        Node newHead = new Node(Integer.MIN_VALUE);
        Node cur = newHead;

        while (true){
            // 最小值节点
            Node minNode = null;
            // 最小值节点所在的链表
            int minIndex = -1;

            // 遍历每个链表的当前节点，找到最小值节点
            for (int i = 0; i < nodes.length; i++) {
                if (nodes[i] == null){
                    continue;
                }

                if (minNode == null || nodes[i].value < minNode.value){
                    minNode = nodes[i];
                    minIndex = i;
                }
            }

            // 退出条件
            if (minIndex == -1){
                break;
            }

            cur.next = minNode;
            cur = cur.next;
            nodes[minIndex] = nodes[minIndex].next;
        }

        Node res = newHead.next;
        newHead.next = null;

        return res;
    }

    /**
     * 方法二：
     *  使用小根堆将当前K个节点进行排序
     *  每次从小根堆的堆顶取值，然后将该节点所在的链表向后移动一个
     *  TODO 因为小根堆存放的是Node节点类型，所以可以直接将节点向后移动，不需要方法一中寻找节点所在的链表
     *
     *  时间复杂度：
     *      小根堆取出一个数的时间复杂度为 O(logN)，小根堆每次存K个数
     *      因此总时间复杂度为 O(N*logK)，空间复杂度 O(K)
     */
    public static Node mergeKLists1(Node[] nodes){
        if (nodes == null || nodes.length < 1){
            return null;
        }

        Node newHead = new Node(Integer.MIN_VALUE);
        Node cur = newHead;

        PriorityQueue<Node> pQ = new PriorityQueue<>(new Comparator<Node>() {
            @Override
            public int compare(Node o1, Node o2) {
                return o1.value - o2.value;
            }
        });

        for (int i = 0; i < nodes.length; i++) {
            pQ.add(nodes[i]);
        }

        while (!pQ.isEmpty()){
            // 最小值节点就是小根堆的堆顶
            Node minNode = pQ.poll();

            cur.next = minNode;
            cur = cur.next;

            // 最小值节点所在链表向后移动一个
            if (minNode.next != null){
                pQ.add(minNode.next);
            }
        }

        Node res = newHead.next;
        newHead.next = null;

        return res;
    }

    /**
     * 方法三：
     *  TODO MergeSort的分治思想：
     *     step1 分解：将原问题分解为若干个规模较小，相互独立，与原问题形式相同的子问题；
     *     step2 解决：若子问题规模较小而容易被解决则直接解，否则递归地解各个子问题
     *     step3 合并：将各个子问题的解合并为原问题的解
     *
     *  先将K个节点分为 k/2, k/4, k/8,...
     *  直到分成两个时，使用code12的mergeTwoList2递归版进行递归合并
     *
     * 时间复杂度：O(N*logK)
     * 空间复杂度：O(K)
     */
    public static Node mergeKLists2(Node[] nodes){
        if (nodes == null || nodes.length < 1){
            return null;
        }

        return mergeSort(nodes, 0, nodes.length - 1);
    }

    // 拆分成子问题，直到只剩下两个链表
    public static Node mergeSort(Node[] nodes, int l, int r){
        // base case
        if (l == r){
            return nodes[l];
        }

        int mid = (l + r) >> 1;

        Node node1 = mergeSort(nodes, l, mid);
        Node node2 = mergeSort(nodes, mid + 1, r);

        return merge(node1, node2);
    }

    // 合并两个有序链表：递归法
    public static Node merge(Node node1, Node node2){
        if (node1 == null && node2 == null) {
            return null;
        } else if (node1 == null) {
            return node2;
        } else if (node2 == null) {
            return node1;
        }

        Node head = null;

        if (node1.value < node2.value){
            head = node1;
            head.next = merge(node1.next, node2);
        }else {
            head = node2;
            head.next = merge(node1, node2.next);
        }

        return head;
    }

    public static void print(Node head){
        while (head != null){
            System.out.print(head.value + " ");
            head = head.next;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Node head1 = new Node(1);
        head1.next = new Node(4);
        head1.next.next = new Node(5);

        Node head2 = new Node(1);
        head2.next = new Node(3);
        head2.next.next = new Node(4);

        Node head3 = new Node(2);
        head3.next = new Node(6);

        Node[] nodes = new Node[]{head1, head2, head3};

        Node head = mergeKLists2(nodes);
        print(head); // 1 1 2 3 4 4 5 6
    }
}
