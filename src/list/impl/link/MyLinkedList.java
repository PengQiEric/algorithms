package list.impl.link;

import list.impl.MyAbstractList;

/**
 * This class prepare for the node which uses in linked list
 * 
 * @author qipeng
 * 
 */

/**
 * @author qipeng
 * 
 */
public class MyLinkedList<T> extends MyAbstractList<T> {

	private Node first, last;

	public MyLinkedList() {

	}

	public MyLinkedList(T[] o) {
		super(o);
	}

	/**
	 * Add an element o to the beginning of the list
	 * 
	 * @param o
	 */
	public void addFirst(T o) {
		Node newNode = new Node(o);
		newNode.pointer = first;
		first = newNode;
		size++;

		if (last == null) {
			last = first;
		}
	}

	/**
	 * Add an element o at the last of the list
	 * 
	 * @param o
	 */
	public void addLast(T o) {
		Node newNode = new Node(o);
		if (last != null) {
			last.pointer = newNode;
			last = newNode;
		} else {
			last = newNode;
			first = newNode;
		}
		size++;
	}

	@Override
	public void add(T o) {
		this.addLast(o);
	}

	@Override
	public void add(T o, int index) {
		if (index == 0) {
			addFirst(o);
		} else if (index >= size) {
			addLast(o);
		} else {
			Node newNode = new Node(o);
			// current is to find the previous index node position
			// when index=2; then current = first.pointer, then the current
			// point to the second node
			Node current = first;
			for (int i = 1; i < index; i++) {
				current = current.pointer;
			}
			newNode.pointer = current.pointer;
			current.pointer = newNode;
		}
		size++;
	}

	@Override
	public void clear() {
		first = null;
		last = null;
	}

	@Override
	public boolean contain(Object o) {
		Node current = first;
		for (int i = 0; i < size; i++) {
			if (current.element.equals(o)) {
				return true;
			}
			current = current.pointer;
		}
		return false;
	}

	@SuppressWarnings("unchecked")
	@Override
	public T get(int index) {
		if (index == 0) {
			return (T) first.element;
		} else if (index == size) {
			return (T) last.element;
		} else {
			Node current = first;
			for (int i = 0; i < index; i++) {
				current = current.pointer;
			}
			return (T) current.element;
		}
	}

	/**
	 * Return the first element in the list
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public T getFirst() {
		if (first == null) {
			return null;
		}
		return (T) first.element;
	}

	/**
	 * Return the last element in the list
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public T getLast() {
		if (last == null) {
			return null;
		}
		return (T) last.element;
	}

	@Override
	public int indexOf(Object o) {
		int index = 0;
		if (o != null) {
			for (Node current = first; current.pointer != null; current = current.pointer) {
				if (current.element.equals(o)) {
					return index;
				}
				index++;
			}
		} else {
			for (Node current = first; current.pointer != null; current = current.pointer) {
				if (current.element == null) {
					return index;
				}
				index++;
			}
		}
		return -1;
	}

	@Override
	public boolean isEmpty() {
		return super.isEmpty();
	}

	@Override
	public int lastIndexOf(Object o) {
		int index = 0;
		int last = -1;
		if (o != null) {
			for (Node current = first; current.pointer != null; current = current.pointer) {
				if (current.element.equals(o)) {
					last = index;
				}
				index++;
			}
		} else {
			for (Node current = first; current.pointer != null; current = current.pointer) {
				if (current.element == null) {
					last = index;
				}
				index++;
			}
		}
		return last;
	}

	@SuppressWarnings("unchecked")
	@Override
	public T remove(int index) {
		if (index < 0 || index > size)
			return null;
		if (index == 0)
			removeFirst();
		if (index == size)
			removeLast();
		Node previous = first;
		for (int i = 1; i < index; i++) {
			previous = previous.pointer;
		}
		Node current = previous.pointer;
		previous.pointer = current.pointer;
		// This is important
		size--;
		return (T) current.pointer;
	}

	/**
	 * Remove the first node and return the object T that is contained in the
	 * removed node
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public T removeFirst() {
		if (first == null)
			return null;
		Node previous = first;
		first = first.pointer;
		// 如果这时候链表为空，那么未指针也要设置为null
		if (first == null) {
			last = null;
		}
		size--;
		return (T) previous.element;
	}

	/**
	 * Remove the last node and return the object T that is contained in the
	 * removed node
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public T removeLast() {
		if (last == null)
			return null;
		Node previous = first;
		for (; previous.pointer != null; previous = previous.pointer)
			;
		Node current = last;
		last = previous;
		// 如果这时候链表为空了，那么first指针也要设置为空
		if (last == null) {
			first = null;
		}
		size--;
		return (T) current.element;
	}

	@Override
	public boolean remove(Object o) {
		if (first == null) {
			return false;
		}
		Node previous = first;
		if (first.element.equals(o)) {
			first = first.pointer;
			size--;
			// 如果只有一个元素的时候
			if (first == null) {
				last = null;
			}
			return true;
		}
		if (last.element.equals(o)) {
			for (; previous.pointer != null; previous = previous.pointer)
				;
			last = previous;
			size--;
			return true;
		}
		for (Node current = previous.pointer; current != null; previous = previous.pointer) {
			if (current.element.equals(o)) {
				previous.pointer = current.pointer;
				size--;
				return true;
			}
			// ......
			current = current.pointer;
		}

		return false;
	}

	@SuppressWarnings("unchecked")
	@Override
	public T set(int index, T o) {
		Node current = first;
		for (int i = 0; i < index; i++) {
			current = current.pointer;
		}
		Object old = current.element;
		current.element = o;
		return (T) old;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString();
	}

	private static class Node {
		public Object element;
		public Node pointer;

		public Node(Object o) {
			this.element = o;
		}
	}

}
