package NowCoder.basic_class.exercise01.Sort;

public class NetherlandsFlag {
    public static int[] method(int[] arr, int num) {
        if (arr == null || arr.length < 2) {
            return null;
        }

        return partition(arr, 0, arr.length - 1, num);
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

    public static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    public static void main(String[] args) {
        int[] arr = generateArr();
        printArr(arr);
        int[] p = method(arr, 3);
        printArr(arr);
        System.out.println(p[0]);
        System.out.println(p[1]);
    }

    public static int[] generateArr() {
        int[] arr = new int[10];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * 10);
        }
        return arr;
    }

    public static void printArr(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }
}
