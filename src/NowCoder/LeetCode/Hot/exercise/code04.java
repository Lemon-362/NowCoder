package NowCoder.LeetCode.Hot.exercise;

public class code04 {
    public static double medianInTwoArrays1(int[] arr1, int[] arr2){
        int len = arr1.length + arr2.length;
        int[] help = new int[len];
        int p1 = 0;
        int p2 = 0;
        int index = 0;

        while (p1 < arr1.length && p2 < arr2.length){
            help[index++] = arr1[p1] < arr2[p2] ? arr1[p1++] : arr2[p2++];
        }

        while (p1 < arr1.length){
            help[index++] = arr1[p1++];
        }

        while (p2 < arr2.length){
            help[index++] = arr2[p2++];
        }

        if (len % 2 == 0){
            return (help[len / 2 - 1] + help[len / 2]) / 2.0;
        }else {
            return help[(len - 1) / 2] * 1.0;
        }
    }

    public static void main(String[] args) {
        int[] arr1 = {1, 2};
        int[] arr2 = {3, 4};
        System.out.println(medianInTwoArrays1(arr1, arr2)); // 2.5
    }
}
