package NowCoder.basic_class.exercise;

import java.util.HashMap;
import java.util.List;
import java.util.Stack;

/*
	并查集：
		查询两个元素是否属于同一集合
		合并两个元素所在的集合

	makeSet  findHead  isSameSet  union

		先将每个数各自形成一个集合，往上节点指回自己（代表节点）

	查询：均向上找，找到代表节点时停止，若代表节点相同，则在同一集合中
	合并：找到代表节点，将元素少的挂在元素多的集合上

	优化：对于某节点，往上找到代表节点后，将从该节点向上的所有节点，
		  都扁平化，直接连在代表节点上
 */
public class code33_UnionFind {
    public static class Node{

    }

    public static class UnionFindSet{
        private HashMap<Node, Node> fatherMap;
        private HashMap<Node, Integer> sizeMap;

        public UnionFindSet() {
            this.fatherMap = new HashMap<>();
            this.sizeMap = new HashMap<>();
        }

        public void makeSet(List<Node> nodes){
            if (nodes == null){
                return;
            }
            for (Node node : nodes) {
                this.fatherMap.put(node, node);
                this.sizeMap.put(node, 1);
            }
        }

        public Node findHead(Node node){
            if (node == null){
                return null;
            }
            Stack<Node> stack = new Stack<>();
            Node cur = node;
            Node parent = this.fatherMap.get(node);
            while (cur != parent){
                stack.push(cur);
                cur = parent;
                parent = this.fatherMap.get(cur);
            }
            while (!stack.isEmpty()){
                this.fatherMap.put(stack.pop(), parent);
            }
            return parent;
        }

        public boolean isSameSet(Node a, Node b){
            if (a == null || b == null){
                return false;
            }
            return findHead(a) == findHead(b);
        }

        public void union(Node a, Node b){
            if (a == null || b == null){
                return;
            }
            Node aHead = findHead(a);
            Node bHead = findHead(b);
            if (aHead != bHead){
                int aSetSize = this.sizeMap.get(aHead);
                int bSetSize = this.sizeMap.get(bHead);
                if (aSetSize <= bSetSize){
                    this.fatherMap.put(aHead, bHead);
                    this.sizeMap.put(bHead, aSetSize + bSetSize);
                }else {
                    this.fatherMap.put(bHead, aHead);
                    this.sizeMap.put(aHead, aSetSize + bSetSize);
                }
            }
        }
    }

}
