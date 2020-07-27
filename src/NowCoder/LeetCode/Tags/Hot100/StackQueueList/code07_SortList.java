package NowCoder.LeetCode.Tags.Hot100.StackQueueList;

/*
148. 排序链表
    在 O(n log n) 时间复杂度和常数级空间复杂度下，对链表进行排序

 */
public class code07_SortList {
    public static class Node{
        private int value;
        private Node next;

        public Node(int value) {
            this.value = value;
        }
    }

    /*
    链表问题:
        要求时间复杂度O(N*logN) ==> 二分 ==> 链表排序 ==> 归并排序

        递归法的归并排序:
            归并排序要求从中间mid开始向两边拆, 所以首先要找到链表的中间节点
            然后断开next指针, 再向两边拆, 最后使用两个有序链表的合并的方法

            空间复杂度: O(N)
     */
    public static Node sortList(Node head){
        if (head == null){
            return null;
        }

        return mergeSort(head);
    }

    public static Node mergeSort(Node head){
        // TODO base case: 如果仅有一个节点, 也直接返回
        if (head == null || head.next == null){
            return head;
        }

        // 找到mid中间节点
        Node midNode = findMidNode(head);
        // 断开next指针
        Node next = midNode.next;
        midNode.next = null;

        // 向两边递归, 拆分
        Node left = mergeSort(head);
        Node right = mergeSort(next);

        // 合并
        return mergeTwoLists(left, right);
    }

    public static Node findMidNode(Node head){
        // TODO 防止没有快指针的位置
        if (head == null || head.next == null){
            return null;
        }

        Node p1 = head;
        Node p2 = head.next.next;

        // TODO p2.next != null: 链表长度可能是偶数, 那么就要找上中位数
        while (p2 != null && p2.next != null){
            p1 = p1.next;
            p2 = p2.next.next;
        }

        return p1;
    }

    public static Node mergeTwoLists(Node head1, Node head2){
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
            head.next = mergeTwoLists(head1.next, head2);
        }else {
            head = head2;
            head.next = mergeTwoLists(head1, head2.next);
        }

        return head;
    }

    public static void printList(Node head) {
        if (head == null) {
            return;
        }
        while (head != null) {
            System.out.print(head.value + " ");
            head = head.next;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Node head = new Node(4);
        head.next = new Node(2);
        head.next.next = new Node(1);
        head.next.next.next = new Node(3);

        Node newHead = sortList(head);

        printList(newHead);
    }

}
