package rtg.world.biome.realistic.vanilla;

import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Biomes;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenIceSpike;
import net.minecraft.world.gen.feature.WorldGenerator;
import rtg.api.biome.BiomeConfig;
import rtg.api.biome.vanilla.config.BiomeConfigVanillaIceMountains;
import rtg.util.CellNoise;
import rtg.util.OpenSimplexNoise;
import rtg.world.gen.feature.WorldGenBlob;
import rtg.world.gen.feature.WorldGenLog;
import rtg.world.gen.feature.tree.WorldGenTreeRTGPine;
import rtg.world.gen.feature.tree.WorldGenTreeRTGPineSmall;
import rtg.world.gen.surface.vanilla.SurfaceVanillaIceMountains;
import rtg.world.gen.terrain.vanilla.TerrainVanillaIceMountains;

import java.util.Random;

public class RealisticBiomeVanillaIceMountains extends RealisticBiomeVanillaBase {

    public static IBlockState topBlock = Biomes.iceMountains.topBlock;
    public static IBlockState fillerBlock = Biomes.iceMountains.fillerBlock;

    public RealisticBiomeVanillaIceMountains(BiomeConfig config) {

        super(config,
                Biomes.iceMountains,
                Biomes.frozenRiver,
                new TerrainVanillaIceMountains(230f, 80f, 0f),
                new SurfaceVanillaIceMountains(config, topBlock, fillerBlock, Blocks.snow.getDefaultState(), Blocks.snow.getDefaultState(), Blocks.packed_ice.getDefaultState(), Blocks.ice.getDefaultState(), 60f,
                        -0.14f, 14f, 0.25f));
        this.noLakes = true;
    }

    @Override
    public void rDecorate(World world, Random rand, int chunkX, int chunkY, OpenSimplexNoise simplex, CellNoise cell, float strength, float river) {

        /**
         * Using rDecorateSeedBiome() to partially decorate the biome? If so, then comment out this method.
         */
        rOreGenSeedBiome(world, rand, new BlockPos(chunkX, 0, chunkY), simplex, cell, strength, river, baseBiome);

        for (int l = 0; l < 6f * strength; ++l) {
            int i1 = chunkX + rand.nextInt(16) + 8;
            int j1 = chunkY + rand.nextInt(16) + 8;
            int k1 = world.getHeight(new BlockPos(i1, 0, j1)).getY();

            if (k1 < 95 && rand.nextInt(16) == 0) {
                (new WorldGenBlob(Blocks.cobblestone, 0, rand)).generate(world, rand, new BlockPos(i1, k1, j1));
            }
        }

        float l = simplex.noise2(chunkX / 100f, chunkY / 100f) * 12f + 4f;
        for (int b1 = 0; b1 < l * strength; b1++) {
            int j6 = chunkX + rand.nextInt(16) + 8;
            int k10 = chunkY + rand.nextInt(16) + 8;
            int z52 = world.getHeight(new BlockPos(j6, 0, k10)).getY();

            if (z52 < 90) {
                WorldGenerator worldgenerator =
                        rand.nextInt(8) != 0 ? new WorldGenTreeRTGPine(4, rand.nextInt(4) == 0 ? 1 : 0)
                                : rand.nextInt(3) != 0 ? new WorldGenTreeRTGPineSmall(3 + rand.nextInt(6), 6 + rand.nextInt(8), 0)
                                : new WorldGenIceSpike();
                worldgenerator.generate(world, rand, new BlockPos(j6, z52, k10));
            } else if (z52 < 120) {
                WorldGenerator worldgenerator =
                        rand.nextInt(4) != 0 ? new WorldGenTreeRTGPineSmall(1 + rand.nextInt(3), 3 + rand.nextInt(5), rand.nextInt(2))
                                : new WorldGenIceSpike();
                worldgenerator.generate(world, rand, new BlockPos(j6, z52, k10));
            }
        }

        if (this.config.getPropertyById(BiomeConfigVanillaIceMountains.decorationLogsId).valueBoolean) {

            if (rand.nextInt((int) (12f / strength)) == 0) {
                int x22 = chunkX + rand.nextInt(16) + 8;
                int z22 = chunkY + rand.nextInt(16) + 8;
                int y22 = world.getHeight(new BlockPos(x22, 0, z22)).getY();
                if (y22 < 100) {
                    (new WorldGenLog(1, 3 + rand.nextInt(4), false)).generate(world, rand, new BlockPos(x22, y22, z22));
                }
            }
        }
    }
}
