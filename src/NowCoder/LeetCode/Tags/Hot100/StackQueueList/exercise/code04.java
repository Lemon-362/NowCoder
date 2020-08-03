package NowCoder.LeetCode.Tags.Hot100.StackQueueList.exercise;

import java.util.Comparator;
import java.util.PriorityQueue;

/*
23. 合并K个排序链表
    给定K个已排序的链表，将它们合并成一个新的排序链表

 */
public class code04 {
    public static class Node {
        private int value;
        private Node next;

        public Node(int value) {
            this.value = value;
        }
    }


    public static Node mergeKLists1(Node[] nodes) {
        if (nodes == null || nodes.length < 1) {
            return null;
        }

        PriorityQueue<Node> pQ = new PriorityQueue<>(new Comparator<Node>() {
            @Override
            public int compare(Node o1, Node o2) {
                return o1.value - o2.value;
            }
        });

        Node newHead = new Node(Integer.MIN_VALUE);
        Node cur = newHead;

        for (int i = 0; i < nodes.length; i++) {
            pQ.add(nodes[i]);
        }

        while (!pQ.isEmpty()){
            Node node = pQ.poll();

            cur.next = node;
            cur = cur.next;

            if (node.next != null){
                pQ.add(node.next);
            }
        }

        return newHead.next;
    }

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

        Node left = mergeKLists(nodes, l, mid);
        Node right = mergeKLists(nodes, mid + 1, r);

        return merge(left, right);
    }

    public static Node merge(Node head1, Node head2){
        // base case
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
            head.next = merge(head1.next, head2);
        }else {
            head = head2;
            head.next = merge(head1, head2.next);
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
