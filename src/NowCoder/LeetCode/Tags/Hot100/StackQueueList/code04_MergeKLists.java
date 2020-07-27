package NowCoder.LeetCode.Tags.Hot100.StackQueueList;

import java.util.Comparator;
import java.util.PriorityQueue;

/*
23. 合并K个排序链表
    给定K个已排序的链表，将它们合并成一个新的排序链表

 */
public class code04_MergeKLists {
    public static class Node {
        private int value;
        private Node next;

        public Node(int value) {
            this.value = value;
        }
    }

    /*
    链表问题:
        维护一个k大小的小根堆, 里面存放Node类型
        因为size=k, 所以可以保证每次都是在k个链表中找最小值
        每次取出堆顶元素, 然后将该节点所在链表后移

        TODO 思想:
            将链表数组就看成是一个数组, 长度为k
            每次找出最小值 ==> 小根堆
            后移之后依然是k大小的数组

        时间复杂度：
            小根堆取出一个数的时间复杂度为 O(logK)，小根堆每次存K个数
            因此总时间复杂度为 O(N*logK)，空间复杂度 O(K)
     */
    public static Node mergeKLists1(Node[] nodes) {
        if (nodes == null || nodes.length < 1) {
            return null;
        }

        Node newHead = new Node(Integer.MIN_VALUE);
        Node cur = newHead;

        PriorityQueue<Node> pQ = new PriorityQueue<>(new myComparator());

        for (int i = 0; i < nodes.length; i++) {
            pQ.add(nodes[i]);
        }

        while (!pQ.isEmpty()) {
            Node node = pQ.poll();

            cur.next = node;
            cur = cur.next;

            // 需要判断next是否为空
            if (node.next != null) {
                node = node.next;
                pQ.add(node);
            }
        }

        return newHead.next;
    }

    public static class myComparator implements Comparator<Node> {

        @Override
        public int compare(Node o1, Node o2) {
            return o1.value - o2.value;
        }
    }

    /*
    链表问题:
        k个链表 ==> 采取分治思想, 划分为子问题, 直到划分到两个链表时, 开始使用合并两个有序链表来合并

        TODO MergeSort的分治思想:
            step1 分解：将原问题分解为若干个规模较小，相互独立，与原问题形式相同的子问题；
            step2 解决：若子问题规模较小而容易被解决则直接解，否则递归地解各个子问题
            step3 合并：将各个子问题的解合并为原问题的解

        TODO 思想:
             对于数组, 原问题如果可以拆分成多个子问题, 并且子问题的求解和原问题的方式是相同的时候
             可以使用分治思想, 先拆分成多个子问题, 递归到只剩两个对象时, 然后再两两合并

        时间复杂度：O(N*logK)
        空间复杂度：O(K)

     */
    public static Node mergeKLists2(Node[] nodes){
        if (nodes == null || nodes.length < 1) {
            return null;
        }

        return mergeKLists(nodes, 0, nodes.length - 1);
    }

    public static Node mergeKLists(Node[] nodes, int l, int r){
        // base case
        if (l == r){
            return nodes[l];
        }

        int mid = (l + r) >> 1;

        // 拆
        Node node1 = mergeKLists(nodes, l, mid);
        Node node2 = mergeKLists(nodes, mid + 1, r);
        // 合
        return mergeTwoLists(node1, node2);
    }

    public static Node mergeTwoLists(Node head1, Node head2){
        if (head1 == null && head2 == null){
            return null;
        }else if (head1 == null){
            return head2;
        }else if (head2 == null){
            return head1;
        }

        Node head = null;

        if (head1.value < head2.value){
            head = head1;
            head.next = mergeTwoLists(head1.next, head2);
        }else {
            head = head2;
            head.next = mergeTwoLists(head1, head2.next);
        }

        return head;
    }

    public static void print(Node head) {
        while (head != null) {
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

//        Node head = mergeKLists1(nodes);
        Node head = mergeKLists2(nodes);
        print(head); // 1 1 2 3 4 4 5 6
    }
}
