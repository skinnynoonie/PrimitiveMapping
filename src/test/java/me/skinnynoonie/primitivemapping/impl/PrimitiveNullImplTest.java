package me.skinnynoonie.primitivemapping.impl;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PrimitiveNullImplTest {

    @Test
    void metadataStoresData_addMetadataReturnsItself() {
        PrimitiveNullImpl primitiveNull = PrimitiveNullImpl.create();
        Object obj = new Object();

        assertSame(primitiveNull.addMetadata(obj), primitiveNull);
        assertTrue(primitiveNull.getMetadata(Object.class).filter(data -> data == obj).isPresent());
        assertFalse(primitiveNull.getMetadata(String.class).isPresent());
    }

    @Test
    void onlyAsNullReturnsItself() {
        PrimitiveNullImpl primitiveNull = PrimitiveNullImpl.create();

        assertTrue(primitiveNull.asNull().filter(value -> value == primitiveNull).isPresent());
        assertFalse(primitiveNull.asMap().isPresent());
        assertFalse(primitiveNull.asList().isPresent());
        assertFalse(primitiveNull.asString().isPresent());
        assertFalse(primitiveNull.asNumber().isPresent());
        assertFalse(primitiveNull.asBoolean().isPresent());
    }

    @Test
    void staticCreateWorks_doesNotReturnReference() {
        assertNotSame(PrimitiveNullImpl.create(), PrimitiveNullImpl.create());
    }

}