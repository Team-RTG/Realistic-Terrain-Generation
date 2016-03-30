package rtg.world.biome.realistic.biomesoplenty;

import biomesoplenty.api.biome.BOPBiomes;
import biomesoplenty.api.block.BOPBlocks;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Biomes;
import net.minecraft.world.biome.BiomeGenBase;
import rtg.api.biome.BiomeConfig;
import rtg.world.gen.surface.biomesoplenty.SurfaceBOPDeadSwamp;
import rtg.world.gen.terrain.biomesoplenty.TerrainBOPDeadSwamp;

public class RealisticBiomeBOPDeadSwamp extends RealisticBiomeBOPBase {
    public static BiomeGenBase bopBiome = BOPBiomes.dead_swamp.get();

    public static IBlockState topBlock = BOPBlocks.grass.getDefaultState();
    public static IBlockState fillerBlock = BOPBlocks.dirt.getDefaultState();

    public RealisticBiomeBOPDeadSwamp() {
        super(
                bopBiome, Biomes.river,
                new TerrainBOPDeadSwamp(),
                new SurfaceBOPDeadSwamp(config, topBlock, fillerBlock)
        );
    }
}
