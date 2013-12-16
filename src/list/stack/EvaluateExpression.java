package list.stack;

/**
 * 这个算法主要是用来解决表达式的运算
 * 可以扩展括号，来增强运算的灵活性＃
 * 这个运算的主要思想就是如果前一个运算符的优先级大于后一个运算符，那么这是可以执行前一个运算符
 * ，并且将结果压入栈中；如果前一个运算符的优先级小于后一个运算符，那么将这个运算同样加入栈中
 * @author qipeng
 *
 */
public class EvaluateExpression {
	/*
	 * private final static String operators = "+-";
	 * 
	 * public void createRules() { HashMap map = new HashMap(); }
	 */

	/**
	 * 判断操作符的优先级别
	 * 
	 * @param oper1
	 * @param oper2
	 * @return
	 */
	public Character precede(char oper1, char oper2) {
		if (oper1 == '+' || oper1 == '-') {
			if (oper2 == '+' || oper2 == '-' || oper2 == '#')
				return '>';
			if (oper2 == '*' || oper2 == '/')
				return '<';
		}
		if (oper1 == '*' || oper1 == '/') {
			// can be omitted
			if (oper2 == '+' || oper2 == '-' || oper2 == '*' || oper2 == '/'
					|| oper2 == '#')
				return '>';
		}
		if (oper1 == '#') {
			if (oper2 == '#')
				return '=';
			else
				return '<';
		}
		// 当都不匹配的时候该返回什么呢？
		return null;
	}

	/**
	 * 执行相对应的算数运算
	 * 
	 * @param a
	 * @param oper
	 * @param b
	 * @return 强数据类型在类型转换方面确实不是很方便
	 */
	public Integer operate(int a, char oper, int b) {
		if (oper == '+')
			return a + b;
		if (oper == '-')
			return b - a;
		if (oper == '*')
			return a * b;
		if (oper == '/')
			return b / a;
		return null;
	}

	/**
	 * 判断一个字符是不是一个操作符
	 * 
	 * @param c
	 * @return
	 */
	public boolean in(char c) {
		if (c == '+' || c == '-' || c == '*' || c == '/' || c == '#')
			return true;
		else
			return false;
	}

	public Integer evaluateExpression(String expression) {
		char[] elements = expression.toCharArray();
		// 存放操作数的栈
		MyStack opnd = new MyStack();
		// 存放操作符的栈
		MyStack optr = new MyStack();
		int num = 0;
		char c = elements[num++];
		optr.push(c);
		//当栈顶是＃（表达式的第一个＃）并且最后一个元素是＃时，表示运算结束了
		while (elements[num] != '#' || !((Character) optr.getTop()).equals('#')) {
			c = elements[num];
			if (!in(c)) {
				Integer i = Integer.valueOf(((Character) c).toString());
				opnd.push(i);
				num++;
			} else {
				switch (precede((Character) optr.getTop(), c)) {
				case '<': // 栈顶元素优先权低
					optr.push(c);
					num++;
					break;
				case '=':
					optr.pop();
					num++;
					break;
				case '>':
					char operator = (Character) optr.pop();
					//其实这是一个运算的第二个数
					int i = (Integer) opnd.pop();
					//这是一个运算的第一个数，因而做减法的时候是j-i才是正确的顺序
					int j = (Integer) opnd.pop();
					opnd.push(operate(i, operator, j));
				}
			}
		}
		return (Integer) opnd.getTop();
	}

	public static void main(String[] args) {
		EvaluateExpression ee = new EvaluateExpression();
		System.out.println(ee.evaluateExpression("#1-2/3+4#").toString());
	}
}
