package NowCoder.advanced_class.code.class_1;

/*
	KMP应用1：
		已知一字符串，要求在后面添加字符，使得长度最短，且包含两次原始字符串（开头位置不同）
 */
public class Code_02_KMP_ShortestHaveTwice {

	public static String answer(String str) {
		if (str == null || str.length() == 0) {
			return "";
		}
		char[] chas = str.toCharArray();
		if (chas.length == 1) { // 长度为1，直接复制一个
			return str + str;
		}
		if (chas.length == 2) { // 长度为2，如果整个字符串有最长前后缀，则重复其中一个，否则重复整个
			return chas[0] == chas[1] ? (str + String.valueOf(chas[0])) : (str + str);
		}
		// 长度大于等于3的
		int endNext = endNextLength(chas); // 得到整个字符串的匹配程度
		return str + str.substring(endNext); // str加上剩余部分（从最大前缀后一个开始）
	}

	// 求整个字符串的匹配程度，然后将最大前缀与最大后缀对齐，补上剩余部分
	public static int endNextLength(char[] chas) {
		// TODO 多了一个长度（整个字符串的匹配程度）
		int[] next = new int[chas.length + 1]; // 多了一个长度（整个字符串的匹配程度）
		next[0] = -1;
		next[1] = 0;
		int pos = 2;
		int cn = 0;
		while (pos < next.length) {
			if (chas[pos - 1] == chas[cn]) {
				next[pos++] = ++cn;
			} else if (cn > 0) {
				cn = next[cn];
			} else {
				next[pos++] = 0;
			}
		}
		return next[next.length - 1]; // 返回整个字符串的匹配程度
	}

	public static void main(String[] args) {
		String test1 = "a";
		System.out.println(answer(test1));

		String test2 = "aa";
		System.out.println(answer(test2));

		String test3 = "ab";
		System.out.println(answer(test3));

		String test4 = "abcdabcd";
		System.out.println(answer(test4));

		String test5 = "abracadabra";
		System.out.println(answer(test5));

	}

}
