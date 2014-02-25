package com.game.bomberman.utils;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

// -------------------------------------------------------------------------
/**
 * A simple implementation of an LRU cache.
 *
 * @param <K>
 * @param <V>
 * @author danh0902
 * @author chingyg
 * @author kyleh8
 * @version 2013.12.8
 */

public class LRUCache<K, V>
{
    /**
     * Called when a cached element is about to be removed.
     *
     * @param <K>
     * @param <V>
     */
    public interface CacheEntryRemovedListener<K, V>
    {
        // ----------------------------------------------------------
        /**
         * Notify entry removed
         *
         * @param key
         *            K value
         * @param value
         *            V value
         */
        void notifyEntryRemoved(K key, V value);
    }

    private Map<K, V>                       cache;
    private CacheEntryRemovedListener<K, V> entryRemovedListener;


    /**
     * Creates the cache with the specified max entries.
     * @param maxEntries
     */
    public LRUCache(final int maxEntries)
    {
        cache = new LinkedHashMap<K, V>(maxEntries + 1, .75F, true) {
            public boolean removeEldestEntry(Map.Entry<K, V> eldest)
            {
                if (size() > maxEntries)
                {
                    if (entryRemovedListener != null)
                    {
                        entryRemovedListener.notifyEntryRemoved(
                            eldest.getKey(),
                            eldest.getValue());
                    }
                    return true;
                }
                return false;
            }
        };
    }


    // ----------------------------------------------------------
    /**
     * add the key to the collection
     * @param key
     * @param value
     */
    public void add(K key, V value)
    {
        cache.put(key, value);
    }


    // ----------------------------------------------------------
    /**
     * Gets key
     * @param key
     * @return the key
     */
    public V get(K key)
    {
        return cache.get(key);
    }


    // ----------------------------------------------------------
    /**
     * Gets the collection
     * @return the collection
     */
    public Collection<V> retrieveAll()
    {
        return cache.values();
    }


    // ----------------------------------------------------------
    /**
     * set the listener
     * @param entryRemovedListener
     */
    public void setEntryRemovedListener(
        CacheEntryRemovedListener<K, V> entryRemovedListener)
    {
        this.entryRemovedListener = entryRemovedListener;
    }
}
