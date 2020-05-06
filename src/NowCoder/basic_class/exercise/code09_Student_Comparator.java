package NowCoder.basic_class.exercise;

import java.util.Arrays;
import java.util.Comparator;

public class code09_Student_Comparator {

    public static class Student {
        private String name;
        private int id;
        private int age;

        public Student(String name, int id, int age) {
            this.name = name;
            this.id = id;
            this.age = age;
        }
    }

    public static class idSort implements Comparator<Student> {

        @Override
        public int compare(Student o1, Student o2) {
            return o1.id - o2.id;
        }
    }

    public static class ageSort implements Comparator<Student> {

        @Override
        public int compare(Student o1, Student o2) {
            return o1.age - o2.age;
        }
    }

    public static void printString(Student[] students) {
        for (Student student : students) {
            System.out.println("姓名：" + student.name + ",ID：" + student.id
                    + ",年龄：" + student.age);
        }
        System.out.println("=================");
    }


    public static void main(String[] args) {
        // 创建学生类的实例
        Student student1 = new Student("A", 1, 24);
        Student student2 = new Student("B", 2, 22);
        Student student3 = new Student("C", 3, 23);
        // 创建学生类的数组
        Student[] students = {student3, student2, student1};
        // 打印数组
        printString(students);
        // 排序
        Arrays.sort(students, new idSort());
        printString(students);
        Arrays.sort(students, new ageSort());
        printString(students);
    }
}
