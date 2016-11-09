package rtg.world.biome.realistic.vanilla;

import net.minecraft.init.Biomes;
import net.minecraft.world.biome.Biome;

import rtg.api.biome.BiomeConfig;
import rtg.api.biome.vanilla.config.BiomeConfigVanillaColdTaiga;
import rtg.util.CellNoise;
import rtg.util.OpenSimplexNoise;
import rtg.world.biome.deco.collection.DecoCollectionTaiga;
import rtg.world.gen.surface.vanilla.SurfaceVanillaColdTaiga;
import rtg.world.gen.terrain.TerrainBase;

public class RealisticBiomeVanillaColdTaiga extends RealisticBiomeVanillaBase {

    public static Biome biome = Biomes.COLD_TAIGA;
    public static Biome river = Biomes.FROZEN_RIVER;

    public RealisticBiomeVanillaColdTaiga(BiomeConfig config) {

        super(config, biome, river,
            new rtg.world.gen.terrain.vanilla.TerrainVanillaColdTaiga(),
            new SurfaceVanillaColdTaiga(config, biome.topBlock, biome.fillerBlock)
        );

        this.addDecoCollection(new DecoCollectionTaiga(this.config._boolean(BiomeConfigVanillaColdTaiga.decorationLogsId), 8f));
    }

    @Override
    public TerrainBase initTerrain() {

        return new TerrainVanillaColdTaiga();
    }

    public class TerrainVanillaColdTaiga extends TerrainBase {

        public TerrainVanillaColdTaiga() {

        }

        public float generateNoise(OpenSimplexNoise simplex, CellNoise cell, int x, int y, float border, float river) {

            return terrainFlatLakes(x, y, simplex, river, 13f, 66f);
        }
    }
}
