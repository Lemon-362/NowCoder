package NowCoder.Nowcoder.exercise;

public class code62 {
    public static class Node{
        private int value;
        private Node left;
        private Node right;

        public Node(int value) {
            this.value = value;
        }
    }

    public static Node KthNode(Node head, int k){
        if (head == null || k < 1){
            return null;
        }

        return morrisIn(head, k);
    }

    public static Node morrisIn(Node head, int k){
        Node cur = head;
        Node mostRight = null;

        while (cur != null){
            mostRight = cur.left;
            if (mostRight != null){
                while (mostRight.right != null && mostRight.right != cur){
                    mostRight = mostRight.right;
                }
                if (mostRight.right == null){
                    mostRight.right = cur;
                    cur = cur.left;
                }else {
                    mostRight.right = null;

                    k--;
                    if (k == 0){
                        return cur;
                    }

                    cur = cur.right;
                }
            }else {
                k--;
                if (k == 0){
                    return cur;
                }

                cur = cur.right;
            }
        }

        return null;
    }

    public static void main(String[] args) {
        Node head = new Node(5);
        head.left = new Node(3);
        head.right = new Node(6);
        head.left.left = new Node(2);
        head.left.right = new Node(4);
        head.left.left.left = new Node(1);

        System.out.println(KthNode(head, 3).value); // 3
    }
}
