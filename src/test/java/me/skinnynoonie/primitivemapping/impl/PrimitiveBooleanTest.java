package me.skinnynoonie.primitivemapping.impl;

import me.skinnynoonie.primitivemapping.PrimitiveBoolean;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PrimitiveBooleanTest {

    @Test
    void metadataStoresData_addMetadataReturnsItself_hasMetadataWorks() {
        PrimitiveBoolean primitive = PrimitiveBoolean.of(true);
        Object randomData = new Object();

        assertSame(primitive.addMetadata(randomData), primitive);
        assertTrue(primitive.hasMetadata(Object.class));
        assertFalse(primitive.hasMetadata(String.class));
        assertSame(primitive.getMetadata(Object.class), randomData);
    }

    @Test
    void throwsUnsupportedOperationForImproperAsMethodUse_isMethodWorks() {
        PrimitiveBoolean primitive = PrimitiveBoolean.of(true);

        assertSame(primitive.asBoolean(), primitive);
        assertThrowsExactly(UnsupportedOperationException.class, primitive::asMap);
        assertThrowsExactly(UnsupportedOperationException.class, primitive::asList);
        assertThrowsExactly(UnsupportedOperationException.class, primitive::asNumber);
        assertThrowsExactly(UnsupportedOperationException.class, primitive::asString);
        assertThrowsExactly(UnsupportedOperationException.class, primitive::asNull);
        assertTrue(primitive.isBoolean());
        assertFalse(primitive.isMap());
        assertFalse(primitive.isList());
        assertFalse(primitive.isNumber());
        assertFalse(primitive.isString());
        assertFalse(primitive.isNull());
    }

    @Test
    void staticOfWorks_createsNewObjectNotReference_valueMethodWorks() {
        assertTrue(PrimitiveBoolean.of(true).value());
        assertFalse(PrimitiveBoolean.of(false).value());
        assertNotSame(PrimitiveBoolean.of(true), PrimitiveBoolean.of(true));
    }

}