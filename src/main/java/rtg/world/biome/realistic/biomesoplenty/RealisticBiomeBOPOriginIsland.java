package rtg.world.biome.realistic.biomesoplenty;

import biomesoplenty.api.biome.BOPBiomes;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Biomes;
import net.minecraft.world.biome.BiomeGenBase;
import rtg.api.biome.BiomeConfig;
import rtg.world.gen.surface.biomesoplenty.SurfaceBOPOriginIsland;
import rtg.world.gen.terrain.biomesoplenty.TerrainBOPOriginIsland;

public class RealisticBiomeBOPOriginIsland extends RealisticBiomeBOPBase {
    public static BiomeGenBase bopBiome = BOPBiomes.origin_island.get();

    public static IBlockState topBlock = bopBiome.topBlock;
    public static IBlockState fillerBlock = bopBiome.fillerBlock;

    public RealisticBiomeBOPOriginIsland() {
        super(
                bopBiome, Biomes.river,
                new TerrainBOPOriginIsland(65f, 80f, 38f),
                new SurfaceBOPOriginIsland(config, topBlock, fillerBlock)
        );
    }
}
