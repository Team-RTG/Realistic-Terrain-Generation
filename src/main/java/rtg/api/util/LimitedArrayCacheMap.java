/*
 * File         : LimitedArrayCacheMap.java
 * Last Modified: 20180328-02:19:46-0400
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
import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.Spliterators;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;


public final class LimitedArrayCacheMap<K extends Comparable<K>, V> implements Map<K, V>, Serializable
{
    private static final long serialVersionUID = 7161452652266833375L;

    private final int                    capacity;
    private final ImmutableEntry<K, V>[] entries;
    private       int                    index = 0;

    @SuppressWarnings("unchecked")
    public LimitedArrayCacheMap(final int capacity)
    {
        this.capacity = capacity;
        this.entries = new ImmutableEntry[capacity];
    }

    @Override
    public int size()
    {
        return (int) Arrays.stream(this.entries)
                         .filter(Objects::nonNull).count();
    }

    @Override
    public boolean isEmpty()
    {
        return Arrays.stream(this.entries)
                   .noneMatch(Objects::nonNull);
    }

    @Override
    public boolean containsKey(final Object key)
    {
        return IntStream.range(0, this.capacity)
                   .anyMatch(i -> this.entries[i].getKey().equals(key));
    }

    @Override
    public boolean containsValue(final Object value)
    {
        return IntStream.range(0, this.capacity)
                   .anyMatch(i -> this.entries[i].getValue().equals(value));
    }

    @Nullable
    @Override
    public V get(final Object key)
    {
        return Arrays.stream(this.entries)
                   .filter(e -> e != null && e.getKey().equals(key))
                   .findFirst()
                   .map(ImmutableEntry::getValue)
                   .orElse(null);
    }

    @Nullable
    @Override
    public V put(final K key, final V value)
    {
        V ret = null;
        if (this.entries[this.index] != null)
        {
            ret = this.entries[this.index].getValue();
        }
        this.entries[this.index] = new ImmutableEntry<>(key, value);
        this.removeDuplicates();
        if (this.index == this.capacity - 1) {
            this.index = 0;
        }
        else {
            this.index++;
        }
        return ret;
    }

    @Override
    public V remove(final Object key)
    {
        throw new UnsupportedOperationException("Immutable data");
    }

    @Override
    public void putAll(@Nonnull final Map<? extends K, ? extends V> m)
    {
        m.forEach(this::put);
    }

    @Override
    public void clear()
    {
        this.index = 0;
        IntStream.range(0, this.capacity)
            .forEach(i -> this.entries[i] = null);
    }

    @Nonnull
    @Override
    public Set<K> keySet()
    {
        return Arrays.stream(this.entries)
                   .filter(Objects::nonNull)
                   .map(ImmutableEntry::getKey)
                   .collect(Collectors.toCollection(LinkedHashSet::new));
    }

    @Nonnull
    @Override
    public Collection<V> values()
    {
        return Arrays.stream(this.entries)
                   .filter(Objects::nonNull)
                   .map(ImmutableEntry::getValue)
                   .collect(Collectors.toCollection(LinkedHashSet::new));
    }

    @Nonnull
    @Override
    public Set<Entry<K, V>> entrySet()
    {
        return Arrays.stream(this.entries)
                   .filter(Objects::nonNull)
                   .collect(Collectors.toCollection(LinkedHashSet::new));
    }

    @SuppressWarnings("MethodDoesntCallSuperMethod")
    @Override
    public LimitedArrayCacheMap<K, V> clone()
    {
        LimitedArrayCacheMap<K, V> clone = new LimitedArrayCacheMap<>(this.capacity);
        System.arraycopy(this.entries, 0, clone.entries, 0, this.capacity);
        clone.index = this.index;
        return clone;
    }

    @Override
    public boolean equals(final Object o)
    {
        if (this == o)
        {
            return true;
        }
        if (!(o instanceof LimitedArrayCacheMap))
        {
            return false;
        }
        LimitedArrayCacheMap<?, ?> other = (LimitedArrayCacheMap<?, ?>) o;
        return this.capacity == other.capacity &&
               IntStream.range(0, this.capacity)
                   .allMatch(i -> this.entries[i].equals(other.entries[i]));
    }

    @Override
    public int hashCode()
    {
        int ret = 1;
        ret += this.entrySet().stream().mapToInt(Entry::hashCode).sum();
        return ret;
    }

    @Override
    public String toString()
    {
        StringBuilder s = new StringBuilder();
        s.append("LimitedArrayCacheMap[");
        boolean first = true;
        for (Entry<K, V> e : this.entrySet())
        {
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

    public Stream<Entry<K, V>> stream()
    {
        return StreamSupport.stream(Spliterators.spliterator(this.entrySet(), 0), false);
    }

    private void removeDuplicates()
    {
        IntStream.range(0, this.capacity)
            .filter(i -> i != this.index && this.entries[i] != null && this.entries[i].equals(this.entries[this.index]))
            .forEach(i -> this.entries[i] = null);
    }

    @Immutable
    private static final class ImmutableEntry<K extends Comparable<K>, V> implements Entry<K, V>, Comparable<ImmutableEntry<K, V>>, Serializable
    {
        private static final long serialVersionUID = -4241056911694303514L;

        private final K key;
        private final V value;

        private ImmutableEntry(K key, V value)
        {
            this.key   = key;
            this.value = value;
        }

        @Override
        public K getKey()
        {
            return this.key;
        }

        @Override
        public V getValue()
        {
            return this.value;
        }

        @Override
        public V setValue(V v)
        {
            throw new UnsupportedOperationException("Immutable data");
        }

        @Override
        public boolean equals(@Nullable Object o)
        {
            return (o != null) && (o instanceof Entry && this.getKey().equals(((Entry) o).getKey()));// We only care about the key
        }

        @Override
        public int hashCode()
        {
            return this.getKey() == null ? 0 : this.getKey().hashCode();// We only care about the key
        }

        @Override
        public String toString()
        {
            return this.getKey() + "=" + this.getValue();
        }

        @Override
        public int compareTo(@Nonnull final ImmutableEntry<K, V> o)
        {
            return this.getKey().compareTo(o.getKey());// We only care about the key
        }
    }
}
