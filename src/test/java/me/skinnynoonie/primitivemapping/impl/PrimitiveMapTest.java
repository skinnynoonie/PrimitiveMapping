package me.skinnynoonie.primitivemapping.impl;

import me.skinnynoonie.primitivemapping.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrowsExactly;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PrimitiveMapTest {

    @Test
    void metadataStoresData_addMetadataReturnsItself_hasMetadataWorks() {
        PrimitiveMap primitive = PrimitiveMap.createSynchronized();
        Object randomData = new Object();

        assertSame(primitive.addMetadata(randomData), primitive);
        assertTrue(primitive.hasMetadata(Object.class));
        assertFalse(primitive.hasMetadata(String.class));
        assertSame(primitive.getMetadata(Object.class), randomData);
    }

    @Test
    void put_putWithMetadataStoresMetadata_returnsItself() {
        PrimitiveMap primitiveMap = PrimitiveMap.createSynchronized();
        Object randomData = new Object();

        assertSame(primitiveMap, primitiveMap.putWithMetadata("null", PrimitiveNull.create(), randomData));
        assertSame(primitiveMap, primitiveMap.put(PrimitiveString.of("one"), PrimitiveNumber.ofInt(1)));

        PrimitiveElement key = primitiveMap.keySet().iterator().next();

        assertTrue(key.hasMetadata(Object.class));
        assertSame(randomData, key.getMetadata(Object.class));

        assertFalse(key.hasMetadata(String.class));
        assertNull(primitiveMap.getMetadata(String.class));
    }

    @Test
    void throwsUnsupportedOperationForImproperAsMethodUse_isMethodWorks() {
        PrimitiveMap primitive = PrimitiveMap.createSynchronized();

        assertSame(primitive.asMap(), primitive);
        assertThrowsExactly(UnsupportedOperationException.class, primitive::asList);
        assertThrowsExactly(UnsupportedOperationException.class, primitive::asNumber);
        assertThrowsExactly(UnsupportedOperationException.class, primitive::asString);
        assertThrowsExactly(UnsupportedOperationException.class, primitive::asBoolean);
        assertThrowsExactly(UnsupportedOperationException.class, primitive::asNull);
        assertTrue(primitive.isMap());
        assertFalse(primitive.isList());
        assertFalse(primitive.isNumber());
        assertFalse(primitive.isString());
        assertFalse(primitive.isBoolean());
        assertFalse(primitive.isNull());
    }

    @Test
    void staticCreateSynchronizedWorks_createsNewObjectNotReference() {
        assertNotSame(PrimitiveMap.createSynchronized(), PrimitiveMap.createSynchronized());
    }

    @Test
    void get_keySet_entrySet_putWorksAndReturnsItself_deleteWorksAndReturnsItself() {
        PrimitiveMap map = PrimitiveMap.createSynchronized();
        PrimitiveMap randData = PrimitiveMap.createSynchronized();

        assertEquals(0, map.entrySet().size());
        assertEquals(0, map.keySet().size());

        assertSame(map.put("key", randData), map);
        assertSame(map.get("key"), randData);

        assertEquals(1, map.entrySet().size());
        assertEquals(1, map.keySet().size());

        assertEquals("key", map.entrySet().iterator().next().getKey().value());

        assertSame(map.delete("key"), map);
        assertEquals(0, map.entrySet().size());
        assertEquals(0, map.keySet().size());
        assertNotSame(map.get("key"), randData);
    }

}