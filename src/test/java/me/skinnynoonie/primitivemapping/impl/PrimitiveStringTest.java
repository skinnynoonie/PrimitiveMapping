package me.skinnynoonie.primitivemapping.impl;

import me.skinnynoonie.primitivemapping.PrimitiveString;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PrimitiveStringTest {

    @Test
    void metadataStoresData_addMetadataReturnsItself_hasMetadataWorks() {
        PrimitiveString primitive = PrimitiveString.of("TEST");
        Object randomData = new Object();

        assertSame(primitive.addMetadata(randomData), primitive);
        assertTrue(primitive.hasMetadata(Object.class));
        assertFalse(primitive.hasMetadata(String.class));
        assertSame(primitive.getMetadata(Object.class), randomData);
    }

    @Test
    void throwsUnsupportedOperationForImproperAsMethodUse_isMethodWorks() {
        PrimitiveString primitive = PrimitiveString.of("TEST");

        assertSame(primitive.asString(), primitive);
        assertThrowsExactly(UnsupportedOperationException.class, primitive::asMap);
        assertThrowsExactly(UnsupportedOperationException.class, primitive::asList);
        assertThrowsExactly(UnsupportedOperationException.class, primitive::asNumber);
        assertThrowsExactly(UnsupportedOperationException.class, primitive::asBoolean);
        assertThrowsExactly(UnsupportedOperationException.class, primitive::asNull);
        assertTrue(primitive.isString());
        assertFalse(primitive.isMap());
        assertFalse(primitive.isList());
        assertFalse(primitive.isNumber());
        assertFalse(primitive.isBoolean());
        assertFalse(primitive.isNull());
    }

    @Test
    void staticConstructors_of_ofOrElse_workAndReturnNew() {
        assertNotSame(PrimitiveString.of("a"), PrimitiveString.of("a"));
        assertNotSame(PrimitiveString.ofOrElse("a", null), PrimitiveString.of("a"));
        assertNotSame(PrimitiveString.ofOrElse(null, "a"), PrimitiveString.of("a"));
    }

    @Test
    void testEquals_value() {
        assertEquals("hi", PrimitiveString.of("hi").value());
        assertNotEquals("NOT HI", PrimitiveString.of("hi").value());
    }

    @Test
    void testHashCode() {
        assertEquals(PrimitiveString.of("hi").hashCode(), "hi".hashCode());
        assertEquals(PrimitiveString.of("TEST").hashCode(), "TEST".hashCode());
        assertNotEquals(PrimitiveString.of("TEST").hashCode(), "DIFFERENT".hashCode());
    }

}