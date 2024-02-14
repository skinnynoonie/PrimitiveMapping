package me.skinnynoonie.primitivemapping;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

public final class PrimitiveMapImpl extends AbstractPrimitiveElement implements PrimitiveMap {

    public static PrimitiveMap createSynchronized() {
        Map<String, PrimitiveElement> syncMap = new ConcurrentHashMap<>();
        return new PrimitiveMapImpl(syncMap);
    }

    private final Map<String, PrimitiveElement> internalMap;

    private PrimitiveMapImpl(Map<String, PrimitiveElement> internalMap) {
        this.internalMap = internalMap;
    }

    @Override
    public PrimitiveMap asMap() {
        return this;
    }

    @Override
    public boolean isMap() {
        return true;
    }

    @Override
    public Optional<PrimitiveElement> get(String key) {
        if (key == null) {
            throw new IllegalArgumentException("key can not be null");
        }

        return Optional.ofNullable(this.internalMap.get(key));
    }

    @Override
    public void put(String key, PrimitiveElement value) {
        if (key == null) {
            throw new IllegalArgumentException("key can not be null");
        }

        if (value == null) {
            throw new IllegalArgumentException("value can not be null");
        }

        this.internalMap.put(key, value);
    }

    @Override
    public void delete(String key) {
        if (key == null) {
            throw new IllegalArgumentException("key can not be null");
        }

        this.internalMap.remove(key);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("{");
        for (String key : this.internalMap.keySet()) {
            sb.append("\"").append(key).append("\": ").append(this.internalMap.get(key)).append(", ");
        }
        sb.deleteCharAt(sb.length() - 1);
        sb.deleteCharAt(sb.length() - 1);
        sb.append("}");
        return sb.toString();
    }

}
