package rtg.world.biome.realistic.biomesoplenty;

import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.BiomeGenBase;

import biomesoplenty.api.biome.BOPBiomes;

import rtg.api.biome.BiomeConfig;
import rtg.world.biome.deco.DecoBaseBiomeDecorations;
import rtg.world.gen.surface.biomesoplenty.SurfaceBOPColdDesert;
import rtg.world.gen.terrain.biomesoplenty.TerrainBOPColdDesert;

public class RealisticBiomeBOPColdDesert extends RealisticBiomeBOPBase {

    public static BiomeGenBase bopBiome = BOPBiomes.cold_desert.get();

    public static IBlockState topBlock = bopBiome.topBlock;
    public static IBlockState fillerBlock = bopBiome.fillerBlock;

    public RealisticBiomeBOPColdDesert(BiomeConfig config) {

        super(config,
            bopBiome, BiomeGenBase.frozenRiver,
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

        DecoBaseBiomeDecorations decoBaseBiomeDecorations = new DecoBaseBiomeDecorations();
        this.addDeco(decoBaseBiomeDecorations);
    }
}
