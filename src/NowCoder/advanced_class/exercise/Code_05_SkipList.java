package NowCoder.advanced_class.exercise;

import java.util.ArrayList;
import java.util.Iterator;

/*
	跳表
 */
public class Code_05_SkipList {

	// 每个跳表的结构
	public static class SkipListNode {
		public Integer value;
		public ArrayList<SkipListNode> nextNodes; // 如果ArrayList长度为10，说明有10层
		// 索引越小，第几层的层数越高
		// nextNodes[0]：表示在第一层上node的下一个节点
		// nextNodes[9]：表示在第九层上node的下一个节点

		public SkipListNode(Integer value) {
			this.value = value;
			nextNodes = new ArrayList<SkipListNode>();
		}
	}

	public static class SkipListIterator implements Iterator<Integer> {
		SkipList list;
		SkipListNode current;

		public SkipListIterator(SkipList list) {
			this.list = list;
			this.current = list.getHead();
		}

		public boolean hasNext() {
			return current.nextNodes.get(0) != null;
		}

		public Integer next() {
			current = current.nextNodes.get(0);
			return current.value;
		}
	}

	// 跳表
	public static class SkipList {
		private SkipListNode head; // 系统最小
		private int maxLevel; // 所有数据中的最大层数
		private int size; // 加进的key数量
		private static final double PROBABILITY = 0.5; // 以P概率产生0，以1-P概率产生1

		public SkipList() {
			size = 0;
			maxLevel = 0;
			head = new SkipListNode(null);
			head.nextNodes.add(null);
		}

		public SkipListNode getHead() {
			return head;
		}

		public void add(Integer newValue) {
			if (!contains(newValue)) {
				size++;
				int level = 0;
				// 确定roll出的层数
				while (Math.random() < PROBABILITY) { // roll，小于P则产生一个1，产生一层
					level++;
				}
				while (level > maxLevel) { // 当前roll出的层数 > 之前的最大层数，此时系统最小要扩充node
					head.nextNodes.add(null);
					maxLevel++;
				}
				SkipListNode newNode = new SkipListNode(newValue); // 新加的数的node节点
				SkipListNode current = head; // 从系统最小的头部开始

				int levelAll = maxLevel;

				do { // 先从最高层开始往右走
					current = findNext(newValue, current, levelAll); // 找到最后一个小于e的所在的List
					// 然后先重新设置指针指向，再往下走
					if (level >= levelAll) {
						newNode.nextNodes.add(0, current.nextNodes.get(level)); // 每次都加到0位置上，之前list的数会往后移动
						current.nextNodes.set(level, newNode); // 当前节点的向右指针指向新加的数
						level--;
					}
				} while (levelAll-- > 0); // TODO 0位置不用，从level减到1都会设置指针
			}
		}

		public void delete(Integer deleteValue) {
			if (contains(deleteValue)) {
				SkipListNode deleteNode = find(deleteValue);
				size--;
				int level = maxLevel;
				SkipListNode current = head;
				do {
					current = findNext(deleteNode.value, current, level);
					if (deleteNode.nextNodes.size() > level) {
						current.nextNodes.set(level, deleteNode.nextNodes.get(level));
					}
				} while (level-- > 0);
			}
		}

		// Returns the skiplist node with greatest value <= e
		private SkipListNode find(Integer e) {
			return find(e, head, maxLevel);
		}

		// Returns the skiplist node with greatest value <= e
		// Starts at node start and level
		private SkipListNode find(Integer e, SkipListNode current, int level) {
			do {
				current = findThis(e, current, level);
			} while (level-- > 0);
			return current;
		}

		// Returns the node at a given level with highest value less than e
		// e：新加的数	current：当前节点		level：e对应的层数
		// 在同一层中，往右找，一直找到最后一个小于e的所在的List（往右必须有连接）
		private SkipListNode findThis(Integer e, SkipListNode current, int level) {
			SkipListNode next = current.nextNodes.get(level); // 当前节点在level层的下级节点（右边连接的节点）
			while (next != null) {
				Integer value = next.value;
				if (lessThan(e, value)) { // e < value 当前的数 < 下级节点的值
					break;
				}
				// 如果 当前的数 > 下级节点的值，则往下走
				current = next; // 当前节点变成右边一个节点
				next = current.nextNodes.get(level); // 然后继续往右找
			}
			return current;
		}

		private SkipListNode findNext(Integer e, SkipListNode current, int level) {
			SkipListNode next = current.nextNodes.get(level); // 当前节点在level层的下级节点（右边连接的节点）
			while (next != null) {
				Integer value = next.value;
				if (notBiggerThan(e, value)) { // e < value 当前的数 < 下级节点的值
					break;
				}
				// 如果 当前的数 > 下级节点的值，则往下走
				current = next; // 当前节点变成右边一个节点
				next = current.nextNodes.get(level); // 然后继续往右找
			}
			return current;
		}

		public int size() {
			return size;
		}

		public boolean contains(Integer value) {
			SkipListNode node = find(value);
			return node != null && node.value != null && equalTo(node.value, value);
		}

		public Iterator<Integer> iterator() {
			return new SkipListIterator(this);
		}

		/******************************************************************************
		 * Utility Functions *
		 ******************************************************************************/
		private boolean notBiggerThan(Integer a, Integer b){
			return a.compareTo(b) <= 0;
		}

		private boolean lessThan(Integer a, Integer b) {
			return a.compareTo(b) < 0;
		}

		private boolean equalTo(Integer a, Integer b) {
			return a.compareTo(b) == 0;
		}

	}

	public static void main(String[] args) {
		SkipList skipList = new SkipList();
		skipList.add(5);
		skipList.add(6);
		skipList.add(7);
		skipList.delete(6);
		System.out.println(skipList.contains(6));
	}

}
