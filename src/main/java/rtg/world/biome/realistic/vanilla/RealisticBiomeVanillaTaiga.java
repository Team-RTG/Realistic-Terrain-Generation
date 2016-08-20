package rtg.world.biome.realistic.vanilla;

import net.minecraft.init.Biomes;
import net.minecraft.world.biome.Biome;

import rtg.api.biome.BiomeConfig;
import rtg.api.biome.vanilla.config.BiomeConfigVanillaTaiga;
import rtg.world.biome.deco.collection.DecoCollectionTaiga;
import rtg.world.gen.surface.vanilla.SurfaceVanillaTaiga;
import rtg.world.gen.terrain.vanilla.TerrainVanillaTaiga;

public class RealisticBiomeVanillaTaiga extends RealisticBiomeVanillaBase {

    public static Biome biome = Biomes.TAIGA;
    public static Biome river = Biomes.RIVER;

    public RealisticBiomeVanillaTaiga(BiomeConfig config) {

        super(config, biome, river,
            new TerrainVanillaTaiga(),
            new SurfaceVanillaTaiga(config, biome.topBlock, biome.fillerBlock)
        );

        this.addDecoCollection(new DecoCollectionTaiga(this.config._boolean(BiomeConfigVanillaTaiga.decorationLogsId), 10f));
    }
}
