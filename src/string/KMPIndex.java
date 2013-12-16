package string;

/**
 * KMP算法实现串的匹配
 * 其与SStringIndex.java中的方法相比主要的特点时主串中的i指针不需要回溯
 * 时间复杂度O(m+n),m和n分别为主串和匹配串的长度
 * @author qipeng
 */
public class KMPIndex {
	private int[] next;

	public int index(String s, String t, int startPos) {
		char[] source = s.toCharArray();
		char[] target = t.toCharArray();
		int i = startPos;
		int j = 0;
		while (i < source.length && j < target.length) {
			if (j == -1 || source[i] == target[j]) {
				i++;
				j++;
			} else {
				j = next[j];
			}
		}
		if (j == target.length)
			return i - target.length;
		return -1;
	}

	/**
	 * 初始化KMP的“向右滑动”的next表 利用递归完成整个过程 但是这个实现有个缺陷，在第二个if的时候只能完成一次回溯，
	 * 不能实现多次回溯（这样就忽略了一些更小的匹配串的可能） 更完美的实现<code>initialNext1</code>
	 * 
	 * @param t
	 */
	public void initialNext(String t) {
		char[] target = t.toCharArray();
		next = new int[target.length];
		next[0] = -1;
		int k;
		for (int i = 1; i < target.length; i++) {
			k = next[i - 1];
			if (k < 0) {
				next[i] = 0;
				continue;
			}
			if (target[i - 1] == target[k])// 如果P(k)=P(j)则相等的字串为P(1)...P(k)和P(j-k+1)...P(j)
			{
				next[i] = k + 1;
			} else if (next[k] >= 0 && target[i - 1] == target[next[k]])
			// 如果第一个判断不成立，那么相当于两个模式串在k,j处不相等，那么根据KMP规则，要向右滑动
			{
				next[i] = next[k] + 1;
			} else {
				next[i] = 0;
			}
			// System.out.println(target[i] + "," + next[i]);
		}
	}

	/**
	 * 从分析KMPnext数组的定义来用递归来初始化该数组
	 * 
	 * @param t
	 */
	public void initialNext1(String t) {
		char[] target = t.toCharArray();
		next = new int[target.length];
		next[0] = -1;
		// 遍历next数组中的元素
		int i = 0;
		// next数组在碰到不匹配时的“向右滑动”距离
		int k = -1;
		while (i < next.length - 1) {
			if (k == -1 || target[i] == target[k]) {
				i++;
				k++;
				next[i] = k;
			} else {
				// 如果不满足第一种情况，那么回溯，看有没有更小的串可以完成匹配
				k = next[k];
			}
		}
		for (int element : next) {
			System.out.print(element + " ");
		}
	}

	/**
	 * <code>initialNext1</code>方法的改进版本，在比较的时候可以增大向右的“滑动”距离
	 * 
	 * @param t
	 */
	public void initialNext2(String t) {
		char[] target = t.toCharArray();
		int i = 0;
		int k = -1;
		next[0] = -1;
		while (i < target.length - 1)// 循环结束的条件是i小于倒数第二个元素的下标
		{
			if (k == 1 || target[i] == target[k]) {
				i++;
				k++;
				if (target[i] != target[k])// 如果此时i和k不等，也就是target[k]是有可能与主串中对应元素匹配时
				{
					next[i] = k;
				} else // 否则直接和P(next[k])比较
				{
					next[i] = next[k];
				}
			}
		}
	}

	public static void main(String[] args) {
		KMPIndex kmp = new KMPIndex();
		kmp.initialNext1("abaabcac");
		// System.out.println(kmp.index("abababcacabaabcac", "abaabcac", 0));
	}
}
