package me.skinnynoonie.primitivemapping;

public final class PrimitiveBoolean extends AbstractPrimitiveElement<PrimitiveBoolean> {

    public static PrimitiveBoolean of(boolean bool) {
        return new PrimitiveBoolean(bool);
    }

    public static PrimitiveBoolean ofTrue() {
        return of(true);
    }

    public static PrimitiveBoolean ofFalse() {
        return of(false);
    }

    private final boolean bool;

    private PrimitiveBoolean(boolean bool) {
        this.bool = bool;
    }

    public boolean value() {
        return this.bool;
    }

    @Override
    public String toString() {
        return String.valueOf(this.bool);
    }

}
