package NowCoder.Hard;

/*
    统计一个数字在排序数组中出现的次数。
        排序数组中，如果有很多相同元素，那么一定在一起，所以可以通过二分法，查找第一个和最后一个的索引
        通过下标计算就能得到相同元素的个数
    TODO 有序数组问题的解题思路：画折线图 + 二分法
        递归和循环可以相互转换
 */
public class No_code37_GetNumOfK {
    /** TODO 有序数组 ==> 二分法
     * 循环法 + 二分法
     *
     * TODO 一定要考虑边界情况
     *  当target不在arr数组中时, 左右指针会在同一位置
     *  此时最好是写成两个函数, 方便return -1
     */
    public static int GetNumberOfK(int[] arr, int aim) {
        if (arr == null || arr.length < 1) {
            return 0;
        }

        int leftIndex = getLeftIndex(arr, aim);
        int rightIndex = getRightIndex(arr, aim);

        if (leftIndex == -1 && rightIndex == -1){
            return 0;
        }else {
            return rightIndex - leftIndex + 1;
        }
    }

    public static int getLeftIndex(int[] arr, int aim){
        int i = 0;
        int j = arr.length - 1;
        int mid = 0;

        while (i <= j){
            mid = (i + j) >> 1;

            if (arr[mid] > aim){
                j = mid - 1;
            }else if (arr[mid] < aim){
                i = mid + 1;
            }else {
                if (mid - 1 >= 0 && arr[mid - 1] == aim){
                    j = mid - 1;
                }else {
                    return mid;
                }
            }
        }

        return -1;
    }

    public static int getRightIndex(int[] arr, int aim){
        int i = 0;
        int j = arr.length - 1;
        int mid = 0;

        while (i <= j){
            mid = (i + j) >> 1;

            if (arr[mid] > aim){
                j = mid - 1;
            }else if (arr[mid] < aim){
                i = mid + 1;
            }else {
                if (mid + 1 < arr.length && arr[mid + 1] == aim){
                    i = mid + 1;
                }else {
                    return mid;
                }
            }
        }

        return -1;
    }

    /**
     * 递归法 + 二分法
     * (1) 找第一个aim:
     *  - arr[mid] > aim ==> 第一个aim在mid之前 ==> j = mid - 1
     *  - arr[mid] < aim ==> 第一个aim在mid之后 ==> i = mid + 1
     *  - arr[mid] == aim ==> 要看mid之前的一个是否也为aim
     *                      - 之前一个 == aim ==> 第一个aim在mid之前 ==> j = mid - 1
     *                      - 之前一个 != aim ==> 第一个aim就是mid位置
     *                      - 特殊情况: mid到达最左边 ==> 第一个aim就是mid位置
     * (2) 找最后一个aim:
     * - arr[mid] > aim ==> 最后一个aim在mid之前 ==> j = mid - 1
     * - arr[mid] < aim ==> 最后一个aim在mid之后 ==> i = mid + 1
     * - arr[mid] == aim ==> 要看mid之后的一个是否也为aim
     *                      - 之后一个 == aim ==> 最后一个aim在mid之前 ==> i = mid + 1
     *                      - 之后一个 != aim ==> 最后一个aim就是mid位置
     *                      - 特殊情况: mid到达最右边 ==> 最后一个aim就是mid位置
     *
     * 由此可以看出, 使用递归即可解决, 递归内使用二分法
     *
     * 时间复杂度: O(logN)
     */
    public static int GetNumberOfK1(int[] arr, int aim){
        if (arr == null || arr.length < 1) {
            return 0;
        }

        int left = getLeftIndexByOrder(arr, aim, 0, arr.length - 1);
        int right = getRightIndexByOrder(arr, aim, 0, arr.length - 1);

        // TODO 考虑边界情况
        if (left == -1 && right == -1){
            return 0;
        }else {
            return right - left + 1;
        }
    }

    public static int getLeftIndexByOrder(int[] arr, int aim, int l, int r){
        // base case
        if (l > r){
            return -1;
        }

        int mid = (l + r) >> 1;

        if (arr[mid] > aim){
            r = mid - 1;
            return getLeftIndexByOrder(arr, aim, l, r);
        }else if (arr[mid] < aim){
            l = mid + 1;
            return getLeftIndexByOrder(arr, aim, l, r);
        }else {
            if (mid - 1 >= l && arr[mid - 1] == aim){
                r = mid - 1;
                return getLeftIndexByOrder(arr, aim, l, r);
            }else {
                return mid;
            }
        }
    }

    public static int getRightIndexByOrder(int[] arr, int aim, int l, int r){
        // base case
        if (l > r){
            return -1;
        }

        int mid = (l + r) >> 1;

        if (arr[mid] > aim){
            r = mid - 1;
            return getRightIndexByOrder(arr, aim, l, r);
        }else if (arr[mid] < aim){
            l = mid + 1;
            return getRightIndexByOrder(arr, aim, l, r);
        }else {
            if (mid + 1 <= r && arr[mid + 1] == aim){
                l = mid + 1;
                return getRightIndexByOrder(arr, aim, l, r);
            }else {
                return mid;
            }
        }
    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 3, 3, 3, 4, 5};

        System.out.println(GetNumberOfK(arr, 3)); // 4
        System.out.println(GetNumberOfK1(arr, 3));

        System.out.println("***************");

        System.out.println(GetNumberOfK(arr, 6)); // 0
        System.out.println(GetNumberOfK1(arr, 6));
    }
}
