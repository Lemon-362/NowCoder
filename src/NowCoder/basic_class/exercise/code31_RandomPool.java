package NowCoder.basic_class.exercise;

/*
	设计RandomPool结构：有以下三个功能
		insert(key)：将某个key加入到该结构
		delete(key)：将某个key移除
		getRandom()：等概率随机返回结构中的任何一个key
	TODO 因为要求等概率返回，所以必须记录当前结构中insert的元素个数，根据索引返回key
	     那么就要用两张哈希表，一张存<key, index>，一张存<index, key>
	insert时，两张表都进，size在最后++
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

        // 删除其中一条会产生空洞，在getRandom时可以随机到一个空洞的位置，那么就必须得重新random，时间复杂度就不是O(1)了
        // 产生空洞时，拿最后一个记录去填上，保证index永远连续
        public void delete(K key){
            if (this.keyIndexMap.containsKey(key)){
                int deleteIndex = this.keyIndexMap.get(key);
                int lastIndex = --this.size; // 用来填空洞的index
                K lastValue = this.indexKeyMap.get(lastIndex); // 用来填空洞的key

                this.keyIndexMap.put(lastValue, deleteIndex); // 将key填到要删除的index位置
                this.indexKeyMap.put(deleteIndex, lastValue); // 将要删除的index位置填成key

                this.keyIndexMap.remove(key); // 删除key
                this.indexKeyMap.remove(lastIndex); // 删除最后一条
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
