package rtg.api.config.property;

import javax.annotation.Nullable;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

import org.apache.commons.lang3.ArrayUtils;


@SuppressWarnings("unused")
public abstract class ConfigPropertyArray<T extends Comparable<T>> extends ConfigProperty {

    private final Class<T> clazz;
    private T minValue;
    private T maxValue;
    private Collection<T> values;

    @SafeVarargs
    private ConfigPropertyArray(Type type, String name, String category, String description, Class<T> clazz, @Nullable T minValue, @Nullable T maxValue, T... values) {
        super(type, name, category, description);
        this.clazz = clazz;
        this.minValue = minValue;
        this.maxValue = maxValue;
        this.values = Arrays.asList(values);
    }

    public T getMinValue() {
        return this.minValue;
    }

    public T getMaxValue() {
        return this.maxValue;
    }

    @SuppressWarnings("unchecked")
    public T[] getValues() {
        return this.values.toArray((T[]) Array.newInstance(this.clazz, this.values.size()));
    }

    public Collection<T> getAsCollection() {
        return Collections.unmodifiableCollection(this.values);
    }

    public final void set(final T[] values) {
        this.values = Collections.unmodifiableCollection(Arrays.asList(values));
    }

    public static final class ConfigPropertyArrayInteger extends ConfigPropertyArray<Integer> {

        public ConfigPropertyArrayInteger(String name, String category, String description, int minValue, int maxValue, Integer... values) {
            super(Type.INTEGER_ARRAY, name, category, description, Integer.class, minValue, maxValue, values);
        }

        public int[] getPrimitives() {
            return ArrayUtils.toPrimitive(super.getValues());
        }
    }

    public static final class ConfigPropertyArrayDouble extends ConfigPropertyArray<Double> {

        public ConfigPropertyArrayDouble(String name, String category, String description, double minValue, double maxValue, Double... values) {
            super(Type.DOUBLE_ARRAY, name, category, description, Double.class, minValue, maxValue, values);
        }

        public double[] getPrimitives() {
            return ArrayUtils.toPrimitive(super.getValues());
        }
    }

    public static final class ConfigPropertyArrayString extends ConfigPropertyArray<String> {

        public ConfigPropertyArrayString(String name, String category, String description, String[] values) {
            super(Type.STRING_ARRAY, name, category, description, String.class, null, null, values);
        }
    }
}
