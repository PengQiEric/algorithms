package list;

/**
 * @author qipeng
 *
 */
public interface MyList<T> {
	/**
	 * Add a new element o at the end of the list
	 * @param o 
	 */
	public void add(T o);
	
	/**
	 * Add a new element o at the specified index in this list
	 * @param o
	 * @param index
	 */
	public void add(T o, int index);
	/**
	 * Clear the list
	 */
	public void clear();
	/**
	 * Return true if the list contains the element o
	 * @param o
	 * @return
	 */
	public boolean contain(Object o);
	/**
	 * Return the element from this list at the specified index
	 * @param index
	 * @return
	 */
	public T get(int index);
	/**
	 * Return the index of the first matching element in this list
	 * Return -1 if no match
	 * @param o
	 * @return
	 */
	public int indexOf(Object o);
	/**
	 * Return true if the list contains no element
	 * @return
	 */
	public boolean isEmpty();
	/**
	 * Return the index of the last matching element in this list
	 * Return -1 if not contains o
	 * @param o
	 * @return
	 */
	public int lastIndexOf(Object o);
	/**
	 * Remove the first occurrence of the element o from this list
	 * Shift any subsequent elements to the left
	 * Return true if the element is removed
	 * @param o
	 * @return
	 */
	public boolean remove(Object o);
	/**
	 * Remove the element at the specified position in this list
	 * Shift any subsequent elements to the left
	 * Return the element that was removed from the list
	 * @param index
	 * @return
	 */
	public T remove(int index);
	/**
	 * Replace the element at the specified position in the list with specified element and return the previous element
	 * @param index
	 * @param o
	 */
	public T set(int index, T o);
	/**
	 * Return the number of elements in the list
	 * @return
	 */
	public int size();
}
