package NowCoder.Nowcoder.exercise;

public class code37 {
    public static int searchNumInSortedArr(int[] arr, int aim) {
        if (arr == null || arr.length < 1) {
            return 0;
        }

        int leftIndex = getLeftIndex(arr, aim);
        int rightIndex = getRightIndex(arr, aim, 0, arr.length - 1);

        if (leftIndex != -1 && rightIndex != -1){
            return rightIndex - leftIndex + 1;
        }else {
            return 0;
        }
    }

    public static int getRightIndex(int[] arr, int aim, int i, int j){
        // base case
        if (i > j){
            return -1;
        }

        int mid = (i + j) >> 1;

        if (arr[mid] > aim){ // 1
            j = mid - 1;
            return getRightIndex(arr, aim, i, j);
        }else if (arr[mid] < aim){ // 2
            i = mid + 1;
            return getRightIndex(arr, aim, i, j);
        }else { // 3
            if (mid + 1 <= j && arr[mid + 1] == aim){
                i = mid + 1;
                return getRightIndex(arr, aim, i, j);
            }else {
                return mid;
            }
        }
    }

    public static int getLeftIndex(int[] arr, int aim){
        int i = 0;
        int j = arr.length - 1;
        int mid;

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

    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 3, 3, 3, 4, 5};

        System.out.println(searchNumInSortedArr(arr, 3)); // 4

        System.out.println("***************");

        System.out.println(searchNumInSortedArr(arr, 6)); // 0

    }
}
