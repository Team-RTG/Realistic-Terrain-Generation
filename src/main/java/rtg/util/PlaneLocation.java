package rtg.util;


import com.google.common.collect.Lists;
import net.minecraft.util.math.ChunkPos;

/**
 * @author Zeno410
 * @author srs_bsns
 * @deprecated started @ 2016.09.13
 */
@SuppressWarnings("deprecation")
public abstract class PlaneLocation {

    public abstract int getX();
    public abstract int getZ();

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 71 * hash + this.getX();
        hash = 71 * hash + this.getZ();
        return hash;
    }

    @Override
    public boolean equals(final Object obj) {
        return !((obj == null) || (!(obj instanceof PlaneLocation))) &&
            this.getX() == ((PlaneLocation) obj).getX() && this.getZ() == ((PlaneLocation) obj).getZ();
    }

    @Override
    public String toString() { return Lists.newArrayList(getX(), getZ()).toString(); }

    /**
     * A subclass of PlaneLocation which allows the vars to be changed.
     * Intended for probing hash sets and maps
     *
     * @deprecated started @ 2016.09.13
     */
    public static class Probe extends ChunkPos {
        private int x;
        private int z;
        public Probe(int x, int z) { super(x, z); this.x=x; this.z=z; }
        public int getX() {return this.x;}
        public int getZ() {return this.z;}
        public void setX(int x) {this.x = x;}
        public void setZ(int z) {this.z = z;}
    }

    /**
     * @deprecated started @ 2016.09.13
     */
    public static class Invariant extends PlaneLocation {
        private final int x;
        private final int z;

        /**
         * @param x
         * @param z
         * @deprecated
         */
        public Invariant(int x, int z) { this.x = x; this.z = z; }
        public int getX() {return this.x;}
        public int getZ() {return this.z;}
    }
}