package me.skinnynoonie.primitivemapping;

/**
 * A representation of a list that can hold any primitive element.
 * There should be no {@code null} values, but instead a null representation.
 * This list is synchronized.
 */
public interface PrimitiveList extends PrimitiveElement, Iterable<PrimitiveElement> {

    /**
     * Gets the element at the specified index (starts from 0).
     *
     * @param index The index of the element.
     * @return The element at the index.
     * @throws IndexOutOfBoundsException If the index is less than 0 or larger than {@link #size()} - 1.
     */
    PrimitiveElement get(int index);

    /**
     * Adds an element to the end of this list.
     *
     * @param element The element to add.
     * @return This list.
     * @throws IllegalArgumentException If any arguments are null.
     */
    PrimitiveList add(PrimitiveElement element);

    /**
     * Removes the first instance of the element in this list.
     *
     * @param element The element to remove from this list.
     * @throws IllegalArgumentException If any arguments are null.
     */
    void remove(PrimitiveElement element);

    /**
     * Checks if this list contains the element.
     *
     * @param element Check if this element is located in this list.
     * @return true if the element is in this list, otherwise false.
     * @throws IllegalArgumentException If any arguments are null.
     */
    boolean contains(PrimitiveElement element);

    /**
     * Returns the size of all PrimitiveElements in this list.
     *
     * @return the size of this list.
     */
    int size();

}
