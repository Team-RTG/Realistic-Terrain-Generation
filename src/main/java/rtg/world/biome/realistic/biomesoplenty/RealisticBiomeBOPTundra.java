package rtg.world.biome.realistic.biomesoplenty;

import biomesoplenty.api.biome.BOPBiomes;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Biomes;
import net.minecraft.world.biome.BiomeGenBase;
import rtg.api.biome.BiomeConfig;
import rtg.world.gen.surface.biomesoplenty.SurfaceBOPTundra;
import rtg.world.gen.terrain.biomesoplenty.TerrainBOPTundra;

public class RealisticBiomeBOPTundra extends RealisticBiomeBOPBase {
    public static BiomeGenBase bopBiome = BOPBiomes.tundra.get();

    public static IBlockState topBlock = bopBiome.topBlock;
    public static IBlockState fillerBlock = bopBiome.fillerBlock;

    public RealisticBiomeBOPTundra() {
        super(
                bopBiome, Biomes.river,
                new TerrainBOPTundra(),
                new SurfaceBOPTundra(config, topBlock, fillerBlock)
        );
    }
}
