package NowCoder.basic_class.exercise01.HashMap;

import NowCoder.basic_class.exercise.code31_RandomPool;

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
                keyIndexMap.put(key, size);
                indexKeyMap.put(size, key);
                size++;
            }
        }

        public K getRandom(){
            if (size == 0){
                return null;
            }

            int num = (int)(Math.random() * size);
            return indexKeyMap.get(num);
        }

        public void delete(K key){
            if (keyIndexMap.containsKey(key)){
                int deleteIndex = keyIndexMap.get(key);
                int lastIndex = --size;
                K lastValue = indexKeyMap.get(lastIndex);

                keyIndexMap.put(lastValue, deleteIndex);
                indexKeyMap.put(deleteIndex, lastValue);

                keyIndexMap.remove(key);
                indexKeyMap.remove(lastIndex);
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
