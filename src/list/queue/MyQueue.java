package list.queue;

import list.impl.link.MyLinkedList;

/**
 * 因为插入和删除是两个方向的，所以链表的效率会好些
 * @author qipeng
 *
 */
public class MyQueue {
	private MyLinkedList list = new MyLinkedList();
	
	/**
	 * Inserts the specified element into the queue
	 * @param o
	 */
	@SuppressWarnings("unchecked")
	public void offer(Object o){
		list.addLast(o);
	}
	
	/**
	 * Retrieve and remove the head of the queue
	 * @return
	 */
	public Object poll(){
		return list.removeFirst();
	}
	
	public int getSize(){
		return list.size();
	}
	
	public boolean isEmpty(){
		return list.size()==0;
	}
}
