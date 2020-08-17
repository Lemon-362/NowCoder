package NowCoder.二分查找;

public class BinarySearch {

    /*
    二分查找:
        注意点:
        (1) 不要出现else, 而是用else if将所有可能情况都列出来
        (2) int mid = left + (right - left) / 2

        while循环条件的 <= 和 < 的区别:
            (1) while(left <= right)
            ==> right = arr.length - 1
            ==> 搜索区间: [left, right] 左闭右闭
            ==> 终止条件: left == right + 1
            ==> 最终 return -1

            (2) while(left < right)
            ==> right = arr.length
            ==> 搜索区间: [left, right) 左闭右开
            ==> 终止条件: left == right
            TODO 此时会漏掉一个区间 [mid, mid), 所以如果使用 < 需要加上一点修改
            ==> 最终 return arr[left] == aim ? left : -1

        1. 寻找一个数
            left = mid + 1, right = mid - 1的解释:
            TODO 前提: 搜索区间是左闭右闭, 即 while(left <= right)
            那么, 已经检查过arr[mid]了, 应该从区间中去除
            所以剩下两个区间是
                [left, mid - 1] ==> right = mid - 1
                [mid + 1, right] ==> left = mid + 1

        2.
        int left = 0;
        int right = arr.length - 1;

        while (left <= right)

        if (arr[mid] == aim)
            (1) TODO 寻找一个数 ==> return mid;
            (2) TODO 寻找左边界 ==> right = mid - 1;
            (3) TODO 寻找右边界 ==> left = mid + 1;

        if (arr[mid] < aim)
            left = mid + 1;
        if (arr[mid] > aim)
            right = mid - 1;

        TODO 寻找一个数 ==> return -1;
        TODO 寻找左边界 ==> if (left >= arr.length || arr[left] != aim) {
                                return -1;
                            }
                            return left;
        TODO 寻找右边界 ==> if (right < 0 || arr[right] != aim) {
                                return -1;
                            }
                            return right;

     */

    /*
        二分框架(包含寻找一个数, 寻找左边界, 寻找右边界)
            剑指code06_FindMinInRotateMatrix
            剑指code37_GetNumOfK
    */
    public static int binarySearch(int[] arr, int aim){
        int left = 0;
        int right = arr.length - 1;

        while (left <= right){
            int mid = left + (right - left) / 2;

            if (arr[mid] == aim){
                // TODO 寻找一个数
//                return mid;
                // TODO 寻找左边界
//                right = mid - 1;
                // TODO 寻找右边界
//                left = mid + 1;
            }else if (arr[mid] < aim){
                left = mid + 1;
            }else if (arr[mid] > aim){
                right = mid - 1;
            }
        }

        // TODO 寻找一个数
//        return -1;
        // TODO 寻找左边界
//        if (left >= arr.length || arr[left] != aim) {
//            return -1;
//        }
//        return left;
        // TODO 寻找右边界
//        if (right < 0 || arr[right] != aim) {
//            return -1;
//        }
//        return right;

        return 0;
    }

    /*
        TODO 寻找一个数
     */
    public static int findNum(int[] arr, int aim) {
        int left = 0;
        // TODO 注意点1
        int right = arr.length - 1;

        while (left <= right) { // TODO 注意点2
            int mid = left + (right - left) / 2;

            if (arr[mid] == aim) {
                return mid;
            } else if (arr[mid] < aim) { // TODO 注意点3
                left = mid + 1;
            } else if (arr[mid] > aim) { // TODO 注意点4
                right = mid - 1;
            }
        }

        // TODO 注意点5
        return -1;
    }

    /*
        TODO 寻找左边界
         热题Array_code23_SearchRangeOfNum
     */
    public static int findLeft(int[] arr, int aim) {
        int left = 0;
        int right = arr.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (arr[mid] == aim) { // TODO 注意点2
                right = mid - 1;
            } else if (arr[mid] < aim) {
                left = mid + 1;
            } else if (arr[mid] > aim) { // TODO 注意点3
                right = mid - 1;
            }
        }

        // TODO 注意点4
        if (left >= arr.length || arr[left] != aim) {
            return -1;
        }

        return left;
    }

    /*
        TODO 寻找右边界
         热题Array_code23_SearchRangeOfNum
     */
    public static int findRight(int[] arr, int aim) {
        int left = 0;
        int right = arr.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (arr[mid] == aim) { // TODO 注意点1
                left = mid + 1;
            } else if (arr[mid] < aim) {
                left = mid + 1;
            } else if (arr[mid] > aim) {
                right = mid - 1;
            }
        }

        // TODO 注意点2
        if (right < 0 || arr[right] != aim) {
            return -1;
        }

        return right;
    }



}
