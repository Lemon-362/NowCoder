package NowCoder.Nowcoder.exercise;

public class code13 {
    public static void exchange(int[] arr){
        if (arr == null || arr.length < 1){
            return;
        }

        boolean flag = false;

        for (int i = arr.length - 1; i >= 0; i--) {
            for (int j = 0; j < i; j++) {
                if (arr[j] % 2 == 0 && arr[j + 1] % 2 == 1){
                    swap(arr, j, j + 1);
                    flag = true;
                }
            }
            if (!flag){
                break;
            }
        }
    }

    public static void swap(int[] arr, int i, int j){
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    public static void printArr(int[] arr){
        if (arr == null){
            return;
        }
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        exchange(arr);
        printArr(arr); // 1 3 5 7 9 2 4 6 8
    }
}
