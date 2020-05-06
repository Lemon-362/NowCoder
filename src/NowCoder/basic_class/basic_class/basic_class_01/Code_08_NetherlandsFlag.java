package NowCoder.basic_class.basic_class.basic_class_01;

public class Code_08_NetherlandsFlag {

	/**
	 *
	 * @param arr 数组arr
	 * @param l arr最左边
	 * @param r arr最右边
	 * @param p 用于比较的数num
	 * @return
	 */
	public static int[] partition(int[] arr, int l, int r, int p) {
		int less = l - 1; // less区域初始化在l的左边
		int more = r + 1; // more区域初始化在r的右边
		while (l < more) { // l相当于一个指针
			if (arr[l] < p) { // cur < num
				swap(arr, ++less, l++); // 将less区域的后一个与cur交换，相当于less指针先后移再交换
				// 交换完cur指针后移
			} else if (arr[l] > p) { // cur > num
				swap(arr, --more, l); // 将more区域的前一个与cur交换，相当于more指针先前移再交换
				// 交换完cur指针不后移，要继续判断交换过来的数与num的关系，直接进入下一个循环
			} else { // cur == num
				l++; // cur直接后移
			}
		}
		// 最后返回等于num的部分 [less+1, more-1]
		return new int[] { less + 1, more - 1 };
	}

	// for test
	public static void swap(int[] arr, int i, int j) {
		int tmp = arr[i];
		arr[i] = arr[j];
		arr[j] = tmp;
	}

	// for test
	public static int[] generateArray() {
		int[] arr = new int[10];
		for (int i = 0; i < arr.length; i++) {
			arr[i] = (int) (Math.random() * 5);
		}
		return arr;
	}

	// for test
	public static void printArray(int[] arr) {
		if (arr == null) {
			return;
		}
		for (int i = 0; i < arr.length; i++) {
			System.out.print(arr[i] + " ");
		}
		System.out.println();
	}

	public static void main(String[] args) {
		int[] test = generateArray();

		printArray(test);
		int[] res = partition(test, 0, test.length - 1, 2);
		printArray(test);
		System.out.println(res[0]);
		System.out.println(res[1]);

	}
}
