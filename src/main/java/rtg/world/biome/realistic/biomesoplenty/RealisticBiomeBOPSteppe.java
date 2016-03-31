package rtg.world.biome.realistic.biomesoplenty;

import biomesoplenty.api.biome.BOPBiomes;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Biomes;
import net.minecraft.world.biome.BiomeGenBase;
import rtg.world.gen.surface.biomesoplenty.SurfaceBOPSteppe;
import rtg.world.gen.terrain.biomesoplenty.TerrainBOPSteppe;

public class RealisticBiomeBOPSteppe extends RealisticBiomeBOPBase {
    public static BiomeGenBase bopBiome = BOPBiomes.steppe.get();

    public static IBlockState topBlock = bopBiome.topBlock;
    public static IBlockState fillerBlock = bopBiome.fillerBlock;

    public RealisticBiomeBOPSteppe() {
        super(
                bopBiome, Biomes.river,
                new TerrainBOPSteppe(65f, 68f, 30f),
                new SurfaceBOPSteppe(config, topBlock, fillerBlock)
        );
    }
}
