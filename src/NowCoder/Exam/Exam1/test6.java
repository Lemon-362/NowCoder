package NowCoder.Exam.Exam1;

public class test6 {
    public static int minCoins(int[] value, int[] count, int aim) {
        int num = 0;
        for (int i = value.length - 1; i >= 0; i--) {
            int c = Math.min(aim / value[i], count[i]);

            aim -= c * value[i];

            num += c;
        }

        if (aim > 0){
            num = -1;
        }

        return num;
    }

    public static void main(String[] args) {

        int[] value = {1, 5, 10, 50, 100};
        int[] count = {5, 2, 2, 3, 5};
        int aim = 55;

        System.out.println(minCoins(value, count, aim));
    }
}
