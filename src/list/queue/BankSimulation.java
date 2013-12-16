package list.queue;

import java.util.Random;

import list.impl.link.MyLinkedList;

/**
 * @δ��ȷʵ��
 * ��ɢ�¼�ģ��
 * ��ʵ��������Ϊ����ʾ��δ�����ɢ�¼��ģ������ǵ����Ҳ����ɢ�¼�����ģ��
 * Ҳ���������������������¼�������������Ȼ������¼������ʣ��ڲ���Event����������Ӧ�Ĵ���
 * �������ݽṹ������������ʾ�¼��������У���ʾ�����ŶӵĶ���
 * ���������¼�������1���ͻ��������ķ���Ϊ��customerArrived en.nType=0
 * 					2���ͻ��뿪��������¼��ķ���Ϊ��customerDeparture en.nType>0
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
		ev = new MyLinkedList(); // ��ʼ���¼�����Ϊ�ձ�
		queues = new MyQueue[4];
		// �趨��һ���ͻ������¼�
		en = new Event(0, 0);
		ev.add(en);
		for (int i = 0; i < queues.length; i++) {
			queues[i] = new MyQueue();
		}
	}

	@SuppressWarnings("unchecked")
	public void customerArrived() {
		// ����ͻ������¼�,en.nType=0
		++customerNum;
		Random r = new Random();
		int duration = r.nextInt(100);
		int intertime = r.nextInt(100); // ��Ŀǰʱ�䣫intertime�����¿ͻ�����
		en = new Event(en.occurTime + intertime, 0);
		if (en.occurTime < closeTime) {
			//�¿ͻ������¼����������
			ev.add(en);
		}
		int minQueue = minQueue(queues);
		queues[minQueue].offer(en);
		if (queues[minQueue].getSize() == 1) {
			Event endEvent = new Event(en.occurTime + duration, minQueue);
			//��һ���ͻ��뿪�¼����������
			ev.add(endEvent);
		}
	}

	public void customerDeparture() {
		int i = en.nType;
		customer = (QueueElement) queues[i].poll();
		//����ÿͻ�������ʱ��
		totalTime += en.occurTime - customer.arrivalTime;
		if (!queues[i].isEmpty()) {
			//������в��գ����趨һ���µĶ�ͷ�뿪�¼������¼���
			ev.add(new Event(en.occurTime + customer.duration, i));
		}
	}

	public static void main(String[] args) {
		BankSimulation bs = new BankSimulation();
		bs.openForDay();
		System.out.println();
	}

	/**
	 * �ж������¼��ķ���ʱ��
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
		int nType; // �¼����ͣ�0��ʾ�����¼���1��4��ʾ�ĸ����ڵ��뿪�¼�

		public Event() {
		}

		public Event(int occurTime, int nType) {
			this.occurTime = occurTime;
			this.nType = nType;
		}
	}

	private class QueueElement {
		int arrivalTime;
		int duration; // ����ҵ������ʱ��
	}
}
