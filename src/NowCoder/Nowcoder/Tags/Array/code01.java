package NowCoder.Nowcoder.Tags.Array;

public class code01 {
    /*
    时间复杂度： O(N+M), 最多走M行,N列
     */
    public static boolean findNum(int[][] arr, int num){
        if (arr == null || arr.length < 1){
            return false;
        }

        int curR = 0;
        int curC = arr[0].length - 1;

        while (curR < arr.length && curC > -1){
            if (arr[curR][curC] < num){
                curR++;
            }else if (arr[curR][curC] > num){
                curC--;
            }else {
                return true;
            }
        }

        return false;
    }

    public static void main(String[] args) {
        int[][] matrix = new int[][]{{0, 1, 2, 3, 4, 5, 6},// 0
                {10, 12, 13, 15, 16, 17, 18},// 1
                {23, 24, 25, 26, 27, 28, 29},// 2
                {44, 45, 46, 47, 48, 49, 50},// 3
                {65, 66, 67, 68, 69, 70, 71},// 4
                {96, 97, 98, 99, 100, 111, 122},// 5
                {166, 176, 186, 187, 190, 195, 200},// 6
                {233, 243, 321, 341, 356, 370, 380} // 7
        };

        System.out.println(findNum(matrix, 175));
    }
}
