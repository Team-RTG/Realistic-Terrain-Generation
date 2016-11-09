package rtg.world.biome.realistic.vanilla;

import net.minecraft.init.Biomes;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.Biome;

import rtg.api.biome.BiomeConfig;
import rtg.api.biome.vanilla.config.BiomeConfigVanillaForestHills;
import rtg.util.BlockUtil;
import rtg.util.CellNoise;
import rtg.util.OpenSimplexNoise;
import rtg.world.biome.deco.collection.DecoCollectionForest;
import rtg.world.gen.surface.vanilla.SurfaceVanillaForestHills;
import rtg.world.gen.terrain.TerrainBase;

public class RealisticBiomeVanillaForestHills extends RealisticBiomeVanillaBase {

    public static Biome biome = Biomes.FOREST_HILLS;
    public static Biome river = Biomes.RIVER;

    public RealisticBiomeVanillaForestHills(BiomeConfig config) {

        super(config, biome, river,
            new SurfaceVanillaForestHills(config, Blocks.GRASS.getDefaultState(), Blocks.DIRT.getDefaultState(), 0f, 1.5f, 60f, 65f, 1.5f, BlockUtil.getStateDirt(2), 0.15f)
        );

        this.noLakes = true;

        this.addDecoCollection(new DecoCollectionForest(this.config._boolean(BiomeConfigVanillaForestHills.decorationLogsId)));
    }

    @Override
    public TerrainBase initTerrain() {

        return new TerrainVanillaForestHills();
    }

    public class TerrainVanillaForestHills extends TerrainBase {

        private float hillStrength = 30f;

        public TerrainVanillaForestHills() {

            this(72f, 30f);
        }

        public TerrainVanillaForestHills(float bh, float hs) {

            base = bh;
            hillStrength = hs;
        }

        @Override
        public float generateNoise(OpenSimplexNoise simplex, CellNoise cell, int x, int y, float border, float river) {

            return terrainHighland(x, y, simplex, cell, river, 10f, 68f, hillStrength, base - 62f);

        }
    }
}
