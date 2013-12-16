package list.impl;
import list.MyList;

public abstract class MyAbstractList<T> implements MyList<T> {
	
	protected int size=0; // the size of the list
	
	/**
	 * Create a default list 
	 */
	public MyAbstractList(){
	}
	/**
	 * Create a list from an array from an array of objects
	 * @param a
	 */
	public MyAbstractList(T[] a){
		for(T e:a){
			this.add(e);
		}
	}
	@Override
	public void add(T o) {
		this.add(o, size);
	}
	@Override
	public boolean isEmpty() {
		return this.size==0;
	}
	@Override
	public int size(){
		return size;
	}
}
