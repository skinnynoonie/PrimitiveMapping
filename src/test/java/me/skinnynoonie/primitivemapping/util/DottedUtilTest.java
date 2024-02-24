package me.skinnynoonie.primitivemapping.util;

import me.skinnynoonie.primitivemapping.PrimitiveBoolean;
import me.skinnynoonie.primitivemapping.PrimitiveMap;
import me.skinnynoonie.primitivemapping.PrimitiveNull;
import me.skinnynoonie.primitivemapping.PrimitiveNumber;
import me.skinnynoonie.primitivemapping.PrimitiveString;
import me.skinnynoonie.primitivemapping.PrimitiveList;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DottedUtilTest {

    @Test
    @SuppressWarnings("ConstantConditions")
    void get_and_set_bothWork() {
        PrimitiveMap map = PrimitiveMap.createSynchronized()
                .put("hi", PrimitiveBoolean.ofTrue())
                .put("yo", PrimitiveNull.create())
                .put("lmfao", PrimitiveNumber.ofInt(1))
                .put("obj", PrimitiveMap.createSynchronized()
                        .put("nestedList", PrimitiveList.createSynchronized()
                                .add(PrimitiveNumber.ofDouble(5.0))
                                .add(PrimitiveMap.createSynchronized()
                                        .put("ONE_MORE_NESTED_LIST", PrimitiveList.createSynchronized()
                                                .add(PrimitiveNumber.ofDouble(555555555555.0))
                                                .add(PrimitiveString.of("Hi"))
                                        )
                                        .put("bool", PrimitiveBoolean.of(false))
                                )
                        )
                );

        assertTrue(DottedUtil.get(map, "hi").asBoolean().value());
        assertTrue(DottedUtil.get(map, "yo").isNull());
        assertEquals(1, DottedUtil.get(map, "lmfao").asNumber().asInt());

        assertTrue(DottedUtil.get(map, "obj").isMap());
        assertEquals(2, DottedUtil.get(map, "obj.nestedList").asList().size());
        assertEquals(2, DottedUtil.get(map, "obj.nestedList").asList().get(1).asMap().get("ONE_MORE_NESTED_LIST").asList().size());

        DottedUtil.set(map, "hi", PrimitiveBoolean.ofFalse());
        assertFalse(DottedUtil.get(map, "hi").asBoolean().value());

        DottedUtil.set(map, "obj.nestedList", PrimitiveNull.create());
        assertTrue(DottedUtil.get(map, "obj.nestedList").isNull());
    }

}