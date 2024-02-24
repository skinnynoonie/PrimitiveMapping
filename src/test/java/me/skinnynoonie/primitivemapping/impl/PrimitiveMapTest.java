package me.skinnynoonie.primitivemapping.impl;

import me.skinnynoonie.primitivemapping.PrimitiveMap;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

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
    void get_keySet_putWorksAndReturnsItself_deleteWorksAndReturnsItself() {
        PrimitiveMap map = PrimitiveMap.createSynchronized();
        PrimitiveMap randData = PrimitiveMap.createSynchronized();

        assertEquals(0, map.keySet().size());

        assertSame(map.put("key", randData), map);
        assertSame(map.get("key"), randData);

        assertEquals(1, map.keySet().size());

        assertEquals("key", map.keySet().iterator().next());

        assertSame(map.delete("key"), map);
        assertEquals(0, map.keySet().size());
        assertNotSame(map.get("key"), randData);
    }

}