/*
 */
package exterminatorjeff.undergroundbiomes.api;

import net.minecraft.world.chunk.IChunkProvider;

/**
 *
 * @author curtisadams
 */
public interface UBStrataColumnProvider {
    public UBStrataColumn strataColumn(int x, int z);
}
