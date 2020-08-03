package NowCoder.LeetCode.Tags.Hot100.Array;

/*
283. 移动 0
    给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，同时保持非零元素的相对顺序

 */
public class code02_MoveZeros {

    /*
    方法一: 冒泡排序

        时间复杂度: O(N^2)
     */
    public static void moveZeros1(int[] arr) {
        if (arr == null || arr.length < 1) {
            return;
        }

        for (int i = arr.length - 1; i >= 0; i--) {
            for (int j = 0; j < i; j++) {
                if (arr[j] == 0){
                    swap(arr, j, j + 1);
                }
            }
        }
    }

    public static void swap(int[] arr, int i, int j){
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    /*
    方法二: 统计非零元素, 将非零元素覆盖0的位置, 在最后加上0的个数
        需要两次遍历

        时间复杂度: O(N)
     */
    public static void moveZeros2(int[] arr){
        if (arr == null || arr.length < 1) {
            return;
        }

        int count = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] != 0){
                arr[count++] = arr[i];
            }
        }

        for (int i = count; i < arr.length; i++) {
            arr[i] = 0;
        }
    }

    /*
    方法三: 一次遍历, 用指针j表示0的位置
        在遍历arr的过程中, 遇到非零元素时, 将其和指针j的位置互换

        时间复杂度: O(N)
     */
    public static void moveZeros3(int[] arr){
        if (arr == null || arr.length < 1) {
            return;
        }

        int j = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] != 0){
//                int temp = arr[i];
//                arr[i] = arr[j];
//                arr[j++] = temp;

                // TODO 优化, 当表示0的指针j的位置超过了非零元素的位置时, 就不需要交换
                if (i > j){
                    arr[j] = arr[i];
                    arr[i] = 0;
                }
                j++;
            }
        }
    }


    public static void print(int[] arr){
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {

        int[] arr = {0, 1, 0, 3, 12};

        moveZeros3(arr);
        print(arr);

    }
}
