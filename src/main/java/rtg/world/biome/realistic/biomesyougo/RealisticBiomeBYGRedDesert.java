package rtg.world.biome.realistic.biomesyougo;

import java.util.Random;

import net.minecraft.init.Biomes;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.ChunkPrimer;

import rtg.api.biome.BiomeConfig;
import rtg.config.rtg.ConfigRTG;
import rtg.util.CellNoise;
import rtg.util.OpenSimplexNoise;
import rtg.world.biome.deco.DecoBaseBiomeDecorations;
import rtg.world.biome.deco.collection.DecoCollectionDesertRiver;
import rtg.world.gen.surface.biomesyougo.SurfaceBYGRedDesert;
import rtg.world.gen.terrain.TerrainBase;

public class RealisticBiomeBYGRedDesert extends RealisticBiomeBYGBase {

    public static Biome river = Biomes.RIVER;

    public RealisticBiomeBYGRedDesert(Biome biome, BiomeConfig config) {

        super(config, biome, river,
            new SurfaceBYGRedDesert(config, biome.topBlock, biome.fillerBlock, 0f, 1.5f, 60f, 65f, 1.5f, biome.fillerBlock, 0.10f)
        );

        this.waterSurfaceLakeChance = 0;
        this.noLakes = true;

        this.addDecoCollection(new DecoCollectionDesertRiver());

        DecoBaseBiomeDecorations decoBaseBiomeDecorations = new DecoBaseBiomeDecorations();
        this.addDeco(decoBaseBiomeDecorations);
    }

    @Override
    public TerrainBase initTerrain() {

        return new TerrainBYGRedDesert();
    }

    public class TerrainBYGRedDesert extends TerrainBase {

        public TerrainBYGRedDesert() {

            super(64);
        }

        @Override
        public float generateNoise(OpenSimplexNoise simplex, CellNoise cell, int x, int y, float border, float river) {
            //return terrainPolar(x, y, simplex, river);
            float duneHeight = (minDuneHeight + (float) ConfigRTG.duneHeight);

            duneHeight *= (1f + simplex.octave(2).noise2((float) x / 330f, (float) y / 330f)) / 2f;

            float stPitch = 200f;    // The higher this is, the more smoothly dunes blend with the terrain
            float stFactor = duneHeight;
            float hPitch = 70;    // Dune scale
            float hDivisor = 40;

            return terrainPolar(x, y, simplex, river, stPitch, stFactor, hPitch, hDivisor, base) +
                groundNoise(x, y, 1f, simplex);
        }
    }

    @Override
    public void rReplace(ChunkPrimer primer, int i, int j, int x, int y, int depth, World world, Random rand, OpenSimplexNoise simplex, CellNoise cell, float[] noise, float river, Biome[] base) {

        this.rReplaceRiverSurface(primer, i, j, x, y, depth, world, rand, simplex, cell, noise, river, base);
    }
}
