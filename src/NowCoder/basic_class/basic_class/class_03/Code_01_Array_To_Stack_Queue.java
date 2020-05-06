package NowCoder.basic_class.basic_class.class_03;

public class Code_01_Array_To_Stack_Queue {

	public static class ArrayStack {
		private Integer[] arr;
		private Integer index; // index表示新加的数应该放在的位置索引

		public ArrayStack(int initSize) {
			if (initSize < 0) {
				throw new IllegalArgumentException("The init size is less than 0");
			}
			// 初始化
			arr = new Integer[initSize];
			index = 0;
		}

		public Integer peek() {
			if (index == 0) {
				return null;
			}
			// 直接返回栈顶，但是index不变化
			return arr[index - 1];
		}

		public void push(int obj) {
			// TODO index表示新加的数，若index到达尾部，则没有空间再新加数了
			if (index == arr.length) {
				throw new ArrayIndexOutOfBoundsException("The queue is full");
			}
			arr[index++] = obj;
		}

		public Integer pop() {
			if (index == 0) {
				throw new ArrayIndexOutOfBoundsException("The queue is empty");
			}
			// 首先index要-1，因为index表示的是新加的数的位置，是空的，其下面的一个才是栈顶
			return arr[--index];
		}
	}

	public static class ArrayQueue {
		// size：已经存了几个数
		// start：取出的数所在的位置
		// end：新加的数所在的位置
		// 约束条件
		// 1. start和size：size!=0  ==>  取出start位置的数
		// 2. end和size： size<len  ==>  新加的数放在end位置
		// 3. start和end到达尾部时回到开头，直到填满或取完
		private Integer[] arr;
		private Integer size;
		private Integer start;
		private Integer end;

		public ArrayQueue(int initSize) {
			if (initSize < 0) {
				throw new IllegalArgumentException("The init size is less than 0");
			}
			arr = new Integer[initSize];
			size = 0;
			start = 0;
			end = 0;
		}

		public Integer peek() {
			if (size == 0) {
				return null;
			}
			return arr[start];
		}

		public void push(int obj) {
			// TODO size表示队列中已有元素个数，数组长度就是总的元素个数
			if (size == arr.length) {
				throw new ArrayIndexOutOfBoundsException("The queue is full");
			}
			// 新加数：首先size要+1，然后在end出新加数，最后end+1或者到开头
			size++;
			arr[end] = obj;
			end = end == arr.length - 1 ? 0 : end + 1;
		}

		public Integer poll() {
			if (size == 0) {
				throw new ArrayIndexOutOfBoundsException("The queue is empty");
			}
			// 取出数：首先size要-1，然后在start出取出数，最后start+1或者到开头
			size--;
			int tmp = start;
			start = start == arr.length - 1 ? 0 : start + 1;
			return arr[tmp];
		}
	}

	public static void main(String[] args) {

	}

}
