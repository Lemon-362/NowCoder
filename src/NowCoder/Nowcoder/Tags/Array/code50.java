package NowCoder.Nowcoder.Tags.Array;

public class code50 {
    public static int duplicate(int[] arr){
        if (arr == null || arr.length < 1){
            return -1;
        }

        for (int i = 0; i < arr.length; i++) {
            while (i != arr[i]){
                if (arr[i] == arr[arr[i]]){
                    return arr[i];
                }else {
                    swap(arr, i, arr[i]);
                }
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
        int mid = 0;

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

    public static int count(int[] arr, int l, int r){
        int count = 0;

        for (int i = 0; i < arr.length; i++) {
            if (arr[i] >= l && arr[i] <= r){
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