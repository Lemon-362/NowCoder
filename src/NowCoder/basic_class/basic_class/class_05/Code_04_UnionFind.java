package NowCoder.basic_class.basic_class.class_05;

import java.util.HashMap;
import java.util.List;
import java.util.Stack;

/*
	并查集：
		查询两个元素是否属于同意集合
		合并两个元素所在的集合
	先将每个数各自形成一个集合，往上节点指回自己（代表节点）

	查询：均向上找，找到代表节点时停止，若代表节点相同，则在同一集合中
	合并：找到代表节点，将元素少的挂在元素多的集合上

	优化：对于某节点，往上找到代表节点后，将从该节点向上的所有节点，
		  都扁平化，直接连在代表节点上
 */
public class Code_04_UnionFind {

	public static class Node {
		// whatever you like
	}

	public static class UnionFindSet {
		public HashMap<Node, Node> fatherMap; // child - parent
		public HashMap<Node, Integer> sizeMap;

		public UnionFindSet() {
			fatherMap = new HashMap<Node, Node>();
			sizeMap = new HashMap<Node, Integer>();
		}

		// 先将每个数各自形成一个集合，往上节点指回自己（代表节点）
		public void makeSets(List<Node> nodes) {
			fatherMap.clear();
			sizeMap.clear();
			for (Node node : nodes) {
				fatherMap.put(node, node);
				sizeMap.put(node, 1);
			}
		}

		// 寻找代表节点，并扁平化
		private Node findHead(Node node) {
			// 非递归
			Stack<Node> stack = new Stack<>();
			Node cur = node;
			Node parent = fatherMap.get(cur);
			while (cur!=parent){
				stack.push(cur);
				cur = parent;
				parent = fatherMap.get(cur);
			}
			// 优化
			while (!stack.isEmpty()){
				fatherMap.put(stack.pop(), parent);
			}
			return parent;

			// 递归
//			Node father = fatherMap.get(node);
//			if (father != node) {
//				father = findHead(father);
//			}
//			fatherMap.put(node, father);
//			return father;
		}

		// 查询：是否是同一集合
		public boolean isSameSet(Node a, Node b) {
			return findHead(a) == findHead(b);
		}

		// 合并：找到代表节点，将元素少的挂在元素多的集合上
		public void union(Node a, Node b) {
			if (a == null || b == null) {
				return;
			}
			Node aHead = findHead(a);
			Node bHead = findHead(b);
			if (aHead != bHead) {
				int aSetSize= sizeMap.get(aHead);
				int bSetSize = sizeMap.get(bHead);
				if (aSetSize <= bSetSize) { // a所在集合元素少，挂到b上
					fatherMap.put(aHead, bHead);
					sizeMap.put(bHead, aSetSize + bSetSize);
				} else {
					fatherMap.put(bHead, aHead);
					sizeMap.put(aHead, aSetSize + bSetSize);
				}
			}
		}

	}

	public static void main(String[] args) {

	}

}
