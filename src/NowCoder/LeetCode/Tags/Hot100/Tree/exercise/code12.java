package NowCoder.LeetCode.Tags.Hot100.Tree.exercise;

public class code12 {
    public static class Node {
        private int value;
        private Node left;
        private Node right;

        public Node(int value) {
            this.value = value;
        }
    }

    public static int rob(Node head) {
        if (head == null) {
            return 0;
        }

        int rob = process(head).rob;
        int no_rob = process(head).no_rob;

        return Math.max(rob, no_rob);
    }

    public static class ReturnData {
        private int rob;
        private int no_rob;

        public ReturnData(int rob, int no_rob) {
            this.rob = rob;
            this.no_rob = no_rob;
        }
    }

    public static ReturnData process(Node head){
        // base case
        if (head == null){
            return new ReturnData(0, 0);
        }

        int rob = head.value;
        int no_rob = 0;

        ReturnData leftData = process(head.left);
        ReturnData rightData = process(head.right);

        rob += leftData.no_rob + rightData.no_rob;

        no_rob += Math.max(leftData.rob, leftData.no_rob) + Math.max(rightData.rob, rightData.no_rob);

        return new ReturnData(rob, no_rob);
    }


    public static void main(String[] args) {
        Node head = new Node(3);
        head.left = new Node(4);
        head.right = new Node(5);
        head.left.left = new Node(1);
        head.left.right = new Node(3);
        head.right.right = new Node(1);

        System.out.println(rob(head)); // 9
    }
}
