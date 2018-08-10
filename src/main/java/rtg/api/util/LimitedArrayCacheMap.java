/*
 * File         : LimitedArrayCacheMap.java
 * Last Modified: 20180607-05:28:54-0400
 *
 * Copyright (c) 2018 srs_bsns (forfrdm [at] gmail.com)
 *
 * The MIT License (MIT)
 *
 * Permission is hereby granted, free of charge, to any person obtaining a
 * copy of this software and associated documentation files (the
 * "Software"), to deal in the Software without restriction, including
 * without limitation the rights to use, copy, modify, merge, publish,
 * distribute, sublicense, and/or sell copies of the Software, and to
 * permit persons to whom the Software is furnished to do so, subject to
 * the following conditions:
 *
 * The above copyright notice and this permission notice shall be included
 * in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS
 * OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
 * MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT.
 * IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY
 * CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT,
 * TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE
 * SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 *
 */

package rtg.api.util;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.concurrent.Immutable;
import java.io.Serializable;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import java.util.Spliterators;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;


public final class LimitedArrayCacheMap<K, V> implements Map<K, V>, Serializable {

    private static final long serialVersionUID = 7161452652266833375L;

    private final int capacity;
    private final ImmutableEntry<K, V>[] entries;
    private int index = 0;

    @SuppressWarnings("unchecked")
    public LimitedArrayCacheMap(final int capacity) {
        this.capacity = capacity;
        this.entries = new ImmutableEntry[capacity];
    }

    public int getCapacity() {
        return this.capacity;
    }

    @Override
    public int size() {
        int count = 0;
        for (ImmutableEntry<K, V> entry : this.entries) {
            if (entry != null) {
                count++;
            }
        }
        return count;
    }

    @Override
    public boolean isEmpty() {
        for (ImmutableEntry<K, V> entry : this.entries) {
            if (entry != null) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean containsKey(final Object key) {
        for (ImmutableEntry<K, V> entry : this.entries) {
            if (entry != null && entry.getKey().equals(key)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean containsValue(final Object value) {
        for (ImmutableEntry<K, V> entry : this.entries) {
            if (entry != null && entry.getValue().equals(value)) {
                return true;
            }
        }
        return false;
    }

    @Nullable
    @Override
    public V get(final Object key) {
        for (ImmutableEntry<K, V> entry : this.entries) {
            if (entry != null && entry.getKey().equals(key)) {
                return entry.getValue();
            }
        }
        return null;
    }

    @Override
    public V getOrDefault(final Object key, V defaultValue) {
        V ret;
        return ((ret = this.get(key)) != null) ? ret : defaultValue;
    }

    @Nullable
    @Override
    public V put(final K key, final V value) {
        V ret = null;
        if (this.entries[this.index] != null) {
            ret = this.entries[this.index].getValue();
        }
        this.entries[this.index] = new ImmutableEntry<>(key, value);
        this.removeDuplicates();
        this.index = (this.index == this.capacity - 1) ? 0 : this.index + 1;
        return ret;
    }

    @Override
    public V remove(final Object key) {
        throw new UnsupportedOperationException("Immutable data");
    }

    @Override
    public void putAll(@Nonnull final Map<? extends K, ? extends V> m) {
        m.forEach(this::put);
    }

    @Override
    public void clear() {
        this.index = 0;
        for (int i = 0; i < this.capacity; i++) {
            this.entries[i] = null;
        }
    }

    @Nonnull
    @Override
    public Set<K> keySet() {
        LinkedHashSet<K> keySet = new LinkedHashSet<>();
        for (ImmutableEntry<K, V> entry : this.entries) {
            if (entry != null) {
                keySet.add(entry.getKey());
            }
        }
        return keySet;
    }

    @Nonnull
    @Override
    public Collection<V> values() {
        LinkedHashSet<V> valueSet = new LinkedHashSet<>();
        for (ImmutableEntry<K, V> entry : this.entries) {
            if (entry != null) {
                valueSet.add(entry.getValue());
            }
        }
        return valueSet;
    }

    @Nonnull
    @Override
    public Set<Entry<K, V>> entrySet() {
        LinkedHashSet<Entry<K, V>> entrySet = new LinkedHashSet<>();
        for (ImmutableEntry<K, V> entry : this.entries) {
            if (entry != null) {
                entrySet.add(entry);
            }
        }
        return entrySet;
    }

    @SuppressWarnings("MethodDoesntCallSuperMethod")
    @Override
    public LimitedArrayCacheMap<K, V> clone() {
        LimitedArrayCacheMap<K, V> clone = new LimitedArrayCacheMap<>(this.capacity);
        System.arraycopy(this.entries, 0, clone.entries, 0, this.capacity);
        clone.index = this.index;
        return clone;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }

        if (!(o instanceof LimitedArrayCacheMap)) {
            return false;
        }
        LimitedArrayCacheMap<?, ?> other = (LimitedArrayCacheMap<?, ?>) o;

        if (this.getCapacity() != other.getCapacity()) {
            return false;
        }

        for (int i = 0; i < this.getCapacity(); i++) {
            if (this.entries[i] == null && other.entries[i] != null ||
                this.entries[i] != null && !this.entries[i].equals(other.entries[i])) {
                return false;
            }
        }
        return true;
    }

    @Override
    public int hashCode() {
        int ret = 0;
        for (Entry<K, V> entry : this.entries)// We only care about the key
        {
            if (entry != null) {
                ret += entry.hashCode();
            }
        }
        return ret;
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append("LimitedArrayCacheMap[");
        boolean first = true;
        for (Entry<K, V> e : this.entrySet()) {
            if (first) {
                s.append("{");
            }
            else {
                s.append(",{");
            }
            s.append(e.toString());
            s.append("}");
            first = false;
        }
        s.append("]");
        return s.toString();
    }

    public Stream<Entry<K, V>> stream() {
        return StreamSupport.stream(Spliterators.spliterator(this.entrySet(), 0), false);
    }

    private void removeDuplicates() {
        for (int i = 0; i < this.capacity; i++) {
            if (i != this.index && this.entries[i] != null && this.entries[i].equals(this.entries[this.index])) {
                this.entries[i] = null;
            }
        }
    }

    @Immutable
    private static final class ImmutableEntry<K, V> implements Entry<K, V>, Serializable {

        private static final long serialVersionUID = -4241056911694303514L;

        private final K key;
        private final V value;

        private ImmutableEntry(K key, V value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public K getKey() {
            return this.key;
        }

        @Override
        public V getValue() {
            return this.value;
        }

        @Override
        public V setValue(V v) {
            throw new UnsupportedOperationException("Immutable data");
        }

        @Override
        public boolean equals(@Nullable Object o) {
            return (o != null) && (o instanceof Entry<?, ?> && this.getKey().equals(((Entry<?, ?>) o).getKey()));// We only care about the key
        }

        @Override
        public int hashCode() {
            return this.getKey().hashCode();// We only care about the key
        }

        @Override
        public String toString() {
            return this.getKey() + "=" + this.getValue();
        }
    }
}
