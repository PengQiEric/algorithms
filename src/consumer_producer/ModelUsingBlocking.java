package consumer_producer;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * ����һ��������ζ��߳�������������������ģ�͵�һ��demo�����õ���
 * BlockingQueue����ӿ�
 * @author qipeng
 *
 */
public class ModelUsingBlocking 
{
	public static void main(String[] args)
	{
		BlockingQueue<Integer> sharedQueue = new LinkedBlockingQueue<Integer>();
		Thread consumer1 = new Thread(new Consumer(sharedQueue),"consumer1");		
//		Thread producer1 = new Thread(new Producer(sharedQueue),"producer1");
		Thread producer = new Thread(new Producer(sharedQueue),"producer");
		Thread consumer = new Thread(new Consumer(sharedQueue),"consumer");
		producer.start();
//		producer1.start();
		consumer.start();
		consumer1.start();
	}
}

class Producer implements Runnable
{
	private final BlockingQueue<Integer> sharedQueue;
	
	public Producer(BlockingQueue<Integer> sharedQueue)
	{
		this.sharedQueue = sharedQueue;
	}
	
	public void run()
	{
		for(int i=0; i<10; i++)
		{
			try {
				System.out.println("produced:"+i+" "+Thread.currentThread().getName());
				sharedQueue.put(i);
			} catch (InterruptedException e) {
				Logger.getLogger(Producer.class.getName()).log(Level.SEVERE, null, e);
			}
			
		}
	}
}

class Consumer implements Runnable
{
	private final BlockingQueue<Integer> sharedQueue;
	
	public Consumer(BlockingQueue<Integer> sharedQueue)
	{
		this.sharedQueue = sharedQueue;
	}
	
	public void run()
	{
		while(true)
		{
			try
			{
				System.out.println("consumed��"+sharedQueue.take()+" "+Thread.currentThread().getName());
			}
			catch(InterruptedException ex)
			{
				Logger.getLogger(Consumer.class.getName()).log(Level.SEVERE, null, ex);
			}
		}
	}
}