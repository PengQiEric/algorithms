package list.queue;

/**
 * ѭ�����е�ʵ��
 * @author qipeng
 *
 * @param <T>
 */
public class MyArrayQueue<T> {
	// ˽�б�����_��ͷ
	private T[] _queue;
	//��ʵ����ѭ�����еĳ���ΪMAX_SIZE��1,���µ�һ����϶�������ֶ���Ϊ���ա����ǡ�����
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
	 * @return �������ɹ�����true�������������������false
	 */
	public boolean enqueue(T element) {
		if ((_rear + 1) % MAX_SIZE == _front)
			return false;
		_queue[_rear] = element;
		//���ȱ���rear���Լӣ�������ȡ���һֱΪ0
		_rear = (++_rear) % MAX_SIZE;
		return true;
	}

	/**
	 * @return �������Ϊ�գ�����null;���򷵻������е�Ԫ��
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
		//ע�⣥������������һ����������ô��3��5���ص��ǣ�3,����Ҫ��һ��MAX_SIZE
		return ((_rear - _front + MAX_SIZE) % MAX_SIZE);
	}
	
	public static void main(String[] args) {
		MyArrayQueue<Integer> queue = new MyArrayQueue<Integer>();
		queue.init();
		//û��Ԫ�أ�����null
		System.out.println(queue.dequeue());
		queue.enqueue(1);
		queue.enqueue(2);
		System.out.println(queue.enqueue(3));
		System.out.println(queue.enqueue(4));
		System.out.println(queue.enqueue(5));
	}

}
