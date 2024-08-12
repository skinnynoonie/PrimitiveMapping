package me.skinnynoonie.primitivemapping.util;

import me.skinnynoonie.primitivemapping.*;

import java.util.Arrays;
import java.util.Optional;
import java.util.function.Supplier;
import java.util.regex.Pattern;

/**
 * This class supports dot notation as "directories" for maps.
 * <p>For example: "one.two.three" would access "some value" in this JSON string:
 * {@code {"one": {"two": {"three": "some value"}}}}
 */
public final class DottedUtil {

    public static Optional<PrimitiveMap> getMap(PrimitiveMap map, String path) {
        return Optional.ofNullable(get(map, path))
                .filter(PrimitiveElement::isMap)
                .map(PrimitiveElement::asMap);
    }

    public static Optional<PrimitiveList> getList(PrimitiveMap map, String path) {
        return Optional.ofNullable(get(map, path))
                .filter(PrimitiveElement::isList)
                .map(PrimitiveElement::asList);
    }

    public static Optional<String> getString(PrimitiveMap map, String path) {
        return Optional.ofNullable(get(map, path))
                .filter(PrimitiveElement::isString)
                .map(PrimitiveElement::asString)
                .map(PrimitiveString::value);
    }

    public static Optional<Byte> getByte(PrimitiveMap map, String path) {
        return getNumber(map, path, Byte.class);
    }

    public static Optional<Short> getShort(PrimitiveMap map, String path) {
        return getNumber(map, path, Short.class);
    }

    public static Optional<Integer> getInt(PrimitiveMap map, String path) {
        return getNumber(map, path, Integer.class);
    }

    public static Optional<Long> getLong(PrimitiveMap map, String path) {
        return getNumber(map, path, Long.class);
    }

    public static Optional<Float> getFloat(PrimitiveMap map, String path) {
        return getNumber(map, path, Float.class);
    }

    public static Optional<Double> getDouble(PrimitiveMap map, String path) {
        return getNumber(map, path, Double.class);
    }

    public static Optional<Boolean> getBoolean(PrimitiveMap map, String path) {
        return Optional.ofNullable(get(map, path))
                .filter(PrimitiveElement::isBoolean)
                .map(PrimitiveElement::asBoolean)
                .map(PrimitiveBoolean::value);
    }

    public static PrimitiveElement get(PrimitiveMap map, String path) {
        String[] pathNodes = path.split(Pattern.quote("."));
        PrimitiveMap parentMap = map;

        String[] nodesExcludingLastElement = Arrays.copyOfRange(pathNodes, 0, pathNodes.length - 1);
        for (String node : nodesExcludingLastElement) {
            PrimitiveElement element = parentMap.get(node);

            if (element != null && element.isMap()) {
                parentMap = element.asMap();
                continue;
            }

            return null;
        }

        return parentMap.get(pathNodes[pathNodes.length - 1]);
    }

    public static void set(PrimitiveMap map, String path, PrimitiveElement value) {
        String[] pathNodes = path.split(Pattern.quote("."));
        PrimitiveMap parentMap = map;

        String[] nodesExcludingLastElement = Arrays.copyOfRange(pathNodes, 0, pathNodes.length - 1);
        for (String node : nodesExcludingLastElement) {
            PrimitiveElement element = parentMap.get(node);

            if (element != null && element.isMap()) {
                parentMap = element.asMap();
                continue;
            }

            PrimitiveMap overridingMap = PrimitiveMap.createSynchronized();
            parentMap.put(node, overridingMap);
            parentMap = overridingMap;
        }

        parentMap.put(pathNodes[pathNodes.length - 1], value);
    }

    @SuppressWarnings("unchecked")
    private static <T extends Number> Optional<T> getNumber(PrimitiveMap map, String path, Class<T> numberClass) {
        PrimitiveElement element = get(map, path);
        if (element == null || !element.isNumber()) {
            return Optional.empty();
        }

        PrimitiveNumber number = element.asNumber();
        if (numberClass == Byte.class) {
            return (Optional<T>) emptyOptionalIfException(number::asByte);
        } else if (numberClass == Short.class) {
            return (Optional<T>) emptyOptionalIfException(number::asShort);
        } else if (numberClass == Integer.class) {
            return (Optional<T>) emptyOptionalIfException(number::asInt);
        } else if (numberClass == Long.class) {
            return (Optional<T>) emptyOptionalIfException(number::asLong);
        } else if (numberClass == Float.class) {
            return (Optional<T>) emptyOptionalIfException(number::asFloat);
        } else if (numberClass == Double.class) {
            return (Optional<T>) emptyOptionalIfException(number::asDouble);
        } else {
            throw new UnsupportedOperationException("can not convert to unknown number type. input: " + numberClass.getName());
        }
    }

    private static <T> Optional<T> emptyOptionalIfException(Supplier<T> supplier) {
        try {
            return Optional.of(supplier.get());
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    private DottedUtil() {
        throw new UnsupportedOperationException("can not instantiate util class");
    }

}
