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

        // 制作每个节点的集合
        public void makeSet(List<Node> list){
            if (list == null){
                return;
            }

            for (Node node : list) {
                this.parentMap.put(node, node);
                this.sizeMap.put(node, 1);
            }
        }

        // 向上查找代表节点
        public Node findParentNode(Node node){
            if (node == null){
                return null;
            }

            Stack<Node> stack = new Stack<>();
            Node cur = node;
            Node parent = this.parentMap.get(cur);

            while (parent != cur){
                stack.push(cur);
                cur = parent;
                parent = this.parentMap.get(cur);
            }

            return parent;
        }

        // 查询是否在同一集合
        public boolean isSameSet(Node node1, Node node2){
            if (node1 == null || node2 == null){
                return false;
            }

            return findParentNode(node1) == findParentNode(node2);
        }

        // 合并两个集合
        public void union(Node node1, Node node2){
            if (node1 == null || node2 == null){
                return;
            }

            Node parent1 = findParentNode(node1);
            Node parent2 = findParentNode(node2);

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
