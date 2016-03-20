package rtg.world.biome.realistic.biomesoplenty;

import biomesoplenty.api.biome.BOPBiomes;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Biomes;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.BiomeGenBase;
import rtg.api.biome.BiomeConfig;
import rtg.world.gen.surface.biomesoplenty.SurfaceBOPGlacier;
import rtg.world.gen.terrain.biomesoplenty.TerrainBOPGlacier;

public class RealisticBiomeBOPGlacier extends RealisticBiomeBOPBase {
    public static BiomeGenBase bopBiome = BOPBiomes.glacier.get();

    public static IBlockState topBlock = bopBiome.topBlock;
    public static IBlockState fillerBlock = bopBiome.fillerBlock;

    public RealisticBiomeBOPGlacier(BiomeConfig config) {
        super(config,
                bopBiome, Biomes.frozenRiver,
                new TerrainBOPGlacier(230f, 100f, 0f),
                new SurfaceBOPGlacier(config, topBlock, fillerBlock, topBlock, fillerBlock, Blocks.packed_ice.getDefaultState(), Blocks.ice.getDefaultState(), 60f,
                        -0.14f, 14f, 0.25f)
        );
    }
}
