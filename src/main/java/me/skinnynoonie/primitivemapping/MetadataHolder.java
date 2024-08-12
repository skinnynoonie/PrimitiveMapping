package me.skinnynoonie.primitivemapping;

public interface MetadataHolder {

    /**
     * Gets metadata from this holder.
     *
     * @param metadataClass The metadata class used to retrieve the metadata.
     * @return Metadata of type metadataClass, or null if no metadata was found.
     */
    <M> M getMetadata(Class<M> metadataClass);

    /**
     * Checks if this holder contains metadata from the provided class.
     *
     * @param metadataClass The class of the metadata to check.
     * @return true if the metadata exists, otherwise false.
     */
    boolean hasMetadata(Class<?> metadataClass);

    /**
     * Adds metadata to this holder.
     *
     * @param metadata The metadata to add to this holder.
     * @return This holder.
     */
    MetadataHolder addMetadata(Object metadata);

}
