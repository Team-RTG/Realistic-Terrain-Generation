package rtg.world.biome.realistic.vanilla;

import net.minecraft.init.Biomes;
import net.minecraft.world.biome.Biome;

import rtg.api.biome.BiomeConfig;
import rtg.api.biome.vanilla.config.BiomeConfigVanillaTaiga;
import rtg.util.CellNoise;
import rtg.util.OpenSimplexNoise;
import rtg.world.biome.deco.collection.DecoCollectionTaiga;
import rtg.world.gen.surface.vanilla.SurfaceVanillaTaiga;
import rtg.world.gen.terrain.TerrainBase;

public class RealisticBiomeVanillaTaiga extends RealisticBiomeVanillaBase {

    public static Biome biome = Biomes.TAIGA;
    public static Biome river = Biomes.RIVER;

    public RealisticBiomeVanillaTaiga(BiomeConfig config) {

        super(config, biome, river,
            new SurfaceVanillaTaiga(config, biome.topBlock, biome.fillerBlock)
        );

        this.addDecoCollection(new DecoCollectionTaiga(this.config._boolean(BiomeConfigVanillaTaiga.decorationLogsId), 10f));
    }

    @Override
    public TerrainBase initTerrain() {

        return new TerrainVanillaTaiga();
    }

    public class TerrainVanillaTaiga extends TerrainBase {

        public TerrainVanillaTaiga() {

        }

        public float generateNoise(OpenSimplexNoise simplex, CellNoise cell, int x, int y, float border, float river) {

            return terrainFlatLakes(x, y, simplex, river, 8f, 68f);
        }
    }
}
