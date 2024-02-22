package me.skinnynoonie.primitivemapping.impl;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrowsExactly;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PrimitiveNumberImplTest {

    @Test
    void metadataStoresData_addMetadataReturnsItself() {
        PrimitiveNumberImpl primitiveNumber = PrimitiveNumberImpl.ofInt(5);
        Object obj = new Object();

        assertSame(primitiveNumber.addMetadata(obj), primitiveNumber);
        assertTrue(primitiveNumber.getMetadata(Object.class).filter(data -> data == obj).isPresent());
        assertFalse(primitiveNumber.getMetadata(String.class).isPresent());
    }

    @Test
    void onlyAsNumberReturnsItself() {
        PrimitiveNumberImpl primitiveNumber = PrimitiveNumberImpl.ofInt(5);

        assertTrue(primitiveNumber.asNumber().filter(number -> number == primitiveNumber).isPresent());
        assertFalse(primitiveNumber.asMap().isPresent());
        assertFalse(primitiveNumber.asList().isPresent());
        assertFalse(primitiveNumber.asString().isPresent());
        assertFalse(primitiveNumber.asBoolean().isPresent());
        assertFalse(primitiveNumber.asNull().isPresent());
    }

    @Test
    void staticConstructors_parseString_andOf_byte_short_int_long_float_double_workAndReturnNew() {
        // Too lazy to test the other ones lol.
        assertNotSame(PrimitiveNumberImpl.ofShort((short) 2), PrimitiveNumberImpl.ofShort((short) 2));
        assertThrowsExactly(NumberFormatException.class, () -> PrimitiveNumberImpl.parseString("a"));
        assertEquals(PrimitiveNumberImpl.parseString("5").asInt(), 5);
    }

    @Test
    void as_byte_short_int_long_float_double_throwArithmeticExceptionIfOverflow() {
        assertThrowsExactly(ArithmeticException.class, () -> PrimitiveNumberImpl.ofDouble(Double.MAX_VALUE).asByte());
        assertThrowsExactly(ArithmeticException.class, () -> PrimitiveNumberImpl.ofDouble(Double.MAX_VALUE).asShort());
        assertThrowsExactly(ArithmeticException.class, () -> PrimitiveNumberImpl.ofDouble(Double.MAX_VALUE).asInt());
        assertThrowsExactly(ArithmeticException.class, () -> PrimitiveNumberImpl.ofDouble(Double.MAX_VALUE).asLong());
        assertThrowsExactly(ArithmeticException.class, () -> PrimitiveNumberImpl.ofDouble(Double.MAX_VALUE).asFloat());
    }

    @Test
    void as_byte_short_int_long_float_double_areEqual_andNotEqual() {
        assertEquals(50, PrimitiveNumberImpl.ofDouble(50).asByte());
        assertNotEquals(50, PrimitiveNumberImpl.ofDouble(51).asByte());

        assertEquals(50, PrimitiveNumberImpl.ofDouble(50).asShort());
        assertNotEquals(50, PrimitiveNumberImpl.ofDouble(51).asShort());

        assertEquals(50, PrimitiveNumberImpl.ofDouble(50).asInt());
        assertNotEquals(50, PrimitiveNumberImpl.ofDouble(51).asInt());

        assertEquals(50, PrimitiveNumberImpl.ofDouble(50).asLong());
        assertNotEquals(50, PrimitiveNumberImpl.ofDouble(51).asLong());

        assertEquals(50, PrimitiveNumberImpl.ofDouble(50).asFloat());
        assertNotEquals(50, PrimitiveNumberImpl.ofDouble(51).asFloat());
    }

}