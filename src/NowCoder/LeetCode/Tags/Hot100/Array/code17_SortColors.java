package NowCoder.LeetCode.Tags.Hot100.Array;

/*
75. 颜色分类
    给定一个包含红色、白色和蓝色，一共 n 个元素的数组，
    原地对它们进行排序，使得相同颜色的元素相邻，并按照红色、白色、蓝色顺序排列。
    此题中，我们使用整数 0、 1 和 2 分别表示红色、白色和蓝色。
    TODO 实际上就是荷兰国旗问题, 因为荷兰国旗就是三色的

 */
public class code17_SortColors {

    public static void sortColors(int[] arr) {
        if (arr == null || arr.length < 1) {
            return;
        }

        int less = -1;
        int more = arr.length;
        int cur = 0;

        while (cur < more) {
            if (arr[cur] == 0) {
                swap(arr, ++less, cur++);
            } else if (arr[cur] == 2) {
                swap(arr, --more, cur);
            } else {
                cur++;
            }
        }
    }

    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void main(String[] args) {

        int[] arr = {2, 0, 2, 1, 1, 0};

        sortColors(arr);
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
    }
}
