package me.skinnynoonie.primitivemapping;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public final class PrimitiveMap extends AbstractPrimitiveElement<PrimitiveMap> {

    public static PrimitiveMap createSynchronized() {
        return new PrimitiveMap(new ConcurrentHashMap<>());
    }

    private final Map<PrimitiveString, PrimitiveElement> internalMap;

    private PrimitiveMap(Map<PrimitiveString, PrimitiveElement> internalMap) {
        this.internalMap = internalMap;
    }

    public PrimitiveElement get(String key) {
        if (key == null) {
            throw new IllegalArgumentException("key can not be null");
        }

        return this.internalMap.get(PrimitiveString.of(key));
    }

    public Set<Map.Entry<PrimitiveString, PrimitiveElement>> entrySet() {
        return this.internalMap.entrySet();
    }

    public PrimitiveMap put(String key, PrimitiveElement value) {
        if (key == null) {
            throw new IllegalArgumentException("key can not be null");
        }

        if (value == null) {
            throw new IllegalArgumentException("value can not be null");
        }

        this.internalMap.put(PrimitiveString.of(key), value);
        return this;
    }

    public PrimitiveMap delete(String key) {
        if (key == null) {
            throw new IllegalArgumentException("key can not be null");
        }

        this.internalMap.remove(PrimitiveString.of(key));
        return this;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("{");

        for (PrimitiveString key : this.internalMap.keySet()) {
            sb.append("\"").append(key.value()).append("\": ").append(this.internalMap.get(key)).append(", ");
        }

        sb.deleteCharAt(sb.length() - 1);
        sb.deleteCharAt(sb.length() - 1);
        sb.append("}");

        return sb.toString();
    }

}
