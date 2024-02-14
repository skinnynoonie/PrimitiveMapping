package me.skinnynoonie.primitivemapping;

import java.util.Optional;

/**
 * Representation of a primitive element with loose rules (such as String, List, and Map).
 * All lists, maps, etc. are synchronized.
 */
public interface PrimitiveElement {

    /**
     * Gets the metadata attached to this element by the class.
     * The metadata class will be the <b>same</b> as the data Object.
     *
     * @param metadataClass The class of the data class.
     * @return The metadata associated with the metadataClass in an {@code Optional}.
     * @throws IllegalArgumentException If any arguments are null.
     */
    <T> Optional<T> getMetadata(Class<T> metadataClass);

    /**
     * Sets a metadata value for this element, which can be retrieved with {@link #getMetadata(Class)}.
     * The metadata class and data class must be the same.
     *
     * @param metadataClass The data's class, which will be used as the key.
     * @param data The data to attach to this element.
     * @throws IllegalArgumentException If any arguments are null (including the data argument) or if
     *                                  metadataClass and data's class are not the same.
     */
    void setMetadata(Class<?> metadataClass, Object data);

    /**
     * Gets this element as a map.
     *
     * @return This element as a {@link PrimitiveMap}.
     * @throws UnsupportedOperationException If this element is not a {@link PrimitiveMap}.
     */
    PrimitiveMap asMap();

    /**
     * Gets this element as a list.
     *
     * @return This element as a {@link PrimitiveList}.
     * @throws UnsupportedOperationException If this element is not a {@link PrimitiveList}.
     */
    PrimitiveList asList();

    /**
     * Gets this element as a String.
     *
     * @return This element as a {@link String}.
     * @throws UnsupportedOperationException If this element is not a {@link String}.
     */
    String asString();

    /**
     * Gets this element as a byte.
     *
     * @return This element as a byte.
     * @throws UnsupportedOperationException If this element is not a number.
     * @throws ArithmeticException If this number is too large to represent a byte.
     */
    byte asByte();

    /**
     * Gets this element as a short.
     *
     * @return This element as a short.
     * @throws UnsupportedOperationException If this element is not a number.
     * @throws ArithmeticException If this number is too large to represent a short.
     */
    short asShort();

    /**
     * Gets this element as an int.
     *
     * @return This element as an int.
     * @throws UnsupportedOperationException If this element is not a number.
     * @throws ArithmeticException If this number is too large to represent an int.
     */
    int asInt();

    /**
     * Gets this element as a long.
     *
     * @return This element as a long.
     * @throws UnsupportedOperationException If this element is not a number.
     * @throws ArithmeticException If this number is too large to represent a long.
     */
    long asLong();

    /**
     * Gets this element as a float.
     *
     * @return This element as a float.
     * @throws UnsupportedOperationException If this element is not a number.
     * @throws ArithmeticException If this number is too large to represent a float.
     */
    float asFloat();

    /**
     * Gets this element as a double.
     *
     * @return This element as a double.
     * @throws UnsupportedOperationException If this element is not a number.
     * @throws ArithmeticException If this number is too large to represent a double.
     */
    double asDouble();

    /**
     * Checks whether this element is represented as {@code null}.
     * Note that Java's {@code null} primitive is not the same as a null representation (could be this element).
     * A literal primitive {@code null}, for example, can mean a non-existing entry in a {@link PrimitiveMap}.
     * A primitive null (not Java's null), for example, can mean a null value in a {@link PrimitiveMap} from an
     * existing entry.
     *
     * @return true if this element is a representation of null otherwise false.
     */
    boolean isNull();

    /**
     * Checks if this element is a map.
     *
     * @return true if this element is a {@link PrimitiveMap}, otherwise false.
     */
    boolean isMap();

    /**
     * Checks if this element is a list.
     *
     * @return true if this element is a {@link PrimitiveList}, otherwise false.
     */
    boolean isList();

    /**
     * Checks if this element is a String.
     *
     * @return true if this element is a {@link String}, otherwise false.
     */
    boolean isString();

    /**
     * Checks if this element is a primitive number.
     *
     * @return true if this element is a primitive number, otherwise false.
     */
    boolean isNumber();

    /**
     * The String representation will display in a JSON format, excluding any metadata of this element.
     *
     * @return String representation of this element.
     */
    @Override
    String toString();

}
