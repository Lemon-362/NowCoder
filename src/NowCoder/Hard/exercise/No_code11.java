package NowCoder.Hard.exercise;

public class No_code11 {
    public static int numberOf1(int n) {
        int count = 0;

        for (int i = 31; i >= 0; i--) {
            int path = (n >> i) & 1;

            if (path == 1){
                count++;
            }
        }

        return count;
    }

    public static int count1(int n) {
        int count = 0;

        while (n > 0){
            if ((n & 1) == 1){
                count++;
            }

            n = n >> 1;
        }

        return count;
    }

    public static int count2(int n) {
        int count = 0;

        while (n > 0){
            count++;

            n = (n - 1) & n;
        }

        return count;
    }

    public static void main(String[] args) {
        System.out.println(numberOf1(33211)); // 8
        System.out.println(count1(33211));
        System.out.println(count2(33211));
    }
}
