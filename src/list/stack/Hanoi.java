package list.stack;

/**
 * Hanoi游戏，利用递归解决这个问题，从而验证其实在递归的过程中其实是维护了一个“递归工作栈”
 * #每一层的递归所需要的信息构成一个“工作纪录”，其中包括所有的参数，局部变量以及上一层的返回地址 （就是本层从哪获取上次递归的结果）
 * 
 * @author qipeng
 * 
 */
public class Hanoi {
	// 纪录移动的步数
	private int moveNum = 0;

	/**
	 * 
	 * @param n hanoi的层数
	 * @param x 目前的木桩位置
	 * @param y	需要借助的木桩
	 * @param z 目标木桩位置
	 */
	public void hanoi(int n, char x, char y, char z) {
		if (n == 1) {
			move(n, x, z);
		} else {
			hanoi(n - 1, x, z, y); 	//借助Z将n-1层木块从x转移到y
			move(n, x, z);	//将n从x转移到z
			hanoi(n - 1, y, x, z); //将剩余的n－1层木块借助x从y转移到z
		}
	}

	/**
	 * 将第n块积木从x木堆转移到z木堆
	 * 
	 * @param n
	 * @param x
	 * @param y
	 * @param z
	 */
	public void move(int n, char x, char z) {
		//输出移动的步伐
		System.out.println(moveNum++ + " move disk " + n + " from " + x
				+ " to " + z);
	}

	public static void main(String[] args) {
		Hanoi h = new Hanoi();
		h.hanoi(7, 'A', 'B', 'C');
	}
}
