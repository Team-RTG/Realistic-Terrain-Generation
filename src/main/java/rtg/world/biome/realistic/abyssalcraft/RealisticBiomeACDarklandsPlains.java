package rtg.world.biome.realistic.abyssalcraft;

import net.minecraft.init.Biomes;
import net.minecraft.world.biome.Biome;

import rtg.api.biome.BiomeConfig;
import rtg.world.biome.deco.DecoBaseBiomeDecorations;
import rtg.world.gen.surface.abyssalcraft.SurfaceACDarklandsPlains;
import rtg.world.gen.terrain.abyssalcraft.TerrainACDarklandsPlains;

public class RealisticBiomeACDarklandsPlains extends RealisticBiomeACBase {

    public RealisticBiomeACDarklandsPlains(Biome acBiome, BiomeConfig config) {

        super(config,
            acBiome,
            Biomes.RIVER,
            new TerrainACDarklandsPlains(),
            new SurfaceACDarklandsPlains(config, acBiome.topBlock, acBiome.fillerBlock)
        );

        DecoBaseBiomeDecorations decoBaseBiomeDecorations = new DecoBaseBiomeDecorations();
        this.addDeco(decoBaseBiomeDecorations);
    }
}
