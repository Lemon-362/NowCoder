package NowCoder.basic_class.basic_class.class_05;

import java.util.HashMap;

/*
	设计RandomPool结构：有以下三个功能
		insert(key)：将某个key加入到该结构
		delete(key)：将某个key移除
		getRandom()：等概率随即返回结构中的任何一个key

	用两张哈希表，一张存<key, index>，一张存<index, key>
	insert时，两张表都进，index在后者++
*/

public class Code_02_RandomPool {

	public static class Pool<K> {
		private HashMap<K, Integer> keyIndexMap;
		private HashMap<Integer, K> indexKeyMap;
		private int size;

		public Pool() {
			this.keyIndexMap = new HashMap<K, Integer>();
			this.indexKeyMap = new HashMap<Integer, K>();
			this.size = 0;
		}

		public void insert(K key) {
			if (!this.keyIndexMap.containsKey(key)) {
				this.keyIndexMap.put(key, this.size);
				this.indexKeyMap.put(this.size++, key);
			}
		}

		// 删除B，将最后一个填到B上，再删去最后一个
		// 因为index可以变化，而key是不能变的
		public void delete(K key) {
			if (this.keyIndexMap.containsKey(key)) {
				int deleteIndex = this.keyIndexMap.get(key); // 需要删除的key的index
				int lastIndex = --this.size; // 最后一个key的index
				K lastKey = this.indexKeyMap.get(lastIndex); // 最后一个index的key

				this.keyIndexMap.put(lastKey, deleteIndex); // 将最后一个index的key覆盖到需要删除的key的index处
				this.indexKeyMap.put(deleteIndex, lastKey);

				this.keyIndexMap.remove(key); // 删除key
				this.indexKeyMap.remove(lastIndex); // 删除最后一个位置
			}
		}

		public K getRandom() {
			if (this.size == 0) {
				return null;
			}
			int randomIndex = (int) (Math.random() * this.size); // 0 ~ size -1
			return this.indexKeyMap.get(randomIndex);
		}

	}

	public static void main(String[] args) {
		Pool<String> pool = new Pool<String>();
		pool.insert("zuo");
		pool.insert("cheng");
		pool.insert("yun");
		System.out.println(pool.getRandom());
		System.out.println(pool.getRandom());
		System.out.println(pool.getRandom());
		System.out.println(pool.getRandom());
		System.out.println(pool.getRandom());
		System.out.println(pool.getRandom());

	}

}
