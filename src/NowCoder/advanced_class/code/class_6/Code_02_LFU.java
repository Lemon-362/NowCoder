package NowCoder.advanced_class.code.class_6;

import java.util.HashMap;

/*
    LFU：根据使用次数进行更新
 */
public class Code_02_LFU {

    // Node本身可以构成双向链表
    public static class Node {
        public Integer key;
        public Integer value;
        public Integer times;
        public Node up;
        public Node down;

        public Node(int key, int value, int times) {
            this.key = key;
            this.value = value;
            this.times = times;
        }
    }

    public static class LFUCache {
        // 每一个Node都代表一个次数,Node里又有up和down构成双向链表
        public static class NodeList {
            public Node head;
            public Node tail;
            public NodeList last;
            public NodeList next;

            public NodeList(Node node) { // 要建立一个NodeList，必须要有一个node才能建
                head = node;
                tail = node;
            }

            // TODO 这里认为 从头到尾的次数是 从大到小的，因此每次都往头加，从尾删
            // TODO 头部是最经常发生的,次数是大的
            // 从头部加入node，认为尾部是要先删除的
            public void addNodeFromHead(Node newHead) {
                newHead.down = head;
                head.up = newHead;
                head = newHead;
            }

            public boolean isEmpty() {
                return head == null;
            }

            // 删除节点：可能是因为操作次数增加了，或者size满了
            public void deleteNode(Node node) {
                if (head == tail) { // 只有一个node
                    head = null;
                    tail = null;
                } else {
                    if (node == head) { // 要删除的是头部
                        head = node.down;
                        head.up = null;
                    } else if (node == tail) { // 要删除的是尾部
                        tail = node.up;
                        tail.down = null;
                    } else { // 要删除的是中间的
                        node.up.down = node.down;
                        node.down.up = node.up;
                    }
                }
                node.up = null;
                node.down = null;
            }
        }

        private int capacity;
        private int size; // 实际个数
        private HashMap<Integer, Node> records; // key(Integer) -> node，记录key的信息，没有用K泛型
        private HashMap<Node, NodeList> heads; // node来自哪个NodeList
        private NodeList headList; // 第一个NodeList（次数最低的），用于删除次数最低的NodeList的头部

        public LFUCache(int capacity) {
            this.capacity = capacity;
            this.size = 0;
            this.records = new HashMap<>();
            this.heads = new HashMap<>();
            headList = null;
        }

        public void set(int key, int value) {
            if (records.containsKey(key)) { // key已经存在
                Node node = records.get(key);
                node.value = value;
                node.times++;
                NodeList curNodeList = heads.get(node); // 找到key当前属于哪个NodeList
                move(node, curNodeList); // 移动key到下一个NodeList（次数+1的NodeList）
            } else { // key不存在
                if (size == capacity) { // size满，删尾部，并从链表中删除
                    Node node = headList.tail;
                    headList.deleteNode(node);
                    modifyHeadList(headList); // 重新设置headList,换头操作
                    records.remove(node.key);
                    heads.remove(node);
                    size--;
                }
                // 加入新节点，次数为1
                Node node = new Node(key, value, 1);
                if (headList == null) { // 整个NodeList为空
                    headList = new NodeList(node); // 建一个headList
                } else {
                    if (headList.head.times.equals(node.times)) { // 当前头部的次数等于1，直接挂在headList的头部
                        headList.addNodeFromHead(node);
                    } else { // 不存在次数为1的nodeList，就要新建一个次数为1的NodeList
                        NodeList newList = new NodeList(node);
                        newList.next = headList;
                        headList.last = newList;
                        headList = newList;
                    }
                }
                records.put(key, node);
                heads.put(node, headList);
                size++;
            }
        }

        // 由于操作了node，需要将该node挂到次数+1的nodeList上
        private void move(Node node, NodeList oldNodeList) {
            oldNodeList.deleteNode(node); // 先将老的nodeList上的node删除
            // 老nodeList删除一个节点后
            // 如果老nodeList为空了，此时次数+1的nodeList的前指针应该指向老nodeList的前一个
            // 如果老nodeList不为空，此时次数+1的nodeList的前指针直接指向老nodeList
            NodeList preList = modifyHeadList(oldNodeList) ? oldNodeList.last
                    : oldNodeList;
            NodeList nextList = oldNodeList.next; // 次数+1的nodeList的前指针直接指向原来老nodeList的下一个
            if (nextList == null) { // 说明老nodeList就是整个NodeList的尾部（最高次数）
                NodeList newList = new NodeList(node); // 生成新的nodeList，作为整个NodeList的尾部
                // 设置新的nodeList的前后指针
                if (preList != null) {
                    preList.next = newList;
                }
                newList.last = preList;
                if (headList == null) {
                    headList = newList;
                }
                // 将node放到次数+1的nodeList
                heads.put(node, newList);
            } else { // 老nodeList不是尾部
                if (nextList.head.times.equals(node.times)) { // 原来的NodeList中有次数+1的nodeList
                    // 直接挂上去
                    nextList.addNodeFromHead(node);
                    // 将node放到次数+1的nodeList
                    heads.put(node, nextList);
                } else { // 不存在次数+1的nodeList，需要新建次数+1的nodeList，然后再挂上去
                    NodeList newList = new NodeList(node); // 新建次数+1的nodeList
                    // 设置新的nodeList的前后指针
                    if (preList != null) {
                        preList.next = newList;
                    }
                    newList.last = preList;
                    newList.next = nextList;
                    nextList.last = newList;
                    if (headList == nextList) {
                        headList = newList;
                    }
                    // 将node放到次数+1的nodeList
                    heads.put(node, newList);
                }
            }
        }

        // return whether delete this head
        // 在nodeList中删除一个节点后，判断是否为空了，为空需要删除整个nodeList，并将headList换成后一个
        // 删除节点后，整个nodeList为空，则返回true
        private boolean modifyHeadList(NodeList nodeList) {
            if (nodeList.isEmpty()) { // 删除节点后，nodeList为空，需要处理
                if (headList == nodeList) { // headList指向的是NodeList的头部，而此时删除头部上的节点后，头部为空
                    // 需要处理的就是NodeList的头部
                    // 那么headList就指向空的nodeList，需要将headList换成后一个
                    headList = nodeList.next;
                    if (headList != null) { // 新头不为空，就要将新头的last指针指向null
                        // 新头为空说明整个NodeList为空，不需要处理
                        headList.last = null;
                    }
                } else { // headList不指向空，要处理的nodeList不是整个NodeList的头部
                    // 也就是说在中间的NodeList删除节点后为空，直接将该NodeList的前后重新设置一下
                    nodeList.last.next = nodeList.next;
                    if (nodeList.next != null) {
                        nodeList.next.last = nodeList.last;
                    }
                }
                return true;
            }
            return false;
        }

        public Integer get(int key) {
            if (!records.containsKey(key)) { // 不存在该key
                return null; // 标记不存在
            }
            Node node = records.get(key);
            node.times++;
            NodeList curNodeList = heads.get(node);
            move(node, curNodeList); // 移动到次数+1的NodeList
            return node.value;
        }

    }
}