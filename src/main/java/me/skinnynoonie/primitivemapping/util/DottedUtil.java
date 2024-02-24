package me.skinnynoonie.primitivemapping.util;

import me.skinnynoonie.primitivemapping.PrimitiveElement;
import me.skinnynoonie.primitivemapping.PrimitiveMap;
import me.skinnynoonie.primitivemapping.PrimitiveNull;

import java.util.Arrays;
import java.util.Optional;
import java.util.regex.Pattern;

/**
 * A basic class that supports null and null safety at the same time.
 * This class supports dot notation as directories for maps.
 * <p>For example: "one.two.three" would be in JSON: {@code {"one": {"two": {"three": "some value"}}}}
 */
public final class DottedUtil {

    public static PrimitiveElement get(PrimitiveMap map, String path) {
        if (map == null) {
            throw new IllegalArgumentException("map can not be null");
        } else if (path == null) {
            throw new IllegalArgumentException("path can not be null");
        }

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
        if (map == null) {
            throw new IllegalArgumentException("map can not be null");
        } else if (path == null) {
            throw new IllegalArgumentException("path can not be null");
        }

        if (value == null) {
            value = PrimitiveNull.create();
        }

        String[] pathNodes = path.split(Pattern.quote("."));
        PrimitiveMap parentMap = map;

        for (String node : Arrays.copyOfRange(pathNodes, 0, pathNodes.length - 1)) {
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

    private DottedUtil() {
        throw new UnsupportedOperationException("can not instantiate util class");
    }

}
