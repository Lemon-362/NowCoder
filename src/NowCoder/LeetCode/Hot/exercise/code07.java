package NowCoder.LeetCode.Hot.exercise;

public class code07 {
    public static int maxArea(int[] arr){
        if (arr == null || arr.length < 2){
            return 0;
        }

        int res = 0;
        int L = 0;
        int R = arr.length - 1;

        while (L < R){
            if (arr[L] < arr[R]){
                res = Math.max(res, (R - L) * arr[L]);
                L++;
            }else {
                res = Math.max(res, (R - L) * arr[R]);
                R--;
            }
        }

        return res;
    }

    public static void main(String[] args) {
        int[] arr = {1, 8, 6, 2, 5, 4, 8, 3, 7};
        System.out.println(maxArea(arr)); // 49
    }
}
