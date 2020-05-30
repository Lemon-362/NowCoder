package NowCoder.Nowcoder.exercise;

public class code65 {
    public static boolean hasPath(char[][] board, String word) {
        if (board.length < 1 || board[0].length < 1 || word.length() < 1) {
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
        if (i < 0 || i >= board.length || j < 0 || j >= board[0].length) {
            return false;
        }
        if (s[index] != board[i][j]) {
            return false;
        }
        if (index == s.length - 1) {
            return true;
        }

        char temp = board[i][j];

        board[i][j] = '#';

        boolean res = process(board, s, i + 1, j, index + 1)
                || process(board, s, i - 1, j, index + 1)
                || process(board, s, i, j + 1, index + 1)
                || process(board, s, i, j - 1, index + 1);

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
