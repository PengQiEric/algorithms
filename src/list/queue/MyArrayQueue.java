package list.queue;

/**
 * 循环队列的实现
 * @author qipeng
 *
 * @param <T>
 */
public class MyArrayQueue<T> {
	// 私有变量以_开头
	private T[] _queue;
	//其实整个循环队列的长度为MAX_SIZE－1,留下的一个空隙用来区分队列为“空”还是“满”
	private final static int MAX_SIZE = 4;
	private int _front=0;
	private int _rear=0;

	@SuppressWarnings("unchecked")
	public void init() {
		_queue = (T[]) new Object[MAX_SIZE];
		_front = 0;
		_rear = 0;
	}

	/**
	 * @param element
	 * @return 如果插入成功返回true，如果队列已满，返回false
	 */
	public boolean enqueue(T element) {
		if ((_rear + 1) % MAX_SIZE == _front)
			return false;
		_queue[_rear] = element;
		//首先必须rear先自加，否则其取余会一直为0
		_rear = (++_rear) % MAX_SIZE;
		return true;
	}

	/**
	 * @return 如果队列为空，返回null;否则返回先入列的元素
	 */
	public T dequeue() {
		int indexOfElement;
		if (_front == _rear)
			return null;
		indexOfElement = _front;
		_front = (++_front) % MAX_SIZE;
		return _queue[indexOfElement];
	}

	public int getSize() {
		//注意％运算符，如果是一个负数，那么－3％5返回的是－3,所以要加一个MAX_SIZE
		return ((_rear - _front + MAX_SIZE) % MAX_SIZE);
	}
	
	public static void main(String[] args) {
		MyArrayQueue<Integer> queue = new MyArrayQueue<Integer>();
		queue.init();
		//没有元素，返回null
		System.out.println(queue.dequeue());
		queue.enqueue(1);
		queue.enqueue(2);
		System.out.println(queue.enqueue(3));
		System.out.println(queue.enqueue(4));
		System.out.println(queue.enqueue(5));
	}

}
