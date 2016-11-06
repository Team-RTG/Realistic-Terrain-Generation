package rtg.world.biome.realistic.vanilla;

import java.util.Random;

import net.minecraft.init.Biomes;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.ChunkPrimer;

import rtg.api.biome.BiomeConfig;
import rtg.config.rtg.ConfigRTG;
import rtg.util.CellNoise;
import rtg.util.OpenSimplexNoise;
import rtg.world.biome.deco.collection.DecoCollectionDesert;
import rtg.world.biome.deco.collection.DecoCollectionDesertRiver;
import rtg.world.gen.surface.vanilla.SurfaceVanillaDesert;
import rtg.world.gen.terrain.TerrainBase;
import rtg.world.gen.terrain.vanilla.TerrainVanillaDesert;

public class RealisticBiomeVanillaDesert extends RealisticBiomeVanillaBase {

    public static Biome biome = Biomes.DESERT;
    public static Biome river = Biomes.RIVER;

    public RealisticBiomeVanillaDesert(BiomeConfig config) {

        super(config, biome, river,
            new TerrainVanillaDesert(),
            new SurfaceVanillaDesert(config, biome.topBlock, biome.fillerBlock)
        );

        this.waterSurfaceLakeChance = 0;
        this.noLakes = true;

        this.addDecoCollection(new DecoCollectionDesertRiver());
        this.addDecoCollection(new DecoCollectionDesert());
    }

    @Override
    public TerrainBase initTerrain() {

        return new TerrainBase(64f) {

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
        };
    }

    @Override
    public void rReplace(ChunkPrimer primer, int i, int j, int x, int y, int depth, World world, Random rand, OpenSimplexNoise simplex, CellNoise cell, float[] noise, float river, Biome[] base) {

        this.rReplaceRiverSurface(primer, i, j, x, y, depth, world, rand, simplex, cell, noise, river, base);
    }

    @Override
    public Biome beachBiome() {
        return this.beachBiome(Biomes.BEACH);
    }
}
