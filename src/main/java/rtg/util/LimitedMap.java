
package rtg.util;

import java.util.*;

/**
 *
 * @author Zeno410
 */
public class LimitedMap<Key,Value> implements Map<Key,Value> {

    private int limit;
    private int nextIndex = 0;
    private boolean reachedLimit = false;
    private final ArrayList<Key> keyList;
    private final HashMap<Key,Value> map;

    public LimitedMap(int maximumSize) {
        limit = maximumSize;
        keyList = new ArrayList<Key>(limit);
        map = new HashMap<Key,Value>(limit);
    }

    public int size() {
       if (reachedLimit) return limit;
       return nextIndex;
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public boolean containsKey(Object arg0) {
        return keyList.contains(arg0);
    }

    public boolean containsValue(Object arg0) {
        for (Value value: map.values()) {
            if (value.equals(arg0)) return true;
        };
        return false;
    }

    public Value get(Object arg0) {
        return map.get(arg0);
    }

    public synchronized Value put(Key arg0, Value arg1) {
        if (reachedLimit) {
            map.remove(keyList.get(nextIndex));
            keyList.set(nextIndex++, arg0);
            if (nextIndex >= limit) {
                nextIndex = 0;
            }
        } else {
            keyList.add(arg0);
            nextIndex ++;
            if (nextIndex >= limit) {
                reachedLimit = true;
                nextIndex = 0;
            }
        }
        return map.put(arg0, arg1);
    }

    public Value remove(Object arg0) {
        return null;// not going to bother
    }

    public void putAll(Map<? extends Key, ? extends Value> arg0) {
        for (Key key: arg0.keySet()) {
            put(key,arg0.get(key));
        }
    }

    public void clear() {
        nextIndex = 0;
        reachedLimit = false;
        keyList.clear();
        map.clear();
    }

    public Set<Key> keySet() {
        return map.keySet();
    }

    public Collection<Value> values() {
        return map.values();
    }

    public Set<Entry<Key, Value>> entrySet() {
        return map.entrySet();
    }

}
