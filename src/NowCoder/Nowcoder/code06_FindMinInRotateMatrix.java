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

    /** TODO 二分查找条件的变换
     * 划分成了两个排序的子数组（前和后）
     * (1) 前 >= 后  ==> arr[p1] >= arr[p2]  ==> 循环条件
     * (2) 最小值是两者的分界线
     *
     * 数组在某一范围内是有序的 ==> 二分法
     *  p1始终指向前面, p2始终指向后面
     *  mid是p1和p2的中间值  TODO 循环条件: arr[p1] >= arr[p2]
     *  (1) mid位于前面 ==> arr[mid] >= arr[p1]
     *                ==> 最小值在mid之后 ==> p1 = mid
     *  (2) mid位于后面 ==> arr[mid] <= arr[p2]
     *                ==> 最小值在mid及其之前 ==> p2 = mid
     *  (3) TODO 结束条件:
     *       p1指向前面的最后一个, p2指向后面的第一个 ==> p1 - p2 == 1
     *       此时, p2即最小值的位置
     *  TODO 特殊情况:
     *  (1) 没有移动元素 ==> arr[p1] < arr[p2] ==> 最小值就是第一个元素
     *      这种情况可以包含在普通情况的(2)中, 只需要将mid初始化为p1
     *      一旦发现第一次arr[p1] < arr[p2], 那么直接返回mid, 此时mid就是第一个元素的位置
     *  (2) arr[p1] == arr[p2] == arr[mid]:
     *  例如: 0 1 1 1 1
     *   ==> 1 0 1 1 1 此时mid在后面
     *   ==> 1 1 1 0 1 此时mid在前面
     *   那么只能循环遍历
     *
     *  时间复杂度: O(logN)
     */
    public static int findMinInRotateArr(int[] arr){
        if (arr == null || arr.length < 1){
            return -1;
        }

        int p1 = 0;
        int p2 = arr.length - 1;
        int mid = p1;

        while (arr[p1] >= arr[p2]){
            mid = (p1 + p2) >> 1;

            // 结束条件
            if (p2 - p1 == 1){
                mid = p2;
                break;
            }

            // 特殊情况(2)
            if (arr[p1] == arr[p2] && arr[p1] == arr[mid]){
                return findInOrder(arr, p1, p2);
            }

            if (arr[mid] >= arr[p1]){
                p1 = mid;
            }else if (arr[mid] <= arr[p2]){
                p2 = mid;
            }
        }

        return arr[mid];
    }

    // 在p1-p2的范围内遍历寻找最小值
    public static int findInOrder(int[] arr, int p1, int p2){
        int res = arr[p1];
        for (int i = p1 + 1; i <= p2; i++) {
            res = Math.min(res, arr[i]);
        }
        return res;
    }

    public static void main(String[] args) {
        int[] arr1 = {7, 0, 1, 1, 1, 1, 1, 2, 3, 4};
        int[] arr2 = {3, 4, 5, 1, 2, 3};
        int[] arr3 = {1, 0, 1, 1, 1};

        System.out.println(method02(arr1)); // 0
        System.out.println(method02(arr2)); // 1
        System.out.println(method02(arr3)); // 0

        System.out.println("********************");

        System.out.println(method(arr1));
        System.out.println(method(arr2));
        System.out.println(method(arr3));

        System.out.println("********************");

        System.out.println(findMinInRotateArr(arr1));
        System.out.println(findMinInRotateArr(arr2));
        System.out.println(findMinInRotateArr(arr3));
    }
}
