package rtg.world.biome.realistic.biomesoplenty;

import net.minecraft.init.Biomes;
import net.minecraft.world.biome.Biome;

import biomesoplenty.api.biome.BOPBiomes;

import rtg.api.biome.BiomeConfig;
import rtg.world.biome.deco.DecoBaseBiomeDecorations;
import rtg.world.gen.surface.biomesoplenty.SurfaceBOPTropicalRainforest;
import rtg.world.gen.terrain.biomesoplenty.TerrainBOPTropicalRainforest;

public class RealisticBiomeBOPTropicalRainforest extends RealisticBiomeBOPBase {

    public static Biome biome = BOPBiomes.tropical_rainforest.get();
    public static Biome river = Biomes.RIVER;

    public RealisticBiomeBOPTropicalRainforest(BiomeConfig config) {

        super(config, biome, river,
            new TerrainBOPTropicalRainforest(0f, 60f, 68f, 200f),
            new SurfaceBOPTropicalRainforest(config, biome.topBlock, biome.fillerBlock)
        );

        DecoBaseBiomeDecorations decoBaseBiomeDecorations = new DecoBaseBiomeDecorations();
        this.addDeco(decoBaseBiomeDecorations);
    }
}
