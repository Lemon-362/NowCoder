package NowCoder.Nowcoder.exercise;

public class code06 {
    // 旋转数组：将一个非递减的数组最开始的几个数搬到数组末尾
    public static int getMin(int[] arr) {
        if (arr == null || arr.length < 1) {
            return 0;
        }

        int p1 = 0;
        int p2 = arr.length - 1;

        if (arr[p1] < arr[p2]){
            return arr[p1];
        }

        int mid = 0;

        while (arr[p1] >= arr[p2]){
            mid = (p1 + p2) >> 1;

            if (p2 - p1 == 1){
                mid = p2;
                break;
            }

            if (arr[p1] == arr[p2] && arr[p2] == arr[mid]){
                return getMinInOrder(arr, p1, p2);
            }

            if (arr[mid] >= arr[p1]){
                p1 = mid;
            }else if (arr[mid] <= arr[p2]){
                p2 = mid;
            }
        }

        return arr[mid];
    }

    public static int getMinInOrder(int[] arr, int l, int r){
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
