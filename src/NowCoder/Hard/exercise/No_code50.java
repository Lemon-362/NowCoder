package NowCoder.Hard.exercise;

public class No_code50 {
    public static int duplicate(int[] arr){
        if (arr == null || arr.length < 1){
            return -1;
        }

        for (int i = 0; i < arr.length; i++) {
            while (arr[i] != i){
                if (arr[i] == arr[arr[i]]){
                    return arr[i];
                }
                swap(arr, i, arr[i]);
            }
        }

        return -1;
    }

    public static void swap(int[] arr, int i, int j){
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static int duplicateWithoutChange(int[] arr){
        if (arr == null || arr.length < 1){
            return -1;
        }

        int l = 1;
        int r = arr.length - 1;
        int mid;

        while (l <= r){
            mid = (l + r) >> 1;

            int count = count(arr, l, mid);

            if (l == r && count > 1){
                return l;
            }

            if (count > (mid - l + 1)){
                r = mid;
            }else {
                l = mid + 1;
            }
        }

        return -1;
    }

    public static int count(int[] arr, int start, int end){
        int count = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] >= start && arr[i] <= end){
                count++;
            }
        }

        return count;
    }

    public static void main(String[] args) {
        int[] arr = {2, 3, 1, 0, 4, 5, 3};

        System.out.println(duplicate(arr)); // 3

        System.out.println("*********************");

        int[] arr1 = {2, 3, 5, 4, 3, 2, 6, 7};
        System.out.println(duplicateWithoutChange(arr1)); // 3
    }
}
