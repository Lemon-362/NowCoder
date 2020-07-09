package NowCoder.Hard.exercise;

import java.util.HashMap;
import java.util.List;
import java.util.Stack;

public class basic_code33 {
    public static class Node {

    }

    // makeSet findHead isSameSet union
    public static class UnionFindSet {
        private HashMap<Node, Integer> sizeMap;
        private HashMap<Node, Node> parentMap;

        public UnionFindSet() {
            this.sizeMap = new HashMap<>();
            this.parentMap = new HashMap<>();
        }

        public void makeSet(List<Node> list){
            if (list == null){
                return;
            }

            for (Node node : list) {
                sizeMap.put(node, 1);
                parentMap.put(node, node);
            }
        }

        public Node findHead(Node node){
            if (node == null){
                return null;
            }

            Node parent = parentMap.get(node);
            Stack<Node> stack = new Stack<>();

            while (parent != node){
                stack.push(node);
                node = parent;
                parent = parentMap.get(node);
            }

            while (!stack.isEmpty()){
                parentMap.put(stack.pop(), parent);
            }

            return parent;
        }

        public boolean isSameSet(Node head1, Node head2){
            if (head1 == null || head2 == null){
                return false;
            }

            return findHead(head1) == findHead(head2);
        }

        public void union(Node head1, Node head2) {
            if (head1 == null || head2 == null) {
                return;
            }

            Node parent1 = findHead(head1);
            Node parent2 = findHead(head2);

            if (parent1 != parent2) {
                int size1 = sizeMap.get(parent1);
                int size2 = sizeMap.get(parent2);

                if (size1 < size2) {
                    sizeMap.put(parent2, size1 + size2);
                    parentMap.put(parent1, parent2);
                } else {
                    sizeMap.put(parent1, size1 + size2);
                    parentMap.put(parent2, parent1);
                }
            }
        }
    }
}
