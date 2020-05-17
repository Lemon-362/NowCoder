package NowCoder.basic_class.exercise01.HashMap;

import java.util.HashMap;
import java.util.List;
import java.util.Stack;

public class UnionFindSet {
    public static class Node {

    }

    public static class UnionFind {
        private HashMap<Node, Node> fatherMap;
        private HashMap<Node, Integer> sizeMap;

        public UnionFind() {
            this.fatherMap = new HashMap<>();
            this.sizeMap = new HashMap<>();
        }

        public void makeSet(List<Node> list) {
            if (list == null) {
                return;
            }

            for (Node node : list) {
                fatherMap.put(node, node);
                sizeMap.put(node, 1);
            }
        }

        public Node findHead(Node node) {
            if (node == null) {
                return null;
            }

            Node cur = node;
            Node father = fatherMap.get(cur);
            Stack<Node> stack = new Stack<>();

            while (cur != father) {
                stack.push(cur);
                cur = father;
                father = fatherMap.get(cur);
            }

            while (!stack.isEmpty()) {
                fatherMap.put(stack.pop(), father);
            }

            return father;
        }

        public boolean isSameSet(Node a, Node b) {
            if (a == null || b == null) {
                return false;
            }

            return findHead(a) == findHead(b);
        }

        public void union(Node a, Node b) {
            if (a == null || b == null) {
                return;
            }

            Node father1 = fatherMap.get(a);
            Node father2 = fatherMap.get(b);

            if (father1 != father2) {
                int size1 = sizeMap.get(father1);
                int size2 = sizeMap.get(father2);

                if (size1 < size2) {
                    fatherMap.put(father1, father2);
                    sizeMap.put(father2, size1 + size2);
                } else {
                    fatherMap.put(father2, father2);
                    sizeMap.put(father1, size1 + size2);
                }
            }
        }
    }
}
