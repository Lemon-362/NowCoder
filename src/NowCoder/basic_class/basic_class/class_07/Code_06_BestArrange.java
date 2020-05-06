package NowCoder.basic_class.basic_class.class_07;

import java.util.Arrays;
import java.util.Comparator;

/*
	宣讲会安排：
		一个会议室不能同时容纳两个宣讲会。
		给定每个宣讲会的开始时间和结束时间（数组形式，每个元素包含两个信息），要求安排日程，使得宣讲的场次最多。

	按照结束时间升序，只要当前时间<=宣讲会的开始时间，就可以宣讲，当前时间变为该场宣讲会的结束时间。
 */
public class Code_06_BestArrange {
	// 每一个宣讲会
	public static class Program {
		public int start;
		public int end;

		public Program(int start, int end) {
			this.start = start;
			this.end = end;
		}
	}

	public static class ProgramComparator implements Comparator<Program> {

		@Override
		public int compare(Program o1, Program o2) {
			return o1.end - o2.end;
		}

	}

	public static int bestArrange(Program[] programs, int start) {
		Arrays.sort(programs, new ProgramComparator()); // 按照结束时间排序
		int result = 0;
		for (int i = 0; i < programs.length; i++) {
			if (start <= programs[i].start) { // 只要当前时刻小于下一场的开始时间就可以宣讲
				result++;
				start = programs[i].end; // 当前时刻更新为上一场的结束时间
			}
		}
		return result;
	}

	public static void main(String[] args) {

	}

}
