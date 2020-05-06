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

        public void makeSet(List<Node> nodes) {
            if (nodes == null) {
                return;
            }
            for (Node node : nodes) {
                this.fatherMap.put(node, node);
                this.sizeMap.put(node, 1);
            }
        }

        public Node findHead(Node node) {
            if (node == null) {
                return null;
            }
            Node cur = node;
            Node father = this.fatherMap.get(node);
            Stack<Node> stack = new Stack<>();
            while (cur != father) {
                stack.push(cur);
                cur = father;
                father = this.fatherMap.get(cur);
            }
            while (!stack.isEmpty()) {
                this.fatherMap.put(stack.pop(), father);
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
            Node aHead = findHead(a);
            Node bHead = findHead(b);

            if (aHead != bHead){
                int aSetSize = this.sizeMap.get(aHead);
                int bSetSize = this.sizeMap.get(bHead);
                if (aSetSize <= bSetSize) {
                    this.fatherMap.put(aHead, bHead);
                    this.sizeMap.put(bHead, aSetSize + bSetSize);
                } else {
                    this.fatherMap.put(bHead, aHead);
                    this.sizeMap.put(aHead, aSetSize + bSetSize);
                }
            }
        }
    }
}
