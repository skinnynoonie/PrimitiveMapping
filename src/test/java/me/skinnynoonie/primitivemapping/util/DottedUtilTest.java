package me.skinnynoonie.primitivemapping.util;

import me.skinnynoonie.primitivemapping.PrimitiveBoolean;
import me.skinnynoonie.primitivemapping.PrimitiveElement;
import me.skinnynoonie.primitivemapping.PrimitiveMap;
import me.skinnynoonie.primitivemapping.PrimitiveNull;
import me.skinnynoonie.primitivemapping.PrimitiveNumber;
import me.skinnynoonie.primitivemapping.PrimitiveString;
import me.skinnynoonie.primitivemapping.impl.PrimitiveBooleanImpl;
import me.skinnynoonie.primitivemapping.impl.PrimitiveListImpl;
import me.skinnynoonie.primitivemapping.impl.PrimitiveMapImpl;
import me.skinnynoonie.primitivemapping.impl.PrimitiveNullImpl;
import me.skinnynoonie.primitivemapping.impl.PrimitiveNumberImpl;
import me.skinnynoonie.primitivemapping.impl.PrimitiveStringImpl;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class DottedUtilTest {

    @Test
    void get_and_set_bothWork() {
        PrimitiveMapImpl map = PrimitiveMapImpl.createSynchronized()
                .put("hi", PrimitiveBooleanImpl.of(true))
                .put("yo", PrimitiveNullImpl.create())
                .put("lmfao", PrimitiveNumberImpl.ofInt(1))
                .put("obj", PrimitiveMapImpl.createSynchronized()
                        .put("nestedList", PrimitiveListImpl.createSynchronized()
                                .add(PrimitiveNumberImpl.ofDouble(5.0))
                                .add(PrimitiveMapImpl.createSynchronized()
                                        .put("ONE_MORE_NESTED_LIST", PrimitiveListImpl.createSynchronized()
                                                .add(PrimitiveNumberImpl.ofDouble(555555555555.0))
                                                .add(PrimitiveStringImpl.of("Hi"))
                                        )
                                        .put("bool", PrimitiveBooleanImpl.of(false))
                                )
                        )
                );

        assertTrue(DottedUtil.get(map, "hi").flatMap(PrimitiveElement::asBoolean).map(PrimitiveBoolean::value).orElse(false));
        assertTrue(DottedUtil.get(map, "yo").flatMap(PrimitiveElement::asNull).isPresent());
        assertEquals(1, DottedUtil.get(map, "lmfao").flatMap(PrimitiveElement::asNumber).map(PrimitiveNumber::asInt).orElse(50));
        assertTrue(DottedUtil.get(map, "obj.nestedList").flatMap(PrimitiveElement::asList).map(list -> list.get(0)).flatMap(PrimitiveElement::asNumber).isPresent());

        DottedUtil.set(map, "lmfao.nestedList", PrimitiveBooleanImpl.of(true));
        assertTrue(DottedUtil.get(map, "lmfao.nestedList").flatMap(PrimitiveElement::asBoolean).isPresent());
        assertFalse(DottedUtil.get(map, "lmfao.nestedList").flatMap(PrimitiveElement::asMap).isPresent());
    }

}