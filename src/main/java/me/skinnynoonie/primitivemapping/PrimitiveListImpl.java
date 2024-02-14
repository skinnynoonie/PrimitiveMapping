package me.skinnynoonie.primitivemapping;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public final class PrimitiveListImpl extends AbstractPrimitiveElement implements PrimitiveList {

    public static PrimitiveList createSynchronized() {
        List<PrimitiveElement> syncList = Collections.synchronizedList(new ArrayList<>());
        return new PrimitiveListImpl(syncList);
    }

    private final List<PrimitiveElement> internalList;

    private PrimitiveListImpl(List<PrimitiveElement> internalList) {
        this.internalList = internalList;
    }

    @Override
    public PrimitiveList asList() {
        return this;
    }

    @Override
    public boolean isList() {
        return true;
    }

    @Override
    public PrimitiveElement get(int index) {
        return this.internalList.get(index);
    }

    @Override
    public void add(PrimitiveElement element) {
        if (element == null) {
            throw new IllegalArgumentException("element can not be null, use PrimitiveNull instead");
        }

        this.internalList.add(element);
    }

    @Override
    public void remove(PrimitiveElement element) {
        if (element == null) {
            throw new IllegalArgumentException("element can not be null, use PrimitiveNull instead");
        }

        this.internalList.remove(element);
    }

    @Override
    public boolean contains(PrimitiveElement element) {
        if (element == null) {
            throw new IllegalArgumentException("element can not be null, use PrimitiveNull instead");
        }

        return this.internalList.contains(element);
    }

    @Override
    public int size() {
        return this.internalList.size();
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
