package me.skinnynoonie.primitivemapping;

public final class PrimitiveNull extends AbstractPrimitiveElement implements PrimitiveElement {

    public static PrimitiveNull create() {
        return new PrimitiveNull();
    }

    private PrimitiveNull() {
    }

    @Override
    public boolean isNull() {
        return true;
    }

    @Override
    public String toString() {
        return "null";
    }

}
