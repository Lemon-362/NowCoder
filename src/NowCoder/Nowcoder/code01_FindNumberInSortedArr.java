package NowCoder.Nowcoder;

/*
    二维数组的查找：
        在一个二维数组中（每个一维数组的长度相同）
        每一行都按照从左到右递增的顺序排序，每一列都按照从上到下递增的顺序排序。
        请完成一个函数，输入这样的一个二维数组和一个整数，判断数组中是否含有该整数。
 */
public class code01_FindNumberInSortedArr {
    // 方法一：逐行逐列搜索
    public static boolean Find1(int target, int[][] array) {
        // 数组每行递增，每列递增
        // 要求输入一个二维数组和一个整数，判断数组中是否含有该整数，返回boolean值
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[0].length; j++) {
                if (array[i][j] == target) {
                    return true;
                }
            }
        }
        return false;
    }

    // 方法二：增强for循环
    public static boolean Find2(int target, int [][] array) {
        for(int[] row: array){
            for(int ele: row){
                if(ele == target){
                    return true;
                }
            }
        }
        return false;
    }

    //方法三：从左下角开始
    public static boolean Find3(int target, int [][] array) {
        // 数组每行递增，每列递增
        // 要求输入一个二维数组和一个整数，判断数组中是否含有该整数，返回boolean值
        // 左下角m是该行最小，该列最大
        // 如果target < m，行--
        // 如果target > m，列++

        int row = array.length;
        int col = array[0].length;
        if(col <= 0 || target < array[0][0] || target > array[row - 1][col - 1]){
            return false;
        }

        int cols = 0; // 临时变量，用于找到所在的列
        int rows = row - 1; // 先在最后一行上搜索
        boolean flag = false; // 设置一个标识符

        while(rows >= 0 && cols < col){ // 先在最后一行上搜索
            if(target < array[rows][cols]){ // 如果target小于等于m，说明在该列上
                rows--;
            }else if(target > array[rows][cols]){ // 如果target大于m，说明不在该列上
                cols++;
            }else{ // 如果target等于m，说明找到了
                flag = true;
                break;
            }
        }
        return flag;
    }

    // 方法四：从右上角开始
    public static boolean Find4(int target, int [][] array) {
        // 数组每行递增，每列递增
        // 要求输入一个二维数组和一个整数，判断数组中是否含有该整数，返回boolean值
        // 右上角m是该行最大，该列最小
        // 如果target < m，列--
        // 如果target > m，行++

        int row = array.length;
        int col = array[0].length;
        if(col <= 0 || target < array[0][0] || target > array[row - 1][col - 1]){
            return false;
        }

        int cols = col - 1; // 临时变量，用于找到所在的列
        int rows = 0;
        boolean flag = false; // 设置一个标识符

        while(cols >= 0 && rows < row){
            if(target < array[rows][cols]){ // 如果target < m，列--
                cols--;
            }else if(target > array[rows][cols]){ // 如果target > m，行++
                rows++;
            }else{ // 如果target等于m，说明找到了
                flag = true;
                break;
            }
        }
        return flag;
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
        int target = 50;
        System.out.println(Find1(target, matrix));
        System.out.println(Find2(target, matrix));
        System.out.println(Find3(target, matrix));
        System.out.println(Find4(target, matrix));
    }
}

