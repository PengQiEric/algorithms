package sort;


public class Merge {
	public static int[] merge(int[] data, int low, int high)
	{
		if(low == high)
		{
			return new int[]{data[low]};
		}
		int mid = (low + high)/2;
		int[] left = merge(data, low, mid);
		int[] right = merge(data, mid+1, high);
		return mergesort(left,right);
	}
	//传过来的一定是两个有序的数组
	public static int[] mergesort(int[] left, int[] right)
	{
		int[] temp = new int[left.length+right.length];
		int i = 0 , j = 0, k = 0;
		while(i<left.length && j<right.length)
		{
			temp[k++] = (left[i]<right[j])? left[i++]:right[j++];
		}
		while(i<left.length)
		{
			temp[k++] = left[i++];
		}
		while(j<right.length)
		{
			temp[k++] = right[j++];
		}
		return temp;
	}
	public static void main(String[] args)
	{
		int[] a = new int[]{9,8,7,6,5,4,3,3,2,1};
		a = merge(a, 0, a.length-1);
		for(int test: a)
		{
			System.out.print(test);
		}
	}
}
