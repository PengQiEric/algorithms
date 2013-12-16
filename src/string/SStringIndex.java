package string;

/**
 * �����������洢һ���ַ�����������ģʽƥ��
 * @author qipeng
 * 
 */
public class SStringIndex {

	/**
	 * ��Sͷ����ʼѰ��ƥ��T���ַ���
	 * 
	 * @param s
	 * @param t
	 * @return
	 */
	public int index(String s, String t) {
		return index(s, t, 0);
	}

	/**
	 * ʱ�临�Ӷ�O(s*t)
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
				// i-j�Ƿ��ص��ַ���ƥ��Ŀ�ʼ������1��ʾ��ǰ�ƶ�һ���ַ�
				i = i - j + 1;
				j = 0;
			}
		}
		if (j == target.length)
			return i - target.length;
		return -1;
	}

	/**
	 * �������һ��ƥ���ֵ
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
		//i == source.lengthʱ
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
