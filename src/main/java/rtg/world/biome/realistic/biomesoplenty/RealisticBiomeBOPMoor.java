package rtg.world.biome.realistic.biomesoplenty;

import biomesoplenty.api.biome.BOPBiomes;
import biomesoplenty.api.block.BOPBlocks;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Biomes;
import net.minecraft.world.biome.BiomeGenBase;
import rtg.api.biome.BiomeConfig;
import rtg.world.gen.surface.biomesoplenty.SurfaceBOPMoor;
import rtg.world.gen.terrain.biomesoplenty.TerrainBOPMoor;

public class RealisticBiomeBOPMoor extends RealisticBiomeBOPBase {
    public static BiomeGenBase bopBiome = BOPBiomes.moor.get();

    public static IBlockState topBlock = BOPBlocks.grass.getDefaultState();
    public static IBlockState fillerBlock = BOPBlocks.dirt.getDefaultState();

    public RealisticBiomeBOPMoor(BiomeConfig config) {
        super(config,
                bopBiome, Biomes.river,
                new TerrainBOPMoor(73f, 79f, 32f),
                new SurfaceBOPMoor(config, topBlock, fillerBlock)
        );
    }
}
