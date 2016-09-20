package rtg.world.biome.realistic.vanilla;

import java.util.Random;

import net.minecraft.init.Biomes;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.ChunkPrimer;

import rtg.api.biome.BiomeConfig;
import rtg.util.CellNoise;
import rtg.util.OpenSimplexNoise;
import rtg.world.biome.deco.collection.DecoCollectionDesert;
import rtg.world.biome.deco.collection.DecoCollectionDesertRiver;
import rtg.world.gen.surface.SurfaceBase;
import rtg.world.gen.surface.SurfaceRiverOasis;
import rtg.world.gen.surface.vanilla.SurfaceVanillaDesertHills;
import rtg.world.gen.terrain.vanilla.TerrainVanillaDesertHills;

public class RealisticBiomeVanillaDesertHills extends RealisticBiomeVanillaBase {

    public static Biome biome = Biomes.DESERT_HILLS;
    public static Biome river = Biomes.RIVER;

    public RealisticBiomeVanillaDesertHills(BiomeConfig config) {

        super(config, biome, river,
            new TerrainVanillaDesertHills(10f, 80f, 68f, 200f),
            new SurfaceVanillaDesertHills(config, Blocks.SAND.getDefaultState(), Blocks.SANDSTONE.getDefaultState(), 0f, 1.5f, 60f, 65f, 1.5f)
        );

        this.waterSurfaceLakeChance = 0;
        this.noLakes = true;

        this.addDecoCollection(new DecoCollectionDesertRiver());
        this.addDecoCollection(new DecoCollectionDesert());
    }

    @Override
    public void rReplace(ChunkPrimer primer, int i, int j, int x, int y, int depth, World world, Random rand,
                         OpenSimplexNoise simplex, CellNoise cell, float[] noise, float river, Biome[] base) {

        this.getSurface().paintTerrain(primer, i, j, x, y, depth, world, rand, simplex, cell, noise, river, base);

        SurfaceBase riverSurface = new SurfaceRiverOasis(this.config);
        riverSurface.paintTerrain(primer, i, j, x, y, depth, world, rand, simplex, cell, noise, river, base);
    }
}
