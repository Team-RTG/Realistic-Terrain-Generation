package rtg.world.biome.realistic.agriculturalrevolution;

import net.minecraft.init.Biomes;
import net.minecraft.world.biome.Biome;

import rtg.api.biome.BiomeConfig;
import rtg.world.biome.deco.DecoBaseBiomeDecorations;
import rtg.world.gen.surface.agriculturalrevolution.SurfaceARCoralReef;
import rtg.world.gen.terrain.agriculturalrevolution.TerrainARCoralReef;

public class RealisticBiomeARCoralReef extends RealisticBiomeARBase {

    public static Biome river = Biomes.RIVER;

    public RealisticBiomeARCoralReef(Biome biome, BiomeConfig config) {

        super(config, biome, river,
            new TerrainARCoralReef(false, -10f, 0f, 0f, 0f, 30f),
            new SurfaceARCoralReef(config, biome.topBlock, biome.fillerBlock)
        );

        this.waterSurfaceLakeChance = 0;
        this.lavaSurfaceLakeChance = 0;
        this.noLakes = true;
        this.noWaterFeatures = true;

        DecoBaseBiomeDecorations decoBaseBiomeDecorations = new DecoBaseBiomeDecorations();
        this.addDeco(decoBaseBiomeDecorations);
    }
}
