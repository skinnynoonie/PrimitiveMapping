package me.skinnynoonie.primitivemapping.impl;

import me.skinnynoonie.primitivemapping.PrimitiveBoolean;
import me.skinnynoonie.primitivemapping.PrimitiveList;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PrimitiveListTest {

    @Test
    void metadataStoresData_addMetadataReturnsItself_hasMetadataWorks() {
        PrimitiveList primitive = PrimitiveList.createSynchronized();
        Object randomData = new Object();

        assertSame(primitive.addMetadata(randomData), primitive);
        assertTrue(primitive.hasMetadata(Object.class));
        assertFalse(primitive.hasMetadata(String.class));
        assertSame(primitive.getMetadata(Object.class), randomData);
    }

    @Test
    void throwsUnsupportedOperationForImproperAsMethodUse_isMethodWorks() {
        PrimitiveList primitive = PrimitiveList.createSynchronized();

        assertSame(primitive.asList(), primitive);
        assertThrowsExactly(UnsupportedOperationException.class, primitive::asMap);
        assertThrowsExactly(UnsupportedOperationException.class, primitive::asNumber);
        assertThrowsExactly(UnsupportedOperationException.class, primitive::asString);
        assertThrowsExactly(UnsupportedOperationException.class, primitive::asBoolean);
        assertThrowsExactly(UnsupportedOperationException.class, primitive::asNull);
        assertTrue(primitive.isList());
        assertFalse(primitive.isMap());
        assertFalse(primitive.isNumber());
        assertFalse(primitive.isString());
        assertFalse(primitive.isBoolean());
        assertFalse(primitive.isNull());
    }

    @Test
    void staticCreateSynchronizedWorks_createsNewObjectNotReference() {
        assertNotSame(PrimitiveList.createSynchronized(), PrimitiveList.createSynchronized());
    }

    @Test
    void get_size_addWorksAndReturnsItself_contains_remove() {
        PrimitiveList primitiveList = PrimitiveList.createSynchronized();
        PrimitiveList randData = PrimitiveList.createSynchronized();

        assertEquals(0, primitiveList.size());

        assertSame(primitiveList.add(randData), primitiveList);
        assertEquals(1, primitiveList.size());
        assertSame(randData, primitiveList.get(0));

        assertTrue(primitiveList.contains(randData));
        assertFalse( primitiveList.contains(PrimitiveBoolean.of(false)) );

        primitiveList.remove(randData);

        assertEquals(0, primitiveList.size());
    }

}