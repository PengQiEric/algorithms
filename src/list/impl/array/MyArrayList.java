package list.impl.array;

import list.impl.MyAbstractList;

public class MyArrayList<T> extends MyAbstractList<T> {
	public static final int INITIAL_CAPACITY = 16;
	public Object[] data = null;

	public MyArrayList() {
		// 无法创建泛型数组，当然我们要实现泛型需要做许多类型的判断
		data = new Object[INITIAL_CAPACITY];
	}

	public MyArrayList(T[] a) {
		data = a;
		// 一个标识
		size = a.length;
	}

	@Override
	public void add(T o) {
		super.add(o);
	}

	public void add(T o, int index) {
		ensureCapacity();

		// 将index（包括）其以后的数据向后移动一个位置
		for (int i = size - 1; i >= index; i--) {
			data[i + 1] = data[i];
		}
		data[index] = o;
		size++;
	}

	/**
	 * 保证数组的容量
	 */
	public void ensureCapacity() {
		if (size >= data.length) {
			Object[] newData = new Object[data.length * 2];
			System.arraycopy(data, 0, newData, 0, size);
			data = newData;
		}
	}

	@Override
	public boolean contain(Object o) {
		return indexOf(o) >= 0;
	}

	@Override
	public void clear() {
		data = null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public T get(int index) {
		return (T) data[index];
	}

	@Override
	public int indexOf(Object o) {
		if (o != null) {
			for (int i = 0; i < data.length; i++) {
				if (o.equals(data[i])) {
					return i;
				}
			}
		} else {
			for (int i = 0; i < data.length; i++) {
				if (data[i] == null) {
					return i;
				}
			}
		}
		return -1;
	}

	@Override
	public boolean isEmpty() {
		return super.isEmpty();
	}

	@Override
	public int lastIndexOf(Object o) {
		if (o != null) {
			for (int i = data.length - 1; i >= 0; i--) {
				if (o.equals(data[i])) {
					return i;
				}
			}
		} else {
			for (int i = data.length - 1; i >= 0; i--) {
				if (data[i] == null) {
					return i;
				}
			}
		}
		return -1;
	}

	@Override
	public T remove(int index) {
		@SuppressWarnings("unchecked")
		T previous = (T) data[index];
		for (int i = index + 1; i < size; i++) {
			data[i] = data[i + 1];
		}
		size--;
		return previous;
	}

	@Override
	public boolean remove(Object o) {
		int index = -1;
		for (int i = 0; i < size; i++) {
			if (o.equals(data[i])) {
				index = i;
				break;
			}
		}
		if (index > -1) {
			for (int i = index + 1; i < size; i++) {
				data[i] = data[i++];
			}
			size--;
			return true;
		} else {
			return false;
		}
	}
	@Override
	public T set(int index, T o) {
		data[index] = o;
		return o;
	}

	@Override
	public String toString() {
		StringBuffer result = new StringBuffer("[");
		for (int i = 0; i < size; i++) {
			result.append(data[i]);
			if (i < size - 1) {
				result.append(", ");
			}
		}
		return result.toString() + "]";
	}
}
