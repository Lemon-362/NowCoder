package NowCoder.advanced_class.basic_class_02;

public class Code_01_KMP {

	public static int getIndexOf(String s, String m) {
		if (s == null || m == null || m.length() < 1 || s.length() < m.length()) {
			return -1;
		}
		char[] ss = s.toCharArray();
		char[] ms = m.toCharArray();
		int si = 0;
		int mi = 0;
		int[] next = getNextArray(ms);
		while (si < ss.length && mi < ms.length) {
			if (ss[si] == ms[mi]) { // 对应位置相等
				si++;
				mi++;
			} else {
				if (next[mi] == -1) { // str2的指针已经跳到开头了，仍然和str1配不上，只能str1的指针往后移，重新与str2的开头比较
					si++;
				} else { // str2的指针可以往前跳，跳到最长前缀的匹配程度位置处
					mi = next[mi];
				}
			}
		}
		// 如果str2的指针走完str2的长度，说明找到了
		return mi == ms.length ? si - mi : -1;
	}

	public static int[] getNextArray(char[] ms) {
		if (ms.length == 1) {
			return new int[] { -1 };
		}
		int[] next = new int[ms.length];
		next[0] = -1;
		next[1] = 0;
		int pos = 2;
		int cn = 0; // 跳到的位置
		while (pos < next.length) {
			if (ms[pos - 1] == ms[cn]) { // 跳到的位置和当前的前一个字符相同，长度确定
				next[pos++] = ++cn; // cn处的next数组的值，因为cn是前一个字符的最长前缀的后一个，所以cn前的长度就是最长前缀的长度
			} else if (cn > 0) { // 说明跳到的位置和当前的前一个字符不同，cn还可以跳
				cn = next[cn]; // 跳到cn对应的next数组的值的位置
			} else { // cn跳到最左（开头）
				next[pos++] = 0;
			}
		}
		return next;
	}

	public static void main(String[] args) {
		String str = "abcabcababaccc";
		String match = "ababa";
		System.out.println(getIndexOf(str, match));

	}

}
