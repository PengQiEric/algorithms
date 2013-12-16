package sort;

import java.util.Arrays;

public class MergeSort {

	public int[] merge(int[] left, int[] right) {
		int[] all = new int[left.length + right.length];
		// ������¼����������ƶ���
		int k = 0;
		for (int i = 0, j = 0; i < left.length || j < right.length;) {
			// �����������Ԫ���Ѿ�ȫ��copy��all�У���right����Ԫ��ʱ
			if (i == left.length && j < right.length) {
				all[k] = right[j];
				j++;
				k++;
			}
			// ����ұ�����Ԫ��ȫ�����룬�������ʣ��Ԫ��ʱ
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
		//�������ĳ���Ωһ��������������������
		if (unsorted.length == 1) {
			return unsorted;
		} else {
			//������һ��Ϊ��,����ź����е�����
			//��ʱ��ʹ�õݹ��أ�������Ҫ����������𽥱�С���������ͬ���ķ��������
			//����Ҫע�������������ܷ���ֵ�أ��������Ҫ��sort�������ص�ֵ����left��right�������ſ��԰ѵݹ��ֵ��������
			left = sort(Arrays.copyOfRange(unsorted, 0, unsorted.length / 2 ));
			right = sort(Arrays.copyOfRange(unsorted, unsorted.length / 2 ,unsorted.length));
			//���������ϲ��õ�����
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
