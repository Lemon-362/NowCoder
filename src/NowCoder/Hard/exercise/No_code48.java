package NowCoder.Hard.exercise;

public class No_code48 {
    public static int addProblem(int a, int b){

        while (b != 0){
            int temp = a;

            a ^= b;

            b = (b & temp) << 1;
        }

        return a;
    }

    public static void main(String[] args) {
        System.out.println(addProblem(5, 6)); // 11
        System.out.println(addProblem(19, 23)); // 42
    }
}
