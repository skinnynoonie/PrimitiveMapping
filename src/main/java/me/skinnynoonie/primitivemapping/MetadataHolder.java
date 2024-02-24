package me.skinnynoonie.primitivemapping;

public interface MetadataHolder {

    /**
     * Gets metadata from a Class.
     * The metadata will be the same class as the input metadata class (no abstraction).
     *
     * @param metadataClass The metadata class used to retrieve the metadata.
     * @return The metadata from the input metadata class. This can return null if the metadata does not exist.
     */
    <M> M getMetadata(Class<M> metadataClass);

    /**
     * Checks if the metadata associated with the metadata class is not null (if it exists).
     *
     * @param metadataClass Check if metadata associated with this class exists.
     * @return true if the metadata class has metadata, otherwise false.
     */
    boolean hasMetadata(Class<?> metadataClass);

    /**
     * Adds metadata to this holder.
     * The "key" to get this metadata will be the lowest-level-class of the input Object.
     *
     * @param data The metadata.
     * @return This metadata holder (for ease of use).
     */
    MetadataHolder addMetadata(Object data);

}
