package filter;

import java.util.UUID;

/**
 * ����ASCII������������ַ�
 * 
 * @author qipeng
 * 
 */
public class CharFilter {

	//�������������ĳ�ΪInteger���飬����ָ��
	public int[] dic = new int[256];
	//�����Integer�Ļ�������Ҫ���������ÿ�������ʼ���ģ���23�к󣬵�int���Զ���ֵ
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
