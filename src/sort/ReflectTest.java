package sort;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import org.junit.*;

import static org.junit.Assert.*;


public class ReflectTest {

	/**
	 * ģ��һ��JUnit��ι�����
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try{
			Class<?> test = Class.forName("sort.MergeSortTest");
			//��ȡ���������з�������
			Method[] methods = test.getDeclaredMethods();
			for(Method m:methods){
				//�ж�ĳ�������ǲ�����û��@Testע��
				if(m.isAnnotationPresent(org.junit.Test.class)){
//					System.out.println(m.getName()+"has test annotation");
					//��������ע�⣬����һ��������Ķ���JUnit�Ļ��ƣ�
					MergeSortTest s = (MergeSortTest)test.newInstance();
					//���и÷���,�������JUnit�ķ������޷����ð�
					m.invoke(s,null);
				}
			}
		}catch(ClassNotFoundException e){
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
	}
}
