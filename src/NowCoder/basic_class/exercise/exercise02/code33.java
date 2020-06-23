package NowCoder.basic_class.exercise.exercise02;

import java.util.HashMap;
import java.util.List;
import java.util.Stack;

public class code33 {
    public static class Node {

    }

    // makeSet findHead isSameSet union
    public static class UnionFindSet {
        private HashMap<Node, Integer> sizeMap;
        private HashMap<Node, Node> fatherMap;

        public UnionFindSet() {
            this.sizeMap = new HashMap<>();
            this.fatherMap = new HashMap<>();
        }

        public void makeSet(List<Node> list) {
            if (list == null) {
                return;
            }

            for (Node node : list) {
                sizeMap.put(node, 1);
                fatherMap.put(node, node);
            }
        }

        public Node findHead(Node node) {
            if (node == null) {
                return null;
            }

            Stack<Node> stack = new Stack<>();
            Node father = fatherMap.get(node);

            while (father != node) {
                stack.push(node);
                node = father;
                father = fatherMap.get(node);
            }

            while (!stack.isEmpty()) {
                fatherMap.put(stack.pop(), father);
            }

            return father;
        }

        public boolean isSameSet(Node head1, Node head2) {
            if (head1 == null || head2 == null) {
                return false;
            }

            return findHead(head1) == findHead(head2);
        }

        public void union(Node head1, Node head2) {
            if (head1 == null || head2 == null) {
                return;
            }

            Node father1 = findHead(head1);
            Node father2 = findHead(head2);

            if (father1 != father2) {
                int size1 = sizeMap.get(father1);
                int size2 = sizeMap.get(father2);

                if (size1 < size2) {
                    sizeMap.put(father2, size1 + size2);
                    fatherMap.put(father1, father2);
                } else {
                    sizeMap.put(father1, size1 + size2);
                    fatherMap.put(father2, father1);
                }
            }
        }
    }
}
