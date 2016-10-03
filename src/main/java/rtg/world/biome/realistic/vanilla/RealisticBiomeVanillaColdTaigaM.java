package rtg.world.biome.realistic.vanilla;

import net.minecraft.init.Biomes;
import net.minecraft.world.biome.Biome;

import rtg.api.biome.BiomeConfig;
import rtg.api.biome.vanilla.config.BiomeConfigVanillaColdTaigaM;
import rtg.world.biome.deco.collection.DecoCollectionTaiga;
import rtg.world.gen.surface.vanilla.SurfaceVanillaColdTaigaM;
import rtg.world.gen.terrain.vanilla.TerrainVanillaColdTaigaM;

public class RealisticBiomeVanillaColdTaigaM extends RealisticBiomeVanillaBase {

    public static Biome biome = Biomes.MUTATED_TAIGA_COLD;
    public static Biome river = Biomes.FROZEN_RIVER;

    public RealisticBiomeVanillaColdTaigaM(BiomeConfig config) {

        super(config, biome, river,
            new TerrainVanillaColdTaigaM(),
            new SurfaceVanillaColdTaigaM(config, biome.topBlock, biome.fillerBlock)
        );

        this.noLakes = true;

        this.addDecoCollection(new DecoCollectionTaiga(this.config._boolean(BiomeConfigVanillaColdTaigaM.decorationLogsId), 8f));
    }
}
