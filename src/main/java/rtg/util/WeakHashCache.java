
package rtg.util;

import java.lang.ref.WeakReference;
import java.util.WeakHashMap;

/**
 * NOT CURRENTLY FUNCTIONAL
 *
 * This is a complex class to achieve the goal of a HashMap where the objects are
 * held with weak references and so can be garbage collected
 *
 * IMPORTANT IMPORTANT IMPORTANT
 * The Key class must be "keyable", meaning that two different objects with the same date
 * will return "equal" and will have the same hashvalue. Otherwise you'll never be able
 * to get the values out! The Java Number classes and String are all keyable, BTW.
 * @author Zeno410
 */
public class WeakHashCache<Key,Value> {
    
    // we make our own
    private final Converter<Value,Key> keyer;

    // this map forces the garbage collector to keep the keys around as long as
    // the values still exist
    private WeakHashMap<Value,Key> storageIndicator = new WeakHashMap<Value,Key>();

    // this map can retrieve the values if they're still around but doesn't stop
    // the garbage collector from tossing them. It also lets the keys be GC'd, which
    // is stopped if need by their existence in storageIndicator;

    private WeakHashMap<Key,WeakReference<Value>> mapping =
            new WeakHashMap<Key,WeakReference<Value>>();

    public WeakHashCache(Converter<Value,Key> keyer) {
        this.keyer = keyer;
    }

    public int size() {return storageIndicator.size();}

    public static class ValueMissing extends Exception {
        // this class exists to force clients to pay attention to missing values;
    }

    public void cache(Value value) {
        Key key = keyer.of(value);
        storageIndicator.put(value, key);
        mapping.put(key, new WeakReference(value));
    }

    public Value get(Key key) throws ValueMissing {
        WeakReference<Value> reference = mapping.get(key);
        if (reference != null) {
            Value result = reference.get();
            if (result != null) return result;
        }
        throw new ValueMissing();
    }
}
