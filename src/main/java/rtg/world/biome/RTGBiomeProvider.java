/*
 * Available under the Lesser GPL License 3.0
 */

package rtg.world.biome;

import net.minecraft.util.BlockPos;
import net.minecraft.world.biome.BiomeGenBase;
import rtg.world.biome.realistic.RealisticBiomeBase;

/**
 *
 * @author Zeno410
 */
public interface RTGBiomeProvider {
    public int[] getBiomesGens(int par1, int par2, int par3, int par4);
    public float getRiverStrength(BlockPos blockPos);
    public float calculateRiver(int x, int y, float st, float biomeHeight);
    public BiomeGenBase getBiomeGenAt(BlockPos blockPos);
    public RealisticBiomeBase getBiomeDataAt(BlockPos blockPos);
    public boolean isBorderlessAt(int x, int y);
}
