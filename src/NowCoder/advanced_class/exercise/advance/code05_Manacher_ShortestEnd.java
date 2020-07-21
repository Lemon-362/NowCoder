package NowCoder.advanced_class.exercise.advance;

/*
    Manacher应用：
		只在字符串后添加字符，如何添加使得整个字符串形成回文，并且添加的个数最少？
		返回需要添加的字符


	解法：
	    改进最长回文右边界，当右边界到达字符串末尾时，
	    一定是从某一位置开始到字符串末尾都是回文，此时添加的个数是最少的。
	    记录此时的回文半径，并break。
	    然后，在后面添加回文之前的逆序
	    TODO 就算到达右边界时没有回文，单独的一个字符也可以形成回文，
	        所以在到达右边界时计算最长回文子串长度，一定是有值的
 */
public class code05_Manacher_ShortestEnd {
    public static String shortestEnd(String s){
        if (s == null || s.length() < 1){
            return null;
        }

        char[] str = manacherString(s);
        int[] pArr = new int[str.length];
        int R = -1;
        int C = -1;
        int len = 0;

        for (int i = 0; i < str.length; i++) {
            pArr[i] = R > i ? Math.min(R - i, pArr[2 * C - i]) : 1;

            while (i + pArr[i] < str.length && i - pArr[i] > -1){
                if (str[i + pArr[i]] == str[i - pArr[i]]){
                    pArr[i]++;
                }else {
                    break;
                }
            }

            if (i + pArr[i] > R){
                R = i + pArr[i];
                C = i;
            }

            if (R == str.length){
                len = pArr[i] - 1;
                break;
            }
        }

        char[] res = new char[s.length() - len];
        for (int i = 0; i < res.length; i++) {
            res[res.length - 1 - i] = str[2 * i + 1];
        }

        return String.valueOf(res);
    }

    public static char[] manacherString(String s){
        char[] str = s.toCharArray();
        char[] res = new char[2 * str.length + 1];
        int index = 0;

        for (int i = 0; i < res.length; i++) {
            res[i] = i % 2 == 0 ? '#' : str[index++];
        }

        return res;
    }

    public static void main(String[] args) {
        String str2 = "abcd123321";
        System.out.println(shortestEnd(str2)); // dcba
    }
}
