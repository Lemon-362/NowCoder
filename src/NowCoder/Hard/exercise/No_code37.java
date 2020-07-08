package NowCoder.Hard.exercise;

public class No_code37 {
    public static int searchNumInSortedArr(int[] arr, int aim) {
        if (arr == null || arr.length < 1) {
            return 0;
        }

        int leftIndex = getLeftIndex(arr, aim, 0, arr.length - 1);
        int rightIndex = getRightIndex(arr, aim);

        if (leftIndex != -1 && rightIndex != -1){
            return rightIndex - leftIndex + 1;
        }else {
            return 0;
        }
    }

    public static int getRightIndex(int[] arr, int aim){
        int l = 0;
        int r = arr.length - 1;
        int mid = 0;

        while (l <= r){
            mid = (l + r) >> 1;

            if (arr[mid] < aim){
                l = mid + 1;
            }else if (arr[mid] > aim){
                r = mid - 1;
            }else {
                if (mid + 1 < arr.length && arr[mid] == arr[mid + 1]){
                    l = mid + 1;
                }else {
                    return mid;
                }
            }
        }

        return -1;
    }

    public static int getLeftIndex(int[] arr, int aim, int l, int r){
        // base case
        if (l > r){
            return -1;
        }

        int mid = (l + r) >> 1;

        if (arr[mid] < aim){
            l = mid + 1;
            return getLeftIndex(arr, aim, l, r);
        }else if (arr[mid] > aim){
            r = mid - 1;
            return getLeftIndex(arr, aim, l, r);
        }else {
            if (mid - 1 >= 0 && arr[mid] == arr[mid - 1]){
                r = mid - 1;
                return getLeftIndex(arr, aim, l, r);
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
