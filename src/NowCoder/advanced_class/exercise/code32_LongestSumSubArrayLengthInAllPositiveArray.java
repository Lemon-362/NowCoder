package NowCoder.advanced_class.exercise;

/*
	累加和等于aim的最长子数组：
		给定一个数组arr，全为正数，和一个整数aim
		求累加和等于aim的最长子数组，要求额外空间复杂度O(1)，时间复杂度O(N)
		TODO 全为正数 --> 使得数组具有单调性,R扩一定增加,L扩一定减小,不存在L扩了还可能增加的情况
		双指针L和R, sum<aim,R右移; sum>=aim,L右移
 */
public class code32_LongestSumSubArrayLengthInAllPositiveArray {
    public static int getMaxLength(int[] arr, int aim) {
        if (arr == null || arr.length < 1) {
            return 0;
        }

        int L = 0;
        int R = 0;
        int len = 0;
        int sum = 0;

        while (L < arr.length && R < arr.length){
            if (sum == aim){
                len = Math.max(len, R - L + 1);
                sum -= arr[L++];
            }else if (sum < aim){
                R++;
                if (R == arr.length){
                    break;
                }
                sum += arr[R];
            }else {
                sum -= arr[L++];
            }
        }

        return len;
    }

    public static int[] generatePositiveArray(int size) {
        int[] result = new int[size];
        for (int i = 0; i != size; i++) {
            result[i] = (int) (Math.random() * 10) + 1;
        }
        return result;
    }

    public static void printArray(int[] arr) {
        for (int i = 0; i != arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 1, 1, 1};
        System.out.println(getMaxLength(arr, 3)); // 3

        int len = 20;
        int k = 15;
        int[] arr1 = generatePositiveArray(len);
        printArray(arr1);
        System.out.println(getMaxLength(arr1, k));
    }
}
