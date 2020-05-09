package NowCoder.basic_class.exercise.exercise02;

public class code06 {
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

        return new int[] {less + 1, more - 1};
    }

    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
