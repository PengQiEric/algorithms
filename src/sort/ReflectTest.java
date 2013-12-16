package sort;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import org.junit.*;

import static org.junit.Assert.*;


public class ReflectTest {

	/**
	 * 模仿一下JUnit如何工作的
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try{
			Class<?> test = Class.forName("sort.MergeSortTest");
			//获取声明的所有方法数组
			Method[] methods = test.getDeclaredMethods();
			for(Method m:methods){
				//判断某个方法是不是有没有@Test注解
				if(m.isAnnotationPresent(org.junit.Test.class)){
//					System.out.println(m.getName()+"has test annotation");
					//如果有这个注解，创建一个测试类的对象（JUnit的机制）
					MergeSortTest s = (MergeSortTest)test.newInstance();
					//运行该方法,但这个是JUnit的方法，无法调用啊
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
