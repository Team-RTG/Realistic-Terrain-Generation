package rtg.world.biome.realistic.biomesyougo;

import net.minecraft.init.Biomes;
import net.minecraft.world.biome.Biome;

import rtg.api.biome.BiomeConfig;
import rtg.world.biome.deco.DecoBaseBiomeDecorations;
import rtg.world.gen.surface.biomesyougo.SurfaceBYGMushroomMountains;
import rtg.world.gen.terrain.biomesyougo.TerrainBYGMushroomMountains;

public class RealisticBiomeBYGMushroomMountains extends RealisticBiomeBYGBase {

    public static Biome river = Biomes.RIVER;

    public RealisticBiomeBYGMushroomMountains(Biome biome, BiomeConfig config) {

        super(config, biome, river,
            new TerrainBYGMushroomMountains(230f, 100f, 68f),
            new SurfaceBYGMushroomMountains(config, biome.topBlock, biome.fillerBlock, 0f, 1.5f, 60f, 65f, 1.5f)
        );

        this.noLakes = true;
        this.noWaterFeatures = true;

        DecoBaseBiomeDecorations decoBaseBiomeDecorations = new DecoBaseBiomeDecorations();
        this.addDeco(decoBaseBiomeDecorations);
    }
}
