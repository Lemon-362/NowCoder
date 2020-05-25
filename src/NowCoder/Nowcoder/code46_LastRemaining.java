package NowCoder.Nowcoder;

/*
    圆圈中最后剩下的数字：
        0,1,,n-1这n个数字排成一个圆圈，从数字0开始，每次从这个圆圈里删除第m个数字。
        求出这个圆圈里剩下的最后一个数字。
例如，0、1、2、3、4这5个数字组成一个圆圈，从数字0开始每次删除第3个数字，
则删除的前4个数字依次是2、0、4、1，因此最后剩下的数字是3

    TODO 约瑟夫环形链表：f(n, m) = 0, n = 1
                                [f(n-1, m) + m] % n, n > 1
         如果是从1开始的，那么结果要+1。如果从0开始，那么不需要。

 */
public class code46_LastRemaining {
    public static int lastRemaining(int n, int m){
        if (n < 1 || m < 1){
            return -1;
        }
        int res = 0;

        for (int i = 2; i <= n; i++) {
            res = (res + m) % i;
        }

        return res;
    }

    public static class Node {
        private int value;
        private Node next;

        public Node(int value) {
            this.value = value;
        }
    }

    public static Node generateList(int n){
        Node head = new Node(0);
        Node cur = head;

        for (int i = 1; i < n - 1; i++) {
            Node next = new Node(i);
            cur.next = next;
            cur = next;
        }

        cur.next = new Node(n - 1);
        cur.next.next = head;

        return head;
    }

    public static int lastRemaining2(int n, int m) {
        Node head = generateList(n);

		if (head.next == head || m < 1) {
			return head.value;
		}
		Node cur = head.next;
		int tmp = 1; // tmp -> list size
		while (cur != head) {
			tmp++;
			cur = cur.next;
		}
		tmp = getLive(tmp, m); // tmp -> service node position
		while (--tmp != 0) {
			head = head.next;
		}
		head.next = head;
		return head.value;
	}

	public static int getLive(int i, int m) { // 长度为i，报到m杀人
		if (i == 1) {
			return 1;
		}
		return (getLive(i - 1, m) + m - 1) % i + 1;
	}

    public static void main(String[] args) {
        System.out.println(lastRemaining(5, 3)); // 3

        System.out.println(lastRemaining2(10, 17)); // 2
    }
}
