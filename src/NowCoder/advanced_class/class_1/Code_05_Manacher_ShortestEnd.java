package NowCoder.advanced_class.class_1;

/*
	Manacher应用：
		只在字符串后添加字符，如何添加使得整个字符串形成回文，并且添加的个数最少？
		返回需要添加的字符。
 */
public class Code_05_Manacher_ShortestEnd {

	public static char[] manacherString(String str) {
		char[] charArr = str.toCharArray();
		char[] res = new char[str.length() * 2 + 1];
		int index = 0;
		for (int i = 0; i != res.length; i++) {
			res[i] = (i & 1) == 0 ? '#' : charArr[index++];
		}
		return res;
	}

	public static String shortestEnd(String str) {
		if (str == null || str.length() == 0) {
			return null;
		}
		char[] charArr = manacherString(str);
		int[] pArr = new int[charArr.length];
		int index = -1;
		int pR = -1;
		int maxContainsEnd = -1;
		for (int i = 0; i != charArr.length; i++) {
			pArr[i] = pR > i ? Math.min(pArr[2 * index - i], pR - i) : 1;
			while (i + pArr[i] < charArr.length && i - pArr[i] > -1) {
				if (charArr[i + pArr[i]] == charArr[i - pArr[i]])
					pArr[i]++;
				else {
					break;
				}
			}
			// 更新R和C
			if (i + pArr[i] > pR) {
				pR = i + pArr[i];
				index = i;
			}
			// 根据题目要求，回文右边界到达数组最后直接break，记录此时的回文半径
			// 当回文右边界到达数组末尾时，一定是从某一位置开始到字符串末尾都是回文，此时添加的个数是最少的。
			if (pR == charArr.length) { // TODO 因为回文右边界是指回文串末尾的后一个位置，所以当R到达字符串末尾后一个位置时停止
				maxContainsEnd = pArr[i];
				break;
			}
		}
		// 在后面添加回文之前的逆序
		char[] res = new char[str.length() - maxContainsEnd + 1];
		for (int i = 0; i < res.length; i++) {
			res[res.length - 1 - i] = charArr[i * 2 + 1]; // 因为charArr是包含分隔符的，只在奇数位上是原来的字符串
		}
		return String.valueOf(res);
	}

	public static void main(String[] args) {
		String str2 = "abc12321";
		System.out.println(shortestEnd(str2));

	}

}
