package rtg.world.biome.realistic.biomesoplenty;

import biomesoplenty.api.biome.BOPBiomes;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Biomes;
import net.minecraft.world.biome.BiomeGenBase;
import rtg.api.biome.BiomeConfig;
import rtg.world.gen.surface.biomesoplenty.SurfaceBOPKelpForest;
import rtg.world.gen.terrain.biomesoplenty.TerrainBOPKelpForest;

public class RealisticBiomeBOPKelpForest extends RealisticBiomeBOPBase {
    public static BiomeGenBase bopBiome = BOPBiomes.kelp_forest.get();

    public static IBlockState topBlock = bopBiome.topBlock;
    public static IBlockState fillerBlock = bopBiome.fillerBlock;

    public RealisticBiomeBOPKelpForest() {
        super(
                bopBiome, Biomes.river,
                new TerrainBOPKelpForest(false, -10f, 0f, 0f, 0f, 30f),
                new SurfaceBOPKelpForest(config, topBlock, fillerBlock)
        );
    }
}
