package NowCoder.Nowcoder;

/*
    求1+2+3+...+n，要求不能使用乘除法、for、while、if、else、switch、case等关键字及条件判断语句（A?B:C）
    TODO 逻辑运算符的短路效应
 */
public class code47_SumProblem {
    // 递归
    public static int process(int n){
        // base case
        if (n == 1){
            return 1;
        }

        n += process(n - 1);

        return n;
    }

    /*
        TODO 由于不能用if判断，所以想到了逻辑运算符的短路效应
            那么递归终止条件 n == 1可以改为 n>1时开启递归，n==1时终止递归，向上回溯
            n>1可以看成是递归条件
            ==> n > 1 && process(n - 1) > 0：
                1. 当n>1不满足时，此时n==1，因为短路效应，process递归不执行，那么就可以回溯了。
                2. 因为是boolean类型，所以递归得改写
     */
    public static int res = 0;

    public static int sumProblem(int n){

//        boolean flag = n > 1 && sumProblem(n - 1) > 0;
//
//        // 此时，n==1，开始回溯
//        res += n;
//
//        return res;

        // 简化：n>1可以看成是递归条件
        boolean flag = n > 1 && (n += sumProblem(n - 1)) > 0;

        return n;
    }

    public static void main(String[] args) {
        System.out.println(process(9)); // 45
        System.out.println(sumProblem(9));
    }
}
