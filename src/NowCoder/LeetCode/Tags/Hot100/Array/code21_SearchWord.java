package NowCoder.LeetCode.Tags.Hot100.Array;

/*
79. 单词搜索
    给定一个二维网格和一个单词，找出该单词是否存在于网格中。
    单词必须按照字母顺序，通过相邻的单元格内的字母构成，
    其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。
    同一个单元格内的字母不允许被重复使用

    TODO 和剑指code65_HasPathInArray相同
        深度优先 + 回溯

 */
public class code21_SearchWord {

    public static boolean searchWord(char[][] board, String word) {
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
        }else {
            return false;
        }
    }

    public static void main(String[] args) {
        char[][] board = {{'A', 'B', 'C', 'E'}, {'S', 'F', 'C', 'S'}, {'A', 'D', 'E', 'E'}};
        String word = "ABCCED";

        char[][] board1 = {{'a', 'b'}, {'c', 'd'}};
        String word1 = "abcd";

        char[][] board2 = {{'a'}};
        String word2 = "b";

        System.out.println(searchWord(board, word)); // true
        System.out.println(searchWord(board1, word1)); // false
        System.out.println(searchWord(board2, word2)); // false

    }
}
