package NowCoder.advanced_class.exercise.exercise01;

public class code03 {
    public static class Node {
        private int value;
        private Node left;
        private Node right;

        public Node(int value) {
            this.value = value;
        }
    }

    public static boolean isSubTree(Node head1, Node head2) {
        if (head1 == null || head2 == null) {
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

        return p2 == str2.length;
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

        String res = head.value + "_";
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

        System.out.println("========子结构==============");

        Node head = new Node(1);
        head.left = new Node(2);
        head.right = new Node(3);
        head.left.left = new Node(4);
        head.left.right = new Node(5);
        head.left.left.left = new Node(6);

        Node head1 = new Node(2);
        head1.left = new Node(4);

        System.out.println(isSubTree(head, head1)); // false
    }
}
