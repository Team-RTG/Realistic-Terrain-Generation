package rtg.api.util;

/**
 * @author Zeno410
 */
public class Valued<Type> implements Comparable<Valued<Type>> {

    private double value;
    private Type item;

    public Valued(double _count, Type _item) {
        value = _count;
        item = _item;
    }

    public double value() {
        return value;
    }

    public Type item() {
        return item;
    }

    public int compareTo(Valued<Type> arg0) {
        if (this.value > arg0.value) {
            return 1;
        }
        if (this.value == arg0.value) {
            return 0;
        }
        return -1;
    }

}
