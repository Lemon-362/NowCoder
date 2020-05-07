package NowCoder.advanced_class.exercise;

/*
    KMP应用2：
		已知两棵树t1和t2，问t1的某一个子树是否和t2相同？
			子树：从某一节点开始往下的所有节点，而不是一部分
	解法：序列化后比较
	TODO 因为序列化的过程中，节点为null的也算在内了，所以如果只是其中一部分，那么最后为null的序列化和整体是对不上的
 */
public class code03_KMP_Tree1ContainTree2 {
    public static class Node {
        private int val;
        private Node left;
        private Node right;

        public Node(int val) {
            this.val = val;
        }
    }

    public static boolean isSubTree(Node head1, Node head2){
        if (head1 == null || head2 == null){
            return false;
        }

        char[] str1 = serial(head1).toCharArray();
        char[] str2 = serial(head2).toCharArray();

        int[] next = getNextArr(str2);

        int p1 = 0;
        int p2 = 0;
        while (p1 < str1.length && p2 < str2.length){
            if (str1[p1] == str2[p2]){
                p1++;
                p2++;
            }else {
                if (next[p2] == -1){
                    p1++;
                }else {
                    p2 = next[p2];
                }
            }
        }

        int len = p2 == str2.length ? p1 - p2 : -1;
        return len != -1;
    }

    public static int[] getNextArr(char[] str){
        int[] next = new int[str.length];

        next[0] = -1;
        next[1] = 0;
        int p = 2;
        int cn = 0;

        while (p < next.length){
            if (str[p - 1] == str[cn]){
                next[p++] = ++cn;
            }else if (cn > 0){
                cn = next[cn];
            }else {
                next[p++] = 0;
            }
        }

        return next;
    }

    public static String serial(Node head){
        if (head == null){
            return "#_";
        }

        String res = head.val + "_";
        res += serial(head.left);
        res += serial(head.right);

        return res;
    }


    public static void main(String[] args) {
        Node t1 = new Node(1);
        t1.left = new Node(2);
        t1.right = new Node(3);
        t1.left.left = new Node(4);
        t1.left.right = new Node(5);
        t1.right.left = new Node(6);
        t1.right.right = new Node(7);
        t1.left.left.right = new Node(8);
        t1.left.right.left = new Node(9);

        Node t2 = new Node(2);
        t2.left = new Node(4);
        t2.left.right = new Node(8);
        t2.right = new Node(5);
        t2.right.left = new Node(9);

        System.out.println(serial(t1));
        // 1_2_4_#_8_#_#_5_9_#_#_#_3_6_#_#_7_#_#_
        System.out.println(serial(t2));
        // 2_4_#_8_#_#_5_9_#_#_#_

        System.out.println(isSubTree(t1, t2)); // true
    }
}
