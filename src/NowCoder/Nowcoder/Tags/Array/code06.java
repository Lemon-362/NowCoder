package NowCoder.Nowcoder.Tags.Array;

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

            if (arr[i] < arr[j]){
                return arr[i];
            }

            if (arr[mid] < arr[j]){
                j = mid;
            }else if (arr[mid] > arr[j]){
                i = mid + 1;
            }else {
                j = mid;
            }
        }

        return arr[i];
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

    public static int search(int[] arr, int aim) {
        if (arr == null || arr.length < 1) {
            return -1;
        }

        int left = 0;
        int right = arr.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (arr[mid] == aim) {
                return mid;
            }

            // 左半边数组
            if (arr[mid] >= arr[left]) {
                // 看aim在是否在左半边
                if (aim >= arr[left] && aim < arr[mid]) { // aim在左半边, 且aim在mid左边
                    right = mid - 1;
                } else { // aim在mid右边
                    left = mid + 1;
                }
            } else { // 右半边数组
                if (aim <= arr[right] && aim > arr[mid]) { // aim在mid右边
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
        }

        return -1;
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
