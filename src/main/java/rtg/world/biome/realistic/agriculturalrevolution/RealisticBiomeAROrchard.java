package rtg.world.biome.realistic.agriculturalrevolution;

import net.minecraft.init.Biomes;
import net.minecraft.world.biome.Biome;

import rtg.api.biome.BiomeConfig;
import rtg.world.biome.deco.DecoBaseBiomeDecorations;
import rtg.world.gen.surface.agriculturalrevolution.SurfaceAROrchard;
import rtg.world.gen.terrain.agriculturalrevolution.TerrainAROrchard;

public class RealisticBiomeAROrchard extends RealisticBiomeARBase {

    public static Biome river = Biomes.RIVER;

    public RealisticBiomeAROrchard(Biome biome, BiomeConfig config) {

        super(config, biome, river,
            new TerrainAROrchard(58f, 67f, 25f),
            new SurfaceAROrchard(config, biome.topBlock, biome.fillerBlock)
        );

        DecoBaseBiomeDecorations decoBaseBiomeDecorations = new DecoBaseBiomeDecorations();
        this.addDeco(decoBaseBiomeDecorations);
    }
}
