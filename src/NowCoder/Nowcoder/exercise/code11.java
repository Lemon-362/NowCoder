package NowCoder.Nowcoder.exercise;

public class code11 {
    public static int numberOf1(int n){
        int res = 0;

        for (int i = 31; i >=0 ; i--) {
            int path = (n >> i) & 1;

            if(path == 1){
                res++;
            }
        }

        return res;
    }

    public static void main(String[] args) {
        System.out.println(numberOf1(33211)); // 8
    }
}
