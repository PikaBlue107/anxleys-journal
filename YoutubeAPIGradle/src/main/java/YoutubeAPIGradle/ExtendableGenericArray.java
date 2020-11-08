/*----------------------------------------------------
 * ExtendableGenericArray.java
 *--------------------------------------------------*/
package YoutubeAPIGradle;

/**
 * Allows an Array of generic objects to be stored. Any index can be accessed,
 * and the array will adapt its internal size to hold at least that many
 * elements.
 * 
 * @author Melody Griesen
 *
 * @param <E> generic object to store in this custom Array
 */
public class ExtendableGenericArray<E> {

	/** Internal array used for storage. */
	E[] list;
	/** Default initial capacity of 7. */
	public static final int DEFAUT_CAPACITY = 7;

	/**
	 * Initialize the custom Array with the specified initial capacity.
	 * 
	 * @param capacity initial capacity for the Array. Cannot be negative.
	 * @throws IllegalArgumentException if capacity is negative
	 */
	@SuppressWarnings("unchecked")
	public ExtendableGenericArray(int capacity) {
		if (capacity < 0)
			throw new IllegalArgumentException("Array cannot have negative capacity!");
		list = (E[]) new Object[capacity];
	}
	
	/**
	 * Initialize the custom Array with the default initial capacity.
	 */
	public ExtendableGenericArray() {
		this(DEFAUT_CAPACITY);
	}

	/**
	 * Set the element at the specified index. The previous element is returned. If
	 * the array is not currently big enough, it will be extended to allow for such
	 * an index to be accessed.
	 * 
	 * @param idx  the index of the element to set
	 * @param data the new element to place at the index
	 * @return the previous element there
	 */
	public E set(int idx, E data) {
		// Grow the array to include idx, if needed
		ensureAddressable(idx);

		// Store previous data
		E old = list[idx];
		// Set new data
		list[idx] = data;
		// Return old data
		return old;
	}

	/**
	 * Retrieves the element at the specified index. If the array is not currently
	 * big enough, it will be extended to allow for such an index to be accessed.
	 * 
	 * @param idxthe index of the element to retrieve.
	 * @return the element at the specified index
	 */
	public E get(int idx) {
		// Grow the array to include idx, if needed
		ensureAddressable(idx);
		// Return the data at the specified index
		return list[idx];
	}

	/**
	 * Returns the current length of the underlying array.
	 * 
	 * @return the internal array's length.
	 */
	public int length() {
		return list.length;
	}

	/**
	 * Ensures that the underlying array can accomodate an access to the given index
	 * by growing the array similar to an ArrayList if needed.
	 * 
	 * @param idx
	 */
	private void ensureAddressable(int idx) {
		// If we already have enough space, cool, we're done
		if( idx < length() )
			return;
		
		// Calculate new capacity
		int oldCapacity = length(), newCapacity = oldCapacity * 2 + 1;
		while( idx >= newCapacity ) {
			oldCapacity = newCapacity;
			newCapacity = oldCapacity * 2 + 1;
		}

		// Create new list object
		@SuppressWarnings("unchecked")
		E[] newList = (E[]) new Object[newCapacity];

		// Copy over existing data
		for (int i = 0; i < length(); i++)
			newList[i] = list[i];

		// Set the underlying list to be the new list
		list = newList;
	}
}
