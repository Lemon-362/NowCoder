package NowCoder.Nowcoder;

import java.util.HashMap;

/*
    数组中超过长度一半的数字：
        数组中有一个数字出现的次数超过数组长度的一半，请找出这个数字。
        例如输入一个长度为9的数组{1,2,3,2,2,2,5,4,2}。
        由于数字2在数组中出现了5次，超过数组长度的一半，因此输出2。如果不存在则输出0。
 */
public class code28_MoreThanHalfNum {
    public static int method(int[] array) {
        // 最多只可能有一个值超过一半
        HashMap<Integer, Integer> map = new HashMap<>();
        int index = 0;
        for (int i = 0; i < array.length; i++) {
            if (!map.containsKey(array[i])) {
                map.put(array[i], 1);
            } else {
                index = map.get(array[i]);
                map.put(array[i], index + 1);
            }
        }
        // 遍历map的key，判断对应的value是否大于长度的一半
        int halfLen = array.length / 2;
        for(Integer key: map.keySet()){
            if(map.get(key) > halfLen){
                return key;
            }
        }
        return 0;
    }

    public static void main(String[] args) {
        int[] array = {1, 2, 3, 2, 2, 2, 5, 4, 2};
//        HashMap<Integer, Integer> map = method(array);
//        for (Integer key : map.keySet()) {
//            System.out.print("key=" + key + " ");
//        }
//        System.out.println();
//        for (Integer value : map.values()) {
//            System.out.print("value=" + value + " ");
//        }
        System.out.println(method(array));
    }
}
