package rtg.world.biome.realistic.biomesoplenty;

import biomesoplenty.api.biome.BOPBiomes;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Biomes;
import net.minecraft.world.biome.BiomeGenBase;
import rtg.api.biome.BiomeConfig;
import rtg.world.gen.surface.biomesoplenty.SurfaceBOPWoodland;
import rtg.world.gen.terrain.biomesoplenty.TerrainBOPWoodland;

public class RealisticBiomeBOPWoodland extends RealisticBiomeBOPBase {
    public static BiomeGenBase bopBiome = BOPBiomes.woodland.get();

    public static IBlockState topBlock = bopBiome.topBlock;
    public static IBlockState fillerBlock = bopBiome.fillerBlock;

    public RealisticBiomeBOPWoodland(BiomeConfig config) {
        super(config,
                bopBiome, Biomes.river,
                new TerrainBOPWoodland(0f, 65f, 72f, 120f),
                new SurfaceBOPWoodland(config, topBlock, fillerBlock)
        );
    }
}
