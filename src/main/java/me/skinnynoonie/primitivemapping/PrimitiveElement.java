package me.skinnynoonie.primitivemapping;

/**
 * Representation of a primitive element with loose rules (such as String, List, and Map).
 * All lists, maps, etc. are synchronized.
 */
public interface PrimitiveElement extends MetadataHolder {

    @Override
    PrimitiveElement addMetadata(Object metadata);

    PrimitiveMap asMap();

    PrimitiveList asList();

    PrimitiveString asString();

    PrimitiveBoolean asBoolean();

    PrimitiveNumber asNumber();

    PrimitiveNull asNull();

    boolean isMap();

    boolean isList();

    boolean isString();

    boolean isBoolean();

    boolean isNumber();

    boolean isNull();

    @Override
    String toString();

}
