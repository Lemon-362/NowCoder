package NowCoder.LeetCode.Tags.Hot100.Tree.exercise;

public class code5 {
        public static class Node{
        private int value;
        private Node left;
        private Node right;

        public Node(int value) {
            this.value = value;
        }
    }

    public static int sum = 0;

    public static Node convertBST1(Node head){
        if (head == null) {
            return null;
        }

        convertBST1(head.right);

        sum += head.value;
        head.value = sum;

        convertBST1(head.left);

        return head;
    }

    public static Node convertBST2(Node head){
        if (head == null) {
            return null;
        }

        int sum = 0;
        Node cur = head;
        Node mostLeft = null;

        while (cur != null){
            mostLeft = cur.right;
            if (mostLeft != null){
                while (mostLeft.left != null && mostLeft.left != cur){
                    mostLeft = mostLeft.left;
                }
                if (mostLeft.left == null){
                    mostLeft.left = cur;

                    cur = cur.right;
                }else {
                    mostLeft.left = null;

                    sum += cur.value;
                    cur.value = sum;

                    cur = cur.left;
                }
            }else {
                sum += cur.value;
                cur.value = sum;

                cur = cur.left;
            }
        }

        return head;
    }

    public static void printInOrder(Node head){
        if (head == null){
            return;
        }

        printInOrder(head.left);
        System.out.print(head.value + " ");
        printInOrder(head.right);

    }

    public static void main(String[] args) {
        Node head = new Node(5);
        head.left = new Node(3);
        head.right = new Node(7);
        head.left.left = new Node(2);
        head.left.right = new Node(4);
        head.right.left = new Node(6);
        head.right.right = new Node(8);

        printInOrder(head); // 2 3 4 5 6 7 8
        System.out.println();

//        Node head1 = convertBST1(head);
//        printInOrder(head1); // 35 33 30 26 21 15 8

        Node head2 = convertBST2(head);
        printInOrder(head2); // 35 33 30 26 21 15 8

    }
}
