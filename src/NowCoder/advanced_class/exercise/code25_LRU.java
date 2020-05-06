package NowCoder.advanced_class.exercise;

import java.util.HashMap;

/*
	LRU可变更缓存结构：TODO Least Recently Used(LRU)最近最少使用
		set(key, value)  get(key)
	要求：
		1. set和get的时间复杂度为O(1)
		2. 某个key的set或get操作一旦发生，认为这个key的记录成为最经常使用的
		3. 当缓存超过K时，移除最不经常使用的记录
 */
public class code25_LRU {
    public static class Node<K, V> {
        private K key;
        private V value;
        private Node<K, V> next;
        private Node<K, V> last;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    public static class DoubleLinkedList<K, V> {
        private Node<K, V> head;
        private Node<K, V> tail;

        public DoubleLinkedList() {
            this.head = null;
            this.tail = null;
        }

        public void moveNodeToTail(Node<K, V> node){
            if (this.tail == node){
                return;
            }
            if (this.head == node){
                Node<K, V> lastNode = node.last;
                lastNode.next = null;
                this.head = lastNode;

                Node<K, V> tailNode = this.tail;
                tailNode.last = node;
                node.next = tailNode;
                node.last = null;
                this.tail = node;
            }else {
                Node<K, V> lastNode = node.last;
                Node<K, V> nextNode = node.next;
                lastNode.next = nextNode;
                nextNode.last = lastNode;

                Node<K, V> tailNode = this.tail;
                tailNode.last = node;
                node.next = tailNode;
                node.last = null;
                this.tail = node;
            }
        }

        public Node<K, V> removeNodeFromHead(){
            if (this.head == null){
                return null;
            }
            Node<K, V> headNode = this.head;
            if (this.tail == headNode){
                this.head = null;
                this.tail = null;
                return headNode;
            }else {
                Node<K, V> lastNode = headNode.last;
                lastNode.next = null;
                headNode.last = null;
                return headNode;
            }
        }

        public void addNodeToTail(Node<K, V> node){
            if (this.tail == null){
                this.tail = node;
                this.head = node;
            }else {
                Node<K, V> tailNode = this.tail;
                tailNode.last = node;
                node.next = tailNode;
                node.last = null;
                this.tail = node;
            }
        }
    }

    public static class LRU<K, V> {
        private int capacity;
        private HashMap<K, Node<K, V>> map;
        private DoubleLinkedList<K, V> list;

        public LRU(int capacity) {
            this.map = new HashMap<>();
            this.list = new DoubleLinkedList<>();
            this.capacity = capacity;
        }

        public V get(K key){
            if (map.containsKey(key)){
                Node<K, V> getNode = map.get(key);

                list.moveNodeToTail(getNode);

                return getNode.value;
            }else {
                return null;
            }
        }

        public void set(K key, V value){
            if (map.containsKey(key)){
                Node<K, V> setNode = map.get(key);
                setNode.value = value;
                map.put(key, setNode);

                list.moveNodeToTail(setNode);
            }else {
                if (map.size() == this.capacity){
                    this.removeNode();
                }
                Node<K, V> setNode = new Node<>(key, value);
                map.put(key, setNode);
                list.addNodeToTail(setNode);
            }
        }

        public void removeNode(){
            Node<K, V> head = list.removeNodeFromHead();
            this.map.remove(head.key);
        }
    }

    public static void main(String[] args) {
        LRU<String, Integer> lru = new LRU<>(3);
        lru.set("A", 1);
        lru.set("B", 2);
        lru.set("C", 3);
        System.out.println(lru.get("B")); // 2
        System.out.println(lru.get("A")); // 1
        lru.set("D", 4);
        System.out.println(lru.get("D")); // 4
        System.out.println(lru.get("C")); // null
    }

}
