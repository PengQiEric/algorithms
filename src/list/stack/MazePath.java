package list.stack;

/**
 * ���������һ���Թ���Ѱ�Ҽ�·�� �������ѵ��ڻ���ת��Ĳ��Ժ�����������Ǽ򵥵�·����������ж����grid�Ѿ���·���ϣ�
 * 
 * @author qipeng
 * 
 */
public class MazePath {

	private MazeGrid[][] maze = new MazeGrid[MAX_LENGTH][MAX_LENGTH];
	private MyStack stack = new MyStack();
	private static final int MAX_LENGTH = 10;

	/**
	 * ��ʼ������࣬��һ����ά����������һ���Թ������ж�ά�����Ԫ�ص�ֵΪtrue��ʾ�ϰ�
	 */
	public void init() {
		for (int i = 0; i < MAX_LENGTH; i++) {
			for (int j = 0; j < MAX_LENGTH; j++) {
				maze[i][j] = new MazeGrid();
			}
		}
		// ��ΧһȦ���ϰ�
		for (int i = 0; i < maze[0].length; i++) {
			maze[0][i].isBarrier = true;
			maze[MAX_LENGTH - 1][i].isBarrier = true;
		}
		for (int j = 1; j < maze.length - 1; j++) {
			maze[j][0].isBarrier = true;
			maze[j][MAX_LENGTH - 1].isBarrier = true;
		}
		// �ڲ��ϰ���λ��
		maze[1][3].isBarrier = true;
		maze[2][3].isBarrier = true;
		maze[1][7].isBarrier = true;
		maze[2][7].isBarrier = true;
		maze[3][5].isBarrier = true;
		maze[3][6].isBarrier = true;
		maze[4][2].isBarrier = true;
		maze[4][3].isBarrier = true;
		maze[4][4].isBarrier = true;
		maze[5][4].isBarrier = true;
		maze[6][2].isBarrier = true;
		maze[6][6].isBarrier = true;
		maze[7][2].isBarrier = true;
		maze[7][3].isBarrier = true;
		maze[7][4].isBarrier = true;
		maze[7][6].isBarrier = true;
		maze[7][7].isBarrier = true;
		maze[8][1].isBarrier = true;
	}

	/**
	 * Ѱ�Ҽ�·��
	 * 
	 * @param start
	 * @param end
	 * @return
	 */
	public MyStack findPath(Pos start, Pos end) {
		int order = 1;
		PathInfo current = new PathInfo(order, start, 0);
		do {
			// ������grid�����ϰ������Ҳ��ڼ�·����
			if (pass(current)) {
				// �����gridѹ��·��ջ��
				stack.push(current);
				// �����grid���Ϊ��·����
				maze[current.pos.xPos][current.pos.yPos].isInPath = true;
				// ���current�ڵ����յ㣬��������·��ջ
				if (current.pos.equals(end))
					return stack;
				// current��ֵΪ��һ���ڵ�
				current = nextPos(current);
			} else {
				// ���currentԪ�ز���ͨ������ô��currentָ��ջ��Ԫ��
				current = (PathInfo) stack.getTop();
				// ���Ŀǰ�ķ��������ת��(0,1,2)����ô��ת����ڵ㷽��ȥѰ����һ���ڵ�
				if (current.di < 3) {
					current.di += 1;
					current = nextPos(current);
				}// ���di��ֵ���ڵ���3,˵������ڵ����һ���ڵ㷽���Ѿ��޷�����ת��
				else {
					// ������ڵ��Ƴ���
					stack.pop();
					// currentָ����ǰһ���ڵ㣬Ҳ�������ڵ�ջ��
					current = (PathInfo) stack.getTop();
					// currentԪ�ص���һ���ڵ�ķ���ı�
					current.di += 1;
					// �����ʱ��ڵ��di�����4,��ô����ڵ��޷�ת���ˣ��Ƴ����Ԫ��
					// ѭ���Ƴ�ֱ��ת��ķ��򲻵���4�Ժ�
					while (current.di == 4) {
						stack.pop();
						current = (PathInfo) stack.getTop();
						current.di += 1;
					}
					// ��ȡ��һ���ڵ㣬��һ��ѭ���ж�����ڵ�
					current = nextPos(current);
				}
			}
		} while (!stack.isEmpty());
		return stack;
	}

	/**
	 * �ж�����ڵ��ǲ����ϰ������Ƿ��Ѿ���·������
	 * 
	 * @param node
	 * @return
	 */
	private boolean pass(PathInfo node) {
		if (maze[node.pos.xPos][node.pos.yPos].isBarrier
				|| maze[node.pos.xPos][node.pos.yPos].isInPath) {
			return false;
		} else {
			return true;
		}
	}

	/**
	 * �ƶ�����һ������
	 * 
	 * @param node
	 * @return
	 */
	private PathInfo nextPos(PathInfo node) {
		if (node.di == 0) {
			return new PathInfo(node.ord + 1, node.pos.xPos - 1, node.pos.yPos,
					0);
		} else if (node.di == 1) {
			return new PathInfo(node.ord + 1, node.pos.xPos, node.pos.yPos + 1,
					0);
		} else if (node.di == 2) {
			return new PathInfo(node.ord + 1, node.pos.xPos + 1, node.pos.yPos,
					0);
		} else {
			return new PathInfo(node.ord + 1, node.pos.xPos, node.pos.yPos - 1,
					0);
		}
	}

	/*
	 * ÿһ��·����Ԫ�ص�������Ϣ��������ţ����꣬����
	 */
	private class PathInfo {
		public int ord; // ͨ������·���ϵġ���š�
		public Pos pos; // ͨ�������Թ��е�����
		public int di; // �Ӵ�ͨ��������һͨ���ġ�����

		public PathInfo(int ord, Pos pos, int di) {
			this.ord = ord;
			this.pos = pos;
			this.di = di;
		}

		public PathInfo(int ord, int xPos, int yPos, int di) {
			this.ord = ord;
			this.pos = new Pos(xPos, yPos);
			this.di = di;
		}
	}

	/**
	 * �������ֶηǳ���Ҫ���ڶ����ֶμ�����˸�grid�Ƿ���·����
	 */
	private class MazeGrid {
		public boolean isBarrier = false; // ���grid�Ƿ����ϰ�
		public boolean isInPath = false; // ���grid�Ƿ��Ѿ��ڸ�·����
	}

	/**
	 * ÿһ��grid������
	 */
	private class Pos {
		public int xPos; // �Թ��еĸ�λ�õ�ˮƽ����
		public int yPos; // �Թ��еĸ�λ�õĴ�ֱ����

		public Pos(int xPos, int yPos) {
			this.xPos = xPos;
			this.yPos = yPos;
		}

		// �Զ��������equals����
		public boolean equals(Pos p) {
			if ((xPos == p.xPos) && (yPos == p.yPos)) {
				return true;
			} else {
				return false;
			}
		}

		public int hashCode() {
			return ((Integer) xPos).hashCode() + ((Integer) yPos).hashCode();
		}
	}

	public static void main(String[] args) {
		MazePath path = new MazePath();
		path.init();
		Pos start = path.new Pos(1, 1);
		Pos end = path.new Pos(8, 8);
		// System.out.println(path.maze[1][1].isInPath);
		MyStack stack = path.findPath(start, end);
		System.out.println(stack);
	}
}
