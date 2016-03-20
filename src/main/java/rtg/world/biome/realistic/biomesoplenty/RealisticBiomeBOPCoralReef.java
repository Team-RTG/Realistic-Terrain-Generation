package rtg.world.biome.realistic.biomesoplenty;

import biomesoplenty.api.biome.BOPBiomes;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Biomes;
import net.minecraft.world.biome.BiomeGenBase;
import rtg.api.biome.BiomeConfig;
import rtg.world.gen.surface.biomesoplenty.SurfaceBOPCoralReef;
import rtg.world.gen.terrain.biomesoplenty.TerrainBOPCoralReef;

public class RealisticBiomeBOPCoralReef extends RealisticBiomeBOPBase {
    public static BiomeGenBase bopBiome = BOPBiomes.coral_reef.get();

    public static IBlockState topBlock = bopBiome.topBlock;
    public static IBlockState fillerBlock = bopBiome.fillerBlock;

    public RealisticBiomeBOPCoralReef(BiomeConfig config) {
        super(config,
                bopBiome, Biomes.river,
                new TerrainBOPCoralReef(false, -10f, 0f, 0f, 0f, 30f),
                new SurfaceBOPCoralReef(config, topBlock, fillerBlock)
        );
    }
}
