package NowCoder.advanced_class.class_1;

/*
	KMP算法：判断str1中是否含有str2子串
		子串：必须是连续的
		子序列：不连续
 */
public class Code_01_KMP {

	// 法一：String.indexOf
	// 直接返回子串的开始位置
	// 时间复杂度：O(N)

	// 法二：暴力法
	// 双指针，p1指向str1，p2指向str2，p2始终从头开始，与p1逐个比较
	// 若不同，则p1跳回开头往后移动一个，再与p2从头开始比较
	// 时间复杂度：O(N-M) (N≥M)

	// 法三：KMP算法 利用匹配程度对暴力法加速
	// 匹配程度：对于一个字符，它前面的字符串的前缀和后缀相同时最长的长度
	// 1. 求str2的匹配程度数组next
	// 2. str1和str2逐一比较字符，当两者不同时，str2的指针根据next数组往前跳，直到跳到next数组开头（元素为-1）
	// 然后str1的指针往后移，重新比较
	public static int getIndexOf(String s, String m) {
		if (s == null || m == null || m.length() < 1 || s.length() < m.length()) {
			return -1;
		}
		char[] str1 = s.toCharArray();
		char[] str2 = m.toCharArray();
		int s1 = 0;
		int s2 = 0;
		// 获得str2每个位置上的匹配程度
		int[] next = getNextArray(str2);

		while (s1 < str1.length && s2 < str2.length) {
			if (str1[s1] == str2[s2]) { // 对应位置元素相等，指针同时后移
				s1++;
				s2++;
			} else { // 元素不等，s2往前跳，跳完之后，再进行比较，如果不等继续往前跳，直到跳到开头
				if (next[s2] == -1) { // s2指针是开头，说明前面没有字符串，不能跳，只能让s1后移一个，重新比较
					s1++;
				} else { // str2的指针可以往前跳，跳到最长前缀的匹配程度位置处
					s2 = next[s2]; // s2跳到当前元素对应的匹配程度处（索引）
					// 相当于将str2的最长前缀对应到str1的对应位置上，然后从最长前缀后一个开始和s1继续比较
				}
			}
		}
		// 如果str2的指针走完str2的长度，说明找到了，此时s1包含了完整的s2，s1-s2就是子串开始的位置
		return s2 == str2.length ? s1 - s2 : -1;
	}

	// next匹配程度数组求解,类似于dp表的填写
	public static int[] getNextArray(char[] str2) {
		if (str2.length == 1) {
			return new int[] { -1 };
		}
		int[] next = new int[str2.length];
		next[0] = -1; // 开头
		next[1] = 0; // 第一个位置
		int pos = 2; // 从第二个位置开始
		int cn = 0; // 跳到的位置，next的指针
		while (pos < next.length) { //
			// TODO 注意：在比较当前位置的前一个字符的前缀后一个字符时，其对应的位置就是匹配程度。
			// TODO 因为next表示匹配程度（前缀长度），所以对应到str中就代表了前缀后一个（因为索引从0开始算的）
			if (str2[pos - 1] == str2[cn]) {
				// 如果 当前位置的前一个字符的前缀后一个字符（也就是匹配程度处） 与 当前位置的前一个字符 相同
				// 当前位置的next = 当前位置的前一个字符的next + 1
				// 对于2索引，1索引的next = 0，所以看 0处的字符 和 1索引处的字符是否相同，相同则 0 + 1
				// 求位置2：看位置1的前缀后一个 == 位置1
				next[pos++] = ++cn;
			} else if (cn > 0) {
				// 如果不同，往前跳，再判断
				cn = next[cn]; // 跳到cn对应的next数组（匹配程度）的值的位置
			} else {
				// cn跳到最左（开头），说明没有相同的前后缀
				next[pos++] = 0;
			}
		}
		return next; // 返回整个数组
	}

	public static void main(String[] args) {
		String str = "abcabcababaccc";
		String match = "ababa";
		System.out.println(str.indexOf(match));
		System.out.println(getIndexOf(str, match));

	}

}
