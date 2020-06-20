package NowCoder.basic_class.exercise.exercise02;

import java.util.HashMap;

public class code31 {
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
            if (!this.keyIndexMap.containsKey(key)){
                this.keyIndexMap.put(key, this.size);
                this.indexKeyMap.put(this.size, key);
            }

            this.size++;
        }

        public K getRandom(){
            int num = (int)(Math.random() * this.size);

            return this.indexKeyMap.get(num);
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
        // ss bb
        System.out.println(pool.getRandom());
        System.out.println(pool.getRandom());
        System.out.println(pool.getRandom());
        System.out.println(pool.getRandom());
        System.out.println(pool.getRandom());
    }
}
