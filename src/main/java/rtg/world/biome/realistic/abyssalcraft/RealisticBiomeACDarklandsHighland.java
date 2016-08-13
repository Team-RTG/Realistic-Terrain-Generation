package rtg.world.biome.realistic.abyssalcraft;

import net.minecraft.init.Biomes;
import net.minecraft.world.biome.Biome;

import rtg.api.biome.BiomeConfig;
import rtg.world.biome.deco.DecoBaseBiomeDecorations;
import rtg.world.gen.surface.abyssalcraft.SurfaceACDarklandsHighland;
import rtg.world.gen.terrain.abyssalcraft.TerrainACDarklandsHighland;

public class RealisticBiomeACDarklandsHighland extends RealisticBiomeACBase {

    public RealisticBiomeACDarklandsHighland(Biome acBiome, BiomeConfig config) {

        super(config,
            acBiome,
            Biomes.RIVER,
            new TerrainACDarklandsHighland(10f, 120f, 10f, 200f),
            new SurfaceACDarklandsHighland(config, acBiome.topBlock, acBiome.fillerBlock, acBiome.topBlock, acBiome.fillerBlock, 60f, -0.14f, 14f, 0.25f)
        );

        this.noLakes = true;
        this.noWaterFeatures = true;

        DecoBaseBiomeDecorations decoBaseBiomeDecorations = new DecoBaseBiomeDecorations();
        this.addDeco(decoBaseBiomeDecorations);
    }
}
