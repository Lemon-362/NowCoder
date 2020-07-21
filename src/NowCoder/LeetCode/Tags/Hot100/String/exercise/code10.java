package NowCoder.LeetCode.Tags.Hot100.String.exercise;

public class code10 {
    public static String minContainString(String s, String t){
        if (s == null || t == null){
            return "";
        }

        int[] need = new int[128];
        int[] contain = new int[128];

        for (int i = 0; i < t.length(); i++) {
            char ch = t.charAt(i);
            need[ch]++;
        }

        int l = 0;
        int r = 0;
        int len = s.length() + 1;
        int count = 0;
        String res = "";

        while (r < s.length()){
            char ch = s.charAt(r);

            contain[ch]++;

            if (need[ch] > 0 && contain[ch] <= need[ch]){
                count++;
            }

            if (count == t.length()){
                while (l <= r && count == t.length()){
                    if (len > r - l + 1){
                        len = r - l + 1;
                        res = s.substring(l, r + 1);
                    }

                    char chh = s.charAt(l);

                    if (need[chh] > 0 && contain[chh] == need[chh]){
                        count--;
                    }

                    contain[chh]--;

                    l++;
                }
            }

            r++;
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
