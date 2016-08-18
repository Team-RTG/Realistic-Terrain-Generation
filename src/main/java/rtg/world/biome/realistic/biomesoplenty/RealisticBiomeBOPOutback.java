package rtg.world.biome.realistic.biomesoplenty;

import net.minecraft.init.Biomes;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.Biome;

import biomesoplenty.api.biome.BOPBiomes;

import rtg.api.biome.BiomeConfig;
import rtg.world.biome.deco.DecoBaseBiomeDecorations;
import rtg.world.gen.surface.biomesoplenty.SurfaceBOPOutback;
import rtg.world.gen.terrain.biomesoplenty.TerrainBOPOutback;

public class RealisticBiomeBOPOutback extends RealisticBiomeBOPBase {

    public static Biome biome = BOPBiomes.outback.get();
    public static Biome river = Biomes.RIVER;

    public RealisticBiomeBOPOutback(BiomeConfig config) {

        super(config, biome, river,
            new TerrainBOPOutback(65f, 50f, 10f),
            new SurfaceBOPOutback(config,
                Blocks.GRASS.getDefaultState(), //Block top
                biome.fillerBlock, //Block filler,
                biome.topBlock, //IBlockState mixTop,
                biome.fillerBlock, //IBlockState mixFill,
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
