package me.skinnynoonie.primitivemapping.impl;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PrimitiveBooleanImplTest {

    @Test
    void metadataStoresData_addMetadataReturnsItself() {
        PrimitiveBooleanImpl primitiveBoolean = PrimitiveBooleanImpl.of(true);

        Object obj = new Object();
        assertSame(primitiveBoolean.addMetadata(obj), primitiveBoolean);
        assertTrue(primitiveBoolean.getMetadata(Object.class).filter(data -> data == obj).isPresent());
        assertFalse(primitiveBoolean.getMetadata(String.class).isPresent());
    }

    @Test
    void onlyAsBooleanReturnsItself() {
        PrimitiveBooleanImpl primitiveBoolean = PrimitiveBooleanImpl.of(true);
        assertTrue(primitiveBoolean.asBoolean().filter(bool -> bool == primitiveBoolean).isPresent());
        assertFalse(primitiveBoolean.asMap().isPresent());
        assertFalse(primitiveBoolean.asList().isPresent());
        assertFalse(primitiveBoolean.asNumber().isPresent());
        assertFalse(primitiveBoolean.asString().isPresent());
        assertFalse(primitiveBoolean.asNull().isPresent());
    }

    @Test
    void staticOfWorks_createsNewObjectNotReference_valueMethodWorks() {
        assertTrue(PrimitiveBooleanImpl.of(true).value());
        assertFalse(PrimitiveBooleanImpl.of(false).value());
        assertNotSame(PrimitiveBooleanImpl.of(true), PrimitiveBooleanImpl.of(true));
    }

}