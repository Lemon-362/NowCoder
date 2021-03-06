package NowCoder.Nowcoder.Tags.Array;

public class code13 {
    public static void exchangeByON(int[] arr) {
        if (arr == null || arr.length < 1) {
            return;
        }

        partition(arr, 0, arr.length - 1);
    }

    public static void partition(int[] arr, int l, int r){
        int less = l - 1;
        int more = r + 1;
        int cur = l;

        while (cur < more){
            if (arr[cur] % 2 == 1){
                swap(arr, ++less, cur++);
            }else {
                swap(arr, --more, cur);
            }
        }
    }

    public static void exchangeByON2(int[] arr) {
        if (arr == null || arr.length < 1) {
            return;
        }

        for (int i = arr.length - 1; i >= 0; i--) {
            for (int j = 0; j < i; j++) {
                if (arr[j] % 2 == 0 && arr[j + 1] % 2 == 1){
                    swap(arr, j, j +1);
                }
            }
        }
    }

    public static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    public static void printArr(int[] arr) {
        if (arr == null) {
            return;
        }
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        exchangeByON(arr);
//        exchangeByON2(arr);
        printArr(arr); // 1 3 5 7 9 2 4 6 8
    }
}
