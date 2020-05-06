package NowCoder.basic_class.exercise;

/*
	设计RandomPool结构：有以下三个功能
		insert(key)：将某个key加入到该结构
		delete(key)：将某个key移除
		getRandom()：等概率随即返回结构中的任何一个key

	用两张哈希表，一张存<key, index>，一张存<index, key>
	insert时，两张表都进，index在后者++
*/

import java.util.HashMap;

public class code31_RandomPool {
    public static class Pool<K>{
        private HashMap<K, Integer> keyIndexMap;
        private HashMap<Integer, K> indexKeyMap;
        private int size;

        public Pool() {
            this.indexKeyMap = new HashMap<>();
            this.keyIndexMap = new HashMap<>();
            this.size = 0;
        }

        public void insert(K key){
            if (!this.keyIndexMap.containsKey(key)){
                this.keyIndexMap.put(key, size);
                this.indexKeyMap.put(size, key);
                size++;
            }
        }

        public K getRandom(){
            if (size == 0){
                return null;
            }
            int index = (int)(Math.random()*size);
            return this.indexKeyMap.get(index);
        }

        public void delete(K key){
            if (this.keyIndexMap.containsKey(key)){
                int deleteIndex = this.keyIndexMap.get(key);
                int lastIndex = --this.size;
                K lastValue = this.indexKeyMap.get(lastIndex);

                this.keyIndexMap.put(lastValue, deleteIndex);
                this.indexKeyMap.put(deleteIndex, lastValue);

                this.keyIndexMap.remove(key);
                this.indexKeyMap.remove(lastIndex);
            }
        }
    }


    public static void main(String[] args) {
        Pool<String> pool = new Pool<>();
        pool.insert("ss");
        pool.insert("aa");
        pool.insert("bb");
        pool.delete("aa");
        System.out.println(pool.getRandom());
        System.out.println(pool.getRandom());
        System.out.println(pool.getRandom());
        System.out.println(pool.getRandom());
        System.out.println(pool.getRandom());
    }
}
