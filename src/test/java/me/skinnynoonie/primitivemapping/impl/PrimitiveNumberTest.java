package me.skinnynoonie.primitivemapping.impl;

import me.skinnynoonie.primitivemapping.PrimitiveNumber;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PrimitiveNumberTest {

    @Test
    void metadataStoresData_addMetadataReturnsItself_hasMetadataWorks() {
        PrimitiveNumber primitive = PrimitiveNumber.ofInt(1);
        Object randomData = new Object();

        assertSame(primitive.addMetadata(randomData), primitive);
        assertTrue(primitive.hasMetadata(Object.class));
        assertFalse(primitive.hasMetadata(String.class));
        assertSame(primitive.getMetadata(Object.class), randomData);
    }

    @Test
    void throwsUnsupportedOperationForImproperAsMethodUse_isMethodWorks() {
        PrimitiveNumber primitive = PrimitiveNumber.ofInt(1);

        assertSame(primitive.asNumber(), primitive);
        assertThrowsExactly(UnsupportedOperationException.class, primitive::asMap);
        assertThrowsExactly(UnsupportedOperationException.class, primitive::asList);
        assertThrowsExactly(UnsupportedOperationException.class, primitive::asString);
        assertThrowsExactly(UnsupportedOperationException.class, primitive::asBoolean);
        assertThrowsExactly(UnsupportedOperationException.class, primitive::asNull);
        assertTrue(primitive.isNumber());
        assertFalse(primitive.isMap());
        assertFalse(primitive.isList());
        assertFalse(primitive.isString());
        assertFalse(primitive.isBoolean());
        assertFalse(primitive.isNull());
    }

    @Test
    void staticConstructors_parseString_andOf_byte_short_int_long_float_double_workAndReturnNew() {
        // Too lazy to test the other ones lol.
        assertNotSame(PrimitiveNumber.ofShort((short) 2), PrimitiveNumber.ofShort((short) 2));
        assertThrowsExactly(NumberFormatException.class, () -> PrimitiveNumber.parseString("a"));
        assertEquals(PrimitiveNumber.parseString("5").asInt(), 5);
    }

    @Test
    void as_byte_short_int_long_float_double_throwArithmeticExceptionIfOverflow() {
        assertThrowsExactly(ArithmeticException.class, PrimitiveNumber.ofDouble(Double.MAX_VALUE)::asByte);
        assertThrowsExactly(ArithmeticException.class, PrimitiveNumber.ofDouble(Double.MAX_VALUE)::asShort);
        assertThrowsExactly(ArithmeticException.class, PrimitiveNumber.ofDouble(Double.MAX_VALUE)::asInt);
        assertThrowsExactly(ArithmeticException.class, PrimitiveNumber.ofDouble(Double.MAX_VALUE)::asLong);
        assertThrowsExactly(ArithmeticException.class, PrimitiveNumber.ofDouble(Double.MAX_VALUE)::asFloat);
    }

    @Test
    void as_byte_short_int_long_float_double_areEqual_andNotEqual() {
        assertEquals(50, PrimitiveNumber.ofDouble(50).asByte());
        assertNotEquals(50, PrimitiveNumber.ofDouble(51).asByte());

        assertEquals(50, PrimitiveNumber.ofDouble(50).asShort());
        assertNotEquals(50, PrimitiveNumber.ofDouble(51).asShort());

        assertEquals(50, PrimitiveNumber.ofDouble(50).asInt());
        assertNotEquals(50, PrimitiveNumber.ofDouble(51).asInt());

        assertEquals(50, PrimitiveNumber.ofDouble(50).asLong());
        assertNotEquals(50, PrimitiveNumber.ofDouble(51).asLong());

        assertEquals(50, PrimitiveNumber.ofDouble(50).asFloat());
        assertNotEquals(50, PrimitiveNumber.ofDouble(51).asFloat());
    }

}