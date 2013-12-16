package string;

/**
 * 利用数组来存储一个字符串，并且做模式匹配
 * @author qipeng
 * 
 */
public class SStringIndex {

	/**
	 * 从S头部开始寻找匹配T的字符串
	 * 
	 * @param s
	 * @param t
	 * @return
	 */
	public int index(String s, String t) {
		return index(s, t, 0);
	}

	/**
	 * 时间复杂度O(s*t)
	 * @param s
	 * @param t
	 * @param startPos
	 * @return
	 */
	public int index(String s, String t, int startPos) {
		char[] source = s.toCharArray();
		char[] target = t.toCharArray();
		int i = startPos;
		int j = 0;
		while (i < source.length && j < target.length) {
			if (target[j++] == source[i++])
				;
			else {
				// i-j是返回到字符串匹配的开始处，＋1表示向前移动一个字符
				i = i - j + 1;
				j = 0;
			}
		}
		if (j == target.length)
			return i - target.length;
		return -1;
	}

	/**
	 * 返回最后一个匹配的值
	 * @param s
	 * @param t
	 * @param startPos
	 * @return
	 */
	public int lastIndexOf(String s, String t, int startPos) {
		char[] source = s.toCharArray();
		char[] target = t.toCharArray();
		int i = startPos;
		int indexPos = -1;
		int j = 0;
		while (i < source.length) {
			if (j == target.length) {
				indexPos = i - target.length;
				j = 0;
			} else if (source[i] == target[j] && j < target.length) {
				i++;
				j++;
			} else {
				i = i - j + 1;
				j = 0;
			}
		}
		//i == source.length时
		if(j==target.length){
			indexPos = i-target.length;
		}
		return indexPos;
	}

	public static void main(String[] args) {
		SStringIndex s = new SStringIndex();
		System.out.println(s.lastIndexOf("acbaacbaacbaa", "cbaa", 0));
	}
}
