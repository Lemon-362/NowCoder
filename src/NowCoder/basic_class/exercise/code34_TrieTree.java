package NowCoder.basic_class.exercise;

import NowCoder.basic_class.basic_class.class_07.Code_01_TrieTree;

/*
	前缀树：将字符串加到树中
		从头节点开始，若没有走向该字符的路，则新开辟一条走向该字符的路，若有则复用
		字符存储在路径上，而不是节点中
	insert  delete  search  prefixNumber
 */
public class code34_TrieTree {
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

    public static class TrieTree {
        private TrieNode head;

        public TrieTree() {
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

        public void delete(String str) {
            if (str == null) {
                return;
            }
            if (search(str) != 0) {
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
}
