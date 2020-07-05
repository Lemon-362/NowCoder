package NowCoder.basic_class.exercise.exercise02;

import java.util.HashMap;

public class code31 {
    public static class Pool<K> {
        private HashMap<K, Integer> keyIndexMap = new HashMap<>();
        private HashMap<Integer, K> indexKeyMap = new HashMap<>();
        private int size = 0;

        public void insert(K key){
            if (!keyIndexMap.containsKey(key)){
                keyIndexMap.put(key, size);
                indexKeyMap.put(size, key);
                size++;
            }
        }

        public K getRandom(){
            int num = (int)(Math.random() * size);

            return indexKeyMap.get(num);
        }

        public void delete(K key){
            int deleteIndex = keyIndexMap.get(key);
            int lastIndex = --size;
            K lastValue = indexKeyMap.get(lastIndex);

            keyIndexMap.put(lastValue, deleteIndex);
            indexKeyMap.put(deleteIndex, lastValue);

            keyIndexMap.remove(key);
            indexKeyMap.remove(lastIndex);
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
