package rtg.world.biome.realistic.biomesoplenty;

import biomesoplenty.api.biome.BOPBiomes;
import biomesoplenty.api.block.BOPBlocks;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Biomes;
import net.minecraft.world.biome.BiomeGenBase;
import rtg.api.biome.BiomeConfig;
import rtg.world.gen.surface.biomesoplenty.SurfaceBOPWetland;
import rtg.world.gen.terrain.biomesoplenty.TerrainBOPWetland;

public class RealisticBiomeBOPWetland extends RealisticBiomeBOPBase {
    public static BiomeGenBase bopBiome = BOPBiomes.wetland.get();

    public static IBlockState topBlock = BOPBlocks.grass.getDefaultState();
    public static IBlockState fillerBlock = BOPBlocks.dirt.getDefaultState();

    public RealisticBiomeBOPWetland(BiomeConfig config) {
        super(config,
                bopBiome, Biomes.river,
                new TerrainBOPWetland(),
                new SurfaceBOPWetland(config, topBlock, fillerBlock)
        );
    }
}
