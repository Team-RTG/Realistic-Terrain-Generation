package rtg.world.biome.realistic.biomesoplenty;

import net.minecraft.init.Biomes;
import net.minecraft.world.biome.Biome;

import biomesoplenty.api.biome.BOPBiomes;

import rtg.api.biome.BiomeConfig;
import rtg.util.CellNoise;
import rtg.util.OpenSimplexNoise;
import rtg.world.biome.deco.DecoBaseBiomeDecorations;
import rtg.world.gen.surface.biomesoplenty.SurfaceBOPRainforest;
import rtg.world.gen.terrain.TerrainBase;

public class RealisticBiomeBOPRainforest extends RealisticBiomeBOPBase {

    public static Biome biome = BOPBiomes.rainforest.get();
    public static Biome river = Biomes.RIVER;

    public RealisticBiomeBOPRainforest(BiomeConfig config) {

        super(config, biome, river,
            new SurfaceBOPRainforest(config, biome.topBlock, biome.fillerBlock, 1.3f)
        );

        DecoBaseBiomeDecorations decoBaseBiomeDecorations = new DecoBaseBiomeDecorations();
        this.addDeco(decoBaseBiomeDecorations);
    }

    @Override
    public TerrainBase initTerrain() {

        return new TerrainBOPRainforest(90f, 300f);
    }

    public class TerrainBOPRainforest extends TerrainBase {

        private float heigth;
        private float width;

        public TerrainBOPRainforest(float mountainHeight, float mountainWidth) {

            heigth = mountainHeight;
            width = mountainWidth;
        }

        @Override
        public float generateNoise(OpenSimplexNoise simplex, CellNoise cell, int x, int y, float border, float river) {

            return terrainSwampMountain(x, y, simplex, cell, river, width, heigth, 140f, 39f, 65f);
        }
    }
}
