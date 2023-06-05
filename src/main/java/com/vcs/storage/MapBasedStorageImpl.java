package com.vcs.storage;

import com.vcs.exception.KeyAlreadyExistsException;
import lombok.SneakyThrows;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;

public class MapBasedStorageImpl<Key,Value> implements Storage<Key,Value>{

    private final Map<Key,Value> storage = new HashMap<Key,Value>();
    @Override
    public Value get(Key key) {
        if(storage.containsKey(key)) {
            return storage.get(key);
        } else {
            throw new NoSuchElementException("Key not found");
        }
    }

    @Override
    public boolean set(Key key, Value value) {
            storage.put(key,value);
            return true;
    }

    @Override
    public boolean update(Key key, Value value) {
        return false;
    }


    @Override
    public void remove(Key key) {
        if(storage.containsKey(key)) {
             storage.remove(key);
        } else {
            throw new NoSuchElementException("Key not found");
        }
    }

    @Override
    public boolean isKeyExists(Key key) {
        return storage.containsKey(key);
    }

    @Override
    public Set<Key> keys() {
        return storage.keySet();
    }


}

/*
https://app.codesignal.com/live-interview/LAEg6JSLjKEfP4gf7
*/
