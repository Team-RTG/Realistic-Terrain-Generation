package rtg.api.util.storage;

import javax.annotation.Nullable;
import java.lang.reflect.Array;


/**
 * This is a simple multi-dimensional bucket cache with fixed dimensions of which the bucket
 * selection is derived from passed coordinates with a look-up in reverse history order.
 *
 * @param <T> The type of objects to store in this cache
 */
public final class SimpleMultiBucketCache<T>
{
    private static final int BUCKET_DIMENSION_SIZE = 8;
    private static final int BUCKET_BITMASK = BUCKET_DIMENSION_SIZE - 1;

    private final int        bucketSize;
    private final long[][][] bucketKeys;
    private final T[][][]    bucketVals;
    private final int[][]    bucketIndex;

    private final ReverseHistoryIndexer indexer;

    public SimpleMultiBucketCache(final Class<T> cacheType, final int bucketSize)
    {
        this(cacheType, bucketSize, false);
    }

    @SuppressWarnings("unchecked")
    public SimpleMultiBucketCache(final Class<T> cacheType, final int bucketSize, final boolean primeIndexes)
    {
        this.bucketSize  = bucketSize;
        this.bucketVals  = (T[][][])Array.newInstance(cacheType, BUCKET_DIMENSION_SIZE, BUCKET_DIMENSION_SIZE, bucketSize);
        this.bucketKeys  = new long[BUCKET_DIMENSION_SIZE][BUCKET_DIMENSION_SIZE][bucketSize];
        this.bucketIndex = new int[BUCKET_DIMENSION_SIZE][BUCKET_DIMENSION_SIZE];
        if (primeIndexes) {
            // prime the indexes to start at 0 on first #put;
            for (int x = 0; x < BUCKET_DIMENSION_SIZE; x++) {
                for (int z = 0; z < BUCKET_DIMENSION_SIZE; z++) {
                    bucketIndex[x][z] = bucketSize - 1;
                }
            }
        }
        this.indexer = (x, z, i) -> {
            final int xformedIndex = bucketIndex[x & BUCKET_BITMASK][z & BUCKET_BITMASK] - i;
            return xformedIndex < 0 ? xformedIndex + bucketSize : xformedIndex;
        };
    }

    private long getKey(final int x, final int z)
    {
        return (x & 0xffffffffL) | (z & 0xffffffffL) << 32;
    }

    private int nextIndex(final int x, final int z)
    {
        int index = bucketIndex[x][z];
        // increase the index by 1 until it reaches the end of the bucket, then reset to 0
        return bucketIndex[x][z] = (++index < bucketSize) ? index : 0;
    }

    public final void put(final int x, final int z, final T value)
    {
        final int bucketX   = x & BUCKET_BITMASK;
        final int bucketZ   = z & BUCKET_BITMASK;
        final int nextIndex = nextIndex(bucketX, bucketZ); // get the next index for adding a new element
        bucketKeys[bucketX][bucketZ][nextIndex] = getKey(x, z);
        bucketVals[bucketX][bucketZ][nextIndex] = value;
    }

    @Nullable
    public final T get(final int x, final int z)
    {
        final int bucketX = x & BUCKET_BITMASK;
        final int bucketZ = z & BUCKET_BITMASK;
        for (int i = 0; i < bucketSize; i++) {
            final int index = indexer.getIndex(x, z, i);
            final T   value = bucketVals[bucketX][bucketZ][index];
            if (value != null && bucketKeys[bucketX][bucketZ][index] == getKey(x, z)) {
                return value;
            }
        }
        return null;
    }

    @FunctionalInterface
    private interface ReverseHistoryIndexer
    {
        /**
         * This method should transform a passed int value into the nth bucketIndex in reverse look-up from the
         * current index of the bucket[x][z].
         *
         * eg: If a bucket of size 8 has an current index of 3,
         * then this method should return:
         *
         * Passed Val | Return Val
         *      0           3
         *      1           2
         *      2           1
         *      3           0
         *      4           7
         *      5           6
         *      6           5
         *      7           4
         *
         * @param x X coordinate (used for bucket selection)
         * @param y Y coordinate (used for bucket selection)
         * @param index the nth index to be transformed
         * @return the nth transformed index
         */
        int getIndex(final int x, final int y, final int index);
    }
}
