package rtg.world.biome.realistic.biomesoplenty;

import net.minecraft.init.Biomes;
import net.minecraft.world.biome.Biome;

import biomesoplenty.api.biome.BOPBiomes;

import rtg.api.biome.BiomeConfig;
import rtg.world.biome.deco.DecoBaseBiomeDecorations;
import rtg.world.gen.surface.biomesoplenty.SurfaceBOPRainforest;
import rtg.world.gen.terrain.biomesoplenty.TerrainBOPRainforest;

public class RealisticBiomeBOPRainforest extends RealisticBiomeBOPBase {

    public static Biome biome = BOPBiomes.rainforest.get();
    public static Biome river = Biomes.RIVER;

    public RealisticBiomeBOPRainforest(BiomeConfig config) {

        super(config, biome, river,
            new TerrainBOPRainforest(90f, 300f),
            new SurfaceBOPRainforest(config, biome.topBlock, biome.fillerBlock, 1.3f)
        );

        DecoBaseBiomeDecorations decoBaseBiomeDecorations = new DecoBaseBiomeDecorations();
        this.addDeco(decoBaseBiomeDecorations);
    }
}
