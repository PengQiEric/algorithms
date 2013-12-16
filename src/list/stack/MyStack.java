package list.stack;

/**
 * 其实这个类用数组来创建会更好，因为其是单方向的添加和删除
 * 
 * @author qipeng
 * 
 */
public class MyStack {
	private int size = 0;
	private Node first;

	
	public Object getTop(){
		return first.element;
	}
	/**
	 * Add an element at the first of the node
	 * 
	 * @param o
	 */
	public void push(Object o) {
		if (first == null) {
			first = new Node(o);
		} else {
			Node current = new Node(o);
			current.next = first;
			first = current;

		}
		this.size++;
	}

	/**
	 * Return false if the stack has no element
	 * 
	 * @return
	 */
	public boolean isEmpty() {
		return size == 0;
	}

	/**
	 * Remove an element at the first of the node
	 * 
	 * @return
	 */
	public Object pop() {
		if (first != null) {
			Node current = first;
			first = first.next;
			this.size--;
			return current.element;
		} else {
			return null;
		}
	}

	/**
	 * Return the number of element in the stack
	 * 
	 * @return
	 */
	public int getSize() {
		return size;
	}

	private static class Node {
		public Object element;
		public Node next;

		public Node(Object o) {
			this.element = o;
		}
	}

	public static void main(String[] args) {
		MyStack stack = new MyStack();
		// System.out.println(stack.isEmpty());
		stack.push(2);
		stack.push(3);
		stack.push(1);
		// System.out.println(stack.getSize());
		System.out.println(stack.pop());
		System.out.println(stack.pop());
	}
}
