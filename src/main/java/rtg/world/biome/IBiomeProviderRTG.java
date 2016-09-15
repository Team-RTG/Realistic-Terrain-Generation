/*
 * Available under the Lesser GPL License 3.0
 */

package rtg.world.biome;

import net.minecraft.world.biome.Biome;

import rtg.world.biome.realistic.RealisticBiomeBase;

/**
 *
 * @author Zeno410
 */
public interface IBiomeProviderRTG {
    int[] getBiomesGens(int x, int z, int par3, int par4);
    float getRiverStrength(int x, int y);
    Biome getBiomeGenAt(int par1, int par2);
    RealisticBiomeBase getBiomeDataAt(int par1, int par2);
    boolean isBorderlessAt(int x, int y);
}
