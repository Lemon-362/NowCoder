package NowCoder.basic_class.exercise01.HashMap;

import java.util.HashMap;
import java.util.List;
import java.util.Stack;

public class UnionFindSet {
    public static class Node {

    }

    public static class UnionFind {
        private HashMap<Node, Integer> sizeMap;
        private HashMap<Node, Node> parentMap;

        public UnionFind() {
            this.sizeMap = new HashMap<>();
            this.parentMap = new HashMap<>();
        }

        public void makeSet(List<Node> list) {
            if (list == null) {
                return;
            }

            for (Node node : list) {
                this.sizeMap.put(node, 1);
                this.parentMap.put(node, node);
            }
        }

        public Node findHead(Node node){
            if (node == null){
                return null;
            }

            Node cur = node;
            Node parent = this.parentMap.get(cur);
            Stack<Node> stack = new Stack<>();

            while (cur != parent){
                stack.push(cur);

                cur = parent;
                parent = this.parentMap.get(cur);
            }

            while (!stack.isEmpty()){
                this.parentMap.put(stack.pop(), parent);
            }

            return parent;
        }

        public boolean isSameSet(Node node1, Node node2){
            if (node1 == null || node2 == null){
                return false;
            }

            return findHead(node1) == findHead(node2);
        }

        public void union(Node node1, Node node2){
            if (node1 == null || node2 == null){
                return;
            }

            Node parent1 = findHead(node1);
            Node parent2 = findHead(node2);

            if (parent1 != parent2){
                int size1 = this.sizeMap.get(parent1);
                int size2 = this.sizeMap.get(parent2);

                if (size1 <= size2){
                    this.sizeMap.put(parent2, size1 + size2);
                    this.parentMap.put(parent1, parent2);
                }else {
                    this.sizeMap.put(parent1, size1 + size2);
                    this.parentMap.put(parent2, parent1);
                }
            }
        }
    }
}
