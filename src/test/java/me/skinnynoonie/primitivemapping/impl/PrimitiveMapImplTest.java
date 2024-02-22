package me.skinnynoonie.primitivemapping.impl;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PrimitiveMapImplTest {

    @Test
    void metadataStoresData_addMetadataReturnsItself() {
        PrimitiveMapImpl primitiveMap = PrimitiveMapImpl.createSynchronized();
        Object obj = new Object();

        assertSame(primitiveMap.addMetadata(obj), primitiveMap);
        assertTrue(primitiveMap.getMetadata(Object.class).filter(data -> data == obj).isPresent());
        assertFalse(primitiveMap.getMetadata(String.class).isPresent());
    }

    @Test
    void onlyAsMapReturnsItself() {
        PrimitiveMapImpl primitiveMap = PrimitiveMapImpl.createSynchronized();

        assertTrue(primitiveMap.asMap().filter(map -> map == primitiveMap).isPresent());
        assertFalse(primitiveMap.asBoolean().isPresent());
        assertFalse(primitiveMap.asList().isPresent());
        assertFalse(primitiveMap.asNumber().isPresent());
        assertFalse(primitiveMap.asString().isPresent());
    }

    @Test
    void staticCreateSynchronizedWorks_createsNewObjectNotReference() {
        assertNotSame(PrimitiveMapImpl.createSynchronized(), PrimitiveMapImpl.createSynchronized());
    }

    @Test
    void get_keySet_putWorksAndReturnsItself_deleteWorksAndReturnsItself() {
        PrimitiveMapImpl map = PrimitiveMapImpl.createSynchronized();
        PrimitiveMapImpl randData = PrimitiveMapImpl.createSynchronized();

        assertEquals(0, map.keySet().size());
        assertSame(map.put("key", randData), map);
        assertTrue(map.get("key").filter(value -> value == randData).isPresent());
        assertEquals(1, map.keySet().size());
        assertEquals("key", map.keySet().iterator().next());
        assertSame(map.delete("key"), map);
        assertEquals(0, map.keySet().size());
        assertFalse(map.get("key").isPresent());
        assertFalse(map.asNull().isPresent());
    }

}