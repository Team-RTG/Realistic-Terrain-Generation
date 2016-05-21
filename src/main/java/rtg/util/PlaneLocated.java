
package rtg.util;

import java.util.HashMap;
import java.util.Set;

/**
 *
 * @author Zeno410
 */
public class PlaneLocated<Type> {
    private HashMap<PlaneLocation,Type> storedVals = new HashMap<PlaneLocation,Type>();

    public final Type get(PlaneLocation location) {
        return storedVals.get(location);
    }
    public final void put(PlaneLocation location, Type stored) {
        storedVals.put(location, stored);
    }

    public final void putAll(HashMap<PlaneLocation,Type> newValues) {
        for (PlaneLocation location: newValues.keySet()) {
            storedVals.put(location, newValues.get(location));
        }
    }

    public final void remove(PlaneLocation location) {
        storedVals.remove(location);
    }

    public boolean confirm(PlaneLocation location, Type value) {
        Type existing = storedVals.get(location);
        if (existing == null) {
            storedVals.put(location, value);
        } else {
            if (!existing.equals(value)) {
                return false;
            }
        }
        return true;
    }

    public int size() {return storedVals.size();}

    public Set<PlaneLocation> locations() {return this.storedVals.keySet();}

}