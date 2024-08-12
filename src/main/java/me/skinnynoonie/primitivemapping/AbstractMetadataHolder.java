package me.skinnynoonie.primitivemapping;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public abstract class AbstractMetadataHolder<T extends MetadataHolder> implements MetadataHolder {

    private final Map<Class<?>, Object> metadataMap;

    protected AbstractMetadataHolder() {
        this(new ConcurrentHashMap<>());
    }

    protected AbstractMetadataHolder(Map<Class<?>, Object> metadataMap) {
        this.metadataMap = metadataMap;
    }

    @Override
    @SuppressWarnings("unchecked")
    public <M> M getMetadata(Class<M> metadataClass) {
        return (M) this.metadataMap.get(metadataClass);
    }

    @Override
    public boolean hasMetadata(Class<?> metadataClass) {
        return this.metadataMap.containsKey(metadataClass);
    }

    @Override
    @SuppressWarnings("unchecked")
    public T addMetadata(Object metadata) {
        this.metadataMap.put(metadata.getClass(), metadata);
        return (T) this;
    }

}
