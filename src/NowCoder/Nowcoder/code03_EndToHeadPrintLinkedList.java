package NowCoder.Nowcoder;

/*
    从尾到头打印链表：
        输入一个链表，从尾到头的顺序返回一个ArrayList
*/

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class code03_EndToHeadPrintLinkedList {
    public static class ListNode {
        private int val;
        private ListNode next;

        public ListNode(int val) {
            this.val = val;
        }
    }

    // 法一：从尾到头 --> 栈
    public static ArrayList<Integer> endToHeadPrint01(ListNode listNode) {
        if (listNode == null) {
            return null;
        }
        ArrayList<Integer> list = new ArrayList<>();
        Stack<Integer> stack = new Stack<>();
        while (listNode != null) {
            stack.push(listNode.val);
            listNode = listNode.next;
        }
        while (!stack.isEmpty()) {
            list.add(stack.pop());
        }
        return list;
    }

    // 法二：调用ArrayList的方法 add(index, value) 每次都加在第一个位置上
    public static ArrayList<Integer> endToHeadPrint02(ListNode listNode){
        if (listNode == null) {
            return null;
        }
        ArrayList<Integer> arrayList = new ArrayList<>();
        while (listNode != null){
            arrayList.add(0, listNode.val);
            listNode = listNode.next;
        }
        return arrayList;
    }

    // 法三：改成递归形式 注意：arrayList要在外面定义好
    static ArrayList<Integer> arrayList = new ArrayList<>();

    public static ArrayList<Integer> endToHeadPrint03(ListNode listNode){
        if (listNode == null) {
            return null;
        }
        if (listNode != null){
            endToHeadPrint03(listNode.next);
            arrayList.add(listNode.val);
        }
        return arrayList;
    }

    public static void main(String[] args) {
        ListNode listNode = new ListNode(3);
        listNode.next = new ListNode(5);
        listNode.next.next = new ListNode(6);
        listNode.next.next.next = new ListNode(8);

        ArrayList<Integer> arrayList = endToHeadPrint01(listNode);
        for (int i = 0; i < arrayList.size(); i++) {
            System.out.print(arrayList.get(i) + " ");
        }
        System.out.println();

        ArrayList<Integer> arrayList1 = endToHeadPrint02(listNode);
        for (int i = 0; i < arrayList1.size(); i++) {
            System.out.print(arrayList1.get(i) + " ");
        }
        System.out.println();

        ArrayList<Integer> arrayList2 = endToHeadPrint03(listNode);
        for (int i = 0; i < arrayList2.size(); i++) {
            System.out.print(arrayList2.get(i) + " ");
        }
        System.out.println();
    }
}
