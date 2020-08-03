package NowCoder.LeetCode.Tags.Hot100.String.exercise;

public class code10 {
    public static String minContainString(String s, String t){
        if (s == null || t == null){
            return "";
        }

        int[] contain = new int[128];
        int[] need = new int[128];

        for (int i = 0; i < t.length(); i++) {
            need[t.charAt(i)]++;
        }

        int count = 0;
        int len = s.length() + 1;
        String res = "";
        int L = 0;
        int R = 0;

        while (R < s.length()){
            char ch = s.charAt(R);

            contain[ch]++;

            if (need[ch] != 0 && contain[ch] <= need[ch]){
                count++;
            }

            if (count == t.length()){
                while (L <= R && count == t.length()){
                    if (len > R - L + 1){
                        len = R - L + 1;
                        res = s.substring(L, R + 1);
                    }

                    ch = s.charAt(L);

                    if (need[ch] != 0 && need[ch] == contain[ch]){
                        count--;
                    }

                    contain[ch]--;

                    L++;
                }
            }

            R++;
        }

        return len == s.length() + 1 ? "" : res;
    }

    public static void main(String[] args) {
        String s = "aaaaaaaaaaaabbbbbcdd";
        String t = "abcdd";

        System.out.println(minContainString(s, t)); // abbbbbcdd

        String s1 = "ADOBECODEBANC";
        String t1 = "ABC";

        System.out.println(minContainString(s1, t1)); // BANC

    }
}
