package rtg.world.biome.realistic.abyssalcraft;

import net.minecraft.world.biome.Biome;

import rtg.api.biome.BiomeConfig;
import rtg.world.biome.deco.DecoBaseBiomeDecorations;
import rtg.world.gen.surface.abyssalcraft.SurfaceACDarklandsMountains;
import rtg.world.gen.terrain.abyssalcraft.TerrainACDarklandsMountains;

public class RealisticBiomeACDarklandsMountains extends RealisticBiomeACBase {

    public RealisticBiomeACDarklandsMountains(Biome acBiome, BiomeConfig config) {

        super(config,
            acBiome,
            Biome.river,
            new TerrainACDarklandsMountains(120f, 100f),
            new SurfaceACDarklandsMountains(config, acBiome.topBlock, acBiome.fillerBlock, false, null, 0.2f)
        );

        this.noLakes = true;
        this.noWaterFeatures = true;

        DecoBaseBiomeDecorations decoBaseBiomeDecorations = new DecoBaseBiomeDecorations();
        this.addDeco(decoBaseBiomeDecorations);
    }
}
