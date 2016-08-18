package rtg.world.biome.realistic.biomesoplenty;

import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.BiomeGenBase;

import biomesoplenty.api.biome.BOPBiomes;

import rtg.api.biome.BiomeConfig;
import rtg.world.biome.deco.DecoBaseBiomeDecorations;
import rtg.world.gen.surface.biomesoplenty.SurfaceBOPOutback;
import rtg.world.gen.terrain.biomesoplenty.TerrainBOPOutback;

public class RealisticBiomeBOPOutback extends RealisticBiomeBOPBase {

    public static BiomeGenBase bopBiome = BOPBiomes.outback.get();

    public static IBlockState topBlock = bopBiome.topBlock;
    public static IBlockState fillerBlock = bopBiome.fillerBlock;

    public RealisticBiomeBOPOutback(BiomeConfig config) {

        super(config,
            bopBiome, BiomeGenBase.river,
            new TerrainBOPOutback(65f, 50f, 10f),
            new SurfaceBOPOutback(config,
                Blocks.grass.getDefaultState(), //Block top
                fillerBlock, //Block filler,
                topBlock, //IBlockState mixTop,
                fillerBlock, //IBlockState mixFill,
                40f, //float mixWidth, 
                -0.15f, //float mixHeight,
                10f, //float smallWidth, 
                0.5f //float smallStrength
            )
        );

        DecoBaseBiomeDecorations decoBaseBiomeDecorations = new DecoBaseBiomeDecorations();
        this.addDeco(decoBaseBiomeDecorations);
    }
}
