package NowCoder.LeetCode.Tags.Hot100.String.exercise;

public class code10 {
    public static String minContainString(String s, String t){
        if (s == null || t == null){
            return "";
        }

        int[] contain = new int[128];
        int[] need = new int[128];
        int count = 0;
        int len = s.length() + 1;
        int left = 0;
        int right = 0;
        String res = "";

        for (int i = 0; i < t.length(); i++) {
            need[t.charAt(i)]++;
        }

        while (right < s.length()){
            char ch = s.charAt(right);

            contain[ch]++;

            if (need[ch] != 0 && contain[ch] <= need[ch]) {
                count++;
            }

            if (count == t.length()){
                while (left <= right && count == t.length()) {
                    if (right - left + 1 < len) {
                        len = right - left + 1;
                        res = s.substring(left, right + 1);
                    }

                    char chh = s.charAt(left);

                    if (need[chh] != 0 && need[chh] == contain[chh]) {
                        count--;
                    }

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

        String s1 = "ADOBECODEBANC";
        String t1 = "ABC";

        System.out.println(minContainString(s1, t1)); // BANC

    }
}
