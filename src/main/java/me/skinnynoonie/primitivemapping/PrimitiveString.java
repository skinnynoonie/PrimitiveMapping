package me.skinnynoonie.primitivemapping;

public final class PrimitiveString extends AbstractPrimitiveElement {

    private final String string;

    public PrimitiveString(String string) {
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
