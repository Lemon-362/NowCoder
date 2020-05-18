package NowCoder.Nowcoder;

/*
    在旋转数组中找最小值：
        旋转数组：将一个数组最开始的几个数搬到数组末尾
    输入一个非递减的数组的旋转数组，输出此旋转数组的最小值
    TODO  画折线图解题
 */
public class code06_FindMinInRotateMatrix {
    // O(logN)
    public static int method(int[] arr) {
        if (arr.length == 0) {
            return 0;
        }
        // 二分法
        int i = 0;
        int j = arr.length - 1;
        int mid = 0;
        while (i < j) {
            mid = (i + j) >> 1;

            // 如果最左边 < 最右边,说明未旋转,又是非递减的,所以最小值一定是最左边的
            if (arr[i] < arr[j]) {
                return arr[i];
            }

            // TODO 旋转后,左排序 >= 右排序
            if (arr[mid] > arr[j]) { // mid在左排序, mid之前的数一定>arr[mid], 所以最小值只可能在mid之后
                // 例如: 3 4 5 1 2
                i = mid + 1;
            } else if (arr[mid] < arr[j]) { // mid在右排序, mid之后的数一定>arr[mid], 所以最小值只可能在mid及其之前
                // 例如: 4 5 1 2 3
                j = mid;
            } else { // arr[mid] == arr[j]
                // 因为是非递减, 所以可能mid--j都是相同值, 而mid之前的可能比mid大(处于左排序), 也可能比mid小(处于右排序)
                // 所以可以从mid往前继续比较
                j = mid;
            }
        }

        return arr[i];
    }

    // O(N)：顺序遍历，找到后一个比前一个小的时候
    public static int method02(int[] arr) {
        if (arr.length == 0) {
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

        System.out.println(method02(arr1)); // 0
        System.out.println(method02(arr2)); // 1
        System.out.println(method02(arr3)); // 0

        System.out.println(method(arr1));
        System.out.println(method(arr2));
        System.out.println(method(arr3));
    }
}
