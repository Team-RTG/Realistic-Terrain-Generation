package rtg.world.biome.realistic.vanilla;

import net.minecraft.init.Biomes;
import net.minecraft.init.Blocks;

import rtg.api.biome.BiomeConfig;
import rtg.api.biome.vanilla.config.BiomeConfigVanillaColdTaigaHills;
import rtg.world.biome.deco.collection.DecoCollectionTaiga;
import rtg.world.gen.surface.vanilla.SurfaceVanillaColdTaigaHills;
import rtg.world.gen.terrain.vanilla.TerrainVanillaColdTaigaHills;

public class RealisticBiomeVanillaColdTaigaHills extends RealisticBiomeVanillaBase {

    public RealisticBiomeVanillaColdTaigaHills(BiomeConfig config) {

        super(config,
            Biomes.COLD_TAIGA_HILLS,
            Biomes.FROZEN_RIVER,
            new TerrainVanillaColdTaigaHills(),
            new SurfaceVanillaColdTaigaHills(config, Blocks.GRASS.getDefaultState(), Blocks.DIRT.getDefaultState(), true, Blocks.SAND.getDefaultState(), 0.2f)
        );

        this.noLakes = true;

        this.addDecoCollection(new DecoCollectionTaiga(this.config._boolean(BiomeConfigVanillaColdTaigaHills.decorationLogsId), 8f));
    }
}
