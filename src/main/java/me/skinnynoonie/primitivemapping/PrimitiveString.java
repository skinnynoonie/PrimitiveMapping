package me.skinnynoonie.primitivemapping;

public final class PrimitiveString extends AbstractPrimitiveElement<PrimitiveString> implements PrimitiveElement {

    public static PrimitiveString of(String str) {
        return new PrimitiveString(str);
    }

    private final String string;

    private PrimitiveString(String string) {
        this.string = string;
    }

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
        } else if (!(other instanceof PrimitiveString)) {
            return false;
        }

        PrimitiveString otherCasted = (PrimitiveString) other;

        return this.string.equals(otherCasted.string);
    }

    @Override
    public int hashCode() {
        return this.string.hashCode();
    }

}
