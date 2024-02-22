package me.skinnynoonie.primitivemapping.impl;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PrimitiveListImplTest {

    @Test
    void metadataStoresData_addMetadataReturnsItself() {
        PrimitiveListImpl primitiveList = PrimitiveListImpl.createSynchronized();
        Object obj = new Object();

        assertSame(primitiveList.addMetadata(obj), primitiveList);
        assertTrue(primitiveList.getMetadata(Object.class).filter(data -> data == obj).isPresent());
        assertFalse(primitiveList.getMetadata(String.class).isPresent());
    }

    @Test
    void onlyAsListReturnsItself() {
        PrimitiveListImpl primitiveList = PrimitiveListImpl.createSynchronized();

        assertTrue(primitiveList.asList().filter(list -> list == primitiveList).isPresent());
        assertFalse(primitiveList.asBoolean().isPresent());
        assertFalse(primitiveList.asMap().isPresent());
        assertFalse(primitiveList.asNumber().isPresent());
        assertFalse(primitiveList.asString().isPresent());
        assertFalse(primitiveList.asNull().isPresent());
    }

    @Test
    void staticCreateSynchronizedWorks_createsNewObjectNotReference() {
        assertNotSame(PrimitiveListImpl.createSynchronized(), PrimitiveListImpl.createSynchronized());
    }

    @Test
    void get_size_addWorksAndReturnsItself_contains_remove() {
        PrimitiveListImpl primitiveList = PrimitiveListImpl.createSynchronized();
        PrimitiveListImpl randData = PrimitiveListImpl.createSynchronized();

        assertEquals(0, primitiveList.size());
        assertSame(primitiveList.add(randData), primitiveList);
        assertEquals(1, primitiveList.size());
        assertSame(randData, primitiveList.get(0));
        assertTrue(primitiveList.contains(randData));

        primitiveList.remove(randData);

        assertEquals(0, primitiveList.size());
    }

}