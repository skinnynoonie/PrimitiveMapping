package me.skinnynoonie.primitivemapping;

public interface PrimitiveString extends PrimitiveElement {

    @Override
    PrimitiveString addMetadata(Object data);

    String value();

}
