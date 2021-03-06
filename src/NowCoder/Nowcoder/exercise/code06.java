package NowCoder.Nowcoder.exercise;

public class code06 {
    // 旋转数组：将一个非递减的数组最开始的几个数搬到数组末尾
    public static int getMin(int[] arr) {
        if (arr == null || arr.length < 1) {
            return 0;
        }

        int i = 0;
        int j = arr.length - 1;
        int mid = 0;

        while (i < j){
            mid = (i + j) >> 1;

            if (j - i == 1){
                mid = j;
                break;
            }

            if (arr[i] == arr[mid] && arr[mid] == arr[j]){
                return findInOrder(arr, i, j);
            }

            if (arr[mid] >= arr[i]){
                i = mid;
            }else if (arr[mid] <= arr[j]){
                j = mid;
            }
        }

        return arr[mid];
    }

    public static int findInOrder(int[] arr, int l, int r){
        int res = arr[l];

        for (int i = l + 1; i <= r; i++) {
            res = Math.min(res, arr[i]);
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
        int[] arr2 = {3, 4, 5, 1, 2, 3};
        int[] arr3 = {1, 0, 1, 1, 1};

        System.out.println(getMin(arr1)); // 0
        System.out.println(getMin(arr2)); // 1
        System.out.println(getMin(arr3)); // 0
    }
}
