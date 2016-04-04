package rtg.world.biome.realistic.vanilla;

import net.minecraft.init.Biomes;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import rtg.util.noise.CellNoise;
import rtg.util.noise.OpenSimplexNoise;
import rtg.world.gen.feature.WorldGenBlob;
import rtg.world.gen.feature.WorldGenLog;
import rtg.world.gen.surface.SurfaceBase;
import rtg.world.gen.surface.vanilla.SurfaceVanillaIcePlains;
import rtg.world.gen.terrain.TerrainBase;

import java.util.Random;

public class RealisticBiomeVanillaIcePlains extends RealisticBiomeVanillaBase {

    public RealisticBiomeVanillaIcePlains() {
        super(
                Biomes.icePlains,
                Biomes.frozenRiver
        );
    }

    @Override
    protected SurfaceBase initSurface() {
        return new SurfaceVanillaIcePlains(this);
    }

    @Override
    protected TerrainBase initTerrain() {
        return new TerrainBase() {
            @Override
            public float generateNoise(OpenSimplexNoise simplex, CellNoise cell, int x, int y, float border, float river) {
                float base = 62;
                float b = simplex.noise2(x / 24f, y / 24f) * 0.25f;
                b *= river;
                float n = simplex.noise2(x / 16f, y / 16f) * 10f - 9f;
                n = (n < 0) ? 0f : n;
                b += n;
                return base + b;
            }
        };
    }

    @Override
    public void rDecorate(World world, Random rand, int chunkX, int chunkY, OpenSimplexNoise simplex, CellNoise cell, float strength, float river) {

        /**
         * Using rDecorateSeedBiome() to partially decorate the biome? If so, then comment out this method.
         */
        //rOreGenSeedBiome(world, rand, new BlockPos(chunkX, 0, chunkY), simplex, cell, strength, river, baseBiome);

        rDecorateSeedBiome(world, rand, chunkX, chunkY, simplex, cell, strength, river, baseBiome);

        if (river > 0.86f) {
            for (int j = 0; j < 5f * strength; j++) {
                int i1 = chunkX + rand.nextInt(16) + 8;
                int j1 = chunkY + rand.nextInt(16) + 8;
                int k1 = world.getHeight(new BlockPos(i1, 0, j1)).getY();

                if (k1 < 64 && rand.nextInt(16) == 0) {
                    (new WorldGenBlob(Blocks.packed_ice, 0, rand)).generate(world, rand, new BlockPos(i1, k1, j1));
                }
            }
        }

        if (this.config.DECORATION_LOG.get()) {

            if (rand.nextInt((int) (24f / strength)) == 0) {
                int j6 = chunkX + rand.nextInt(16) + 8;
                int k10 = chunkY + rand.nextInt(16) + 8;
                int z52 = world.getHeight(new BlockPos(j6, 0, k10)).getY();

                WorldGenerator worldgenerator = new WorldGenLog(1, rand.nextInt(6), false);
                worldgenerator.generate(world, rand, new BlockPos(j6, z52, k10));
            }
        }
    }
}
