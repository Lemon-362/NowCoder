package NowCoder.basic_class.basic_class.class_05;

/*
	岛问题：
		一个矩阵只有0和1两个值，如果有一片1连在一起，则称为一个岛
		求一个矩阵中有多少个岛？
	0	0	1	0	1	0
	1	1	1	0	1	0
	1	0	0	1	0	0
	0	0	0	0	0	0
		共有三个岛

	逐行遍历矩阵（双层for循环），内置感染函数infect，
	每遇到1，就将于此处的1连在一起的1全部变为2，岛的数量+1
	infect函数：递归，上下左右，停止条件：越界 / 位置不为1

	优化：多任务多CPU -- 因为上面的方法是针对整个矩阵，效率不高
		分割 + 合并逻辑（并查集）
	分割成多个块，每个块单独使用感染函数，并且记录边界处的1是由谁感染的（感染中心）
	合并逻辑：查边界的感染中心是否相同，若不同则合并，岛数量-1
 */
public class Code_03_Islands {

	public static int countIslands(int[][] m) {
		if (m == null || m[0] == null) {
			return 0;
		}
		int N = m.length;
		int M = m[0].length;
		int res = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (m[i][j] == 1) {
					res++;
					infect(m, i, j, N, M);
				}
			}
		}
		return res;
	}

	public static void infect(int[][] m, int i, int j, int N, int M) {
		if (i < 0 || i >= N || j < 0 || j >= M || m[i][j] != 1) {
			return;
		}
		m[i][j] = 2;
		infect(m, i + 1, j, N, M);
		infect(m, i - 1, j, N, M);
		infect(m, i, j + 1, N, M);
		infect(m, i, j - 1, N, M);
	}

	public static void main(String[] args) {
		int[][] m1 = {  { 0, 0, 0, 0, 0, 0, 0, 0, 0 }, 
				        { 0, 1, 1, 1, 0, 1, 1, 1, 0 }, 
				        { 0, 1, 1, 1, 0, 0, 0, 1, 0 },
				        { 0, 1, 1, 0, 0, 0, 0, 0, 0 }, 
				        { 0, 0, 0, 0, 0, 1, 1, 0, 0 }, 
				        { 0, 0, 0, 0, 1, 1, 1, 0, 0 },
				        { 0, 0, 0, 0, 0, 0, 0, 0, 0 }, };
		System.out.println(countIslands(m1));

		int[][] m2 = {  { 0, 0, 0, 0, 0, 0, 0, 0, 0 }, 
						{ 0, 1, 1, 1, 1, 1, 1, 1, 0 }, 
						{ 0, 1, 1, 1, 0, 0, 0, 1, 0 },
						{ 0, 1, 1, 0, 0, 0, 1, 1, 0 }, 
						{ 0, 0, 0, 0, 0, 1, 1, 0, 0 }, 
						{ 0, 0, 0, 0, 1, 1, 1, 0, 0 },
						{ 0, 0, 0, 0, 0, 0, 0, 0, 0 }, };
		System.out.println(countIslands(m2));

	}

}
