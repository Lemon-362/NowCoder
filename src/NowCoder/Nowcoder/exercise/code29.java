package NowCoder.Nowcoder.exercise;

public class code29 {
    public static class getMinNumByHeap {
        public static int[] getMinByHeap(int[] arr, int k) {
            if (arr == null || arr.length < 1 || k > arr.length) {
                return null;
            }

            return null;
        }
    }

    public static class getMinNumByBFPRT {
        public static int[] getMinByBFPRT(int[] arr, int k) {
            if (arr == null || arr.length < 1 || k > arr.length) {
                return null;
            }

            return null;
        }
    }

    public static void printArrayByHeap(int[] arr) {
        if (arr == null) {
            return;
        }
        for (int i = arr.length - 1; i >= 0; i--) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    public static void printArrayByBFPRT(int[] arr) {
        if (arr == null) {
            return;
        }
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int[] arr = {6, 9, 1, 3, 1, 2, 2, 5, 6, 1, 3, 5, 9, 7, 2, 5, 6, 1, 9};
        printArrayByHeap(getMinNumByHeap.getMinByHeap(arr, 10)); // 5 3 2 3 1 1 2 2 1 1
        printArrayByBFPRT(getMinNumByBFPRT.getMinByBFPRT(arr, 10)); // 1 3 1 2 2 1 3 2 1 5

        int[] arr2 = {3, 2, 1, 5, 4, 7};
        printArrayByHeap(getMinNumByHeap.getMinByHeap(arr2, 3)); // 1 2 3
        printArrayByBFPRT(getMinNumByBFPRT.getMinByBFPRT(arr2, 3)); // 2 1 3
    }
}
