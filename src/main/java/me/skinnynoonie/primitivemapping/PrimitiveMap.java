package me.skinnynoonie.primitivemapping;

import java.util.Collection;
import java.util.Optional;

/**
 * A map that has no null keys and is synchronized.
 */
public interface PrimitiveMap extends PrimitiveElement {

    @Override
    PrimitiveMap addMetadata(Object data);

    /**
     * Gets the element at with the key.
     * If the key does not exist, an empty Optional will be returned.
     * If the key does exist but the value is null (should be a null representation),
     * a null representation will be returned in an Optional instead.
     *
     * @param key The key of the value to get.
     * @return {@link Optional#empty()} if the entry does not exist, otherwise an Optional with either a null
     *         representation, or the element.
     * @throws IllegalArgumentException If any arguments are null.
     */
    Optional<PrimitiveElement> get(String key);

    /**
     * Gets all the keys associated with this map.
     *
     * @return The keys in a collection.
     */
    Collection<String> keySet();

    /**
     * Adds a value to this map.
     *
     * @param key The key of used to access the value.
     * @param value The value to add to the map.
     * @return This map.
     * @throws IllegalArgumentException If any arguments are null.
     */
    PrimitiveMap put(String key, PrimitiveElement value);

    /**
     * Deletes a key from this map.
     *
     * @param key The key to delete from the map.
     * @return This map.
     * @throws IllegalArgumentException If any arguments are null.
     */
    PrimitiveMap delete(String key);

}
