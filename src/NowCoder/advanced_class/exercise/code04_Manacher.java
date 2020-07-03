package NowCoder.advanced_class.exercise;

/*
    Manacher算法：最长回文子串
        在一字符串中找到最长回文子串
    解法：
        先对原始字符串加#分隔符，然后求每个位置上的pArr回文半径数组，衍生出R和C
        首先对于当前位置一定有一个可以不用验证的区域，然后再往外扩，如果两边扩后仍相等，则pArr+1
 */
public class code04_Manacher {
    // 法一：暴力法
    // 从某一字符向两边扩，逐一比较，不同则后移一个重复操作
    // 缺点：无法对偶数长度的使用
    // 改进：先对字符串的开头、结尾、每个字符间加上分隔符，再用暴力法
    // 时间复杂度：O(N^2)

    // 法二：Manacher算法，对暴力法加速
    // 在加上分隔符后，求回文半径、回文右边界、回文右边界的中心
    // 时间复杂度：O(N)
    public static int maxLength(String s) {
        if (s == null || s.length() < 1) {
            return -1;
        }

        char[] str = manacherString(s);
        int[] pArr = new int[str.length];
        int R = -1;
        int C = -1;
        int len = 0;

        for (int i = 0; i < str.length; i++) {
            pArr[i] = R > i ? Math.min(R - i, pArr[2 * C - i]) : 1;
            while (i + pArr[i] < str.length && i - pArr[i] > -1) {
                if (str[i + pArr[i]] == str[i - pArr[i]]) {
                    pArr[i]++;
                } else {
                    break;
                }
            }

            if (i + pArr[i] > R){
                R = i + pArr[i];
                C = i;
            }

            len = Math.max(len, pArr[i]);
        }

        return len - 1;
    }

    public static char[] manacherString(String s) {
        char[] str = s.toCharArray();
        char[] res = new char[2 * str.length + 1];
        int index = 0;
        for (int i = 0; i < res.length; i++) {
            res[i] = i % 2 == 0 ? '#' : str[index++];
        }
        return res;
    }

    public static void main(String[] args) {
        String str1 = "abc1234321ab";
        String str2 = "babad";
        System.out.println(maxLength(str2)); // 7
    }
}
