package me.skinnynoonie.primitivemapping.impl;

import me.skinnynoonie.primitivemapping.PrimitiveNull;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PrimitiveNullTest {

    @Test
    void metadataStoresData_addMetadataReturnsItself_hasMetadataWorks() {
        PrimitiveNull primitive = PrimitiveNull.create();
        Object randomData = new Object();

        assertSame(primitive.addMetadata(randomData), primitive);
        assertTrue(primitive.hasMetadata(Object.class));
        assertFalse(primitive.hasMetadata(String.class));
        assertSame(primitive.getMetadata(Object.class), randomData);
    }

    @Test
    void throwsUnsupportedOperationForImproperAsMethodUse_isMethodWorks() {
        PrimitiveNull primitive = PrimitiveNull.create();

        assertSame(primitive.asNull(), primitive);
        assertThrowsExactly(UnsupportedOperationException.class, primitive::asMap);
        assertThrowsExactly(UnsupportedOperationException.class, primitive::asList);
        assertThrowsExactly(UnsupportedOperationException.class, primitive::asNumber);
        assertThrowsExactly(UnsupportedOperationException.class, primitive::asString);
        assertThrowsExactly(UnsupportedOperationException.class, primitive::asBoolean);
        assertTrue(primitive.isNull());
        assertFalse(primitive.isMap());
        assertFalse(primitive.isList());
        assertFalse(primitive.isNumber());
        assertFalse(primitive.isString());
        assertFalse(primitive.isBoolean());
    }

    @Test
    void staticCreateWorks_doesNotReturnReference() {
        assertNotSame(PrimitiveNull.create(), PrimitiveNull.create());
    }

}