package recursive;

/**
 * Description:
 * ������ĵݹ����㣬����㷨Ŀǰ���Խ�����������ޣ�����������ֿ��Բ��϶���
 * @author qipeng
 *
 */
public class ProductRecursiveTest {
	
	public int function(Integer x, Integer y){
		
		if(x.toString().length() == 1 && y.toString().length() == 1){
			return x*y;
		}
		int n = x.toString().length();
		int a = Integer.parseInt(x.toString().substring(0, n/2));
		int b = Integer.parseInt(x.toString().substring(n/2, n));
		int c = Integer.parseInt(y.toString().substring(0, n/2));
		int d = Integer.parseInt(y.toString().substring(n/2, n));
		
		return (int)Math.pow(10.0, (double)n)*function(a,c)+(int)Math.pow(10.0, (double)(n/2))*
				(function(a,d)+function(b,c))+function(b,d);
	}
	
	public static void main(String[] args){
		System.out.println(new ProductRecursiveTest().function(4366,3457));
	}
}
