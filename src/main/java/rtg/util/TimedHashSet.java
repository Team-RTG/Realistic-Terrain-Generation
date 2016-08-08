
package rtg.util;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 *
 * @author Zeno410
 */
public class TimedHashSet<Type> implements Set<Type> {
    private final int holdMillis;
    private  Set<Type> map = new HashSet<Type>();
    private LinkTail link = new LinkTail();

    public TimedHashSet(int holdTicks) {
        this.holdMillis = holdTicks;
    }
    public int size() {
        return map.size();
    }

    public boolean isEmpty() {
        return map.isEmpty();
    }

    public boolean contains(Object arg0) {
        clearEntries();
        return map.contains(arg0);
    }

    public Iterator<Type> iterator() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Object[] toArray() {
        return map.toArray();
    }

    public <T> T[] toArray(T[] arg0) {
        return map.toArray(arg0);
    }

    public boolean add(Type arg0) {
        clearEntries();
        // if we already have the key replace value;
        return map.add(arg0);
    }

    public boolean remove(Object arg0) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public boolean containsAll(Collection<?> arg0) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public boolean addAll(Collection<? extends Type> arg0) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public boolean retainAll(Collection<?> arg0) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public boolean removeAll(Collection<?> arg0) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void clear() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    private synchronized void clearEntries() {
        link.clear();;
    }

    private abstract class LinkEntry {
        LinkEntry next;
        abstract boolean old();
        abstract void remove();
    }

    private class LinkTail extends LinkEntry {
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
        void add(Type added) {
            LinkEntry toAdd = new Timed(added);
            toAdd.next = this;
            latest.next = toAdd;
            latest = toAdd;
        }
    }

	private class Timed<Key> extends LinkEntry {
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
