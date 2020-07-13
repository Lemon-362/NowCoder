package NowCoder.Hard.exercise;

import java.util.HashMap;

public class No_code28 {
    public static int getMoreThanHalfNum(int[] arr){
        if (arr == null || arr.length < 1){
            return 0;
        }else if (arr.length == 1){
            return arr[0];
        }

        HashMap<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < arr.length; i++) {
            if (!map.containsKey(arr[i])){
                map.put(arr[i], 1);
            }else {
                int times = map.get(arr[i]) + 1;

                if (times > arr.length / 2){
                    return arr[i];
                }else {
                    map.put(arr[i], times);
                }
            }
        }

        return 0;
    }

    public static int getMoreThanHalfNum1(int[] arr) {
        if (arr == null || arr.length < 1) {
            return 0;
        }else if (arr.length == 1){
            return arr[0];
        }

        int num = getKthMinNum(arr, arr.length / 2);

        int times =  0;

        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == num){
                times++;
            }
        }

        return times > arr.length / 2 ? num : 0;
    }

    public static int getKthMinNum(int[] arr, int k){
        int[] copyArr = copyArr(arr);

        return bfprt(copyArr, 0, copyArr.length / 2, k - 1);
    }

    public static int[] copyArr(int[] arr){
        int[] res = new int[arr.length];

        for (int i = 0; i < arr.length; i++) {
            res[i] = arr[i];
        }

        return res;
    }

    public static int bfprt(int[] arr, int l, int r, int k){
        // base case
        if (l == r){
            return arr[l];
        }

        int num = medianInMedians(arr, l, r);

        int[] p = partition(arr, l, r, num);

        if (k >= p[0] && k <= p[1]){
            return num;
        }else if (k < p[0]){
            return bfprt(arr, l, p[0] - 1, k);
        }else {
            return bfprt(arr, p[1] + 1, r, k);
        }
    }

    public static int[] partition(int[] arr, int l, int r, int num){
        int less = l - 1;
        int more = r + 1;
        int cur = l;

        while (cur < more){
            if (arr[cur] < num){
                swap(arr, ++less, cur++);
            }else if (arr[cur] > num){
                swap(arr, --more, cur);
            }else {
                cur++;
            }
        }

        return new int[]{less + 1, more - 1};
    }

    public static int medianInMedians(int[] arr, int l, int r){
        int len = r - l + 1;
        int offset = len % 5 == 0 ? 0 : 1;
        int group = len / 5 + offset;
        int[] mArr = new int[group];

        for (int i = 0; i < mArr.length; i++) {
            int start = l + 5 * i;
            int end = start + 4;

            if (i == mArr.length - 1){
                mArr[i] = getMedian(arr, start, r);
            }else {
                mArr[i] = getMedian(arr, start, end);
            }
        }

        return bfprt(mArr, 0, mArr.length - 1, mArr.length / 2);
    }

    public static int getMedian(int[] arr, int l, int r){
        selectSort(arr, l, r);
        int mid = (l + r) >> 1;

        return arr[mid];
    }

    public static void selectSort(int[] arr, int l, int r){
        for (int i = l; i <= r; i++) {
            int minIndex = i;
            for (int j = i + 1; j <= r; j++) {
                minIndex = arr[minIndex] > arr[j] ? j : minIndex;
            }
            swap(arr, minIndex, i);
        }
    }

    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static int getMoreThanHalfNum2(int[] arr) {
        if (arr == null || arr.length < 1) {
            return 0;
        }else if (arr.length == 1){
            return arr[0];
        }

        int times = 1;
        int res = arr[0];

        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == res){
                times++;
            }else {
                times--;

                if (times == 0){
                    times = 1;
                    res = arr[i];
                }
            }
        }

        int k = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == res) {
                k++;
            }
        }

        return k > arr.length / 2 ? res : 0;
    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 2, 4, 2, 5, 2, 3};
        System.out.println(getMoreThanHalfNum(arr)); // 0
        System.out.println(getMoreThanHalfNum1(arr));
        System.out.println(getMoreThanHalfNum2(arr));

        System.out.println("*******************");

        int[] arr1 = {1, 2, 3, 2, 2, 2, 5, 4, 2};
        System.out.println(getMoreThanHalfNum(arr1)); // 2
        System.out.println(getMoreThanHalfNum1(arr1));
        System.out.println(getMoreThanHalfNum2(arr1));
    }
}
