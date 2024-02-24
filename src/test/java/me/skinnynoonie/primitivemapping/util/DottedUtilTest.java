package me.skinnynoonie.primitivemapping.util;

import me.skinnynoonie.primitivemapping.*;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class DottedUtilTest {

    PrimitiveMap map = PrimitiveMap.createSynchronized()
            .put("hi", PrimitiveBoolean.ofTrue())
            .put("yo", PrimitiveNull.create())
            .put("someNumber", PrimitiveNumber.ofInt(1))
            .put("obj", PrimitiveMap.createSynchronized()
                    .put("nestedList", PrimitiveList.createSynchronized()
                            .add(PrimitiveNumber.ofDouble(5.0))
                            .add(PrimitiveNull.create())
                    ).put("nestedMap", PrimitiveMap.createSynchronized()
                            .put("nestedBoolean", PrimitiveBoolean.ofTrue())
                    )
            )
            .put("bool", PrimitiveBoolean.ofTrue())
            .put("byte", PrimitiveNumber.ofByte((byte) 1))
            .put("short", PrimitiveNumber.ofShort((short) 1))
            .put("int", PrimitiveNumber.ofInt(1))
            .put("long", PrimitiveNumber.ofLong(1L))
            .put("float", PrimitiveNumber.ofFloat(1.0f))
            .put("double", PrimitiveNumber.ofDouble(Double.MAX_VALUE))
            .put("str", PrimitiveString.of("hi"));

    @Test
    @Order(1)
    @SuppressWarnings("ConstantConditions")
    void get() {
        assertTrue(DottedUtil.get(map, "hi").asBoolean().value());
        assertTrue(DottedUtil.get(map, "yo").isNull());
        assertEquals(1, DottedUtil.get(map, "someNumber").asNumber().asInt());

        assertTrue(DottedUtil.get(map, "obj").isMap());
        assertEquals(2, DottedUtil.get(map, "obj.nestedList").asList().size());
        assertTrue(DottedUtil.get(map, "obj.nestedList").asList().get(1).isNull());
    }

    @Test
    @Order(2)
    @SuppressWarnings("all")
    void getMap_getList() {
        assertEquals(2, DottedUtil.getList(map, "obj.nestedList").get().size());
        assertTrue(DottedUtil.getMap(map, "obj.nestedMap").get().get("nestedBoolean").asBoolean().value());
    }

    @Test
    @Order(3)
    @SuppressWarnings("ConstantConditions")
    void set() {
        DottedUtil.set(map, "hi", PrimitiveBoolean.ofFalse());
        assertFalse(DottedUtil.get(map, "hi").asBoolean().value());

        DottedUtil.set(map, "obj.nestedList", PrimitiveNull.create());
        assertTrue(DottedUtil.get(map, "obj.nestedList").isNull());
    }

    @Test
    void getString() {
        assertEquals(DottedUtil.getString(map, "str").orElse(null), "hi");
    }

    @Test
    void getByte() {
        assertEquals((byte) 1, DottedUtil.getByte(map, "byte").orElse((byte) 100));
        assertEquals((byte) 100, DottedUtil.getByte(map, "double").orElse((byte) 100));
    }

    @Test
    void getShort() {
        assertEquals((short) 1, DottedUtil.getShort(map, "short").orElse((short) 100));
        assertEquals((short) 100, DottedUtil.getShort(map, "double").orElse((short) 100));
    }

    @Test
    void getInt() {
        assertEquals(1, DottedUtil.getInt(map, "int").orElse(100));
        assertEquals(100, DottedUtil.getInt(map, "double").orElse(100));
    }

    @Test
    void getLong() {
        assertEquals(1L, DottedUtil.getLong(map, "long").orElse(100L));
        assertEquals(100L, DottedUtil.getLong(map, "double").orElse(100L));
    }

    @Test
    void getFloat() {
        assertEquals(1.0f, DottedUtil.getFloat(map, "float").orElse(100.0f));
        assertEquals(100.0f, DottedUtil.getFloat(map, "double").orElse(100.0f));
    }

    @Test
    void getDouble() {
        assertEquals(Double.MAX_VALUE, DottedUtil.getDouble(map, "double").orElse(100.0));
    }

    @Test
    void getBoolean() {
        assertTrue(DottedUtil.getBoolean(map, "bool").orElse(false));
        assertFalse(DottedUtil.getBoolean(map, "str").orElse(false));
    }

}