package NowCoder.Exam.Exam3;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

public class test1 {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();
        int m = scanner.nextInt();

        int[] arr = new int[m];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = scanner.nextInt();
        }

        boolean[] vis = new boolean[n + 1];
        Queue<Integer> q1 = new LinkedList<>();
        Queue<Integer> q2 = new LinkedList<>();

        for (int i = 0; i < arr.length; i++) {
            vis[arr[i]] = true;
            q1.offer(arr[i]);
        }

        for (int i = 1; i <= n; i++) {
            if (!vis[i]){
                q2.offer(i);
            }
        }

        int[] res = new int[n];
        int index = 0;

        while (!q1.isEmpty() && !q2.isEmpty()){
            if (q1.peek() > q2.peek()){
                res[index++] = q2.poll();
            }else {
                res[index++] = q1.poll();
            }
        }

        while (!q1.isEmpty()){
            res[index++] = q1.poll();
        }
        while (!q2.isEmpty()){
            res[index++] = q2.poll();
        }

        for (int i = 0; i < res.length; i++) {
            System.out.print(res[i] + " ");
        }
    }

    public String smallestSubsequence(String text) {
        int len = text.length();
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < len; i++) {
            Character c = text.charAt(i);
            if (stack.contains(c)) {
                continue;
            }
            while (!stack.empty() && c < stack.peek() && text.indexOf(stack.peek(), i) != -1) {
                stack.pop();
            }
            stack.push(c);
        }
        StringBuilder sb = new StringBuilder();
        for (Character c : stack) {
            sb.append(c);
        }
        return sb.toString();
    }
}
