package rtg.api.util;

import java.util.HashSet;

import net.minecraft.util.math.BlockPos;

/**
 * Created by WhichOnesPink on 04/01/2017.
 */
public class ChunkOreGenTracker {

    private HashSet<BlockPos> oreChunks = new HashSet<>();

    public ChunkOreGenTracker() {

    }

    public synchronized void addOreChunk(BlockPos pos) {
        oreChunks.add(pos);
    }

    public synchronized void removeOreChunk(BlockPos pos) {
        oreChunks.remove(pos);
    }

    public synchronized boolean hasGeneratedOres(BlockPos pos) {
        return this.oreChunks.contains(pos);
    }
}
