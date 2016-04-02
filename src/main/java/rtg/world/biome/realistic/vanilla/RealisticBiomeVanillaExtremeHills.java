package rtg.world.biome.realistic.vanilla;

import net.minecraft.init.Biomes;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenBush;
import net.minecraft.world.gen.feature.WorldGenPumpkin;
import net.minecraft.world.gen.feature.WorldGenerator;
import rtg.api.config.BiomeConfigHelper;
import rtg.api.config.ConfigProperty;
import rtg.util.noise.CellNoise;
import rtg.util.noise.OpenSimplexNoise;
import rtg.world.gen.feature.WorldGenBlob;
import rtg.world.gen.feature.WorldGenGrass;
import rtg.world.gen.feature.WorldGenLog;
import rtg.world.gen.feature.tree.WorldGenTreeRTGPineEuro;
import rtg.world.gen.feature.tree.WorldGenTreeRTGShrub;
import rtg.world.gen.surface.SurfaceBase;
import rtg.world.gen.surface.vanilla.SurfaceVanillaExtremeHills;
import rtg.world.gen.terrain.TerrainBase;

import java.util.Random;

public class RealisticBiomeVanillaExtremeHills extends RealisticBiomeVanillaBase {

    public final ConfigProperty.PropertyBool DECORATION_LOG;

    public RealisticBiomeVanillaExtremeHills() {

        super(Biomes.extremeHills, Biomes.river);
        this.generatesEmeralds = true;
        this.noLakes = true;
        this.noWaterFeatures = true;
        this.DECORATION_LOG = config.addBool(BiomeConfigHelper.DECORATION_LOG).setDefault(true);
    }

    @Override
    protected SurfaceBase initSurface() {
        return new SurfaceVanillaExtremeHills(config, topBlock, fillerBlock, Blocks.grass.getDefaultState(), Blocks.dirt.getDefaultState(), 60f, -0.14f, 14f, 0.25f);
    }

    @Override
    protected TerrainBase initTerrain() {
        return new TerrainBase() {
            @Override
            public float generateNoise(OpenSimplexNoise simplex, CellNoise cell, int x, int y, float border, float river) {
                return terrainHighland(x, y, simplex, cell, river, 10f, 200f, 120, 10f);
            }
        };
    }

    @Override
    public void rDecorate(World world, Random rand, int chunkX, int chunkY, OpenSimplexNoise simplex, CellNoise cell, float strength, float river) {

        /**
         * Using rDecorateSeedBiome() to partially decorate the biome? If so, then comment out this method.
         */
        rOreGenSeedBiome(world, rand, new BlockPos(chunkX, 1, chunkY), simplex, cell, strength, river, baseBiome);

        // boulders
        for (int l = 0; l < 3f * strength; ++l) {
            int i1 = chunkX + rand.nextInt(16) + 8;
            int j1 = chunkY + rand.nextInt(16) + 8;
            int k1 = world.getHeight(new BlockPos(i1, 1, j1)).getY();

            if (k1 < 95 && rand.nextInt(16) == 0) {
                (new WorldGenBlob(Blocks.mossy_cobblestone, 0, rand)).generate(world, rand, new BlockPos(i1, k1, j1));
            }
        }

        // trees
        float l = simplex.noise2(chunkX / 100f, chunkY / 100f) * 6f + 0.8f;
        for (int b1 = 0; b1 < l * 4f * strength; b1++) {
            int j6 = chunkX + rand.nextInt(16) + 8;
            int k10 = chunkY + rand.nextInt(16) + 8;
            int z52 = world.getHeight(new BlockPos(j6, 1, k10)).getY();

            if (rand.nextInt(24) == 0) {
                WorldGenerator worldgenerator = new WorldGenTreeRTGPineEuro();
                worldgenerator.generate(world, rand, new BlockPos(j6, z52, k10));
            }
        }

        if (DECORATION_LOG.get()) {

            if (l > 0f && rand.nextInt(6) == 0) {
                int x22 = chunkX + rand.nextInt(16) + 8;
                int z22 = chunkY + rand.nextInt(16) + 8;
                int y22 = world.getHeight(new BlockPos(x22, 1, z22)).getY();
                (new WorldGenLog(1, 3 + rand.nextInt(4), false)).generate(world, rand, new BlockPos(x22, y22, z22));
            }
        }

        for (int b = 0; b < 2f * strength; b++) {
            int i1 = chunkX + rand.nextInt(16) + 8;
            int j1 = chunkY + rand.nextInt(16) + 8;
            int k1 = world.getHeight(new BlockPos(i1, 1, j1)).getY();
            if (rand.nextInt(10) == 0) {
                (new WorldGenTreeRTGShrub(rand.nextInt(5) + 4, rand.nextInt(2), rand.nextInt(2))).generate(world, rand, new BlockPos(i1, k1, j1));
            } else {
                (new WorldGenTreeRTGShrub(rand.nextInt(4) + 1, rand.nextInt(2), rand.nextInt(2))).generate(world, rand, new BlockPos(i1, k1, j1));
            }
        }

        if (rand.nextInt((int) (3f / strength)) == 0) {
            int k15 = chunkX + rand.nextInt(16) + 8;
            int k17 = rand.nextInt(64) + 64;
            int k20 = chunkY + rand.nextInt(16) + 8;

            if (rand.nextBoolean()) {
                (new WorldGenBush(Blocks.brown_mushroom)).generate(world, rand, new BlockPos(k15, k17, k20));
            } else {
                (new WorldGenBush(Blocks.red_mushroom)).generate(world, rand, new BlockPos(k15, k17, k20));
            }
        }

        if (rand.nextInt((int) (20f / strength)) == 0) {
            int j16 = chunkX + rand.nextInt(16) + 8;
            int j18 = rand.nextInt(128);
            int j21 = chunkY + rand.nextInt(16) + 8;
            (new WorldGenPumpkin()).generate(world, rand, new BlockPos(j16, j18, j21));
        }

        for (int l14 = 0; l14 < 10f * strength; l14++) {
            int l19 = chunkX + rand.nextInt(16) + 8;
            int k22 = rand.nextInt(128);
            int j24 = chunkY + rand.nextInt(16) + 8;
            (new WorldGenGrass(Blocks.tallgrass, 1)).generate(world, rand, new BlockPos(l19, k22, j24));
        }
    }
}
