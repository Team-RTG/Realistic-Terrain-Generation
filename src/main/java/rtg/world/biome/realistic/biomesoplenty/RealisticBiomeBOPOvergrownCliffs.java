package rtg.world.biome.realistic.biomesoplenty;

import biomesoplenty.api.biome.BOPBiomes;
import biomesoplenty.api.block.BOPBlocks;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Biomes;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import rtg.api.config.biomesoplenty.config.BiomeConfigBOPOvergrownCliffs;
import rtg.util.noise.CellNoise;
import rtg.util.noise.OpenSimplexNoise;
import rtg.world.gen.feature.WorldGenLog;
import rtg.world.gen.surface.biomesoplenty.SurfaceBOPOvergrownCliffs;
import rtg.world.gen.terrain.biomesoplenty.TerrainBOPOvergrownCliffs;

import java.util.Random;

public class RealisticBiomeBOPOvergrownCliffs extends RealisticBiomeBOPBase {
    public static BiomeGenBase bopBiome = BOPBiomes.overgrown_cliffs.get();

    public static IBlockState topBlock = bopBiome.topBlock;
    public static IBlockState fillerBlock = bopBiome.fillerBlock;

    public RealisticBiomeBOPOvergrownCliffs() {
        super(
                bopBiome, Biomes.river,
                new TerrainBOPOvergrownCliffs(300f, 100f, 0f),
                new SurfaceBOPOvergrownCliffs(config, topBlock, fillerBlock, false, null, 0.95f)
        );
        this.generatesEmeralds = true;
    }

    @Override
    public void rDecorate(World world, Random rand, int chunkX, int chunkY, OpenSimplexNoise simplex, CellNoise cell, float strength, float river) {

        /**
         * Using rDecorateSeedBiome() to partially decorate the config? If so, then comment out this method.
         */
        //rOreGenSeedBiome(world, rand, new BlockPos(chunkX, 0, chunkY), simplex, cell, strength, river, baseBiome);

        float l = simplex.noise2(chunkX / 80f, chunkY / 80f) * 60f - 15f;

        if (this.config.getPropertyById(BiomeConfigBOPOvergrownCliffs.decorationLogsId).valueBoolean) {

            if (rand.nextInt((int) (12f / strength)) == 0) {
                int x22 = chunkX + rand.nextInt(16) + 8;
                int z22 = chunkY + rand.nextInt(16) + 8;
                int y22 = world.getHeight(new BlockPos(x22, 0, z22)).getY();

                if (y22 < 100) {
                    (new WorldGenLog(BOPBlocks.log_4, (byte) 0, Blocks.leaves, -1, 3 + rand.nextInt(4))).generate(world, rand, new BlockPos(x22, y22, z22));
                }
            }
        }

        rDecorateSeedBiome(world, rand, chunkX, chunkY, simplex, cell, strength, river, baseBiome);
    }
}
