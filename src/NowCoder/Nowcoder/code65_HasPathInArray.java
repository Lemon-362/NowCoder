package NowCoder.Nowcoder;


/*
    矩阵中的路径：
        判断在一个矩阵中是否存在一条包含某字符串所有字符的路径。
        路径可以从矩阵中的任意一个格子开始，每一步可以在矩阵中向左，向右，向上，向下移动一个格子。
        如果一条路径经过了矩阵中的某一个格子，则该路径不能再进入该格子
 */
public class code65_HasPathInArray {
    // TODO 为了保证不重复进入，可以将走过的格子变成其他符号

    public static boolean hasPath(char[][] board, String word) {
        if (board == null || board.length < 1 || board[0].length < 1) {
            return false;
        }

        char[] s = word.toCharArray();

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (process(board, s, i, j, 0)) {
                    return true;
                }
            }
        }

        return false;
    }

    public static boolean process(char[][] board, char[] s, int i, int j, int index) {
        // base case
        if (i < 0 || i > board.length - 1 || j < 0 || j > board[0].length - 1 || board[i][j] != s[index]) {
            return false;
        }
        if (index == s.length - 1) {
            return true;
        }

        char temp = board[i][j];
        board[i][j] = '!';

        boolean res = process(board, s, i + 1, j, index + 1)
                || process(board, s, i - 1, j, index + 1)
                || process(board, s, i, j + 1, index + 1)
                || process(board, s, i, j - 1, index + 1);

        // 回溯时要还原原字符
        board[i][j] = temp;

        return res;
    }

    public static void main(String[] args) {
        char[][] board = {{'A', 'B', 'C', 'E'}, {'S', 'F', 'C', 'S'}, {'A', 'D', 'E', 'E'}};
        String word = "ABCCED";

        char[][] board1 = {{'a', 'b'}, {'c', 'd'}};
        String word1 = "abcd";

        System.out.println(hasPath(board, word)); // true
        System.out.println(hasPath(board1, word1)); // false

    }

}
