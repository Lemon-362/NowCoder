package NowCoder.advanced_class.exercise;

/*
	KMP算法：判断str1中是否含有str2子串
		子串：必须是连续的
		子序列：不连续
	解法：
	    在双指针暴力法上用next最长前后缀数组加速，p2往前跳
 */
public class code01_KMP {
    // 法一：String.indexOf
    // 直接返回子串的开始位置
    // 时间复杂度：O(N)

    // 法二：暴力法
    // 双指针，p1指向str1，p2指向str2，p2始终从头开始，与p1逐个比较
    // 若不同，则p1跳回开头往后移动一个，再与p2从头开始比较
    // 时间复杂度：O(N-M) (N≥M)

    // 法三：KMP算法 利用匹配程度对暴力法加速
    // 匹配程度：对于一个字符，它前面的字符串的前缀和后缀相同时最长的长度
    // TODO next数组求解的是前缀和后缀相同时的最大长度
    public static int getIndexOf(String s, String m) {
        if (s == null || m == null || s.length() < 1 || m.length() > s.length()) {
            return -1;
        }
        char[] str1 = s.toCharArray();
        char[] str2 = m.toCharArray();

        int[] next = getNextArr(str2);
        int p1 = 0;
        int p2 = 0;

        while (p1 < str1.length && p2 < str2.length) {
            if (str1[p1] == str2[p2]) {
                p1++;
                p2++;
            } else {
                if (next[p2] == -1) {
                    p1++;
                } else {
                    p2 = next[p2];
                }
            }
        }

        return p2 == str2.length ? p1 - p2 : -1;
    }

    public static int[] getNextArr(char[] str) {
        int[] next = new int[str.length];
        next[0] = -1;
        next[1] = 0;
        int p = 2;
        int cn = 0;
        while (p < next.length) {
            if (str[p - 1] == str[cn]) {
                next[p++] = ++cn;
            } else if (cn > 0) {
                cn = next[cn];
            } else {
                next[p++] = 0;
            }
        }
        return next;
    }

    public static void main(String[] args) {
        String str = "acabcababaccc";
        String match = "bcab";
        System.out.println(str.indexOf(match));
        System.out.println(getIndexOf(str, match));
    }
}
