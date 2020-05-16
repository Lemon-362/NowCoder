package NowCoder.basic_class.exercise.exercise02;

import java.util.HashMap;
import java.util.List;
import java.util.Stack;

public class code33 {
    public static class Node{

    }

    public static class UnionFindSet {
        private HashMap<Node, Node> parentMap;
        private HashMap<Node, Integer> sizeMap;

        public UnionFindSet() {
            this.parentMap = new HashMap<>();
            this.sizeMap = new HashMap<>();
        }

        public void makeSet(List<Node> list){
            if (list == null){
                return;
            }

            for (Node node : list) {
                this.parentMap.put(node, node);
                this.sizeMap.put(node, 1);
            }
        }

        public Node findParent(Node node){
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

            return parent;
        }

        public boolean isSameSet(Node a, Node b){
            if (a == null || b == null){
                return false;
            }

            return this.findParent(a) == this.findParent(b);
        }

        public void union(Node a, Node b){
            if (a == null || b == null){
                return;
            }

            Node parent1 = this.findParent(a);
            Node parent2 = this.findParent(b);

            if (parent1 != parent2){
                int size1 = this.sizeMap.get(parent1);
                int size2 = this.sizeMap.get(parent2);

                if (size1 < size2){
                    this.parentMap.put(parent1, parent2);
                    this.sizeMap.put(parent2, size1 + size2);
                }else {
                    this.parentMap.put(parent2, parent1);
                    this.sizeMap.put(parent1, size1 + size2);
                }
            }
        }
    }
}
