package com.cmegroup.palindrome.cache;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * Local data store to store data
 * Uses java.util.concurrent.ConcurrentHashMap.
 *
 */

@Component
public class MapLocalDataStore<K, V> implements LocalDataStore<K, V> {

    private final ConcurrentMap<K, V> localDatastore;

    public MapLocalDataStore() {
        localDatastore = new ConcurrentHashMap<>();
    }

    @Override
    public void addOrUpdate(K key, V value) {
        localDatastore.put(key, value);
    }

    @Override
    public void delete(K key) {
        localDatastore.remove(key);
    }

    @Override
	public V get(K key) {
        return localDatastore.get(key);
    }

    @Override
	public List<V> getAll() {
        return new ArrayList<>(localDatastore.values());
    }

    @Override
    public boolean exists(K key) {
        return localDatastore.containsKey(key);
    }
}