package list.queue;

import java.util.Random;

import list.impl.link.MyLinkedList;

/**
 * @未正确实现
 * 离散事件模拟
 * 其实这个类就是为了演示如何处理离散事件的，而我们的设计也是离散事件驱动模型
 * 也就是我们整个程序是由事件来驱动发生，然后根据事件的性质（内部类Event）来做出相应的处理
 * 两种数据结构：有序链表，表示事件链；队列，表示银行排队的队列
 * 本程序中事件有两种1、客户到达，处理的方法为＃customerArrived en.nType=0
 * 					2、客户离开，处理该事件的方法为＃customerDeparture en.nType>0
 * 
 * @author qipeng
 *
 */
public class BankSimulation {

	private MyLinkedList ev;
	private Event en;
	private MyQueue[] queues;
	private QueueElement customer;
	private int totalTime;
	private int customerNum;
	private int closeTime;

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void openForDay() {
		totalTime = 0;
		customerNum = 0;
		ev = new MyLinkedList(); // 初始化事件链表为空表
		queues = new MyQueue[4];
		// 设定第一个客户到达事件
		en = new Event(0, 0);
		ev.add(en);
		for (int i = 0; i < queues.length; i++) {
			queues[i] = new MyQueue();
		}
	}

	@SuppressWarnings("unchecked")
	public void customerArrived() {
		// 处理客户到达事件,en.nType=0
		++customerNum;
		Random r = new Random();
		int duration = r.nextInt(100);
		int intertime = r.nextInt(100); // 从目前时间＋intertime会有新客户到来
		en = new Event(en.occurTime + intertime, 0);
		if (en.occurTime < closeTime) {
			//新客户到达事件插入事务表
			ev.add(en);
		}
		int minQueue = minQueue(queues);
		queues[minQueue].offer(en);
		if (queues[minQueue].getSize() == 1) {
			Event endEvent = new Event(en.occurTime + duration, minQueue);
			//将一个客户离开事件插入事务表
			ev.add(endEvent);
		}
	}

	public void customerDeparture() {
		int i = en.nType;
		customer = (QueueElement) queues[i].poll();
		//计算该客户逗留的时间
		totalTime += en.occurTime - customer.arrivalTime;
		if (!queues[i].isEmpty()) {
			//如果队列不空，再设定一个新的队头离开事件插入事件表
			ev.add(new Event(en.occurTime + customer.duration, i));
		}
	}

	public static void main(String[] args) {
		BankSimulation bs = new BankSimulation();
		bs.openForDay();
		System.out.println();
	}

	/**
	 * 判断两个事件的发生时刻
	 * 
	 * @param a
	 * @param b
	 * @return
	 */
	private int cmp(Event a, Event b) {
		if (a.occurTime < b.occurTime) {
			return -1;
		} else if (a.occurTime > b.occurTime) {
			return 1;
		} else {
			return 0;
		}
	}

	private int minQueue(MyQueue[] queues) {
		int minSize = queues[0].getSize();
		for (int i = 1; i < queues.length; i++) {
			if (queues[i].getSize() < minSize) {
				minSize = queues[i].getSize();
			}
		}
		return minSize;
	}

	private class Event {
		int occurTime;
		int nType; // 事件类型，0表示到达事件，1至4表示四个窗口的离开事件

		public Event() {
		}

		public Event(int occurTime, int nType) {
			this.occurTime = occurTime;
			this.nType = nType;
		}
	}

	private class QueueElement {
		int arrivalTime;
		int duration; // 办理业务所需时间
	}
}
