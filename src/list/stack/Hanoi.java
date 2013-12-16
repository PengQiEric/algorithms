package list.stack;

/**
 * Hanoi��Ϸ�����õݹ���������⣬�Ӷ���֤��ʵ�ڵݹ�Ĺ�������ʵ��ά����һ�����ݹ鹤��ջ��
 * #ÿһ��ĵݹ�����Ҫ����Ϣ����һ����������¼�������а������еĲ������ֲ������Լ���һ��ķ��ص�ַ �����Ǳ�����Ļ�ȡ�ϴεݹ�Ľ����
 * 
 * @author qipeng
 * 
 */
public class Hanoi {
	// ��¼�ƶ��Ĳ���
	private int moveNum = 0;

	/**
	 * 
	 * @param n hanoi�Ĳ���
	 * @param x Ŀǰ��ľ׮λ��
	 * @param y	��Ҫ������ľ׮
	 * @param z Ŀ��ľ׮λ��
	 */
	public void hanoi(int n, char x, char y, char z) {
		if (n == 1) {
			move(n, x, z);
		} else {
			hanoi(n - 1, x, z, y); 	//����Z��n-1��ľ���xת�Ƶ�y
			move(n, x, z);	//��n��xת�Ƶ�z
			hanoi(n - 1, y, x, z); //��ʣ���n��1��ľ�����x��yת�Ƶ�z
		}
	}

	/**
	 * ����n���ľ��xľ��ת�Ƶ�zľ��
	 * 
	 * @param n
	 * @param x
	 * @param y
	 * @param z
	 */
	public void move(int n, char x, char z) {
		//����ƶ��Ĳ���
		System.out.println(moveNum++ + " move disk " + n + " from " + x
				+ " to " + z);
	}

	public static void main(String[] args) {
		Hanoi h = new Hanoi();
		h.hanoi(7, 'A', 'B', 'C');
	}
}
