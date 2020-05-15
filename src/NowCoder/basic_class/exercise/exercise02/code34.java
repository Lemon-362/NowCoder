package NowCoder.basic_class.exercise.exercise02;

public class code34 {
    public static class TrieNode {
        private int path;
        private int end;
        private TrieNode[] nexts;

        public TrieNode() {
            this.path = 0;
            this.end = 0;
            this.nexts = new TrieNode[26];
        }
    }

    public static class TrieTree {
        private TrieNode head;

        public TrieTree() {
            this.head = new TrieNode();
        }

        // 插入
        public void insert(String str) {
            if (str == null) {
                return;
            }

            char[] s = str.toCharArray();
            TrieNode cur = head;

            for (int i = 0; i < s.length; i++) {
                int index = s[i] - 'a';

                if (cur.nexts[index] == null){
                    cur.nexts[index] = new TrieNode();
                }

                cur = cur.nexts[index];
                cur.path++;
            }

            cur.end++;
        }

        // 查询完整字符串
        public int search(String str) {
            if (str == null) {
                return 0;
            }

            char[] s = str.toCharArray();
            TrieNode cur = head;

            for (int i = 0; i < s.length; i++) {
                int index = s[i] - 'a';

                if (cur.nexts[index] == null){
                    return 0;
                }

                cur = cur.nexts[index];
            }

            return cur.end;
        }

        // 查询前缀
        public int prefixNumber(String str) {
            if (str == null) {
                return 0;
            }

            char[] s = str.toCharArray();
            TrieNode cur = head;

            for (int i = 0; i < s.length; i++) {
                int index = s[i] - 'a';

                if (cur.nexts[index] == null){
                    return 0;
                }

                cur = cur.nexts[index];
            }

            return cur.path;
        }

        // 删除
        public void delete(String str) {
            if (str == null) {
                return;
            }

            char[] s = str.toCharArray();
            TrieNode cur = head;

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
