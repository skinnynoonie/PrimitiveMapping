package me.skinnynoonie.primitivemapping.impl;

import me.skinnynoonie.primitivemapping.PrimitiveBoolean;
import me.skinnynoonie.primitivemapping.PrimitiveElement;
import me.skinnynoonie.primitivemapping.PrimitiveList;
import me.skinnynoonie.primitivemapping.PrimitiveMap;
import me.skinnynoonie.primitivemapping.PrimitiveNull;
import me.skinnynoonie.primitivemapping.PrimitiveNumber;
import me.skinnynoonie.primitivemapping.PrimitiveString;

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
    public PrimitiveElement addMetadata(Object data) {
        if (data == null) {
            throw new IllegalArgumentException("data can not be null");
        }

        this.metadataMap.put(data.getClass(), data);
        return this;
    }

    @Override
    public Optional<PrimitiveMap> asMap() {
        return this instanceof PrimitiveMap ? Optional.of((PrimitiveMap) this) : Optional.empty();
    }

    @Override
    public Optional<PrimitiveList> asList() {
        return this instanceof PrimitiveList ? Optional.of((PrimitiveList) this) : Optional.empty();
    }

    @Override
    public Optional<PrimitiveString> asString() {
        return this instanceof PrimitiveString ? Optional.of((PrimitiveString) this) : Optional.empty();
    }

    @Override
    public Optional<PrimitiveBoolean> asBoolean() {
        return this instanceof PrimitiveBoolean ? Optional.of((PrimitiveBoolean) this) : Optional.empty();
    }

    @Override
    public Optional<PrimitiveNull> asNull() {
        return this instanceof PrimitiveNull ? Optional.of((PrimitiveNull) this) : Optional.empty();
    }

    @Override
    public Optional<PrimitiveNumber> asNumber() {
        return this instanceof PrimitiveNumber ? Optional.of((PrimitiveNumber) this) : Optional.empty();
    }

}
