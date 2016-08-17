package rtg.world.biome.realistic.vanilla;

import net.minecraft.init.Biomes;
import net.minecraft.world.biome.Biome;

import rtg.api.biome.BiomeConfig;
import rtg.api.biome.vanilla.config.BiomeConfigVanillaColdTaiga;
import rtg.world.biome.deco.collection.DecoCollectionTaiga;
import rtg.world.gen.surface.vanilla.SurfaceVanillaColdTaiga;
import rtg.world.gen.terrain.vanilla.TerrainVanillaColdTaiga;

public class RealisticBiomeVanillaColdTaiga extends RealisticBiomeVanillaBase {

    public static Biome biome = Biomes.COLD_TAIGA;
    public static Biome river = Biomes.FROZEN_RIVER;

    public RealisticBiomeVanillaColdTaiga(BiomeConfig config) {

        super(config, biome, river,
            new TerrainVanillaColdTaiga(),
            new SurfaceVanillaColdTaiga(config, biome.topBlock, biome.fillerBlock)
        );

        this.addDecoCollection(new DecoCollectionTaiga(this.config._boolean(BiomeConfigVanillaColdTaiga.decorationLogsId), 8f));
    }
}
