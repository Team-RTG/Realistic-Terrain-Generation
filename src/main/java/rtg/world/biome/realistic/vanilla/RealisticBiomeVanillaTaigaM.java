package rtg.world.biome.realistic.vanilla;

import net.minecraft.init.Biomes;
import net.minecraft.world.biome.Biome;

import rtg.api.biome.BiomeConfig;
import rtg.api.biome.vanilla.config.BiomeConfigVanillaTaigaM;
import rtg.world.biome.deco.collection.DecoCollectionTaiga;
import rtg.world.gen.surface.vanilla.SurfaceVanillaTaigaM;
import rtg.world.gen.terrain.vanilla.TerrainVanillaTaigaM;

public class RealisticBiomeVanillaTaigaM extends RealisticBiomeVanillaBase {

    public static Biome biome = Biomes.MUTATED_TAIGA;
    public static Biome river = Biomes.RIVER;

    public RealisticBiomeVanillaTaigaM(BiomeConfig config) {

        super(config, biome, river,
            new TerrainVanillaTaigaM(70f, 180f, 7f, 100f, 38f, 160f, 68f),
            new SurfaceVanillaTaigaM(config, biome.topBlock, biome.fillerBlock)
        );

        this.noLakes = true;

        this.addDecoCollection(new DecoCollectionTaiga(this.config._boolean(BiomeConfigVanillaTaigaM.decorationLogsId), 10f));
    }
}
