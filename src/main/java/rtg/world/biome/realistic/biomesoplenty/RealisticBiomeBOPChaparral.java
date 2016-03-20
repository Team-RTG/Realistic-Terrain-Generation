package rtg.world.biome.realistic.biomesoplenty;

import biomesoplenty.api.biome.BOPBiomes;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Biomes;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.BiomeGenBase;
import rtg.api.biome.BiomeConfig;
import rtg.world.gen.surface.biomesoplenty.SurfaceBOPChaparral;
import rtg.world.gen.terrain.biomesoplenty.TerrainBOPChaparral;

public class RealisticBiomeBOPChaparral extends RealisticBiomeBOPBase {
    public static BiomeGenBase bopBiome = BOPBiomes.chaparral.get();

    public static IBlockState topBlock = bopBiome.topBlock;
    public static IBlockState fillerBlock = bopBiome.fillerBlock;

    public RealisticBiomeBOPChaparral(BiomeConfig config) {
        super(config,
                bopBiome, Biomes.river,
                new TerrainBOPChaparral(),
                new SurfaceBOPChaparral(config, topBlock, fillerBlock, Blocks.sand.getDefaultState(), 26f, 0.35f)
        );
    }
}
