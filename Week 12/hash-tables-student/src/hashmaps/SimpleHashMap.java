/*
 * Copyright 2023 Marc Liberatore.
 */
package hashmaps;

import java.util.HashSet;
import java.util.Set;

import hashtables.ChainingHashTable;



/**
 * An implementation of a SimpleMap, built using the ChainingHashTable and 
 * SimpleMapEntry classes. This class should behave similarly to the built-in
 * java.util.HashMap, though it is much simpler!
 */
public class SimpleHashMap<K, V> implements SimpleMap<K, V> {
    
    private int size = 0;
    private ChainingHashTable<SimpleMapEntry<K,V>> table = new ChainingHashTable<>();
    private Set<K> keys = new HashSet<K>();

    public SimpleHashMap() {
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void put(K k, V v) {
        SimpleMapEntry newEntry = new SimpleMapEntry<K,V>(k, v);
        if (!table.contains(newEntry)){
            table.add(newEntry);
            size ++ ;
        }
        else{
            table.add(newEntry);
        }        
        keys.add(k);
        
    }

    @Override
    public V get(K k) {
        SimpleMapEntry<K, V> temp = new SimpleMapEntry<K, V>(k, null);
        if (table.contains(temp)){
            return table.get(temp).v;
        }
        return null;
    }

    @Override
    public V getOrDefault(K k, V defaultValue) {
        SimpleMapEntry<K,V> temp = new SimpleMapEntry<K,V>(k, defaultValue);
        if (table.contains(temp)){
            return table.get(temp).v;
        }
        else{
            return defaultValue;
        }
    }

    @Override
    public V remove(K k) {
        SimpleMapEntry<K,V> temp = new SimpleMapEntry<K,V>(k, null);
        V res = getOrDefault(k, null);
        if (table.contains(temp)){
            size --;
        }
        table.remove(temp);
        return res;
        
    }

    @Override
    public Set<K> keys() {
        return keys;
    }    
}
