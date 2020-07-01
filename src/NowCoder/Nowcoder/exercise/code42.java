package NowCoder.Nowcoder.exercise;

public class code42 {
    public static int[] findTwoNumWithSum(int[] arr, int target) {
        if (arr == null || arr.length < 1) {
            return null;
        }

        int i = 0;
        int j = arr.length - 1;
        int sum = 0;
        int[] res = new int[2];

        while (i < j){
            sum = arr[i] + arr[j];

            if (sum < target){
                i++;
            }else if (sum > target){
                j--;
            }else {
                res[0] = arr[i];
                res[1] = arr[j];
                break;
            }
        }

        return res;
    }

    public static void print(int[] arr){
        for (int i : arr) {
            System.out.print(i + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int[] arr1 = {10, 26, 30, 31, 47, 60};
        int[] arr2 = {2, 7, 11, 15};
        int[] res1 = findTwoNumWithSum(arr1, 40);
        int[] res2 = findTwoNumWithSum(arr2, 9);

        print(res1); // 10 30
        print(res2); // 2 7
    }
}
