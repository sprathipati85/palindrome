package com.cmegroup.palindrome.cache;

import java.util.List;

public interface LocalDataStore<K, V> {
    void addOrUpdate(K key, V value);
    void delete(K key);
    V get(K key);
    List<V> getAll();
    boolean exists(K key);
}