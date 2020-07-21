package NowCoder.LeetCode.Tags.Hot100.Tree.exercise;

public class code11 {
        public static class Node {
        private int value;
        private Node left;
        private Node right;

        public Node(int value) {
            this.value = value;
        }
    }

    // 深度优先
    public static Node lowestCommonAncestor(Node head, Node p, Node q) {
        // base case
        if (head == null){
            return null;
        }else if (head == p){
            return p;
        }else if (head == q){
            return q;
        }

        Node left = lowestCommonAncestor(head.left, p, q);
        Node right = lowestCommonAncestor(head.right, p, q);

        // 1
        if (left != null && right != null){
            return head;
        }else if (left == null && right == null){
            return null;
        }else {
            return left == null ? right : left;
        }
    }

    public static void main(String[] args){
        Node head = new Node(3);
        Node head1 = new Node(5);
        Node head2 = new Node(1);
        Node head3 = new Node(6);
        Node head4 = new Node(2);
        Node head5 = new Node(0);
        Node head6 = new Node(8);
        Node head7 = new Node(7);
        Node head8 = new Node(4);

        head.left = head1;
        head.right = head2;
        head1.left = head3;
        head1.right = head4;
        head2.left = head5;
        head2.right = head6;
        head4.left = head7;
        head4.right = head8;

        System.out.println(lowestCommonAncestor(head, head1, head2).value); // 3
        System.out.println(lowestCommonAncestor(head, head7, head8).value); // 2

    }
}
