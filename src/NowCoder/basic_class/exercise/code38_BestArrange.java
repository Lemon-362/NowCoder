package NowCoder.basic_class.exercise;

import java.util.Arrays;
import java.util.Comparator;

/*
	宣讲会安排：
		一个会议室不能同时容纳两个宣讲会。
		给定每个宣讲会的开始时间和结束时间（数组形式，每个元素包含两个信息），要求安排日程，使得宣讲的场次最多。

	按照结束时间升序，只要当前时间<=宣讲会的开始时间，就可以宣讲，当前时间变为该场宣讲会的结束时间。
 */
public class code38_BestArrange {
    public static class Program {
        private int start;
        private int end;

        public Program(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }

    public static int BestArrange(Program[] programs, int start){
        Arrays.sort(programs, new minEndComparator());
        int cur = start;
        int res = 0;
        for (int i = 0; i < programs.length; i++) {
            if (cur <= programs[i].start){
                res++;
                cur = programs[i].end;
            }
        }
        return res;
    }

    public static class minEndComparator implements Comparator<Program> {
        public int compare(Program o1, Program o2){
            return o1.end - o2.end;
        }
    }
}
