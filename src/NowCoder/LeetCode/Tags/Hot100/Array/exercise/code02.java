package NowCoder.LeetCode.Tags.Hot100.Array.exercise;

/*
283. 移动 0
    给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，同时保持非零元素的相对顺序

 */
public class code02 {
    public static void moveZeros1(int[] arr) {
        if (arr == null || arr.length < 1) {
            return;
        }

        for (int i = arr.length - 1; i >= 0; i--) {
            for (int j = 0; j < i; j++) {
                if (arr[j] == 0 && arr[j + 1] != 0) {
                    swap(arr, j, j + 1);
                }
            }
        }
    }

    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void moveZeros2(int[] arr) {
        if (arr == null || arr.length < 1) {
            return;
        }

        int j = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] != 0){
                arr[j] = arr[i];
                j++;
            }
        }

        for (int i = j; i < arr.length; i++) {
            arr[i] = 0;
        }
    }

    public static void moveZeros3(int[] arr) {
        if (arr == null || arr.length < 1) {
            return;
        }

        int j = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] != 0){
                if (j < i){
                    arr[j] = arr[i];
                    arr[i] = 0;
                    j++;
                }
            }
        }
    }


    public static void print(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {

        int[] arr = {0, 1, 0, 3, 4, 0, 2, 8, 30, 0, 0, 12};

        moveZeros3(arr); // 1 3 4 2 8 30 12 0 0 0 0 0
        print(arr);

    }
}
