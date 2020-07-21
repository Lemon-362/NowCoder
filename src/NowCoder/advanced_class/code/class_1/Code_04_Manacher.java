package NowCoder.advanced_class.code.class_1;

/*
    Manacher算法：最长回文子串
        在一字符串中找到最长回文子串
 */
public class Code_04_Manacher {

    // 法一：暴力法
    // 从某一字符向两边扩，逐一比较，不同则后移一个重复操作
    // 缺点：无法对偶数长度的使用
    // 改进：先对字符串的开头、结尾、每个字符间加上分隔符，再用暴力法
    // 时间复杂度：O(N^2)

    // 法二：Manacher算法，对暴力法加速
    // 在加上分隔符后，求回文半径、回文右边界、回文右边界的中心
    // 时间复杂度：O(N)

    // 加分隔符
    public static char[] manacherString(String str) {
        char[] charArr = str.toCharArray();
        char[] res = new char[str.length() * 2 + 1]; // 加分隔符后，长度=2*len+1
        int index = 0;
        for (int i = 0; i != res.length; i++) {
            // (i & 1) == 0：偶数
            // 0，2，4，。。。偶数位置上加分隔符
            res[i] = (i & 1) == 0 ? '#' : charArr[index++];
        }
        return res;
    }

    // Manacher
    public static int maxLcpsLength(String str) {
        if (str == null || str.length() == 0) {
            return 0;
        }
        // 加分隔符
        char[] charArr = manacherString(str);
        int[] pArr = new int[charArr.length]; // 回文半径数组
        int C = -1; // 回文中心
        int R = -1; // 回文右边界，之前所有回文能够到达的最右的位置
        // 如果右边界左边的回文右边界没有到达该位置，那么不变
        int max = Integer.MIN_VALUE;
        // 遍历整个字符串，求i位置的回文中心
        for (int i = 0; i != charArr.length; i++) {
            // TODO 首先找到不用验证的区域
            pArr[i] = i < R ? Math.min(pArr[2 * C - i], R - i) : 1;
            /*
            1. i在回文右边界R的里面（i<R）：那么一定有一个不用验证的区域
               （1）如果i的对称点i'的回文直径也在当前回文直径内，那么i的回文直径和i'一样
               （2）如果i的对称点i'的回文直径不全在当前回文直径内，那么i的回文半径就是R-i
               （3）i的对称点i'的回文直径和当前回文直径的左边界重合，那么R-i一定是回文半径的一部分，但是R往右也有可能是
            2. i不在在回文右边界R的里面：暴力
             */
            // 2 * C - i：i'，pArr[2 * C - i]：i'的回文半径，R - i：R到i的距离
            // 谁小就是它的瓶颈（不用验证的区域）
            // R > i：i在回文右边界的里面，至少有一个不用验证的区域
            // R <= i：i在回文右边界的外面，我自己不用扩，不用验证的区域就是我自己，长度为1
            while (i + pArr[i] < charArr.length && i - pArr[i] > -1) { // 再往外扩一个，情况二和三的话两边不等
                // TODO i + pArr[i]：在不用验证的区域pArr[i]基础上往左右扩
                if (charArr[i + pArr[i]] == charArr[i - pArr[i]]) { // 情况一和四，可以往外扩
                    pArr[i]++; // 如果左右扩了仍相同，那么回文半径就+1
                } else { // 直到扩不了为止
                    break;
                }
            }
            // 更新R和C
            if (i + pArr[i] > R) { // 如果扩出来的区域超过了原来的R，那么回文右边界R和回文中心C都更新
                R = i + pArr[i];
                C = i;
            }
            // 根据题目要求，记录最大回文半径
            // 比较当前为止的回文半径和之前的
            max = Math.max(max, pArr[i]); // 回文半径最大值
        }
        // TODO 因为回文右边界是指回文串末尾的后一个位置，所以回文长度要-1
        return max - 1;
    }

    public static void main(String[] args) {
        String str1 = "abc123321ab";
        System.out.println(maxLcpsLength(str1));
    }

}
