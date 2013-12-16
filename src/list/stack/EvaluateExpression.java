package list.stack;

/**
 * ����㷨��Ҫ������������ʽ������
 * ������չ���ţ�����ǿ���������ԣ�
 * ����������Ҫ˼��������ǰһ������������ȼ����ں�һ�����������ô���ǿ���ִ��ǰһ�������
 * �����ҽ����ѹ��ջ�У����ǰһ������������ȼ�С�ں�һ�����������ô���������ͬ������ջ��
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
	 * �жϲ����������ȼ���
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
		// ������ƥ���ʱ��÷���ʲô�أ�
		return null;
	}

	/**
	 * ִ�����Ӧ����������
	 * 
	 * @param a
	 * @param oper
	 * @param b
	 * @return ǿ��������������ת������ȷʵ���Ǻܷ���
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
	 * �ж�һ���ַ��ǲ���һ��������
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
		// ��Ų�������ջ
		MyStack opnd = new MyStack();
		// ��Ų�������ջ
		MyStack optr = new MyStack();
		int num = 0;
		char c = elements[num++];
		optr.push(c);
		//��ջ���ǣ������ʽ�ĵ�һ�������������һ��Ԫ���ǣ�ʱ����ʾ���������
		while (elements[num] != '#' || !((Character) optr.getTop()).equals('#')) {
			c = elements[num];
			if (!in(c)) {
				Integer i = Integer.valueOf(((Character) c).toString());
				opnd.push(i);
				num++;
			} else {
				switch (precede((Character) optr.getTop(), c)) {
				case '<': // ջ��Ԫ������Ȩ��
					optr.push(c);
					num++;
					break;
				case '=':
					optr.pop();
					num++;
					break;
				case '>':
					char operator = (Character) optr.pop();
					//��ʵ����һ������ĵڶ�����
					int i = (Integer) opnd.pop();
					//����һ������ĵ�һ�����������������ʱ����j-i������ȷ��˳��
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
