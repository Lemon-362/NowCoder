package NowCoder.advanced_class.code.class_6;

import java.util.HashMap;

/*
	LRU可变更缓存结构：Least Recently Used最近最少使用
		set(key, value)  get(key)
	要求：
		1. set和get的时间复杂度为O(1)
		2. 某个key的set或get操作一旦发生，认为这个key的记录成为最经常使用的
		3. 当缓存超过K时，移除最不经常使用的记录
 */
public class Code_01_LRU {
	// 构建双端链表的节点
	public static class Node<K, V> {
		public K key;
		public V value;
		public Node<K, V> last;
		public Node<K,V> next;

		public Node(K key, V value) {
			this.key = key;
			this.value = value;
		}
	}

	// 自定义双端链表
	public static class NodeDoubleLinkedList<K,V> {
		private Node<K,V> head; // 头,优先级最低
		private Node<K,V> tail; // 尾,优先级最高
		// TODO 从头到尾的方向:next  从尾到头的方向:last

		public NodeDoubleLinkedList() {
			this.head = null;
			this.tail = null;
		}

		// 从尾部加节点
		public void addNode(Node<K,V> newNode) {
			if (newNode == null) {
				return;
			}
			if (this.head == null) { // 链表为null
				this.head = newNode;
				this.tail = newNode;
			} else { // 链表不为null
				this.tail.next = newNode;
				newNode.last = this.tail;
				this.tail = newNode;
			}
		}

		// 将链表中node节点移到尾部
		public void moveNodeToTail(Node<K,V> node) {
			// 尾部就是该node,不用操作
			if (this.tail == node) {
				return;
			}
			// node不在尾部,需要断开node两边，并重新设置两边的指针
			if (this.head == node) { // 头部是该node,需要将新头和null相连
				this.head = node.next;
				this.head.last = null;
			} else { // 中间是该node
				node.last.next = node.next;
				node.next.last = node.last;
			}
			// 然后移动node到尾部
			node.last = this.tail;
			node.next = null;
			this.tail.next = node;
			this.tail = node;
		}

		// 移除头部并返回
		public Node<K,V> removeHead() {
			if (this.head == null) {
				return null;
			}
			Node<K,V> res = this.head;
			// 在移除的同时,要重新设置两边的指针
			if (this.head == this.tail) { // 链表中只有一个节点
				this.head = null;
				this.tail = null;
			} else { // 链表中节点超过1个
				this.head = res.next;
				res.next = null;
				this.head.last = null;
			}
			return res;
		}

	}

	public static class MyCache<K, V> {
		// HashMap,用于存数据  key:key  value:(key, value)的Node节点
		private HashMap<K, Node<K,V>> keyNodeMap;
		// 双向链表,用于存优先级  (key, value)的Node节点  从头到尾的优先级:由低到高
		private NodeDoubleLinkedList<K, V> nodeList;
		private int capacity;

		public MyCache(int capacity) {
			if (capacity < 1) {
				throw new RuntimeException("should be more than 0.");
			}
			this.keyNodeMap = new HashMap<K, Node<K,V>>();
			this.nodeList = new NodeDoubleLinkedList<K,V>();
			this.capacity = capacity;
		}

		// get操作
		// 从map中根据key可以得到(key, value)的Node节点value
		// 而双向链表存的就是(key, value), 所以可以找到
		// 断开原来两侧,将该值移到链表尾部(优先级高的位置)
		public V get(K key) {
			if (this.keyNodeMap.containsKey(key)) {
				Node<K,V> res = this.keyNodeMap.get(key);
				this.nodeList.moveNodeToTail(res);
				return res.value;
			}
			return null;
		}

		// set操作
		// 如果map中存在该key,则取出value重新设置,并在双端链表中将该(key, value)移到尾部
		// 如果map中不存在,首先要判断map是否满了
		// 如果map满了,要先从链表中移除头部,并从map中移除对应的key,然后再往map中加数据,双端链表尾部加数据
		public void set(K key, V value) {
			if (this.keyNodeMap.containsKey(key)) {
				Node<K,V> node = this.keyNodeMap.get(key);
				node.value = value;
//				this.keyNodeMap.put(key, node);
				this.nodeList.moveNodeToTail(node);
			} else {
				if (this.keyNodeMap.size() == this.capacity) {
					this.removeMostUnusedCache();
				}
				Node<K,V> newNode = new Node<K,V>(key, value);
				this.keyNodeMap.put(key, newNode);
				this.nodeList.addNode(newNode);
			}
		}

		// 从链表中移除最不常使用的,也就是移除链表头部节点
		private void removeMostUnusedCache() {
			// 从链表中移除头节点
			Node<K,V> removeNode = this.nodeList.removeHead();
			// 从map中移除该key
			K removeKey = removeNode.key;
			this.keyNodeMap.remove(removeKey);
		}

	}

	public static void main(String[] args) {
		MyCache<String, Integer> testCache = new MyCache<String, Integer>(3);
		testCache.set("A", 1);
		testCache.set("B", 2);
		testCache.set("C", 3);
		System.out.println(testCache.get("B")); // 2
		System.out.println(testCache.get("A")); // 1
		testCache.set("D", 4);
		System.out.println(testCache.get("D")); // 4
		System.out.println(testCache.get("C")); // null

	}

}
