package me.skinnynoonie.primitivemapping;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public final class PrimitiveMap extends AbstractPrimitiveElement<PrimitiveMap> {

    public static PrimitiveMap createSynchronized() {
        return new PrimitiveMap(new ConcurrentHashMap<>());
    }

    private final Map<String, PrimitiveElement> internalMap;

    private PrimitiveMap(Map<String, PrimitiveElement> internalMap) {
        this.internalMap = internalMap;
    }

    public PrimitiveElement get(String key) {
        if (key == null) {
            throw new IllegalArgumentException("key can not be null");
        }

        return this.internalMap.get(key);
    }

    public Collection<String> keySet() {
        return this.internalMap.keySet();
    }

    public PrimitiveMap put(String key, PrimitiveElement value) {
        if (key == null) {
            throw new IllegalArgumentException("key can not be null");
        }

        if (value == null) {
            throw new IllegalArgumentException("value can not be null");
        }

        this.internalMap.put(key, value);
        return this;
    }

    public PrimitiveMap delete(String key) {
        if (key == null) {
            throw new IllegalArgumentException("key can not be null");
        }

        this.internalMap.remove(key);
        return this;
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
