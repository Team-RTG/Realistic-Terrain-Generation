
package rtg.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 *
 * @author Zeno410
 */
public class LimitedSet<Type> implements Set<Type>, Iterable<Type> {
    private HashSet<Type> members = new HashSet<Type>();
    private ArrayList<Type> limiting;
    private int nextIndex;
    private int maxSize;
    private boolean full = false;

    public LimitedSet(int maxSize) {
        limiting = new ArrayList<Type> (maxSize);
        this.maxSize = maxSize;
    }

    public int size() {
        return members.size();
    }

    public boolean isEmpty() {
        return members.isEmpty();
    }

    public boolean contains(Object arg0) {
        return members.contains(arg0);
    }

    public Iterator<Type> iterator() {
       return members.iterator();
    }

    public Object[] toArray() {
        return members.toArray();
    }

    public <T> T[] toArray(T[] arg0) {
        return members.toArray(arg0);
    }

    public boolean add(Type arg0) {
        if (full){
            members.remove(limiting.get(nextIndex));
            limiting.set(nextIndex, arg0);
        }  else {
            limiting.add(arg0);
        }
        nextIndex ++;
        if (nextIndex >= maxSize) {
            nextIndex = 0;
            full = true;
        }
        return members.add(arg0);
    }

    public boolean remove(Object arg0) {
        return members.remove(arg0);
    }

    public boolean containsAll(Collection<?> arg0) {
        return members.containsAll(arg0);
    }

    public boolean addAll(Collection<? extends Type> arg0) {
        for (Type added: arg0) {
            add(added);
        }
        return true;
    }

    public boolean retainAll(Collection<?> arg0) {
        return members.retainAll(arg0);
    }

    public boolean removeAll(Collection<?> arg0) {
        return members.removeAll(arg0);
    }

    public void clear() {
        members.clear();
    }

}
