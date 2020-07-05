package NowCoder.Nowcoder.exercise;

public class code37 {
    public static int searchNumInSortedArr(int[] arr, int aim) {
        if (arr == null || arr.length < 1) {
            return 0;
        }

        int leftIndex = getLeftIndex(arr, aim);
        int rightIndex = getRightIndex(arr, 0, arr.length - 1, aim);

        if (leftIndex != -1 && rightIndex != -1){
            return rightIndex - leftIndex + 1;
        }else {
            return 0;
        }
    }

    public static int getLeftIndex(int[] arr, int aim){
        int i = 0;
        int j = arr.length - 1;
        int mid = 0;

        while (i <= j){
            mid = (i + j) >> 1;

            if (arr[mid] < aim){
                i = mid + 1;
            }else if (arr[mid] > aim){
                j = mid - 1;
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

    public static int getRightIndex(int[] arr, int l, int r, int aim){
        // base case
        if (l > r){
            return -1;
        }

        int mid = (l + r) >> 1;

        if (arr[mid] < aim){
            l = mid + 1;
            return getRightIndex(arr, l, r, aim);
        }else if (arr[mid] > aim){
            r = mid - 1;
            return getRightIndex(arr, l, r, aim);
        }else {
            if (mid + 1 < arr.length && arr[mid + 1] == aim){
                l = mid + 1;
                return getRightIndex(arr, l, r, aim);
            }else {
                return mid;
            }
        }
    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 3, 3, 3, 4, 5};

        System.out.println(searchNumInSortedArr(arr, 3)); // 4

        System.out.println("***************");

        System.out.println(searchNumInSortedArr(arr, 6)); // 0

    }
}
