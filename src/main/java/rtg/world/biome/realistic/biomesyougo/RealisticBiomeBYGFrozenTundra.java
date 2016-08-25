package rtg.world.biome.realistic.biomesyougo;

import net.minecraft.init.Biomes;
import net.minecraft.world.biome.Biome;

import rtg.api.biome.BiomeConfig;
import rtg.world.biome.deco.DecoBaseBiomeDecorations;
import rtg.world.gen.surface.biomesyougo.SurfaceBYGFrozenTundra;
import rtg.world.gen.terrain.biomesyougo.TerrainBYGFrozenTundra;

public class RealisticBiomeBYGFrozenTundra extends RealisticBiomeBYGBase {

    public static Biome river = Biomes.FROZEN_RIVER;

    public RealisticBiomeBYGFrozenTundra(Biome biome, BiomeConfig config) {

        super(config, biome, river,
            new TerrainBYGFrozenTundra(),
            new SurfaceBYGFrozenTundra(config, biome.topBlock, biome.fillerBlock)
        );

        DecoBaseBiomeDecorations decoBaseBiomeDecorations = new DecoBaseBiomeDecorations();
        this.addDeco(decoBaseBiomeDecorations);
    }
}
