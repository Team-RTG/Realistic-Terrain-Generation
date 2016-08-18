package rtg.world.biome.realistic.biomesoplenty;

import net.minecraft.block.state.IBlockState;
import net.minecraft.world.biome.BiomeGenBase;

import biomesoplenty.api.biome.BOPBiomes;

import rtg.api.biome.BiomeConfig;
import rtg.world.biome.deco.DecoBaseBiomeDecorations;
import rtg.world.gen.surface.biomesoplenty.SurfaceBOPSnowyForest;
import rtg.world.gen.terrain.biomesoplenty.TerrainBOPSnowyForest;

public class RealisticBiomeBOPSnowyForest extends RealisticBiomeBOPBase {

    public static BiomeGenBase bopBiome = BOPBiomes.snowy_forest.get();

    public static IBlockState topBlock = bopBiome.topBlock;
    public static IBlockState fillerBlock = bopBiome.fillerBlock;

    public RealisticBiomeBOPSnowyForest(BiomeConfig config) {

        super(config,
            bopBiome, BiomeGenBase.frozenRiver,
            new TerrainBOPSnowyForest(58f, 69f, 28f),
            new SurfaceBOPSnowyForest(config,
                topBlock, //Block top
                fillerBlock, //Block filler,
                topBlock, //IBlockState mixTop,
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
