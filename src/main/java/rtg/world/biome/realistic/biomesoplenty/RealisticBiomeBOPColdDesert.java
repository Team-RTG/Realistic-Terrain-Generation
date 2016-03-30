package rtg.world.biome.realistic.biomesoplenty;

import biomesoplenty.api.biome.BOPBiomes;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Biomes;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.BiomeGenBase;
import rtg.api.biome.BiomeConfig;
import rtg.world.gen.surface.biomesoplenty.SurfaceBOPColdDesert;
import rtg.world.gen.terrain.biomesoplenty.TerrainBOPColdDesert;

public class RealisticBiomeBOPColdDesert extends RealisticBiomeBOPBase {
    public static BiomeGenBase bopBiome = BOPBiomes.cold_desert.get();

    public static IBlockState topBlock = bopBiome.topBlock;
    public static IBlockState fillerBlock = bopBiome.fillerBlock;

    public RealisticBiomeBOPColdDesert() {
        super(
                bopBiome, Biomes.frozenRiver,
                new TerrainBOPColdDesert(),
                new SurfaceBOPColdDesert(config,
                        Blocks.snow.getDefaultState(), //Block top
                        fillerBlock, //Block filler,
                        Blocks.snow.getDefaultState(), //IBlockState mixTop,
                        fillerBlock, //IBlockState mixFill,
                        80f, //float mixWidth,
                        -0.15f, //float mixHeight,
                        10f, //float smallWidth,
                        0.5f //float smallStrength
                )
        );
    }
}
