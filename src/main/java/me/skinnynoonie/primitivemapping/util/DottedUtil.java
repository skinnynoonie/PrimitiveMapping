package me.skinnynoonie.primitivemapping.util;

import me.skinnynoonie.primitivemapping.*;

import java.util.Arrays;
import java.util.Optional;
import java.util.function.Supplier;
import java.util.regex.Pattern;

/**
 * A basic class that supports null and null safety at the same time.
 * This class supports dot notation as directories for maps.
 * <p>For example: "one.two.three" would be in JSON: {@code {"one": {"two": {"three": "some value"}}}}
 */
public final class DottedUtil {

    /**
     * Gets an element from the path.
     *
     * @param map The map to retrieve from.
     * @param path The path to use.
     * @return The element nested at the end of the path.
     * @throws IllegalArgumentException If any arguments are null.
     */
    public static Optional<PrimitiveElement> get(PrimitiveMap map, String path) {
        if (map == null) {
            throw new IllegalArgumentException("map can not be null");
        }
        if (path == null) {
            throw new IllegalArgumentException("path can not be null");
        }

        String[] pathNodes = path.split(Pattern.quote("."));
        PrimitiveMap parentMap = map;

        for (String node : Arrays.copyOfRange(pathNodes, 0, pathNodes.length - 1)) {
            PrimitiveElement element = parentMap.get(node).orElse(null);
            if (element != null && element.isMap()) {
                parentMap = element.asMap();
                continue;
            }

            return Optional.empty();
        }

        PrimitiveElement lastElement = parentMap.get(pathNodes[pathNodes.length - 1]).orElse(null);
        return Optional.ofNullable(lastElement);
    }

    /**
     * Sets an element at the path.
     * If a map exists in the middle of the path, the map will be kept.
     * If any keys overlap this operation, then this operation will overwrite those keys.
     *
     * @param map The map to set.
     * @param path The path to use.
     * @param value The value to set at the end of the path.
     * @throws IllegalArgumentException If any arguments are null.
     */
    public static void set(PrimitiveMap map, String path, PrimitiveElement value) {
        if (map == null) {
            throw new IllegalArgumentException("map can not be null");
        }
        if (path == null) {
            throw new IllegalArgumentException("path can not be null");
        }
        if (value == null) {
            throw new IllegalArgumentException("value can not be null");
        }

        String[] pathNodes = path.split(Pattern.quote("."));
        PrimitiveMap parentMap = map;

        for (String node : Arrays.copyOfRange(pathNodes, 0, pathNodes.length - 1)) {
            PrimitiveElement element = parentMap.get(node).orElse(null);
            if (element != null && element.isMap()) {
                parentMap = element.asMap();
                continue;
            }

            PrimitiveMap overridingMap = PrimitiveMapImpl.createSynchronized();
            parentMap.put(node, overridingMap);
            parentMap = overridingMap;
        }

        parentMap.put(pathNodes[pathNodes.length - 1], value);
    }

    /**
     * Gets the element and casts the element if possible.
     * If the element at the end of the path is not of the elementClass, then null will be returned.
     */
    public static <T extends PrimitiveElement> T getAs(PrimitiveMap map, String path, Class<T> elementClass) {
        return get(map, path).filter(elementClass::isInstance).map(elementClass::cast).orElse(null);
    }

    /**
     * Gets the element and casts the element if possible.
     * If the element at the end of the path is not of the elementClass, then fallback will be returned.
     *
     * @throws NullPointerException If fallback is null. Use {@link #getAs(PrimitiveMap, String, Class)} instead.
     */
    public static <T extends PrimitiveElement> T getOr(PrimitiveMap map, String path, T fallback) {
        @SuppressWarnings("unchecked")
        Class<T> elementClass = (Class<T>) fallback.getClass();
        return get(map, path).filter(elementClass::isInstance).map(elementClass::cast).orElse(fallback);
    }

    /**
     * Sets the value at the end of the path to the element specified (the value parameter).
     * If the element specified is {@code null}, then it will be replaced with {@link PrimitiveNull}.
     */
    public static void setString(PrimitiveMap map, String path, String value) {
        set(map, path, value == null ? PrimitiveNull.create() : PrimitiveString.of(value));
    }

    public static String getString(PrimitiveMap map, String path) {
        return getStringOr(map, path, null);
    }

    public static String getStringOr(PrimitiveMap map, String path, String fallback) {
        return get(map, path).filter(PrimitiveElement::isString).map(PrimitiveElement::asString).orElse(fallback);
    }

    public static boolean getBoolean(PrimitiveMap map, String path) {
        return getBooleanOr(map, path, false);
    }

    public static boolean getBooleanOr(PrimitiveMap map, String path, boolean fallback) {
        return get(map, path).filter(PrimitiveElement::isBoolean).map(PrimitiveElement::asBoolean).orElse(fallback);
    }

    public static byte getByte(PrimitiveMap map, String path) {
        return getByteOr(map, path, (byte) 0);
    }

    public static byte getByteOr(PrimitiveMap map, String path, byte fallback) {
        return get(map, path).filter(PrimitiveElement::isNumber).flatMap(element -> emptyIfFail(element::asByte)).orElse(fallback);
    }

    public static short getShort(PrimitiveMap map, String path) {
        return getShortOr(map, path, (short) 0);
    }

    public static short getShortOr(PrimitiveMap map, String path, short fallback) {
        return get(map, path).filter(PrimitiveElement::isNumber).flatMap(element -> emptyIfFail(element::asShort)).orElse(fallback);
    }

    public static int getInt(PrimitiveMap map, String path) {
        return getIntOr(map, path, 0);
    }

    public static int getIntOr(PrimitiveMap map, String path, int fallback) {
        return get(map, path).filter(PrimitiveElement::isNumber).flatMap(element -> emptyIfFail(element::asInt)).orElse(fallback);
    }

    public static long getLong(PrimitiveMap map, String path) {
        return getLongOr(map, path, 0L);
    }

    public static long getLongOr(PrimitiveMap map, String path, long fallback) {
        return get(map, path).filter(PrimitiveElement::isNumber).flatMap(element -> emptyIfFail(element::asLong)).orElse(fallback);
    }

    public static float getFloat(PrimitiveMap map, String path) {
        return getFloatOr(map, path, 0.0f);
    }

    public static float getFloatOr(PrimitiveMap map, String path, float fallback) {
        return get(map, path).filter(PrimitiveElement::isNumber).flatMap(element -> emptyIfFail(element::asFloat)).orElse(fallback);
    }

    public static double getDouble(PrimitiveMap map, String path) {
        return getDoubleOr(map, path, 0.0);
    }

    public static double getDoubleOr(PrimitiveMap map, String path, double fallback) {
        return get(map, path).filter(PrimitiveElement::isNumber).flatMap(element -> emptyIfFail(element::asDouble)).orElse(fallback);
    }

    private static <T> Optional<T> emptyIfFail(Supplier<T> runnable) {
        try {
            return Optional.of(runnable.get());
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    private DottedUtil() {
        throw new UnsupportedOperationException("can not instantiate util class");
    }

}
