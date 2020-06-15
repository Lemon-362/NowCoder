package NowCoder.basic_class.exercise.exercise02;


public class code34 {
    public static class Node {
        private int path;
        private int end;
        private Node[] next;

        public Node() {
            this.path = 0;
            this.end = 0;
            this.next = new Node[26];
        }
    }

    public static class TrieTree {
        private Node head;

        public TrieTree() {
            this.head = new Node();
        }

        public void insert(String s) {
            char[] str = s.toCharArray();
            Node cur = head;

            for (int i = 0; i < str.length; i++) {
                int path = str[i] - 'a';

                if (cur.next[path] == null) {
                    cur.next[path] = new Node();
                }

                cur = cur.next[path];

                cur.path++;
            }

            cur.end++;
        }

        public int search(String s) {
            char[] str = s.toCharArray();
            Node cur = head;

            for (int i = 0; i < str.length; i++) {
                int path = str[i] - 'a';

                if (cur.next[path] == null) {
                    return 0;
                }

                cur = cur.next[path];
            }

            return cur.end;
        }

        public void delete(String s) {
            char[] str = s.toCharArray();
            Node cur = head;

            for (int i = 0; i < str.length; i++) {
                int path = str[i] - 'a';

                if (--cur.next[path].path == 0) {
                    cur.next[path] = null;
                    break;
                }

                cur = cur.next[path];
            }

            cur.end--;
        }

        public int prefixNumber(String s) {
            char[] str = s.toCharArray();
            Node cur = head;

            for (int i = 0; i < str.length; i++) {
                int path = str[i] - 'a';

                if (cur.next[path] == null) {
                    return 0;
                }

                cur = cur.next[path];
            }

            return cur.path;
        }
    }

    public static void main(String[] args) {
        TrieTree trie = new TrieTree();
        System.out.println(trie.search("zuo")); // 0
        trie.insert("zuo");
        System.out.println(trie.search("zuo")); // 1
        trie.delete("zuo");
        System.out.println(trie.search("zuo")); // 0
        trie.insert("zuo");
        trie.insert("zuo");
        trie.delete("zuo");
        System.out.println(trie.search("zuo")); // 1
        trie.delete("zuo");
        System.out.println(trie.search("zuo")); // 0
        trie.insert("zuoa");
        trie.insert("zuoac");
        trie.insert("zuoab");
        trie.insert("zuoad");
        trie.delete("zuoa");
        System.out.println(trie.search("zuoa")); // 0
        System.out.println(trie.prefixNumber("zuo")); // 3
    }
}
