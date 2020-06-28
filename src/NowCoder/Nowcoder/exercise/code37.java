package NowCoder.Nowcoder.exercise;

public class code37 {
    public static int searchNumInSortedArr(int[] arr, int target) {
        if (arr == null || arr.length < 1) {
            return 0;
        }

        int i = 0;
        int j = arr.length - 1;
        while (i <= j) {
            int mid = (i + j) >> 1;
            if (arr[mid] < target) {
                i = mid + 1;
            } else if (arr[mid] > target) {
                j = mid - 1;
            } else {
                j = mid - 1;
            }
        }
        int left = i;

        i = 0;
        j = arr.length - 1;
        while (i <= j) {
            int mid = (i + j) >> 1;
            if (arr[mid] < target) {
                i = mid + 1;
            } else if (arr[mid] > target) {
                j = mid - 1;
            } else {
                i = mid + 1;
            }
        }
        int right = j;

        return right - left + 1;
    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 3, 3, 3, 4, 5};
        System.out.println(searchNumInSortedArr(arr, 3)); // 4
    }
}
