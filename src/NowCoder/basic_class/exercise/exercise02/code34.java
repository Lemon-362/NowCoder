package NowCoder.basic_class.exercise.exercise02;



public class code34 {
    public static class TrieNode{
        private int path;
        private int end;
        private TrieNode[] next;

        public TrieNode() {
            this.path = 0;
            this.end = 0;
            this.next = new TrieNode[26];
        }
    }

    public static class TrieTree {
        private TrieNode head;

        public TrieTree() {
            this.head = new TrieNode();
        }

        public void insert(String str){
            if (str == null){
                return;
            }

            char[] s = str.toCharArray();
            TrieNode cur = head;

            for (int i = 0; i < s.length; i++) {
                int index = s[i] - 'a';

                if (cur.next[index] == null){
                    cur.next[index] = new TrieNode();
                }

                cur = cur.next[index];
                cur.path++;
            }

            cur.end++;
        }

        public int search(String str){
            if (str == null){
                return 0;
            }

            char[] s = str.toCharArray();
            TrieNode cur = head;

            for (int i = 0; i < s.length; i++) {
                int index = s[i] - 'a';

                if (cur.next[index] == null){
                    return 0;
                }

                cur = cur.next[index];
            }

            return cur.end;
        }

        public void delete(String str){
            if (str == null){
                return;
            }

            char[] s = str.toCharArray();
            TrieNode cur = head;

            for (int i = 0; i < s.length; i++) {
                int index = s[i] - 'a';

                if (--cur.next[index].path == 0){
                    cur.next[index] = null;
                    return;
                }

                cur = cur.next[index];
            }

            cur.end--;
        }

        public int prefixNumber(String str){
            if (str == null){
                return 0;
            }

            char[] s = str.toCharArray();
            TrieNode cur = head;

            for (int i = 0; i < s.length; i++) {
                int index = s[i] - 'a';

                if (cur.next[index] == null){
                    return 0;
                }

                cur = cur.next[index];
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
