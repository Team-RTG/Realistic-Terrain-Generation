package rtg.world.biome.realistic.biomesoplenty;

import biomesoplenty.api.biome.BOPBiomes;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Biomes;
import net.minecraft.world.biome.BiomeGenBase;
import rtg.api.biome.BiomeConfig;
import rtg.world.gen.surface.biomesoplenty.SurfaceBOPCrag;
import rtg.world.gen.terrain.biomesoplenty.TerrainBOPCrag;

public class RealisticBiomeBOPCrag extends RealisticBiomeBOPBase {
    public static BiomeGenBase bopBiome = BOPBiomes.crag.get();

    public static IBlockState topBlock = bopBiome.topBlock;
    public static IBlockState fillerBlock = bopBiome.fillerBlock;

    public RealisticBiomeBOPCrag(BiomeConfig config) {
        super(config,
                bopBiome, Biomes.river,
                new TerrainBOPCrag(false, new float[]{2.0f, 0.5f, 6.0f, 0.5f, 10.0f, 0.5f, 14.0f, 0.5f, 18.0f, 0.5f, 22.0f, 0.5f, 26.0f, 0.5f, 30.0f, 0.5f, 34.0f, 0.5f}, 40f, 1f, 1f, 0.5f, 120f),
                new SurfaceBOPCrag(config, topBlock, fillerBlock, topBlock)
        );
        this.generatesEmeralds = true;
        this.noLakes = true;
        this.noWaterFeatures = true;
    }
}
