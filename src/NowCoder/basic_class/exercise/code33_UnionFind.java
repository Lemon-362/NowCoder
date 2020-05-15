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
        // 用于往上找代表节点
        private HashMap<Node, Node> fatherMap;
        // 用于查询节点所在集合共有多少个节点
        private HashMap<Node, Integer> sizeMap;

        public UnionFindSet() {
            this.fatherMap = new HashMap<>();
            this.sizeMap = new HashMap<>();
        }

        // 首先对每个节点各自形成集合，自己作为代表节点
        public void makeSet(List<Node> nodes){
            if (nodes == null){
                return;
            }
            for (Node node : nodes) {
                this.fatherMap.put(node, node);
                this.sizeMap.put(node, 1);
            }
        }

        // 向上查找某一节点的代表节点 TODO 在向上找的过程中扁平化所有节点
        public Node findHead(Node node){
            if (node == null){
                return null;
            }
            // stack用于存储从该节点往上的所有节点，用于扁平化处理
            Stack<Node> stack = new Stack<>();

            Node cur = node;
            Node parent = this.fatherMap.get(node);

            // 找代表节点，也就是找一个节点，他的代表节点指向自己
            while (cur != parent){
                stack.push(cur);
                cur = parent;
                parent = this.fatherMap.get(cur);
            }

            // 扁平化，将从该节点往上的所有节点都直接连到代表节点上
            while (!stack.isEmpty()){
                this.fatherMap.put(stack.pop(), parent);
            }

            return parent;
        }

        // 查询两个节点是否在同一集合中，也就是看两个节点的代表节点是否相同
        public boolean isSameSet(Node a, Node b){
            if (a == null || b == null){
                return false;
            }
            return findHead(a) == findHead(b);
        }

        // 合并两个节点所在的集合
        public void union(Node a, Node b){
            if (a == null || b == null){
                return;
            }
            // TODO 在找代表节点的同时，已经做了扁平化处理了
            Node aHead = findHead(a);
            Node bHead = findHead(b);
            if (aHead != bHead){
                int aSetSize = this.sizeMap.get(aHead);
                int bSetSize = this.sizeMap.get(bHead);
                // 将元素少的集合挂在元素多的底下
                // 扁平化在findHead时已经操作了
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
