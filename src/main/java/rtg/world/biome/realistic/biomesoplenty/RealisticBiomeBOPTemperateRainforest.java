package rtg.world.biome.realistic.biomesoplenty;

import net.minecraft.init.Biomes;
import net.minecraft.world.biome.Biome;

import biomesoplenty.api.biome.BOPBiomes;

import rtg.api.biome.BiomeConfig;
import rtg.util.CellNoise;
import rtg.util.OpenSimplexNoise;
import rtg.world.biome.deco.DecoBaseBiomeDecorations;
import rtg.world.gen.surface.biomesoplenty.SurfaceBOPTemperateRainforest;
import rtg.world.gen.terrain.TerrainBase;

public class RealisticBiomeBOPTemperateRainforest extends RealisticBiomeBOPBase {

    public static Biome biome = BOPBiomes.temperate_rainforest.get();
    public static Biome river = Biomes.RIVER;

    public RealisticBiomeBOPTemperateRainforest(BiomeConfig config) {

        super(config, biome, river,
            new rtg.world.gen.terrain.biomesoplenty.TerrainBOPTemperateRainforest(),
            new SurfaceBOPTemperateRainforest(config, biome.topBlock, biome.fillerBlock, 0.45f)
        );

        DecoBaseBiomeDecorations decoBaseBiomeDecorations = new DecoBaseBiomeDecorations();
        this.addDeco(decoBaseBiomeDecorations);
    }

    @Override
    public TerrainBase initTerrain() {

        return new TerrainBOPTemperateRainforest();
    }

    public class TerrainBOPTemperateRainforest extends TerrainBase {

        public TerrainBOPTemperateRainforest() {

        }

        @Override
        public float generateNoise(OpenSimplexNoise simplex, CellNoise cell, int x, int y, float border, float river) {

            return terrainPlains(x, y, simplex, river, 160f, 10f, 60f, 100f, 65f);
        }
    }
}
