package NowCoder.advanced_class.basic_class_02;

public class Code_04_Manacher {

    public static char[] manacherString(String str) {
        char[] charArr = str.toCharArray();
        char[] res = new char[str.length() * 2 + 1];
        int index = 0;
        for (int i = 0; i != res.length; i++) {
            res[i] = (i & 1) == 0 ? '#' : charArr[index++];
        }
        return res;
    }

    public static int maxLcpsLength(String str) {
        if (str == null || str.length() == 0) {
            return 0;
        }
        char[] charArr = manacherString(str);
        int[] pArr = new int[charArr.length]; // 回文半径数组
        int C = -1;
        int R = -1;
        int max = Integer.MIN_VALUE;
        // 求i位置的回文中心
        for (int i = 0; i != charArr.length; i++) {
            // 2 * C - i：i'，pArr[2 * C - i]：i'的回文半径，R - i：R到i的距离
            // 谁小就是它的瓶颈（不用验证的区域）
            // R > i：i在回文右边界的里面，至少有一个不用验证的区域
            // R <= i：i在回文右边界的外面，我自己不用扩
            pArr[i] = R > i ? Math.min(pArr[2 * C - i], R - i) : 1;
            // 两大类，第二类有三种可能
            while (i + pArr[i] < charArr.length && i - pArr[i] > -1) { // 再往外扩一个，情况二和三的话两边不等
                if (charArr[i + pArr[i]] == charArr[i - pArr[i]]) { // 情况一和四，可以往外扩
                    pArr[i]++;
                } else {
                    break;
                }
            }
            if (i + pArr[i] > R) { // 扩出来的区域超过了R，回文右边界和回文中心都更新
                R = i + pArr[i];
                C = i;
            }
            max = Math.max(max, pArr[i]); // 回文半径最大值
        }
        return max - 1;
    }

    public static void main(String[] args) {
        String str1 = "abc1234321ab";
        System.out.println(maxLcpsLength(str1));
    }

}
