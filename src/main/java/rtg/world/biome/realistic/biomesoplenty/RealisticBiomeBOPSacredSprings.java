package rtg.world.biome.realistic.biomesoplenty;

import biomesoplenty.api.biome.BOPBiomes;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Biomes;
import net.minecraft.world.biome.BiomeGenBase;
import rtg.world.gen.surface.biomesoplenty.SurfaceBOPSacredSprings;
import rtg.world.gen.terrain.biomesoplenty.TerrainBOPSacredSprings;

public class RealisticBiomeBOPSacredSprings extends RealisticBiomeBOPBase {
    public static BiomeGenBase bopBiome = BOPBiomes.sacred_springs.get();

    public static IBlockState topBlock = bopBiome.topBlock;
    public static IBlockState fillerBlock = bopBiome.fillerBlock;

    public RealisticBiomeBOPSacredSprings() {
        super(
                bopBiome, Biomes.river,
                new TerrainBOPSacredSprings(150f, 60f, 68f),
                new SurfaceBOPSacredSprings(config, topBlock, fillerBlock)
        );
        noWaterFeatures = true;
    }
}
