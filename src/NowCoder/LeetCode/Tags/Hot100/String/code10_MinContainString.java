package NowCoder.LeetCode.Tags.Hot100.String;

/**
 * 76. 最小覆盖子串:
 *  给你一个字符串 S、一个字符串 T，请在字符串 S 里面找出：包含 T 所有字符的最小子串
 *  例如:
 *      输入: S = "ADOBECODEBANC", T = "ABC"
 *      输出: "BANC"
 *
 */
public class code10_MinContainString {

    /**
     *  TODO 典型的滑动窗口法
     *
     *  使用头尾双指针表示窗口
     *  (1) 尾部进数, 当窗口中包含了t的所有字符时, 停止移动
     *  (2) 头部出数, 当窗口中刚好不包含t的所有字符时, 停止移动
     *
     *  1. 尾部right移动, 直到包含了t的所有字符, 停止
     *     此时, left-right 是满足条件的范围, 可以计算一次长度
     *  2. 头部left移动, 直到t中的一个字符弹出, 不包含t所有字符, 停止
     *     此时, 重复1
     *  3. 如何检测窗口内字符是否包含了t?
     *      可以设置两个数组contain和need, 一个变量count
     *    (1) count: 表示在s中出现了t中字符的个数
     *    (2) contain数组: 在遍历过程中, s的字符出现一次, 对应位置就 +1
     *    (3) need数组: 首先遍历t字符串, 将出现字符次数在对应位置表示出来
     *         在遍历过程中, 如果 need中s字符对应位置 != 0 && contain中该字符的出现次数 <= need中该字符的应该出现的次数
     *         - != 0: 说明t中有该字符
     *         - contain[ch] <= need[ch]: 说明s中出现了t中所需要的该字符
     *         则 count++
     *
     *    当 count==t.length() 时, 说明 left-right 的窗口已经包含了t的所有字符
     *    此时就可以进入步骤2了
     *
     *  4. 在步骤2中, 首先计算长度len, 然后判断left处的字符是否在need中出现
     *     如果 need中s字符对应位置 != 0 && contain中该字符的出现次数 == need中该字符的应该出现的次数
     *     则说明 left处的字符在t中出现了, 此时count--
     *     一旦 count!=t.length(), 说明此时窗口已经没有完全包含t的所有字符
     *     此时就可以进入步骤1了
     *
     *
     */
    public static String minContainString(String s, String t){
        if (s == null || t == null){
            return "";
        }

        int left = 0;
        int right = 0;
        int count = 0;
        // 最大长度就是s的长度 + 1
        int len = s.length() + 1;
        String res = "";

        int[] need = new int[58];
        int[] contain = new int[58];

        // 首先统计t中每个字符的出现次数
        for (int i = 0; i < t.length(); i++) {
            need[t.charAt(i) - 65]++;
        }

        // 先移动尾部right
        while (right < s.length()){
            int ch = s.charAt(right) - 65;
            // 统计s中每个字符的出现次数
            contain[ch]++;
            // 如果当前字符的出现次数 <= need中该字符应该出现的次数, 则说明需要包含该字符, count++
            if (need[ch] != 0 && contain[ch] <= need[ch]){
                count++;
            }
            // 当count==t的长度时, 说明包含了所有字符, 此时移动头部left
            if (count == t.length()) {
                while (left <= right && count == t.length()) { // 只要窗口中应该包含t中字符的个数count != t的长度, 就退出循环
                    // 更新长度
                    if (len > right - left + 1) {
                        len = right - left + 1;
                        res = s.substring(left, right + 1);
                    }

                    int chh = s.charAt(left) - 65;
                    // 如果当前字符的出现次数 == need中该字符应该出现的次数, 则说明left位于该字符处
                    // 此时left要往后移动了, 所以该位置的字符要移出窗口, count--
                    if (need[chh] != 0 && contain[chh] == need[chh]) {
                        count--;
                    }
                    // 移出left处的字符
                    contain[chh]--;

                    left++;
                }
            }

            right++;
        }

        return len == s.length() + 1 ? "" : res;
    }

    public static void main(String[] args) {
        String s = "aaaaaaaaaaaabbbbbcdd";
        String t = "abcdd";

        System.out.println(minContainString(s, t)); // abbbbbcdd

    }

}
