package me.skinnynoonie.primitivemapping;

public interface PrimitiveNumber extends PrimitiveElement {

    @Override
    PrimitiveNumber addMetadata(Object data);

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

}
