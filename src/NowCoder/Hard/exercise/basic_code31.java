package NowCoder.Hard.exercise;

import java.util.HashMap;

public class basic_code31 {
    public static class Pool<K> {
        private HashMap<K, Integer> keyIndexMap;
        private HashMap<Integer, K> indexKeyMap;
        private int index;

        public Pool() {
            this.keyIndexMap = new HashMap<>();
            this.indexKeyMap = new HashMap<>();
            this.index = 0;
        }

        public void insert(K key){
            if (!keyIndexMap.containsKey(key)){
                keyIndexMap.put(key, index);
                indexKeyMap.put(index, key);
                this.index++;
            }
        }

        public K getRandom(){
            int num = (int) (Math.random() * index);

            return indexKeyMap.get(num);
        }

        public void delete(K key){
            int deleteIndex = keyIndexMap.get(key);
            int lastIndex = --index;
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
