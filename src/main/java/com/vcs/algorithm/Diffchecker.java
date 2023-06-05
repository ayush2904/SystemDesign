package com.vcs.algorithm;

public interface Diffchecker<E> {
    public DiffReport getDiffFromObject(E element1,E element2);
}
