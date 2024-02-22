package me.skinnynoonie.primitivemapping.impl;

import me.skinnynoonie.primitivemapping.PrimitiveNumber;

public final class PrimitiveNumberImpl extends AbstractPrimitiveElement implements PrimitiveNumber {

    public static PrimitiveNumberImpl parseString(String str) {
        return new PrimitiveNumberImpl(Double.parseDouble(str));
    }

    public static PrimitiveNumberImpl ofByte(byte b) {
        return new PrimitiveNumberImpl(b);
    }

    public static PrimitiveNumberImpl ofShort(short s) {
        return new PrimitiveNumberImpl(s);
    }

    public static PrimitiveNumberImpl ofInt(int i) {
        return new PrimitiveNumberImpl(i);
    }

    public static PrimitiveNumberImpl ofLong(long l) {
        return new PrimitiveNumberImpl(l);
    }

    public static PrimitiveNumberImpl ofFloat(float f) {
        return new PrimitiveNumberImpl(f);
    }

    public static PrimitiveNumberImpl ofDouble(double d) {
        return new PrimitiveNumberImpl(d);
    }

    // I might be wrong, but a double can basically represent any number since it will only be used as a consumer.
    // Meaning that this double is only used as a "view" and actions will not be applied to this double.
    // The worst that can happen is a number overflow when casting.
    private final double internalNum;

    private PrimitiveNumberImpl(double internalNum) {
        this.internalNum = internalNum;
    }

    @Override
    public PrimitiveNumber addMetadata(Object data) {
        return (PrimitiveNumber) super.addMetadata(data);
    }

    @Override
    public byte asByte() {
        this.throwIfOutOfBounds(this.internalNum < Byte.MIN_VALUE || this.internalNum > Byte.MAX_VALUE, "byte");
        return (byte) this.internalNum;
    }

    @Override
    public short asShort() {
        this.throwIfOutOfBounds(this.internalNum < Short.MIN_VALUE || this.internalNum > Short.MAX_VALUE, "short");
        return (short) this.internalNum;
    }

    @Override
    public int asInt() {
        this.throwIfOutOfBounds(this.internalNum < Integer.MIN_VALUE || this.internalNum > Integer.MAX_VALUE, "int");
        return (int) this.internalNum;
    }

    @Override
    public long asLong() {
        this.throwIfOutOfBounds(this.internalNum < Long.MIN_VALUE || this.internalNum > Long.MAX_VALUE, "long");
        return (long) this.internalNum;
    }

    @Override
    public float asFloat() {
        this.throwIfOutOfBounds(this.internalNum < Float.MIN_VALUE || this.internalNum > Float.MAX_VALUE, "float");
        return (float) this.internalNum;
    }

    @Override
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
