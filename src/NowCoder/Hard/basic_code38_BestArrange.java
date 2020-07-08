package NowCoder.Hard;

import java.util.Arrays;
import java.util.Comparator;

/*
	宣讲会安排：
		一个会议室不能同时容纳两个宣讲会。
		给定每个宣讲会的开始时间和结束时间（数组形式，每个元素包含两个信息），要求安排日程，使得宣讲的场次最多。

	按照结束时间升序，只要当前时间<=宣讲会的开始时间，就可以宣讲，当前时间变为该场宣讲会的结束时间。
 */
public class basic_code38_BestArrange {
    public static class Program {
        private int start;
        private int end;

        public Program(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }

    public static int BestArrange(Program[] programs, int start) {
        Arrays.sort(programs, new minEndComparator());
        int cur = start;
        int res = 0;
        for (int i = 0; i < programs.length; i++) {
            if (cur <= programs[i].start) {
                res++;
                cur = programs[i].end;
            }
        }
        return res;
    }

    public static class minEndComparator implements Comparator<Program> {
        public int compare(Program o1, Program o2) {
            return o1.end - o2.end;
        }
    }

    public static void main(String[] args) {
        int[] start = {1, 2, 4, 6, 8};
        int[] end = {3, 5, 7, 9, 10};

        Program[] programs = new Program[start.length];
        for (int i = 0; i < programs.length; i++) {
            programs[i] = new Program(start[i], end[i]);
        }

        System.out.println(BestArrange(programs, 0)); // 3

    }
}
