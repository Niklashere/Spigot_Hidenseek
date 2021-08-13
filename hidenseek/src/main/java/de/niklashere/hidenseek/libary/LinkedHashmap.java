package de.niklashere.hidenseek.libary;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

/**
 * LinkedHashMap to access a position or the key from the map.
 *
 * @author Niklashere
 * @since 02.08.2021
 */
class LinkedHashmap<K, V> extends LinkedHashMap<K, V> {

  /**
   * Get the value at position i.
   * 
   * @param i position
   * @return value
   */
  public V getValue(int i) {

    Map.Entry<K, V> entry = this.getEntry(i);
    if (entry == null) {
      return null;
    }

    return entry.getValue();
  }

  /**
   * Get the entry at position i.
   * 
   * @param i position
   * @return entry
   */
  public Map.Entry<K, V> getEntry(int i) {
    Set<Map.Entry<K, V>> entries = entrySet();
    int j = 0;

    for (Map.Entry<K, V> entry : entries) {
      if (j++ == i) {
        return entry;
      }
    }

    return null;

  }

}