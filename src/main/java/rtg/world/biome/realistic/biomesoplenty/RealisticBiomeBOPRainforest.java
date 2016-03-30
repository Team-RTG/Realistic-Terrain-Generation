package rtg.world.biome.realistic.biomesoplenty;

import biomesoplenty.api.biome.BOPBiomes;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Biomes;
import net.minecraft.world.biome.BiomeGenBase;
import rtg.api.biome.BiomeConfig;
import rtg.world.gen.surface.biomesoplenty.SurfaceBOPRainforest;
import rtg.world.gen.terrain.biomesoplenty.TerrainBOPRainforest;

public class RealisticBiomeBOPRainforest extends RealisticBiomeBOPBase {
    public static BiomeGenBase bopBiome = BOPBiomes.rainforest.get();

    public static IBlockState topBlock = bopBiome.topBlock;
    public static IBlockState fillerBlock = bopBiome.fillerBlock;

    public RealisticBiomeBOPRainforest() {
        super(
                bopBiome, Biomes.river,
                new TerrainBOPRainforest(90f, 300f),
                new SurfaceBOPRainforest(config, topBlock, fillerBlock, false, null, 1.3f)
        );
    }
}
