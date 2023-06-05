package com.vcs.storage;

import java.util.Set;

public interface Storage<Key,Value> {
    public Value get(Key key);
    public boolean set(Key key,Value value);
    public boolean update(Key key,Value value);
    public void remove(Key key);
    public boolean isKeyExists(Key key);
    public Set<Key> keys();
}
