package rtg.world.biome.realistic.vanilla;

import net.minecraft.init.Biomes;
import net.minecraft.world.biome.Biome;

import rtg.api.biome.BiomeConfig;
import rtg.api.biome.vanilla.config.BiomeConfigVanillaTaigaM;
import rtg.util.CellNoise;
import rtg.util.OpenSimplexNoise;
import rtg.world.biome.deco.collection.DecoCollectionTaiga;
import rtg.world.gen.surface.vanilla.SurfaceVanillaTaigaM;
import rtg.world.gen.terrain.TerrainBase;

public class RealisticBiomeVanillaTaigaM extends RealisticBiomeVanillaBase {

    public static Biome biome = Biomes.MUTATED_TAIGA;
    public static Biome river = Biomes.RIVER;

    public RealisticBiomeVanillaTaigaM(BiomeConfig config) {

        super(config, biome, river,
            new rtg.world.gen.terrain.vanilla.TerrainVanillaTaigaM(70f, 180f, 7f, 100f, 38f, 160f, 68f),
            new SurfaceVanillaTaigaM(config, biome.topBlock, biome.fillerBlock)
        );

        this.noLakes = true;

        this.addDecoCollection(new DecoCollectionTaiga(this.config._boolean(BiomeConfigVanillaTaigaM.decorationLogsId), 10f));
    }

    @Override
    public TerrainBase initTerrain() {

        return new TerrainVanillaTaigaM(70f, 180f, 7f, 100f, 38f, 160f, 68f);
    }

    public class TerrainVanillaTaigaM extends TerrainBase {

        private float hHeight;
        private float hWidth;
        private float vHeight;
        private float vWidth;
        private float lHeight;
        private float lWidth;
        private float bHeight;

        public TerrainVanillaTaigaM(float hillHeight, float hillWidth, float varHeight, float varWidth, float lakeHeight, float lakeWidth, float baseHeight) {

            hHeight = hillHeight;
            hWidth = hillWidth;

            vHeight = varHeight;
            vWidth = varWidth;

            lHeight = lakeHeight;
            lWidth = lakeWidth;

            bHeight = baseHeight;
        }

        @Override
        public float generateNoise(OpenSimplexNoise simplex, CellNoise cell, int x, int y, float border, float river) {

            return terrainGrasslandHills(x, y, simplex, cell, river, vWidth, vHeight, hWidth, hHeight, bHeight);
        }
    }
}
