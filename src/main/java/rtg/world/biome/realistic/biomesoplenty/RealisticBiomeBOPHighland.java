package rtg.world.biome.realistic.biomesoplenty;

import biomesoplenty.api.biome.BOPBiomes;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Biomes;
import net.minecraft.world.biome.BiomeGenBase;
import rtg.api.biome.BiomeConfig;
import rtg.world.gen.surface.biomesoplenty.SurfaceBOPHighland;
import rtg.world.gen.terrain.biomesoplenty.TerrainBOPHighland;

public class RealisticBiomeBOPHighland extends RealisticBiomeBOPBase {
    public static BiomeGenBase bopBiome = BOPBiomes.highland.get();

    public static IBlockState topBlock = bopBiome.topBlock;
    public static IBlockState fillerBlock = bopBiome.fillerBlock;

    public RealisticBiomeBOPHighland(BiomeConfig config) {
        super(config,
                bopBiome, Biomes.river,
                new TerrainBOPHighland(),
                new SurfaceBOPHighland(config, topBlock, fillerBlock)
        );
        this.generatesEmeralds = true;
        noWaterFeatures = true;
    }
}
