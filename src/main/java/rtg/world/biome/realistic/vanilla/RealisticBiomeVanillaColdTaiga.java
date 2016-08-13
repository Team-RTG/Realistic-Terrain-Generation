package rtg.world.biome.realistic.vanilla;

import net.minecraft.init.Biomes;

import rtg.api.biome.BiomeConfig;
import rtg.api.biome.vanilla.config.BiomeConfigVanillaColdTaiga;
import rtg.world.biome.deco.collection.DecoCollectionTaiga;
import rtg.world.gen.surface.vanilla.SurfaceVanillaColdTaiga;
import rtg.world.gen.terrain.vanilla.TerrainVanillaColdTaiga;

public class RealisticBiomeVanillaColdTaiga extends RealisticBiomeVanillaBase {

    public RealisticBiomeVanillaColdTaiga(BiomeConfig config) {

        super(config,
            Biomes.COLD_TAIGA,
            Biomes.FROZEN_RIVER,
            new TerrainVanillaColdTaiga(),
            new SurfaceVanillaColdTaiga(config, Biomes.COLD_TAIGA.topBlock, Biomes.COLD_TAIGA.fillerBlock)
        );

        this.addDecoCollection(new DecoCollectionTaiga(this.config._boolean(BiomeConfigVanillaColdTaiga.decorationLogsId), 8f));
    }
}
