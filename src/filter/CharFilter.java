package filter;

import java.util.UUID;

/**
 * 利用ASCII规则过滤特殊字符
 * 
 * @author qipeng
 * 
 */
public class CharFilter {

	//这里如果把数组改成为Integer数组，报空指针
	public int[] dic = new int[256];
	//如果是Integer的话，你需要给其数组的每个对象初始化的，在23行后，但int会自动赋值
	//	public Integer[] dic  = new Integer[256];
	public String filterChars = "[]{}:<>\\+-!()_~*|?/&";

	public CharFilter() {
		for (int k = 0; k < filterChars.length(); k++) {
			char c = filterChars.charAt(k);
			if (c < 256) {
				dic[c] = 1;
			}
		}
	}
	
	public String escape(String abc){
		if(abc==null){
			return null;
		}
		StringBuilder rs = new StringBuilder();
		for(int k=0;k<abc.length();k++){
			char c = abc.charAt(k);
			if(c<256 && dic[c]!=1){
				rs.append(c);
			}
			else{
				rs.append("");
			}
		}
		return rs.toString();
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		CharFilter cf = new CharFilter();
//		for(Integer i: cf.dic){
//			System.out.print(i);
//		}
		System.out.println(cf.escape(UUID.randomUUID().toString()));
	}

}
