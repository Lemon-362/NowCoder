package NowCoder.LeetCode.Tags.Hot100.String;

import java.util.*;

/**
 * 49. 字母异位词分组:
 *  给定一个字符串数组，将字母异位词组合在一起。
 *  字母异位词: 字母相同，但排列不同的字符串
 *
 *  题意: 给定一个字符串数组, 将相同字符的字符串分为一组
 *
 */
public class code08_GroupAnagrams {
    /**
     * HashMap的映射关系:
     *  遍历每一个字符串, 首先进行排序
     *  如果map中没有排序后的字符串, 则存入该字符串的key, value初始化为list
     *  当出现过该key, 则直接add进去
     *
     *  TODO 注意:
     *   1. map中出现该key的情况不能作为else语句, 否则的话对于只有一个的组, 它初始化完list后
     *      就不会进入else语句, 导致该key的value为空
     *      所以, 要将add的操作放到最后, 既要初始化, 也要放入map中
     *   2. 需要额外使用变量来排序, 否则原始字符串就不能放入对应的value中
     *   3. Arrays.sort是对数组进行排序, 不可以对String排序
     *
     *  时间复杂度: 假设strs中字符串的最大长度为K, 则排序时间复杂度为 O(KlogK)
     *      需要对strs遍历, 所以最终 O(N*KlogK)
     *  空间复杂度: O(N*K)
     */
    public static List<List<String>> groupAnagrams(String[] strs) {
        if (strs == null || strs.length == 0){
            return null;
        }

        List<List<String>> res = new ArrayList<>();
        HashMap<String, List<String>> map = new HashMap<>();

        for (int i = 0; i < strs.length; i++) {
            char[] str = strs[i].toCharArray();

            Arrays.sort(str);

            String key = String.valueOf(str);

            if (!map.containsKey(key)){
                map.put(key, new ArrayList<>());
            }

            map.get(key).add(strs[i]);
        }

        for(String key : map.keySet()){
            res.add(map.get(key));
        }

        return res;
    }

    public static void main(String[] args) {
        String[] str = {"eat", "tea", "tan", "ate", "nat", "bat"};

        List<List<String>> res = groupAnagrams(str);

        for (List<String> list : res) {
            for (String s : list) {
                System.out.print(s + " ");
            }
            System.out.println();
        }
    }
}
