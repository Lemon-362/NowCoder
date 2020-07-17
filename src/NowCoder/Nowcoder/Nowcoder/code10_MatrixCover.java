package NowCoder.Nowcoder.Nowcoder;

/*
    矩形覆盖：（没有理解）
        我们可以用2*1的小矩形横着或者竖着去覆盖更大的矩形。
        请问用n个2*1的小矩形无重叠地覆盖一个2*n的大矩形，总共有多少种方法？
 */
public class code10_MatrixCover {
    // F(1) = 1, F(2) = 2, F(3) = 3, F(4) = 5
    public static int method(int n){
        if (n <= 0){
            return 0;
        }
        if (n <= 2){
            return n;
        }
        return method(n-1) + method(n-2);
    }

    public static void main(String[] args) {
        System.out.println(method(3)); // 3
        System.out.println(method(4)); // 5
        System.out.println(method(5)); // 8
    }
}
