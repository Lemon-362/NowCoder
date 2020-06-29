package NowCoder.Nowcoder;

/*
    请实现一个函数，用来判断一颗二叉树是不是对称的。
    注意，如果一个二叉树同此二叉树的镜像是同样的，定义其为对称的。
    也就是说，以头节点为中心的对称轴，左右是镜像的。
 */
public class code58_IsMirrorTree {
    public static class Node {
        int value = 0;
        Node left = null;
        Node right = null;

        public Node(int value) {
            this.value = value;
        }
    }

    public static boolean isMirrorTree(Node head) {
        if (head == null) {
            return true;
        }
        // 一层一层的比较每个节点，递归
        return process(head.left, head.right);
    }

    public static boolean process(Node left, Node right){
        // base case
        if (left == null && right == null){
            return true;
        }else if (left == null || right == null){
            return false;
        }

        if (left.value == right.value){
            return process(left.left, right.right) && process(left.right, right.left);
        }else {
            return false;
        }
    }

    public static void main(String[] args) {
        Node head = new Node(1);
        head.left = new Node(2);
        head.right = new Node(2);
        head.left.left = new Node(4);
        head.left.right = new Node(5);
        head.right.left = new Node(5);
        head.right.right = new Node(4);

        Node head1 = new Node(1);
        head1.left = new Node(2);
        head1.right = new Node(2);
        head1.left.left = new Node(4);
        head1.left.right = new Node(3);
        head1.right.left = new Node(5);
        head1.right.right = new Node(4);

        System.out.println(isMirrorTree(head)); // true
        System.out.println(isMirrorTree(head1)); // false
    }
}
