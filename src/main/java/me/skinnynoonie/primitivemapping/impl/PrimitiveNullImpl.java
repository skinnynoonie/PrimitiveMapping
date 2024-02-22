package me.skinnynoonie.primitivemapping.impl;

import me.skinnynoonie.primitivemapping.PrimitiveElement;
import me.skinnynoonie.primitivemapping.PrimitiveNull;

public final class PrimitiveNullImpl extends AbstractPrimitiveElement implements PrimitiveNull {

    public static PrimitiveNullImpl create() {
        return new PrimitiveNullImpl();
    }

    private PrimitiveNullImpl() {
    }

    @Override
    public String toString() {
        return "null";
    }

}
