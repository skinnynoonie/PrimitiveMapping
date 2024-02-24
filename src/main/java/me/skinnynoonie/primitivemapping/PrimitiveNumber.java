package me.skinnynoonie.primitivemapping;

public final class PrimitiveNumber extends AbstractPrimitiveElement<PrimitiveNumber> {

    public static PrimitiveNumber parseString(String str) {
        return new PrimitiveNumber(Double.parseDouble(str));
    }

    public static PrimitiveNumber ofByte(byte b) {
        return new PrimitiveNumber(b);
    }

    public static PrimitiveNumber ofShort(short s) {
        return new PrimitiveNumber(s);
    }

    public static PrimitiveNumber ofInt(int i) {
        return new PrimitiveNumber(i);
    }

    public static PrimitiveNumber ofLong(long l) {
        return new PrimitiveNumber(l);
    }

    public static PrimitiveNumber ofFloat(float f) {
        return new PrimitiveNumber(f);
    }

    public static PrimitiveNumber ofDouble(double d) {
        return new PrimitiveNumber(d);
    }

    // I might be wrong, but a double can basically represent any number since it will only be used as a consumer.
    // Meaning that this double is only used as a "view" and actions will not be applied to this double.
    // The worst that can happen is a number overflow when casting.
    private final double internalNum;

    private PrimitiveNumber(double internalNum) {
        this.internalNum = internalNum;
    }

    public byte asByte() {
        this.throwIfOutOfBounds(this.internalNum < Byte.MIN_VALUE || this.internalNum > Byte.MAX_VALUE, "byte");
        return (byte) this.internalNum;
    }

    public short asShort() {
        this.throwIfOutOfBounds(this.internalNum < Short.MIN_VALUE || this.internalNum > Short.MAX_VALUE, "short");
        return (short) this.internalNum;
    }

    public int asInt() {
        this.throwIfOutOfBounds(this.internalNum < Integer.MIN_VALUE || this.internalNum > Integer.MAX_VALUE, "int");
        return (int) this.internalNum;
    }

    public long asLong() {
        this.throwIfOutOfBounds(this.internalNum < Long.MIN_VALUE || this.internalNum > Long.MAX_VALUE, "long");
        return (long) this.internalNum;
    }

    public float asFloat() {
        this.throwIfOutOfBounds(this.internalNum < Float.MIN_VALUE || this.internalNum > Float.MAX_VALUE, "float");
        return (float) this.internalNum;
    }

    public double asDouble() {
        return this.internalNum;
    }

    private void throwIfOutOfBounds(boolean condition, String primitiveName) {
        if (condition) {
            throw new ArithmeticException("can not cast " + this.internalNum + " to " + primitiveName + " because it is out of bounds");
        }
    }

    @Override
    public String toString() {
        return String.valueOf(this.internalNum);
    }

}
