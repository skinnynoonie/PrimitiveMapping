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

    private final List<PrimitiveElement> internalList;

    private PrimitiveList(List<PrimitiveElement> internalList) {
        this.internalList = internalList;
    }

    public PrimitiveElement get(int index) {
        return this.internalList.get(index);
    }

    public PrimitiveList add(PrimitiveElement element) {
        if (element == null) {
            throw new IllegalArgumentException("element can not be null, use PrimitiveNull instead");
        }

        this.internalList.add(element);
        return this;
    }

    public boolean contains(PrimitiveElement element) {
        if (element == null) {
            throw new IllegalArgumentException("element can not be null, use PrimitiveNull instead");
        }

        return this.internalList.contains(element);
    }

    public int size() {
        return this.internalList.size();
    }

    public void remove(PrimitiveElement element) {
        if (element == null) {
            throw new IllegalArgumentException("element can not be null, use PrimitiveNull instead");
        }

        this.internalList.remove(element);
    }

    @Override
    public Iterator<PrimitiveElement> iterator() {
        return this.internalList.iterator();
    }

    @Override
    public String toString() {
        return this.internalList.toString();
    }

}
