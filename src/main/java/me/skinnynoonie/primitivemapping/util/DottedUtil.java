package me.skinnynoonie.primitivemapping.util;

import me.skinnynoonie.primitivemapping.PrimitiveElement;
import me.skinnynoonie.primitivemapping.PrimitiveMap;
import me.skinnynoonie.primitivemapping.PrimitiveMapImpl;

import java.util.Arrays;
import java.util.Optional;
import java.util.regex.Pattern;

public final class DottedUtil {
    
    public static Optional<PrimitiveElement> get(PrimitiveMap map, String path) {
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

    public static void set(PrimitiveMap map, String path, PrimitiveElement value) {
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
    
    private DottedUtil() {
        throw new UnsupportedOperationException("can not instantiate util class");
    }

}
