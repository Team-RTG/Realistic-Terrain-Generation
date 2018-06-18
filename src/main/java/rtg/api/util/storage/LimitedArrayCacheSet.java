/*
 * File         : LimitedArrayCacheSet.java
 * Last Modified: 20180618-08:21:30-0400
 *
 * Copyright (c) 2018 srs_bsns (forfrdm [at] gmail.com)
 *
 * The MIT License (MIT)
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package rtg.api.util.storage;

import javax.annotation.Nonnull;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.Objects;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.IntStream;


public class LimitedArrayCacheSet<E> implements Set<E>
{
    private final E[]  elements;
    private       int  index = 0;

    @SuppressWarnings("unchecked")
    public LimitedArrayCacheSet(final int capacity) {
        this.elements = (E[]) new Object[capacity];
    }

    @Override
    public int size()
    {
        return (int) Arrays.stream(elements).filter(Objects::nonNull).count();
    }

    @Override
    public boolean isEmpty()
    {
        return Arrays.stream(elements).anyMatch(Objects::nonNull);
    }

    @Override
    public boolean contains(final Object o)
    {
        return Arrays.stream(elements).filter(Objects::nonNull).anyMatch(Predicate.isEqual(o));
    }

    @Override
    @Nonnull
    public Iterator<E> iterator()
    {
        return Arrays.stream(elements).filter(Objects::nonNull).iterator();
    }

    @Override
    @Nonnull
    public Object[] toArray()
    {
        return elements.clone();
    }

    @Override
    @Nonnull
    @SuppressWarnings("unchecked")
    public <T> T[] toArray(@Nonnull T[] a)
    {
        int size = size();
        a = (a.length >= size) ? a : (T[]) java.lang.reflect.Array.newInstance(a.getClass().getComponentType(), size);
        Iterator<E> iter = iterator();
        Arrays.setAll(a, i -> (iter.hasNext()) ? (T)iter.next() : null);
        return a;
    }

    @Override
    public boolean add(@Nonnull final E e)
    {
        if (find(e) >= 0) { return false; }
        elements[nextIndex()] = e;
        return true;
    }

    @Override
    public boolean removeIf(Predicate<? super E> filter)
    {
        boolean ret = false;
        for (int i = 0; i < elements.length; i++) {
            if (elements[i] != null && filter.test(elements[i])) { ret = remove(i); }
        }
        return ret;
    }

    @Override public boolean remove(final Object e)
    {
        return remove(find(e));
    }

    @Override
    public boolean containsAll(@Nonnull final Collection<?> c)
    {
        return c.stream().noneMatch(o1 -> Arrays.stream(elements).filter(Objects::nonNull).noneMatch(Predicate.isEqual(o1)));
    }

    @Override
    public boolean addAll(@Nonnull final Collection<? extends E> c)
    {
        boolean ret = false;
        for (final E e : c) {
            if (add(e)) { ret = true; }
        }
        return ret;
    }

    @Override
    public boolean removeAll(@Nonnull final Collection<?> c)
    {
        boolean ret = false;
        for (final Object e : c) {
            if (remove(e)) { ret = true; }
        }
        return ret;
    }

    @Override
    public boolean retainAll(@Nonnull final Collection<?> c)
    {
        boolean ret = false;
        for (int i = 0; i < elements.length; i++)
        {
            if (elements[i] != null && !c.contains(elements[i]))
            {
                if (remove(i)) { ret = true; }
            }
        }
        return ret;
    }

    @Override
    public void clear()
    {
        Arrays.fill(elements, null);
    }

    private int find(final Object e)
    {
        return IntStream.range(0, elements.length).filter(i -> e != null && e.equals(elements[i])).findFirst().orElse(-1);
    }

    private boolean remove(final int index)
    {
        if (index < 0 || index >= elements.length || elements[index] == null) { return false; }
        elements[index] = null;
        return true;
    }

    private int nextIndex()
    {
        int ret = index;
        if (++index >= elements.length) { index = 0; }
        return ret;
    }

    @Override
    public boolean equals(final Object o)
    {
        if (this == o) { return true; }
        if (o == null || getClass() != o.getClass()) { return false; }
        LimitedArrayCacheSet<?> other = (LimitedArrayCacheSet<?>) o;
        Object[] o1 = Arrays.stream(this.elements) .filter(Objects::nonNull).sorted().toArray();
        Object[] o2 = Arrays.stream(other.elements).filter(Objects::nonNull).sorted().toArray();
        return Arrays.equals(o1, o2);
    }

    @Override
    public int hashCode()
    {
        return Arrays.hashCode(elements);
    }

    public String toString()
    {
        if (size() == 0) { return "[]"; }
        StringBuilder sb = new StringBuilder();
        sb.append('[');
        boolean first = true;
        for (Object o : Arrays.stream(elements).filter(Objects::nonNull).toArray())
        {
            if (first)
            {
                sb.append(o.toString());
                first = false;
            }
            else
            {
                sb.append(", ").append(o.toString());
            }
        }
        return sb.append("]").toString();
    }
}
