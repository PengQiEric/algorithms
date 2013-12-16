package string;

/**
 * KMP�㷨ʵ�ִ���ƥ��
 * ����SStringIndex.java�еķ��������Ҫ���ص�ʱ�����е�iָ�벻��Ҫ����
 * ʱ�临�Ӷ�O(m+n),m��n�ֱ�Ϊ������ƥ�䴮�ĳ���
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
	 * ��ʼ��KMP�ġ����һ�������next�� ���õݹ������������ �������ʵ���и�ȱ�ݣ��ڵڶ���if��ʱ��ֻ�����һ�λ��ݣ�
	 * ����ʵ�ֶ�λ��ݣ������ͺ�����һЩ��С��ƥ�䴮�Ŀ��ܣ� ��������ʵ��<code>initialNext1</code>
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
			if (target[i - 1] == target[k])// ���P(k)=P(j)����ȵ��ִ�ΪP(1)...P(k)��P(j-k+1)...P(j)
			{
				next[i] = k + 1;
			} else if (next[k] >= 0 && target[i - 1] == target[next[k]])
			// �����һ���жϲ���������ô�൱������ģʽ����k,j������ȣ���ô����KMP����Ҫ���һ���
			{
				next[i] = next[k] + 1;
			} else {
				next[i] = 0;
			}
			// System.out.println(target[i] + "," + next[i]);
		}
	}

	/**
	 * �ӷ���KMPnext����Ķ������õݹ�����ʼ��������
	 * 
	 * @param t
	 */
	public void initialNext1(String t) {
		char[] target = t.toCharArray();
		next = new int[target.length];
		next[0] = -1;
		// ����next�����е�Ԫ��
		int i = 0;
		// next������������ƥ��ʱ�ġ����һ���������
		int k = -1;
		while (i < next.length - 1) {
			if (k == -1 || target[i] == target[k]) {
				i++;
				k++;
				next[i] = k;
			} else {
				// ����������һ���������ô���ݣ�����û�и�С�Ĵ��������ƥ��
				k = next[k];
			}
		}
		for (int element : next) {
			System.out.print(element + " ");
		}
	}

	/**
	 * <code>initialNext1</code>�����ĸĽ��汾���ڱȽϵ�ʱ������������ҵġ�����������
	 * 
	 * @param t
	 */
	public void initialNext2(String t) {
		char[] target = t.toCharArray();
		int i = 0;
		int k = -1;
		next[0] = -1;
		while (i < target.length - 1)// ѭ��������������iС�ڵ����ڶ���Ԫ�ص��±�
		{
			if (k == 1 || target[i] == target[k]) {
				i++;
				k++;
				if (target[i] != target[k])// �����ʱi��k���ȣ�Ҳ����target[k]���п����������ж�ӦԪ��ƥ��ʱ
				{
					next[i] = k;
				} else // ����ֱ�Ӻ�P(next[k])�Ƚ�
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
