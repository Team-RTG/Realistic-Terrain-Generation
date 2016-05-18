package teamrtg.rtg.api.util;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author topisani
 */
public class LimitedMap<K, V> {
    private final int size;
    private Map<K, V> map = new HashMap<K, V>();
    private final K[] keys;
    private int nextIndex = 0;

    public LimitedMap(int size) {
        this.size = size;
        this.keys = (K[]) new Object[size];
    }

    public void put(K key, V value) {
        if (!(nextIndex < size)) {
            nextIndex = 0;
        }
        map.remove(keys[nextIndex]);
        map.put(key, value);
        keys[nextIndex] = key;
        nextIndex++;
    }

    public V get(K key) {
        return map.get(key);
    }

    public Set<K> keySet() {
        return this.map.keySet();
    }
}
