package NowCoder.Nowcoder.exercise;

public class code06 {
    public static int getMin(int[] arr) {
        if (arr == null || arr.length < 1) {
            return 0;
        }

        int i = 0;
        int j = arr.length - 1;
        int mid;

        while (i < j) {
            if (arr[i] < arr[j]) {
                return arr[i];
            }

            mid = (i + j) >> 1;

            if (arr[j] > arr[mid]) {
                j = mid;
            }else if (arr[j] < arr[mid]){
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

    public static void main(String[] args) {
        int[] arr1 = {7, 0, 1, 1, 1, 1, 1, 2, 3, 4};
        int[] arr2 = {3, 4, 5, 1, 2, 3};
        int[] arr3 = {1, 0, 1, 1, 1};

        System.out.println(getMin(arr1)); // 0
        System.out.println(getMin(arr2)); // 1
        System.out.println(getMin(arr3)); // 0
    }
}
