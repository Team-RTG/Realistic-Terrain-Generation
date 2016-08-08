
package rtg.util;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author Zeno410
 */
public class TimedHashMap<Key,Value> implements Map<Key,Value> {
    private final int holdMillis;
    private  Map<Key,Value> map = new HashMap<Key,Value>();
    private LinkTail link = new LinkTail();


    public TimedHashMap(int holdTicks) {
        this.holdMillis = holdTicks;
    }

    public int size() {
        return map.size();
    }

    public boolean isEmpty() {
        return map.isEmpty();
    }

    public boolean containsKey(Object arg0) {
        clearEntries();
        return map.containsKey(arg0);
    }

    public boolean containsValue(Object arg0) {
        clearEntries();
        return map.containsValue(arg0);
    }

    public Value get(Object arg0) {
        clearEntries();
        return map.get(arg0);
    }

    public Value put(Key arg0, Value arg1) {
        clearEntries();
        // if we already have the key replace value;
        if (map.containsKey(arg0)) return map.put(arg0, arg1);
        this.link.add(arg0);
        return map.put(arg0, arg1);
    }

    public Value remove(Object arg0) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void putAll(Map<? extends Key, ? extends Value> arg0) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void clear() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Set<Key> keySet() {
        clearEntries();
        return map.keySet();
    }

    public Collection<Value> values() {
        clearEntries();
        return map.values();
    }

    public Set<Entry<Key, Value>> entrySet() {
        clearEntries();
        return map.entrySet();
    }

    private synchronized void clearEntries() {
        link.clear();;
    }

    abstract class LinkEntry {
        LinkEntry next;
        abstract boolean old();
        abstract void remove();
    }


    class LinkTail extends LinkEntry {
        LinkEntry latest;
        LinkTail() {
            this.latest = this;
            this.next = this;
        }
        boolean old() {return false;}
        void remove() {throw new RuntimeException();}
        void clear() {
            while (next.old()) {
                next.remove();
                next = next.next;
            }
        }
        void add(Key added) {
            LinkEntry toAdd = new Timed(added);
            toAdd.next = this;
            latest.next = toAdd;
            latest = toAdd;
        }
    }

    @SuppressWarnings("hiding")
	class Timed<Key> extends LinkEntry {
        final long time;
        final Key timed;
        Timed(Key timed) {
            this.timed = timed;
            time = System.currentTimeMillis();
        }

        void remove() {
            map.remove(timed);
        }

        @Override
        boolean old() {
            return (time + holdMillis<System.currentTimeMillis());
        }


    }

}

