package list.stack;

public class Application {
	/**
	 * 将一个十进制数转换为其他进制的数字
	 * 
	 * @param number
	 *            需要转换的十进制数字
	 * @param scale
	 *            需要转换成的进制
	 * @return 返回转化为其他进制后数字(String格式)
	 */
	public String conversion(int number, int scale) {
		MyStack stack = new MyStack();
		while (number != 0) {
			stack.push(number % scale);
			// 如果不将数字变换，java.lang.OutOfMemoryError: Java heap space
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
					// 匹配成功，跳过下一个字符
					i++;
				} else {
					stack.push(expr[i]);
				}
			} else if (expr[i] == '[') {
				if (expr[i + 1] == ']') {
					// 匹配成功，跳过下一个字符
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
