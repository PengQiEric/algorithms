package sort;

import java.util.Arrays;

public class MergeSort {

	public int[] merge(int[] left, int[] right) {
		int[] all = new int[left.length + right.length];
		// 用来纪录返回数组的移动的
		int k = 0;
		for (int i = 0, j = 0; i < left.length || j < right.length;) {
			// 如果左边数组的元素已经全部copy到all中，而right还有元素时
			if (i == left.length && j < right.length) {
				all[k] = right[j];
				j++;
				k++;
			}
			// 如果右边数组元素全部插入，而左边有剩余元素时
			else if (j == right.length && i < left.length) {
				all[k] = left[i];
				i++;
				k++;
			}
			else if (left[i] > right[j]) {
				all[k] = right[j];
				j++;
				k++;
			}
			else if (left[i] < right[j]) {
				all[k] = left[i];
				i++;
				k++;
			}
		}
		return all;
	}

	public int[] sort(int[] unsorted) {
		int[] left;
		int[] right;
		//如果数组的长度惟一，结束迭代，返回数组
		if (unsorted.length == 1) {
			return unsorted;
		} else {
			//将数组一份为二,获得排好序列的数组
			//何时该使用递归呢？首先是要问题可以在逐渐变小的情况下用同样的方法来解决
			//但需要注意的是如何来接受返回值呢？这里必须要把sort方法返回的值赋给left，right，这样才可以把递归的值保存起来
			left = sort(Arrays.copyOfRange(unsorted, 0, unsorted.length / 2 ));
			right = sort(Arrays.copyOfRange(unsorted, unsorted.length / 2 ,unsorted.length));
			//返回两个合并好的数组
			return merge(left,right);
		/*	return merge(sort(Arrays.copyOfRange(unsorted, 0,unsorted.length/2)),
					sort(Arrays.copyOfRange(unsorted, unsorted.length/2, unsorted.length)));*/
		}

	}

/*	public static void main(String[] args) {
		MergeSort ms = new MergeSort();
		int[] test = {8,4,2,1,3,9,7};
		int[] sort_array = ms.sort(test);
		for(int i:sort_array){
			System.out.print(i);
		}
		
	}*/
}
