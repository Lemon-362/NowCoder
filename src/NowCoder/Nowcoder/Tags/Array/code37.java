package NowCoder.Nowcoder.Tags.Array;

public class code37 {
    public static int searchNumInSortedArr(int[] arr, int aim) {
        if (arr == null || arr.length < 1) {
            return 0;
        }

        int leftIndex = getLeftIndex(arr, 0, arr.length - 1, aim);
        int rightIndex = getRightIndex(arr, 0, arr.length - 1, aim);

        if (leftIndex == -1 && rightIndex == -1){
            return 0;
        }else {
            return rightIndex - leftIndex + 1;
        }
    }

    public static int getRightIndex(int[] arr, int l, int r, int aim){
        // base case
        if (l > r){
            return -1;
        }

        int mid = (l + r) >> 1;

        if (arr[mid] > aim){
            return getRightIndex(arr, l, mid - 1, aim);
        }else if (arr[mid] < aim){
            return getRightIndex(arr, mid + 1, r, aim);
        }else {
            if (mid + 1 <= r && arr[mid + 1] == arr[mid]){
                return getRightIndex(arr, mid + 1, r, aim);
            }else {
                return mid;
            }
        }
    }

    public static int getLeftIndex(int[] arr, int l, int r, int aim){
        int i = l;
        int j = r;
        int mid = 0;

        while (i <= j){
            mid = (i + j) >> 1;

            if (arr[mid] > aim){
                j = mid - 1;
            }else if (arr[mid] < aim){
                i = mid + 1;
            }else {
                if (mid - 1 >= l && arr[mid - 1] == arr[mid]){
                    j = mid - 1;
                }else {
                    return mid;
                }
            }
        }

        return -1;
    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 3, 3, 3, 4, 5};

        System.out.println(searchNumInSortedArr(arr, 3)); // 4

        System.out.println("***************");

        System.out.println(searchNumInSortedArr(arr, 6)); // 0

    }
}
