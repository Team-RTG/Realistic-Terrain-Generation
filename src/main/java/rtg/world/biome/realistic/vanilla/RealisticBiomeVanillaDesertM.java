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
import rtg.world.gen.surface.vanilla.SurfaceVanillaDesertM;
import rtg.world.gen.terrain.vanilla.TerrainVanillaDesertM;

public class RealisticBiomeVanillaDesertM extends RealisticBiomeVanillaBase {

    public static Biome biome = Biomes.MUTATED_DESERT;
    public static Biome river = Biomes.RIVER;

    public RealisticBiomeVanillaDesertM(BiomeConfig config) {

        super(config, biome, river,
            new TerrainVanillaDesertM(10f, 20f, 68f, 200f),
            new SurfaceVanillaDesertM(config, Blocks.SAND.getDefaultState(), Blocks.SANDSTONE.getDefaultState(), 0f, 1.5f, 60f, 65f, 1.5f)
        );

        this.waterSurfaceLakeChance = 0;
        this.noLakes = true;

        this.addDecoCollection(new DecoCollectionDesertRiver());
        this.addDecoCollection(new DecoCollectionDesert());
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
