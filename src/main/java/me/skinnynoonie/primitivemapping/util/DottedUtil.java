package me.skinnynoonie.primitivemapping.util;

import me.skinnynoonie.primitivemapping.PrimitiveElement;
import me.skinnynoonie.primitivemapping.PrimitiveMap;
import me.skinnynoonie.primitivemapping.impl.PrimitiveMapImpl;
import me.skinnynoonie.primitivemapping.impl.PrimitiveNullImpl;

import java.util.Arrays;
import java.util.Optional;
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
            if (element instanceof PrimitiveMap) {
                parentMap = (PrimitiveMap) element;
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
     * If the value is {@code null}, then it will be replaced with a {@link PrimitiveNullImpl}.
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
            value = PrimitiveNullImpl.create();
        }

        String[] pathNodes = path.split(Pattern.quote("."));
        PrimitiveMap parentMap = map;

        for (String node : Arrays.copyOfRange(pathNodes, 0, pathNodes.length - 1)) {
            PrimitiveElement element = parentMap.get(node).orElse(null);
            if (element instanceof PrimitiveMap) {
                parentMap = (PrimitiveMap) element;
                continue;
            }

            PrimitiveMap overridingMap = PrimitiveMapImpl.createSynchronized();
            parentMap.put(node, overridingMap);
            parentMap = overridingMap;
        }

        parentMap.put(pathNodes[pathNodes.length - 1], value);
    }

    private DottedUtil() {
        throw new UnsupportedOperationException("can not instantiate util class");
    }

}
