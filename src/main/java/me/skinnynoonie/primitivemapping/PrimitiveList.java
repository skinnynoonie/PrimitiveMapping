package me.skinnynoonie.primitivemapping;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public final class PrimitiveList extends AbstractPrimitiveElement<PrimitiveList> implements Iterable<PrimitiveElement> {

    public static PrimitiveList createSynchronized() {
        List<PrimitiveElement> syncList = Collections.synchronizedList(new ArrayList<>());
        return new PrimitiveList(syncList);
    }

    private final List<PrimitiveElement> list;

    private PrimitiveList(List<PrimitiveElement> list) {
        this.list = list;
    }

    public PrimitiveElement get(int index) {
        return this.list.get(index);
    }

    public PrimitiveList add(PrimitiveElement element) {
        if (element == null) {
            throw new IllegalArgumentException("element can not be null, use PrimitiveNull instead");
        } else {
            this.list.add(element);
            return this;
        }
    }

    public boolean contains(PrimitiveElement element) {
        return this.list.contains(element);
    }

    public int size() {
        return this.list.size();
    }

    public void remove(PrimitiveElement element) {
        this.list.remove(element);
    }

    @Override
    public Iterator<PrimitiveElement> iterator() {
        return this.list.iterator();
    }

    @Override
    public String toString() {
        return this.list.toString();
    }

}
