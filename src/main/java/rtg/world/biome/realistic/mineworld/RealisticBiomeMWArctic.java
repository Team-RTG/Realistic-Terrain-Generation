package rtg.world.biome.realistic.mineworld;

import net.minecraft.init.Biomes;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.Biome;

import rtg.api.biome.BiomeConfig;
import rtg.world.biome.deco.DecoBaseBiomeDecorations;
import rtg.world.gen.surface.mineworld.SurfaceMWArctic;
import rtg.world.gen.terrain.mineworld.TerrainMWArctic;

public class RealisticBiomeMWArctic extends RealisticBiomeMWBase {

    public static Biome river = Biomes.FROZEN_RIVER;

    public RealisticBiomeMWArctic(Biome biome, BiomeConfig config) {

        super(config, biome, river,
            new TerrainMWArctic(),
            new SurfaceMWArctic(config,
                biome.topBlock, //Block top
                biome.fillerBlock, //Block filler,
                Blocks.SNOW.getDefaultState(), //IBlockState mixTop,
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
