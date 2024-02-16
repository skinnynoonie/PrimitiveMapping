package me.skinnynoonie.primitivemapping;

public class PrimitiveBoolean extends AbstractPrimitiveElement {

    public static PrimitiveBoolean of(boolean bool) {
        return new PrimitiveBoolean(bool);
    }

    private final boolean value;

    private PrimitiveBoolean(boolean value) {
        this.value = value;
    }

    @Override
    public boolean asBoolean() {
        return this.value;
    }

    @Override
    public boolean isBoolean() {
        return true;
    }

    @Override
    public String toString() {
        return String.valueOf(this.value);
    }

}
