package me.skinnynoonie.primitivemapping;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

public abstract class AbstractPrimitiveElement implements PrimitiveElement {

    private final Map<Class<?>, Object> metadataMap;

    protected AbstractPrimitiveElement() {
        this(new ConcurrentHashMap<>());
    }

    protected AbstractPrimitiveElement(Map<Class<?>, Object> internalMetadataMap) {
        this.metadataMap = internalMetadataMap;
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T> Optional<T> getMetadata(Class<T> metadataClass) {
        if (metadataClass == null) {
            throw new IllegalArgumentException("metadataClass can not be null");
        }

        return Optional.ofNullable((T) this.metadataMap.get(metadataClass));
    }

    @Override
    public void setMetadata(Class<?> metadataClass, Object data) {
        if (metadataClass == null) {
            throw new IllegalArgumentException("metadataClass can not be null");
        }

        if (data == null) {
            throw new IllegalArgumentException("data can not be null");
        }

        if (data.getClass() != metadataClass) {
            throw new IllegalArgumentException("class of data and metadataClass are not the same");
        }

        this.metadataMap.put(metadataClass, data);
    }

    @Override
    public PrimitiveMap asMap() {
        throw new UnsupportedOperationException("can not convert element to a PrimitiveMap");
    }

    @Override
    public PrimitiveList asList() {
        throw new UnsupportedOperationException("can not convert element to a PrimitiveList");
    }

    @Override
    public String asString() {
        throw new UnsupportedOperationException("can not convert element to a String");
    }

    @Override
    public boolean asBoolean() {
        throw new UnsupportedOperationException("can not convert element to a boolean");
    }

    @Override
    public byte asByte() {
        throw new UnsupportedOperationException("can not convert element to a byte");
    }

    @Override
    public short asShort() {
        throw new UnsupportedOperationException("can not convert element to a short");
    }

    @Override
    public int asInt() {
        throw new UnsupportedOperationException("can not convert element to an int");
    }

    @Override
    public long asLong() {
        throw new UnsupportedOperationException("can not convert element to a long");
    }

    @Override
    public float asFloat() {
        throw new UnsupportedOperationException("can not convert element to a float");
    }

    @Override
    public double asDouble() {
        throw new UnsupportedOperationException("can not convert element to a double");
    }

    @Override
    public boolean isNull() {
        return false;
    }

    @Override
    public boolean isMap() {
        return false;
    }

    @Override
    public boolean isList() {
        return false;
    }

    @Override
    public boolean isString() {
        return false;
    }

    @Override
    public boolean isBoolean() {
        return false;
    }

    @Override
    public boolean isNumber() {
        return false;
    }

}
