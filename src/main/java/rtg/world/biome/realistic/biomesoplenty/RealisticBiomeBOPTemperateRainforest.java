package rtg.world.biome.realistic.biomesoplenty;

import net.minecraft.init.Biomes;
import net.minecraft.world.biome.Biome;

import biomesoplenty.api.biome.BOPBiomes;

import rtg.api.biome.BiomeConfig;
import rtg.world.biome.deco.DecoBaseBiomeDecorations;
import rtg.world.gen.surface.biomesoplenty.SurfaceBOPTemperateRainforest;
import rtg.world.gen.terrain.biomesoplenty.TerrainBOPTemperateRainforest;

public class RealisticBiomeBOPTemperateRainforest extends RealisticBiomeBOPBase {

    public static Biome biome = BOPBiomes.temperate_rainforest.get();
    public static Biome river = Biomes.RIVER;

    public RealisticBiomeBOPTemperateRainforest(BiomeConfig config) {

        super(config, biome, river,
            new TerrainBOPTemperateRainforest(),
            new SurfaceBOPTemperateRainforest(config, biome.topBlock, biome.fillerBlock, false, null, 0.45f)
        );

        DecoBaseBiomeDecorations decoBaseBiomeDecorations = new DecoBaseBiomeDecorations();
        this.addDeco(decoBaseBiomeDecorations);
    }
}
