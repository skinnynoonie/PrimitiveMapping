package me.skinnynoonie.primitivemapping;

public final class PrimitiveNull extends AbstractPrimitiveElement<PrimitiveNull> implements PrimitiveElement {

    public static PrimitiveNull create() {
        return new PrimitiveNull();
    }

    private PrimitiveNull() {
    }

    @Override
    public String toString() {
        return "null";
    }

}
