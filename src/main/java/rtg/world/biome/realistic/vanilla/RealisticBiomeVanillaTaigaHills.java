package rtg.world.biome.realistic.vanilla;

import net.minecraft.init.Biomes;
import net.minecraft.world.biome.Biome;

import rtg.api.biome.BiomeConfig;
import rtg.api.biome.vanilla.config.BiomeConfigVanillaTaigaHills;
import rtg.util.CellNoise;
import rtg.util.OpenSimplexNoise;
import rtg.world.biome.deco.collection.DecoCollectionTaiga;
import rtg.world.gen.surface.vanilla.SurfaceVanillaTaigaHills;
import rtg.world.gen.terrain.TerrainBase;

public class RealisticBiomeVanillaTaigaHills extends RealisticBiomeVanillaBase {

    public static Biome biome = Biomes.TAIGA_HILLS;
    public static Biome river = Biomes.RIVER;

    public RealisticBiomeVanillaTaigaHills(BiomeConfig config) {

        super(config, biome, river,
            new rtg.world.gen.terrain.vanilla.TerrainVanillaTaigaHills(),
            new SurfaceVanillaTaigaHills(config, biome.topBlock, biome.fillerBlock)
        );

        this.noLakes = true;

        this.addDecoCollection(new DecoCollectionTaiga(this.config._boolean(BiomeConfigVanillaTaigaHills.decorationLogsId), 10f));
    }

    @Override
    public TerrainBase initTerrain() {

        return new TerrainVanillaTaigaHills();
    }

    public class TerrainVanillaTaigaHills extends TerrainBase {

        private float hillStrength = 30f;

        public TerrainVanillaTaigaHills() {

            this(72f, 30f);
        }

        public TerrainVanillaTaigaHills(float bh, float hs) {

            base = bh;
            hillStrength = hs;
        }

        @Override
        public float generateNoise(OpenSimplexNoise simplex, CellNoise cell, int x, int y, float border, float river) {

            return terrainHighland(x, y, simplex, cell, river, 10f, 68f, hillStrength, base - 62f);
        }
    }
}
