package recursive;

/**
 * Description:
 * 最基本的递归运算，这个算法目前可以解决的问题有限，必须这个数字可以不断二分
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
