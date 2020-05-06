package NowCoder.basic_class.basic_class.basic_class_01;

import java.util.Arrays;
import java.util.Comparator;

public class Code_09_Comparator {

	public static class Student {
		public String name;
		public int id;
		public int age;

		public Student(String name, int id, int age) {
			this.name = name;
			this.id = id;
			this.age = age;
		}
	}

	public static class IdAscendingComparator implements Comparator<Student> {

		@Override
		public int compare(Student o1, Student o2) {
			// 返回负数：o1在前
			// 返回正数：o2在前
			// 返回0：o1==o2

			// 从小到大，升序
			if(o1.id < o2.id){ // o1小，在前
				return -1;
			}else if (o1.id > o2.id){ // o2小，在前
				return 1;
			}else { // o1 == o2
				return 0;
			}

			// 升序
//			return o1.id - o2.id;
			// 降序
//			return o2.id - o1.id;
		}

	}

	public static class IdDescendingComparator implements Comparator<Student> {

		@Override
		public int compare(Student o1, Student o2) {
			return o2.id - o1.id;
		}

	}

	public static class AgeAscendingComparator implements Comparator<Student> {

		@Override
		public int compare(Student o1, Student o2) {
			return o1.age - o2.age;
		}

	}

	public static class AgeDescendingComparator implements Comparator<Student> {

		@Override
		public int compare(Student o1, Student o2) {
			return o2.age - o1.age;
		}

	}

	public static void printStudents(Student[] students) {
		for (Student student : students) {
			System.out.println("Name : " + student.name + ", Id : " + student.id + ", Age : " + student.age);
		}

		System.out.println("===========================");
	}

	public static void main(String[] args) {
		Student student1 = new Student("A", 1, 23);
		Student student2 = new Student("B", 2, 21);
		Student student3 = new Student("C", 3, 22);

		Student[] students = new Student[] { student3, student2, student1 };
		printStudents(students);

		// id升序
		Arrays.sort(students, new IdAscendingComparator());
		printStudents(students);
		// id降序
		Arrays.sort(students, new IdDescendingComparator());
		printStudents(students);
		// age升序
		Arrays.sort(students, new AgeAscendingComparator());
		printStudents(students);
		// age降序
		Arrays.sort(students, new AgeDescendingComparator());
		printStudents(students);

	}

}
