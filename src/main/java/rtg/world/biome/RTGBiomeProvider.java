/*
 * Available under the Lesser GPL License 3.0
 */

package rtg.world.biome;

import rtg.world.biome.realistic.RealisticBiomeBase;

import net.minecraft.world.biome.BiomeGenBase;

/**
 *
 * @author Zeno410
 */
public interface RTGBiomeProvider {
    int[] getBiomesGens(int par1, int par2, int par3, int par4);
    float getRiverStrength(int x, int y);
    float calculateRiver(int x, int y, float st, float biomeHeight);
    BiomeGenBase getBiomeGenAt(int par1, int par2);
    RealisticBiomeBase getBiomeDataAt(int par1, int par2);
    boolean isBorderlessAt(int x, int y);
}
