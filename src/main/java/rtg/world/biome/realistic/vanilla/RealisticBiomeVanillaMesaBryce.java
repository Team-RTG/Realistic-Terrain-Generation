package rtg.world.biome.realistic.vanilla;

import net.minecraft.init.Biomes;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraft.world.gen.feature.*;
import rtg.util.noise.CellNoise;
import rtg.util.noise.OpenSimplexNoise;
import rtg.world.biome.realistic.RealisticBiomeBase;
import rtg.world.gen.feature.WorldGenCacti;
import rtg.world.gen.surface.SurfaceBase;
import rtg.world.gen.surface.SurfaceRiverOasis;
import rtg.world.gen.surface.vanilla.SurfaceVanillaMesaBryce;
import rtg.world.gen.terrain.TerrainBase;

import java.util.Random;

public class RealisticBiomeVanillaMesaBryce extends RealisticBiomeVanillaBase {
    public static BiomeGenBase standardBiome = Biomes.mesa;
    public static BiomeGenBase mutationBiome = BiomeGenBase.getBiome(RealisticBiomeBase.getIdForBiome(standardBiome) + MUTATION_ADDEND);

    public RealisticBiomeVanillaMesaBryce() {

        super(
                mutationBiome,
                Biomes.river
        );
    }

    @Override
        protected SurfaceBase initSurface() {
        return new SurfaceVanillaMesaBryce(this, 0);
    }

    @Override
    protected TerrainBase initTerrain() {
        return new TerrainBase() {
            @Override
            public float generateNoise(OpenSimplexNoise simplex, CellNoise cell, int x, int y, float border, float river) {
                return terrainBryce(x, y, simplex, river, 20f, border);
            }
        };
    }

    @Override
    public void rDecorate(World world, Random rand, int chunkX, int chunkY, OpenSimplexNoise simplex, CellNoise cell, float strength, float river) {

        /**
         * Using rDecorateSeedBiome() to partially decorate the biome? If so, then comment out this method.
         */
        rOreGenSeedBiome(world, rand, new BlockPos(chunkX, 0, chunkY), simplex, cell, strength, river, baseBiome);
        for (int l = 0; l < 1; ++l) {
            int i1 = chunkX + rand.nextInt(16) + 8;
            int j1 = chunkY + rand.nextInt(16) + 8;
            int k1 = world.getHeight(new BlockPos(i1, 0, j1)).getY();
            if (k1 < 70) {
                (new WorldGenBlockBlob(Blocks.red_sandstone, 0)).generate(world, rand, new BlockPos(i1, k1, j1));
            }
        }

        if (river > 0.8f) {
            for (int b1 = 0; b1 < 10; b1++) {
                int j6 = chunkX + rand.nextInt(16) + 8;
                int k10 = chunkY + rand.nextInt(16) + 8;
                int z52 = world.getHeight(new BlockPos(j6, 0, k10)).getY();

                WorldGenerator worldgenerator;
                worldgenerator = new WorldGenShrub(Blocks.log.getDefaultState(), Blocks.leaves.getDefaultState());
                worldgenerator.generate(world, rand, new BlockPos(j6, z52, k10));
            }
        } else {
            for (int b1 = 0; b1 < 5; b1++) {
                int j6 = chunkX + rand.nextInt(16) + 8;
                int k10 = chunkY + rand.nextInt(16) + 8;
                int z52 = world.getHeight(new BlockPos(j6, 0, k10)).getY();

                WorldGenerator worldgenerator;
                worldgenerator = new WorldGenShrub(Blocks.log.getDefaultState(), Blocks.leaves.getDefaultState());
                worldgenerator.generate(world, rand, new BlockPos(j6, z52, k10));
            }

            if (rand.nextInt(3) == 0) {
                int i18 = chunkX + rand.nextInt(16) + 8;
                int i23 = chunkY + rand.nextInt(16) + 8;
                (new WorldGenReed()).generate(world, rand, new BlockPos(i18, 60 + rand.nextInt(8), i23));
            }

            for (int i15 = 0; i15 < 5; i15++) {
                int i17 = chunkX + rand.nextInt(16) + 8;
                int i20 = rand.nextInt(160);
                int l22 = chunkY + rand.nextInt(16) + 8;
                (new WorldGenDeadBush()).generate(world, rand, new BlockPos(i17, i20, l22));
            }

            for (int k18 = 0; k18 < 25; k18++) {
                int k21 = chunkX + rand.nextInt(16) + 8;
                int j23 = rand.nextInt(160);
                int k24 = chunkY + rand.nextInt(16) + 8;
                (new WorldGenCacti(true)).generate(world, rand, new BlockPos(k21, j23, k24));
            }
        }
    }

    @Override
    public void rReplace(ChunkPrimer primer, int i, int j, int x, int y, int depth, World world, Random rand,
                         OpenSimplexNoise simplex, CellNoise cell, float[] noise, float river, BiomeGenBase[] base) {

        this.getSurface().paintTerrain(primer, i, j, x, y, depth, world, rand, simplex, cell, noise, river, base);

        SurfaceBase riverSurface = new SurfaceRiverOasis(this);
        riverSurface.paintTerrain(primer, i, j, x, y, depth, world, rand, simplex, cell, noise, river, base);
    }
}
