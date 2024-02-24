package me.skinnynoonie.primitivemapping;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

// This class is tightly coupled to the primitive elements, so it is package-private.
abstract class AbstractPrimitiveElement<T extends PrimitiveElement> implements PrimitiveElement {

    private final Map<Class<?>, Object> metadataMap;

    protected AbstractPrimitiveElement() {
        this(new ConcurrentHashMap<>());
    }

    protected AbstractPrimitiveElement(Map<Class<?>, Object> internalMetadataMap) {
        this.metadataMap = internalMetadataMap;
    }

    @Override
    @SuppressWarnings("unchecked")
    public <M> M getMetadata(Class<M> metadataClass) {
        if (metadataClass == null) {
            throw new IllegalArgumentException("metadataClass can not be null");
        }
        return (M) this.metadataMap.get(metadataClass);
    }

    @Override
    public boolean hasMetadata(Class<?> metadataClass) {
        return this.metadataMap.containsKey(metadataClass);
    }

    @Override
    @SuppressWarnings("unchecked")
    public T addMetadata(Object data) {
        if (data == null) {
            throw new IllegalArgumentException("data can not be null");
        }
        this.metadataMap.put(data.getClass(), data);
        return (T) this;
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
