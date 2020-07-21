package NowCoder.basic_class.Tags.HashMap;

import java.util.HashMap;

public class RandomPool {
    public static class Pool<K> {
        private HashMap<K, Integer> keyIndexMap;
        private HashMap<Integer, K> indexKeyMap;
        private int size;

        public Pool() {
            this.keyIndexMap = new HashMap<>();
            this.indexKeyMap = new HashMap<>();
            this.size = 0;
        }

        public void insert(K key){
            if (!keyIndexMap.containsKey(key)){
                this.keyIndexMap.put(key, this.size);
                this.indexKeyMap.put(this.size, key);
                this.size++;
            }
        }

        public K getRandom(){
            if (this.size == 0){
                return null;
            }

            int index = (int)(Math.random() * this.size);
            return this.indexKeyMap.get(index);
        }

        public void delete(K key){
            if (this.keyIndexMap.containsKey(key)) {
                int deleteIndex = this.keyIndexMap.get(key);
                int lastIndex = --this.size;
                K lastKey = this.indexKeyMap.get(lastIndex);

                this.keyIndexMap.put(lastKey, deleteIndex);
                this.indexKeyMap.put(deleteIndex, lastKey);

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
