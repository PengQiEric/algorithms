package list.stack;

public class Application {
	/**
	 * ��һ��ʮ������ת��Ϊ�������Ƶ�����
	 * 
	 * @param number
	 *            ��Ҫת����ʮ��������
	 * @param scale
	 *            ��Ҫת���ɵĽ���
	 * @return ����ת��Ϊ�������ƺ�����(String��ʽ)
	 */
	public String conversion(int number, int scale) {
		MyStack stack = new MyStack();
		while (number != 0) {
			stack.push(number % scale);
			// ����������ֱ任��java.lang.OutOfMemoryError: Java heap space
			number = number / scale;
		}
		String result = "";
		while (!stack.isEmpty()) {
			result += ((Integer) stack.pop()).toString();
		}
		return result;
	}

	public boolean matchBracket(String expression) {
		MyStack stack = new MyStack();
		char[] expr = expression.toCharArray();
		for (int i = 0; i < expr.length; i++) {
			if (expr[i] == '(') {
				if (expr[i + 1] == ')') {
					// ƥ��ɹ���������һ���ַ�
					i++;
				} else {
					stack.push(expr[i]);
				}
			} else if (expr[i] == '[') {
				if (expr[i + 1] == ']') {
					// ƥ��ɹ���������һ���ַ�
					i++;
				} else {
					stack.push(expr[i]);
				}
			} else if (expr[i] == ')') {
				if (!stack.isEmpty() && (Character) stack.pop() == '(') {
				} else {
					return false;
				}
			} else {
				if (!stack.isEmpty() && (Character) stack.pop() == '[') {
				} else {
					return false;
				}
			}
		}
		if (!stack.isEmpty()) {
			return false;
		}
		return true;
	}

	public static void main(String[] args) {
		Application app = new Application();
		// System.out.println(app.conversion(1234, 8));
		System.out.println(app.matchBracket("[([][])]"));
	}
}
