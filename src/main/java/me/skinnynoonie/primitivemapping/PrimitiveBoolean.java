package me.skinnynoonie.primitivemapping;

public interface PrimitiveBoolean extends PrimitiveElement {

    @Override
    PrimitiveBoolean addMetadata(Object data);

    boolean value();

}
