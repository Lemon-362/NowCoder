package NowCoder.basic_class.exercise01.Trie;

import NowCoder.basic_class.exercise.code34_TrieTree;

public class TrieTree {
    public static class Node {
        private int path;
        private int end;
        private Node[] nexts;

        public Node() {
            this.path = 0;
            this.end = 0;
            this.nexts = new Node[26];
        }
    }

    public static class trieTree {
        private Node head;

        public trieTree() {
            this.head = new Node();
        }

        public void insert(String str) {
            if (str == null){
                return;
            }

            char[] s = str.toCharArray();
            Node cur = head;

            for (int i = 0; i < s.length; i++) {
                int index = s[i] - 'a';

                if (cur.nexts[index] == null){
                    cur.nexts[index] = new Node();
                }

                cur = cur.nexts[index];
                cur.path++;
            }

            cur.end++;
        }

        public int search(String str){
            if (str == null){
                return 0;
            }

            char[] s = str.toCharArray();
            Node cur = head;

            for (int i = 0; i < s.length; i++) {
                int index = s[i] - 'a';

                if (cur.nexts[index] == null){
                    return 0;
                }

                cur = cur.nexts[index];
            }

            return cur.end;
        }

        public void delete(String str){
            if (str == null){
                return;
            }

            char[] s = str.toCharArray();
            Node cur = head;

            for (int i = 0; i < s.length; i++) {
                int index = s[i] - 'a';

                if (--cur.nexts[index].path == 0){
                    cur.nexts[index] = null;
                    return;
                }

                cur = cur.nexts[index];
            }

            cur.end--;
        }

        public int prefixNumber(String str){
            if (str == null){
                return 0;
            }

            char[] s = str.toCharArray();
            Node cur = head;

            for (int i = 0; i < s.length; i++) {
                int index = s[i] - 'a';

                if (cur.nexts[index] == null){
                    return 0;

                }

                cur = cur.nexts[index];
            }

            return cur.path;
        }
    }

    public static void main(String[] args) {
        trieTree trie = new trieTree();
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
