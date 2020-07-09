package NowCoder.advanced_class.exercise.exercise01;

import java.util.HashMap;

public class code25 {
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

        public Node<K, V> removeHeadNode(){
            if (this.head == null){
                return null;
            }

            Node<K, V> headNode = this.head;

            if (this.tail == headNode){
                this.tail = null;
                this.head = null;
            }else {
                Node<K, V> lastNode = headNode.last;
                lastNode.next = null;
                this.head = lastNode;
            }

            return headNode;
        }

        public void addNodeToTail(Node<K, V> node){
            if (this.tail == null){
                this.tail = node;
                this.head = node;
            }

            Node<K, V> tailNode = this.tail;
            tailNode.last = node;
            node.next = tailNode;
            node.last = null;
            this.tail = node;
        }
    }

    public static class LRU<K, V> {
        private int capacity;
        private HashMap<K, Node<K, V>> map;
        private DoubleLinkedList<K, V> list;

        public LRU(int size) {
            this.capacity = size;
            this.map = new HashMap<>();
            this.list = new DoubleLinkedList<>();
        }

        public void set(K key, V value){
            if (!map.containsKey(key)){
                if (this.capacity == map.size()){
                    removeHeadNode();
                }

                Node<K, V> setNode = new Node<>(key, value);

                list.addNodeToTail(setNode);

                map.put(key, setNode);
            }else {
                Node<K, V> setNode = new Node<>(key, value);

                setNode.value = value;

                list.moveNodeToTail(setNode);

                map.put(key, setNode);
            }
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

        public void removeHeadNode(){
            Node<K, V> headNode = list.removeHeadNode();

            map.remove(headNode.key);
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
