package me.skinnynoonie.primitivemapping.impl;

import me.skinnynoonie.primitivemapping.PrimitiveString;

import java.util.Objects;

public final class PrimitiveStringImpl extends AbstractPrimitiveElement implements PrimitiveString {

    public static PrimitiveStringImpl of(String str) {
        if (str == null) {
            throw new IllegalArgumentException("string can not be null");
        }

        return new PrimitiveStringImpl(str);
    }

    public static PrimitiveStringImpl ofOrElse(String str, String fallback) {
        return of(str == null ? fallback : str);
    }

    private final String string;

    private PrimitiveStringImpl(String string) {
        this.string = string;
    }

    @Override
    public PrimitiveString addMetadata(Object data) {
        return (PrimitiveString) super.addMetadata(data);
    }

    @Override
    public String value() {
        return this.string;
    }

    @Override
    public String toString() {
        return '"' + this.string + '"';
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }

        if (!(other instanceof PrimitiveStringImpl)) {
            return false;
        }

        PrimitiveStringImpl otherCasted = (PrimitiveStringImpl) other;

        return Objects.equals(this.string, otherCasted.string);
    }

    @Override
    public int hashCode() {
        return this.string != null ? this.string.hashCode() : 0;
    }

}
