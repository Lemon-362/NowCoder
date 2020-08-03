package InputAndOutput;

import java.util.*;

public class test {
    static int m;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()){
            int n = sc.nextInt(); // 学生
            m = sc.nextInt(); // 课程
            int num = 0;
            List<Student> list = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                int[] score = new int[m];
                for (int j = 0; j < m; j++) {
                    score[j] = sc.nextInt();
                }
                list.add(new Student(num++, score));
                Collections.sort(list);

            }
            for (Student student : list) {
                System.out.println("num:"+student.num);
                for (int i = 0; i < student.score.length; i++) {
                    System.out.print(student.score[i]+" ");
                }
                System.out.println();
            }
        }
    }

    public static class Student implements Comparable<Student>{
        private int num;
        private int[] score;

        public Student(int num, int[] score) {
            this.num = num;
            this.score = score;
        }

        @Override
        public int compareTo(Student o) {
            return compare(this.score, o.score);
        }

        public int compare(int[] x, int[] y){
            for (int i = 0; i < m; i++) {
                if (x[i] - y[i] > 0){
                    return -1;
                }else if (x[i] - y[i] < 0){
                    return 1;
                }
            }
            return 0;
        }
    }
}
