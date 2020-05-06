package NowCoder.basic_class.exercise01.Trie;

import NowCoder.basic_class.exercise.code34_TrieTree;

public class TrieTree {
    public static class TrieNode {
        private int path;
        private int end;
        private TrieNode[] nodes;

        public TrieNode() {
            this.path = 0;
            this.end = 0;
            this.nodes = new TrieNode[26];
        }
    }

    public static class trieTree {
        private TrieNode head;

        public trieTree() {
            this.head = new TrieNode();
        }

        public void insert(String str) {
            if (str == null) {
                return;
            }
            char[] s = str.toCharArray();
            int index = 0;
            TrieNode node = head;
            for (int i = 0; i < s.length; i++) {
                index = s[i] - 'a';
                if (node.nodes[index] == null) {
                    node.nodes[index] = new TrieNode();
                }
                node = node.nodes[index];
                node.path++;
            }
            node.end++;
        }

        public int search(String str) {
            if (str == null) {
                return 0;
            }
            char[] s = str.toCharArray();
            int index = 0;
            TrieNode node = head;
            for (int i = 0; i < s.length; i++) {
                index = s[i] - 'a';
                if (node.nodes[index] == null) {
                    return 0;
                }
                node = node.nodes[index];
            }
            return node.end;
        }

        public void delete(String str) {
            if (str == null) {
                return;
            }
            char[] s = str.toCharArray();
            int index = 0;
            TrieNode node = head;
            for (int i = 0; i < s.length; i++) {
                index = s[i] - 'a';
                if (--node.nodes[index].path == 0) {
                    node.nodes[index] = null;
                    return;
                }
                node = node.nodes[index];
            }
            node.end--;
        }

        public int prefixNumber(String str) {
            if (str == null) {
                return 0;
            }
            char[] s = str.toCharArray();
            int index = 0;
            TrieNode node = head;
            for (int i = 0; i < s.length; i++) {
                index = s[i] - 'a';
                if (node.nodes[index] == null) {
                    return 0;
                }
                node = node.nodes[index];
            }
            return node.path;
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
}
