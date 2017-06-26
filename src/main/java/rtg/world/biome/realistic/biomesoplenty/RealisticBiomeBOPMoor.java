package rtg.world.biome.realistic.biomesoplenty;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Biomes;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.ChunkPrimer;

import biomesoplenty.api.biome.BOPBiomes;
import biomesoplenty.api.block.BOPBlocks;

import rtg.api.config.BiomeConfig;
import rtg.api.util.CliffCalculator;
import rtg.api.world.IRTGWorld;
import rtg.api.world.surface.SurfaceBase;
import rtg.api.world.terrain.TerrainBase;

public class RealisticBiomeBOPMoor extends RealisticBiomeBOPBase {

    public static Biome biome = BOPBiomes.moor.get();
    public static Biome river = Biomes.RIVER;

    public RealisticBiomeBOPMoor() {

        super(biome, river);
    }

    @Override
    public void initConfig() {

        this.getConfig().ALLOW_RIVERS.set(false);
    }

    @Override
    public TerrainBase initTerrain() {

        return new TerrainBOPMoor(68f, 75f, 16f);
    }

    public class TerrainBOPMoor extends TerrainBase {

        private float minHeight;
        private float maxHeight;
        private float hillStrength;
        private float lift;

        // 63f, 80f, 30f

        public TerrainBOPMoor(float minHeight, float maxHeight, float hillStrength) {

            this.minHeight = minHeight;
            this.maxHeight = (maxHeight > rollingHillsMaxHeight) ? rollingHillsMaxHeight : ((maxHeight < this.minHeight) ? rollingHillsMaxHeight : maxHeight);
            this.hillStrength = hillStrength;
            lift = minHeight - 62f;
        }

        @Override
        public float generateNoise(IRTGWorld rtgWorld, int x, int y, float border, float river) {

            return terrainRollingHills(x, y, rtgWorld.simplex(), river, hillStrength, maxHeight, groundNoise, groundNoiseAmplitudeHills, lift);
        }
    }

    @Override
    public SurfaceBase initSurface() {

        return new SurfaceBOPMoor(config, BOPBlocks.grass.getDefaultState(), BOPBlocks.dirt.getDefaultState());
    }

    public class SurfaceBOPMoor extends SurfaceBase {

        public SurfaceBOPMoor(BiomeConfig config, IBlockState top, IBlockState filler) {

            super(config, top, filler);
        }

        @Override
        public void paintTerrain(ChunkPrimer primer, int i, int j, int x, int z, int depth, IRTGWorld rtgWorld, float[] noise, float river, Biome[] base) {

            Random rand = rtgWorld.rand();
            float c = CliffCalculator.calc(x, z, noise);
            boolean cliff = c > 1.4f ? true : false;

            for (int k = 255; k > -1; k--) {
                Block b = primer.getBlockState(x, k, z).getBlock();
                if (b == Blocks.AIR) {
                    depth = -1;
                }
                else if (b == Blocks.STONE) {
                    depth++;

                    if (cliff) {
                        if (depth > -1 && depth < 2) {
                            if (rand.nextInt(3) == 0) {

                                primer.setBlockState(x, k, z, hcCobble(rtgWorld, i, j, x, z, k));
                            }
                            else {

                                primer.setBlockState(x, k, z, hcStone(rtgWorld, i, j, x, z, k));
                            }
                        }
                        else if (depth < 10) {
                            primer.setBlockState(x, k, z, hcStone(rtgWorld, i, j, x, z, k));
                        }
                    }
                    else {
                        if (depth == 0 && k > 61) {
                            primer.setBlockState(x, k, z, topBlock);
                        }
                        else if (depth < 4) {
                            primer.setBlockState(x, k, z, fillerBlock);
                        }
                    }
                }
            }
        }
    }

    @Override
    public void initDecos() {
        DecoBOPBaseBiomeDecorations decoBOPBaseBiomeDecorations = new DecoBOPBaseBiomeDecorations();
        this.addDeco(decoBOPBaseBiomeDecorations);
    }
}
