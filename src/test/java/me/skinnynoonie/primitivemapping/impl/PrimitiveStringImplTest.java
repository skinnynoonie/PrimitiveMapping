package me.skinnynoonie.primitivemapping.impl;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PrimitiveStringImplTest {

    @Test
    void metadataStoresData_addMetadataReturnsItself() {
        PrimitiveStringImpl primitiveString = PrimitiveStringImpl.of("a");
        Object obj = new Object();

        assertSame(primitiveString.addMetadata(obj), primitiveString);
        assertTrue(primitiveString.getMetadata(Object.class).filter(data -> data == obj).isPresent());
        assertFalse(primitiveString.getMetadata(String.class).isPresent());
    }

    @Test
    void onlyAsStringReturnsItself() {
        PrimitiveStringImpl primitiveString = PrimitiveStringImpl.of("a");

        assertTrue(primitiveString.asString().filter(string -> string == primitiveString).isPresent());
        assertFalse(primitiveString.asMap().isPresent());
        assertFalse(primitiveString.asList().isPresent());
        assertFalse(primitiveString.asNumber().isPresent());
        assertFalse(primitiveString.asBoolean().isPresent());
        assertFalse(primitiveString.asNull().isPresent());
    }

    @Test
    void staticConstructors_of_ofOrElse_workAndReturnNew() {
        assertNotSame(PrimitiveStringImpl.of("a"), PrimitiveStringImpl.of("a"));
        assertNotSame(PrimitiveStringImpl.ofOrElse("a", null), PrimitiveStringImpl.of("a"));
        assertNotSame(PrimitiveStringImpl.ofOrElse(null, "a"), PrimitiveStringImpl.of("a"));
    }

    @Test
    void value() {
        assertEquals("hi", PrimitiveStringImpl.of("hi").value());
        assertNotEquals("hiafsdasdfasdfasdfasdf", PrimitiveStringImpl.of("hi").value());
    }

    @Test
    void testEquals() {
        assertEquals(PrimitiveStringImpl.of("hi"), PrimitiveStringImpl.of("hi"));
        assertNotEquals(PrimitiveStringImpl.of("hi"), PrimitiveStringImpl.of("hello"));
    }

    @Test
    void testHashCode() {
        assertEquals(PrimitiveStringImpl.of("hi").hashCode(), "hi".hashCode());
        assertEquals(PrimitiveStringImpl.of("hiasfdasfd").hashCode(), "hiasfdasfd".hashCode());
        assertNotEquals(PrimitiveStringImpl.of("asf").hashCode(), "asdf".hashCode());
    }

}