package me.skinnynoonie.primitivemapping;

import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public final class PrimitiveMap extends AbstractPrimitiveElement<PrimitiveMap> implements PrimitiveElement {

    public static PrimitiveMap createSynchronized() {
        return new PrimitiveMap(new ConcurrentHashMap<>());
    }

    private final Map<PrimitiveString, PrimitiveElement> internalMap;

    private PrimitiveMap(Map<PrimitiveString, PrimitiveElement> internalMap) {
        this.internalMap = internalMap;
    }

    public PrimitiveElement get(String key) {
        return this.get(PrimitiveString.ofOrElse(key, null));
    }

    public PrimitiveElement get(PrimitiveString key) {
        if (key == null) {
            throw new IllegalArgumentException("key can not be null");
        }
        return this.internalMap.get(key);
    }

    public PrimitiveMap put(String key, PrimitiveElement value) {
        return this.putWithMetadata(key, value, null);
    }

    public PrimitiveMap putWithMetadata(String key, PrimitiveElement value, Object metadata) {
        if (key == null) {
            throw new IllegalArgumentException("key can not be null");
        } else if (value == null) {
            throw new IllegalArgumentException("value can not be null");
        }

        PrimitiveString primitiveKey = PrimitiveString.of(key);
        if (metadata != null) {
            primitiveKey.addMetadata(metadata);
        }

        this.internalMap.put(primitiveKey, value);
        return this;
    }

    public PrimitiveMap delete(String key) {
        if (key == null) {
            throw new IllegalArgumentException("key can not be null");
        }
        this.internalMap.remove(PrimitiveString.of(key));
        return this;
    }

    public Set<Map.Entry<PrimitiveString, PrimitiveElement>> entrySet() {
        return this.internalMap.entrySet();
    }

    public Collection<PrimitiveString> keySet() {
        return this.internalMap.keySet();
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
