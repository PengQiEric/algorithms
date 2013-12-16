package list.stack;

/**
 * 这个类是在一个迷宫中寻找简单路径 这个类的难点在回退转向的策略和如果标记这个是简单的路径（即如何判断这个grid已经在路径上）
 * 
 * @author qipeng
 * 
 */
public class MazePath {

	private MazeGrid[][] maze = new MazeGrid[MAX_LENGTH][MAX_LENGTH];
	private MyStack stack = new MyStack();
	private static final int MAX_LENGTH = 10;

	/**
	 * 初始化这个类，用一个二维数组来创建一个迷宫，其中二维数组的元素的值为true表示障碍
	 */
	public void init() {
		for (int i = 0; i < MAX_LENGTH; i++) {
			for (int j = 0; j < MAX_LENGTH; j++) {
				maze[i][j] = new MazeGrid();
			}
		}
		// 外围一圈的障碍
		for (int i = 0; i < maze[0].length; i++) {
			maze[0][i].isBarrier = true;
			maze[MAX_LENGTH - 1][i].isBarrier = true;
		}
		for (int j = 1; j < maze.length - 1; j++) {
			maze[j][0].isBarrier = true;
			maze[j][MAX_LENGTH - 1].isBarrier = true;
		}
		// 内部障碍的位置
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
	 * 寻找简单路径
	 * 
	 * @param start
	 * @param end
	 * @return
	 */
	public MyStack findPath(Pos start, Pos end) {
		int order = 1;
		PathInfo current = new PathInfo(order, start, 0);
		do {
			// 如果这个grid不是障碍，而且不在简单路径上
			if (pass(current)) {
				// 将这个grid压入路径栈中
				stack.push(current);
				// 将这个grid标记为在路径上
				maze[current.pos.xPos][current.pos.yPos].isInPath = true;
				// 如果current节点是终点，返回整个路径栈
				if (current.pos.equals(end))
					return stack;
				// current赋值为下一个节点
				current = nextPos(current);
			} else {
				// 如果current元素不能通过，那么将current指向栈顶元素
				current = (PathInfo) stack.getTop();
				// 如果目前的方向可以再转动(0,1,2)，那么旋转这个节点方向去寻找下一个节点
				if (current.di < 3) {
					current.di += 1;
					current = nextPos(current);
				}// 如果di的值大于等于3,说明这个节点的下一个节点方向已经无法再旋转了
				else {
					// 将这个节点移出来
					stack.pop();
					// current指向其前一个节点，也就是现在的栈顶
					current = (PathInfo) stack.getTop();
					// current元素的下一个节点的方向改变
					current.di += 1;
					// 如果这时候节点的di变成了4,那么这个节点无法转动了，移出这个元素
					// 循环移出直到转变的方向不等于4以后
					while (current.di == 4) {
						stack.pop();
						current = (PathInfo) stack.getTop();
						current.di += 1;
					}
					// 获取下一个节点，下一次循环判断这个节点
					current = nextPos(current);
				}
			}
		} while (!stack.isEmpty());
		return stack;
	}

	/**
	 * 判断这个节点是不是障碍或者是否已经在路径上了
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
	 * 移动到下一个坐标
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
	 * 每一个路径上元素的所有信息，包括序号，坐标，方向
	 */
	private class PathInfo {
		public int ord; // 通道块在路径上的“序号”
		public Pos pos; // 通道块在迷宫中的坐标
		public int di; // 从此通道走向下一通道的“方向”

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
	 * 这两个字段非常重要，第二个字段即解决了该grid是否在路径中
	 */
	private class MazeGrid {
		public boolean isBarrier = false; // 这个grid是否是障碍
		public boolean isInPath = false; // 这个grid是否已经在该路径上
	}

	/**
	 * 每一个grid的坐标
	 */
	private class Pos {
		public int xPos; // 迷宫中的该位置的水平坐标
		public int yPos; // 迷宫中的该位置的垂直坐标

		public Pos(int xPos, int yPos) {
			this.xPos = xPos;
			this.yPos = yPos;
		}

		// 自定义这个类equals方法
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
