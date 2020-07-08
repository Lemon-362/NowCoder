package NowCoder.Hard.exercise;

public class No_code65 {
    public static boolean hasPath(char[][] board, String word) {
        if (board.length < 1 || board[0].length < 1 || word.length() < 1) {
            return false;
        }

        char[] str = word.toCharArray();

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (process(board, str, i, j, 0)) {
                    return true;
                }
            }
        }

        return false;
    }

    public static boolean process(char[][] board, char[] str, int i, int j, int index) {
        // base case
        if (index == str.length) {
            return true;
        }

        if (i < 0 || i >= board.length || j < 0 || j >= board[0].length) {
            return false;
        }

        char temp = board[i][j];

        if (board[i][j] == str[index]) {
            board[i][j] = '#';

            boolean res = process(board, str, i + 1, j, index + 1)
                    || process(board, str, i - 1, j, index + 1)
                    || process(board, str, i, j + 1, index + 1)
                    || process(board, str, i, j - 1, index + 1);

            if (res){
                return true;
            }else {
                board[i][j] = temp;
                return false;
            }
        }

        return false;
    }

    public static void main(String[] args) {
        char[][] board = {{'A', 'B', 'C', 'E'}, {'S', 'F', 'C', 'S'}, {'A', 'D', 'E', 'E'}};
        String word = "ABCCED";

        char[][] board1 = {{'a', 'b'}, {'c', 'd'}};
        String word1 = "abcd";

        char[][] board2 = {{'a'}};
        String word2 = "b";

        System.out.println(hasPath(board, word)); // true
        System.out.println(hasPath(board1, word1)); // false
        System.out.println(hasPath(board2, word2)); // false

    }
}
