package rtg.world.biome.realistic.biomesoplenty;

import biomesoplenty.api.biome.BOPBiomes;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Biomes;
import net.minecraft.world.biome.BiomeGenBase;
import rtg.api.biome.BiomeConfig;
import rtg.world.gen.surface.biomesoplenty.SurfaceBOPMarsh;
import rtg.world.gen.terrain.biomesoplenty.TerrainBOPMarsh;

public class RealisticBiomeBOPMarsh extends RealisticBiomeBOPBase {
    public static BiomeGenBase bopBiome = BOPBiomes.marsh.get();

    public static IBlockState topBlock = bopBiome.topBlock;
    public static IBlockState fillerBlock = bopBiome.fillerBlock;

    public RealisticBiomeBOPMarsh(BiomeConfig config) {
        super(config,
                bopBiome, Biomes.river,
                new TerrainBOPMarsh(),
                new SurfaceBOPMarsh(config, topBlock, fillerBlock)
        );
    }
}
