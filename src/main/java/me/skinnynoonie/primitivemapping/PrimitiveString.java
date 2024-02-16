package me.skinnynoonie.primitivemapping;

public final class PrimitiveString extends AbstractPrimitiveElement {

    public static PrimitiveString of(String str) {
        if (str == null) {
            throw new IllegalArgumentException("string can not be null");
        }

        return new PrimitiveString(str);
    }

    public static PrimitiveString ofOrElse(String str, String fallback) {
        return of(str == null ? fallback : str);
    }

    private final String string;

    private PrimitiveString(String string) {
        this.string = string;
    }

    @Override
    public boolean isString() {
        return true;
    }

    @Override
    public String asString() {
        return this.string;
    }

    @Override
    public byte asByte() {
        return Byte.parseByte(this.string);
    }

    @Override
    public short asShort() {
        return Short.parseShort(this.string);
    }

    @Override
    public int asInt() {
        return Integer.parseInt(this.string);
    }

    @Override
    public long asLong() {
        return Long.parseLong(this.string);
    }

    @Override
    public float asFloat() {
        return Float.parseFloat(this.string);
    }

    @Override
    public double asDouble() {
        return Double.parseDouble(this.string);
    }

    @Override
    public String toString() {
        return '"' + this.string + '"';
    }

}
