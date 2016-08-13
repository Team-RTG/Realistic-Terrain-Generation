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
public interface RTGBiomeProvider {
    public int[] getBiomesGens(int par1, int par2, int par3, int par4);
    public float getRiverStrength(int x, int y);
    public Biome getBiomeGenAt(int par1, int par2);
    public RealisticBiomeBase getBiomeDataAt(int par1, int par2);
    public boolean isBorderlessAt(int x, int y);
}
