/*
 * Available under the Lesser GPL License 3.0
 */

package rtg.api.world.biome;

import net.minecraft.world.biome.Biome;

/**
 *
 * @author Zeno410
 */
// TODO: [1.12] !IMPORTANT! This interface should have methods from both the vanilla BiomeProvider (superclass) and our subclass so we can access them all.
public interface IBiomeProviderRTG
{
// TODO: [1.12] To be removed. This is an anitquated method that does the same thing as BiomeProvider#getBiomes but returns a int[] instead of Biome[].
    @Deprecated int[] getBiomesGens(int worldX, int worldZ, int areaX, int areaZ);

// TODO: [1.12] To be moved to ChunkGeneratorRTG
    @Deprecated float getRiverStrength(int worldX, int worldZ);

// TODO: [1.12] To be removed.
    @Deprecated Biome getBiomeGenAt(int worldX, int worldZ);

// TODO: [1.12] To be removed.
    @Deprecated IRealisticBiome getBiomeDataAt(int worldX, int worldZ);

// TODO: [1.12] To be moved. The only usage is in the volcano generator.
    @Deprecated boolean isBorderlessAt(int chunkX, int chunkZ);
}
