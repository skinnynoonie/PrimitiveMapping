package me.skinnynoonie.primitivemapping;

import java.util.Optional;

/**
 * Representation of a primitive element with loose rules (such as String, List, and Map).
 * All lists, maps, etc. are synchronized.
 */
public interface PrimitiveElement {

    /**
     * Gets the metadata attached to this element by the class.
     * The metadata class will be the <b>same</b> as the data Object.
     *
     * @param metadataClass The class of the data class.
     * @return The metadata associated with the metadataClass in an {@code Optional}.
     * @throws IllegalArgumentException If any arguments are null.
     */
    <M> Optional<M> getMetadata(Class<M> metadataClass);

    /**
     * Sets a metadata value for this element, which can be retrieved with {@link #getMetadata(Class)}.
     * The metadata will be retrieved using a hard reference to the data's class.
     *
     * @param data The data to attach to this element.
     * @throws IllegalArgumentException If any arguments are null.
     */
    PrimitiveElement addMetadata(Object data);

    Optional<PrimitiveMap> asMap();

    Optional<PrimitiveList> asList();

    Optional<PrimitiveString> asString();

    Optional<PrimitiveBoolean> asBoolean();

    Optional<PrimitiveNumber> asNumber();

    Optional<PrimitiveNull> asNull();

    @Override
    String toString();

}
