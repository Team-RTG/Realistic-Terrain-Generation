package rtg.world.biome.realistic.biomesoplenty;

import net.minecraft.init.Biomes;
import net.minecraft.world.biome.Biome;

import biomesoplenty.api.biome.BOPBiomes;

import rtg.api.biome.BiomeConfig;
import rtg.world.biome.deco.DecoBaseBiomeDecorations;
import rtg.world.gen.surface.biomesoplenty.SurfaceBOPXericShrubland;
import rtg.world.gen.terrain.biomesoplenty.TerrainBOPXericShrubland;

public class RealisticBiomeBOPXericShrubland extends RealisticBiomeBOPBase {

    public static Biome biome = BOPBiomes.xeric_shrubland.get();
    public static Biome river = Biomes.RIVER;

    public RealisticBiomeBOPXericShrubland(BiomeConfig config) {

        super(config, biome, river,
            new TerrainBOPXericShrubland(),
            new SurfaceBOPXericShrubland(config,
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
