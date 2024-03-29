package me.skinnynoonie.primitivemapping;

public final class PrimitiveBoolean extends AbstractPrimitiveElement<PrimitiveBoolean> implements PrimitiveElement {

    public static PrimitiveBoolean of(boolean bool) {
        return new PrimitiveBoolean(bool);
    }

    public static PrimitiveBoolean ofTrue() {
        return of(true);
    }

    public static PrimitiveBoolean ofFalse() {
        return of(false);
    }

    private final boolean value;

    private PrimitiveBoolean(boolean value) {
        this.value = value;
    }

    public boolean value() {
        return this.value;
    }

    @Override
    public String toString() {
        return String.valueOf(this.value);
    }

}
