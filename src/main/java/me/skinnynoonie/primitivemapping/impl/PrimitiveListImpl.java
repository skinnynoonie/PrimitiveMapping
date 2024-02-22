package me.skinnynoonie.primitivemapping.impl;

import me.skinnynoonie.primitivemapping.PrimitiveElement;
import me.skinnynoonie.primitivemapping.PrimitiveList;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public final class PrimitiveListImpl extends AbstractPrimitiveElement implements PrimitiveList {

    public static PrimitiveListImpl createSynchronized() {
        List<PrimitiveElement> syncList = Collections.synchronizedList(new ArrayList<>());
        return new PrimitiveListImpl(syncList);
    }

    private final List<PrimitiveElement> internalList;

    private PrimitiveListImpl(List<PrimitiveElement> internalList) {
        this.internalList = internalList;
    }

    @Override
    public PrimitiveList addMetadata(Object data) {
        return (PrimitiveList) super.addMetadata(data);
    }

    @Override
    public PrimitiveElement get(int index) {
        return this.internalList.get(index);
    }

    @Override
    public PrimitiveList add(PrimitiveElement element) {
        if (element == null) {
            throw new IllegalArgumentException("element can not be null, use PrimitiveNullImpl instead");
        }

        this.internalList.add(element);
        return this;
    }

    @Override
    public void remove(PrimitiveElement element) {
        if (element == null) {
            throw new IllegalArgumentException("element can not be null, use PrimitiveNullImpl instead");
        }

        this.internalList.remove(element);
    }

    @Override
    public boolean contains(PrimitiveElement element) {
        if (element == null) {
            throw new IllegalArgumentException("element can not be null, use PrimitiveNullImpl instead");
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
