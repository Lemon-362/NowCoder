package NowCoder.LeetCode.Tags.Hot100.String.exercise;

import java.util.ArrayList;
import java.util.List;

public class code06 {
    public static List<String> list = new ArrayList<>();

    public static List<String> generateParenthesis(int N){
        if (N <= 0){
            return null;
        }

        process(N, N, "");

        return list;
    }

    public static void process(int leftNum, int rightNum, String res){
        // base case
        if (leftNum == 0 && rightNum == 0){
            list.add(res);
            return;
        }

        // 1
        if (leftNum > 0 && leftNum <= rightNum){
            process(leftNum - 1, rightNum, res + "(");
        }

        // 2
        if (rightNum > 0 && rightNum > leftNum){
            process(leftNum, rightNum - 1, res + ")");
        }
    }

    public static void main(String[] args) {
        List<String> list = generateParenthesis(3);
        for (String s : list) {
            System.out.println(s);
        }
//        ((()))
//        (()())
//        (())()
//        ()(())
//        ()()()
    }
}
