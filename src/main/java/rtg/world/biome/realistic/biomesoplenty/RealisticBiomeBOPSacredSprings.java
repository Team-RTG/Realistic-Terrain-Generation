package rtg.world.biome.realistic.biomesoplenty;

import net.minecraft.init.Biomes;
import net.minecraft.world.biome.Biome;

import biomesoplenty.api.biome.BOPBiomes;

import rtg.api.biome.BiomeConfig;
import rtg.util.CellNoise;
import rtg.util.OpenSimplexNoise;
import rtg.world.biome.deco.DecoBaseBiomeDecorations;
import rtg.world.gen.surface.biomesoplenty.SurfaceBOPSacredSprings;
import rtg.world.gen.terrain.TerrainBase;

public class RealisticBiomeBOPSacredSprings extends RealisticBiomeBOPBase {

    public static Biome biome = BOPBiomes.sacred_springs.get();
    public static Biome river = Biomes.RIVER;

    public RealisticBiomeBOPSacredSprings(BiomeConfig config) {

        super(config, biome, river,
            new rtg.world.gen.terrain.biomesoplenty.TerrainBOPSacredSprings(150f, 30f, 68f),
            new SurfaceBOPSacredSprings(config, biome.topBlock, biome.fillerBlock)
        );

        this.noWaterFeatures = true;
        this.waterSurfaceLakeChance = 2;

        DecoBaseBiomeDecorations decoBaseBiomeDecorations = new DecoBaseBiomeDecorations();
        this.addDeco(decoBaseBiomeDecorations);
    }

    @Override
    public TerrainBase initTerrain() {

        return new TerrainBOPSacredSprings(150f, 30f, 68f);
    }

    public class TerrainBOPSacredSprings extends TerrainBase {

        private float width;
        private float strength;
        private float lakeDepth;
        private float lakeWidth;
        private float terrainHeight;

        public TerrainBOPSacredSprings(float mountainWidth, float mountainStrength, float height) {

            width = mountainWidth;
            strength = mountainStrength;
            terrainHeight = height;
        }

        @Override
        public float generateNoise(OpenSimplexNoise simplex, CellNoise cell, int x, int y, float border, float river) {

            return terrainLonelyMountain(x, y, simplex, cell, river, strength, width, terrainHeight);

        }
    }
}
