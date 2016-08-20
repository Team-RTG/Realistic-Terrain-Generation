package rtg.world.biome.realistic.biomesoplenty;

import net.minecraft.init.Biomes;
import net.minecraft.world.biome.Biome;

import biomesoplenty.api.biome.BOPBiomes;

import rtg.api.biome.BiomeConfig;
import rtg.world.biome.deco.DecoBaseBiomeDecorations;
import rtg.world.gen.surface.biomesoplenty.SurfaceBOPSnowyForest;
import rtg.world.gen.terrain.biomesoplenty.TerrainBOPSnowyForest;

public class RealisticBiomeBOPSnowyForest extends RealisticBiomeBOPBase {

    public static Biome biome = BOPBiomes.snowy_forest.get();
    public static Biome river = Biomes.FROZEN_RIVER;

    public RealisticBiomeBOPSnowyForest(BiomeConfig config) {

        super(config, biome, river,
            new TerrainBOPSnowyForest(58f, 69f, 28f),
            new SurfaceBOPSnowyForest(config,
                biome.topBlock, //Block top
                biome.fillerBlock, //Block filler,
                biome.topBlock, //IBlockState mixTop,
                biome.fillerBlock, //IBlockState mixFill,
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
