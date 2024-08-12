package me.skinnynoonie.primitivemapping;

public abstract class AbstractPrimitiveElement<T extends PrimitiveElement> extends AbstractMetadataHolder<T> implements PrimitiveElement {

    protected AbstractPrimitiveElement() {
    }

    @Override
    public PrimitiveMap asMap() {
        try {
            return (PrimitiveMap) this;
        } catch (ClassCastException e) {
            throw new UnsupportedOperationException(e);
        }
    }

    @Override
    public PrimitiveList asList() {
        try {
            return (PrimitiveList) this;
        } catch (ClassCastException e) {
            throw new UnsupportedOperationException(e);
        }
    }

    @Override
    public PrimitiveString asString() {
        try {
            return (PrimitiveString) this;
        } catch (ClassCastException e) {
            throw new UnsupportedOperationException(e);
        }
    }

    @Override
    public PrimitiveBoolean asBoolean() {
        try {
            return (PrimitiveBoolean) this;
        } catch (ClassCastException e) {
            throw new UnsupportedOperationException(e);
        }
    }

    @Override
    public PrimitiveNull asNull() {
        try {
            return (PrimitiveNull) this;
        } catch (ClassCastException e) {
            throw new UnsupportedOperationException(e);
        }
    }

    @Override
    public PrimitiveNumber asNumber() {
        try {
            return (PrimitiveNumber) this;
        } catch (ClassCastException e) {
            throw new UnsupportedOperationException(e);
        }
    }

    @Override
    public boolean isMap() {
        return this instanceof PrimitiveMap;
    }

    @Override
    public boolean isList() {
        return this instanceof PrimitiveList;
    }

    @Override
    public boolean isString() {
        return this instanceof PrimitiveString;
    }

    @Override
    public boolean isBoolean() {
        return this instanceof PrimitiveBoolean;
    }

    @Override
    public boolean isNumber() {
        return this instanceof PrimitiveNumber;
    }

    @Override
    public boolean isNull() {
        return this instanceof PrimitiveNull;
    }

}
