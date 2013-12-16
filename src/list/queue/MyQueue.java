package list.queue;

import list.impl.link.MyLinkedList;

/**
 * ��Ϊ�����ɾ������������ģ����������Ч�ʻ��Щ
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
