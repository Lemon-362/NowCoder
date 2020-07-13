package NowCoder.Hard.exercise;

public class No_code06 {
    // 旋转数组：将一个非递减的数组最开始的几个数搬到数组末尾
    // TODO 二分查找仅适用于没有重复元素的情况，如果有重复元素，只能遍历
    public static int getMin(int[] arr) {
        if (arr == null || arr.length < 1) {
            return 0;
        }

        int i = 0;
        int j = arr.length - 1;
        int mid = 0;

        while (i < j){
            mid = (i + j) >> 1;

            if (arr[i] < arr[j]){
                return arr[i];
            }

            if (j - i == 1){
                mid = j;
            }

            if (arr[mid] == arr[i] && arr[mid] == arr[j]) {
                return findInSort(arr, i, j);
            }

            if (arr[mid] >= arr[i]){
                i = mid + 1;
            }else if (arr[mid] <= arr[j]){
                j = mid - 1;
            }
        }

        return arr[mid];
    }

    public static int findInSort(int[] arr, int i, int j){
        int res = arr[i];

        for (int k = i + 1; k <= j; k++) {
            res = Math.min(res, arr[k]);
        }

        return res;
    }

    public static int compareMethod(int[] arr) {
        if (arr == null || arr.length < 1) {
            return 0;
        }

        for (int i = 0; i < arr.length - 1; i++) {
            if (arr[i] > arr[i + 1]) {
                return arr[i + 1];
            }
        }

        return arr[0];
    }

    public static void main(String[] args) {
        int[] arr1 = {7, 0, 1, 1, 1, 1, 1, 2, 3, 4};
        int[] arr2 = {3, 4, 5, 6, 1, 2};
        int[] arr3 = {1, 0, 1, 1, 1};

        System.out.println(getMin(arr1)); // 0
        System.out.println(getMin(arr2)); // 1
        System.out.println(getMin(arr3)); // 0

    }
}
