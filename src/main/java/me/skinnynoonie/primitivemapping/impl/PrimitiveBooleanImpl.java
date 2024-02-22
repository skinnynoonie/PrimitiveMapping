package me.skinnynoonie.primitivemapping.impl;

import me.skinnynoonie.primitivemapping.PrimitiveBoolean;

public final class PrimitiveBooleanImpl extends AbstractPrimitiveElement implements PrimitiveBoolean {

    public static PrimitiveBooleanImpl of(boolean bool) {
        return new PrimitiveBooleanImpl(bool);
    }

    private final boolean value;

    private PrimitiveBooleanImpl(boolean value) {
        this.value = value;
    }

    @Override
    public PrimitiveBoolean addMetadata(Object data) {
        return (PrimitiveBoolean) super.addMetadata(data);
    }

    @Override
    public boolean value() {
        return this.value;
    }

    @Override
    public String toString() {
        return String.valueOf(this.value);
    }

}
