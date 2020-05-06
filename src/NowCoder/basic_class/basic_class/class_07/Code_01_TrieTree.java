package NowCoder.basic_class.basic_class.class_07;

/*
	前缀树：将字符串加到树中
		从头节点开始，若没有走向该字符的路，则新开辟一条走向该字符的路，若有则复用
		字符存储在路径上，而不是节点中
	改进：在每个节点上加两个数据
		1. path：经过（到达）该节点的字符多少次
		2. end：以该字符结尾多少次
 */
public class Code_01_TrieTree {

	public static class TrieNode {
		public int path; // 到达
		public int end; // 结尾
		public TrieNode[] nexts; // 路径

		public TrieNode() {
			path = 0;
			end = 0;
			nexts = new TrieNode[26];
		}
	}

	public static class Trie {
		private TrieNode root; // 头节点

		public Trie() {
			root = new TrieNode(); // 26个空的
		}

		// 插入
		public void insert(String word) {
			if (word == null) {
				return;
			}
			char[] chs = word.toCharArray();
			TrieNode node = root;
			int index = 0;
			for (int i = 0; i < chs.length; i++) {
				index = chs[i] - 'a'; // ASCII码值
				if (node.nexts[index] == null) { // 没有走向该字符的路径
					node.nexts[index] = new TrieNode();
				}
				node = node.nexts[index]; // 否则复用
				node.path++; // 经过
			}
			node.end++; // 结尾
		}

		// 删除：path=0时停止（说明往后的之经过一次，也就没有复用），往后的所有字符直接删除
		public void delete(String word) {
			if (search(word) != 0) {
				char[] chs = word.toCharArray();
				TrieNode node = root;
				int index = 0;
				for (int i = 0; i < chs.length; i++) {
					index = chs[i] - 'a';
					if (--node.nexts[index].path == 0) { // 边界条件
						node.nexts[index] = null;
						return;
					}
					node = node.nexts[index];
				}
				node.end--; // end结尾 -1，表示删除了一组字符串
			}
		}

		// 查询含有多少个
		public int search(String word) {
			if (word == null) {
				return 0;
			}
			char[] chs = word.toCharArray();
			TrieNode node = root;
			int index = 0;
			for (int i = 0; i < chs.length; i++) {
				index = chs[i] - 'a';
				if (node.nexts[index] == null) { // 必须包含完整个word字符串，有一个不对都为0
					return 0;
				}
				node = node.nexts[index];
			}
			return node.end; // end是以该字符结尾，表示复用的次数。
			// 不能用path返回，因为path表示经过，后面的字符也可能经过该字符
		}

		// 前缀数
		public int prefixNumber(String pre) {
			if (pre == null) {
				return 0;
			}
			char[] chs = pre.toCharArray();
			TrieNode node = root;
			int index = 0;
			for (int i = 0; i < chs.length; i++) {
				index = chs[i] - 'a';
				if (node.nexts[index] == null) {
					return 0;
				}
				node = node.nexts[index];
			}
			return node.path; // 返回path，因为后面的也会经过这些字符，需要计算重复次数
		}
	}

	public static void main(String[] args) {
		Trie trie = new Trie();
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
