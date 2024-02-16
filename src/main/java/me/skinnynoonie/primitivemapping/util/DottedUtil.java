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
     * If the value is {@code null}, then it will be replaced with a {@link PrimitiveNull}.
     *
     * @param map The map to set.
     * @param path The path to use.
     * @param value The value to set at the end of the path.
     * @throws IllegalArgumentException If the map or path is null.
     */
    public static void set(PrimitiveMap map, String path, PrimitiveElement value) {
        if (map == null) {
            throw new IllegalArgumentException("map can not be null");
        }
        if (path == null) {
            throw new IllegalArgumentException("path can not be null");
        }
        if (value == null) {
            value = PrimitiveNull.create();
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
    public static <T extends PrimitiveElement> Optional<T> getAs(PrimitiveMap map, String path, Class<T> elementClass) {
        return get(map, path).filter(elementClass::isInstance).map(elementClass::cast);
    }

    public static Optional<String> getString(PrimitiveMap map, String path) {
        return getAs(map, path, PrimitiveString.class).map(PrimitiveString::asString);
    }

    public static Optional<Boolean> getBoolean(PrimitiveMap map, String path) {
        return getAs(map, path, PrimitiveBoolean.class).map(PrimitiveElement::asBoolean);
    }

    public static Optional<Byte> getByte(PrimitiveMap map, String path) {
        return getAs(map, path, PrimitiveNumber.class).flatMap(element -> emptyIfFail(element::asByte));
    }

    public static Optional<Short> getShort(PrimitiveMap map, String path) {
        return getAs(map, path, PrimitiveNumber.class).flatMap(element -> emptyIfFail(element::asShort));
    }

    public static Optional<Integer> getInt(PrimitiveMap map, String path) {
        return getAs(map, path, PrimitiveNumber.class).flatMap(element -> emptyIfFail(element::asInt));
    }

    public static Optional<Long> getLong(PrimitiveMap map, String path) {
        return getAs(map, path, PrimitiveNumber.class).flatMap(element -> emptyIfFail(element::asLong));
    }

    public static Optional<Float> getFloat(PrimitiveMap map, String path) {
        return getAs(map, path, PrimitiveNumber.class).flatMap(element -> emptyIfFail(element::asFloat));
    }

    public static Optional<Double> getDouble(PrimitiveMap map, String path) {
        return getAs(map, path, PrimitiveNumber.class).flatMap(element -> emptyIfFail(element::asDouble));
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
