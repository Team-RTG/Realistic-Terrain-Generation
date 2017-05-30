package rtg.world.biome.realistic.vanilla;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Biomes;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.ChunkPrimer;

import rtg.api.config.BiomeConfig;
import rtg.api.util.BlockUtil;
import rtg.api.util.CliffCalculator;
import rtg.api.world.RTGWorld;
import rtg.api.world.deco.DecoTree;
import rtg.api.world.gen.feature.tree.rtg.TreeRTG;
import rtg.api.world.gen.feature.tree.rtg.TreeRTGCocosNucifera;
import rtg.api.world.surface.SurfaceBase;
import rtg.api.world.terrain.TerrainBase;


public class RealisticBiomeVanillaBeach extends RealisticBiomeVanillaBase {

    public static Biome biome = Biomes.BEACH;
    public static Biome river = Biomes.RIVER;

    public RealisticBiomeVanillaBeach() {

        super(biome, river);
    }

    @Override
    public void initConfig() {

        this.getConfig().ALLOW_VILLAGES.set(false);
        this.getConfig().addProperty(this.getConfig().ALLOW_PALM_TREES).set(true);
    }

    @Override
    public TerrainBase initTerrain() {

        return new TerrainVanillaBeach();
    }

    public class TerrainVanillaBeach extends TerrainBase {

        public TerrainVanillaBeach() {

        }

        @Override
        public float generateNoise(RTGWorld rtgWorld, int x, int y, float border, float river) {

            return terrainBeach(x, y, rtgWorld.simplex, river, 180f, 35f, 63f);
        }
    }

    @Override
    public SurfaceBase initSurface() {

        return new SurfaceVanillaBeach(config, biome.topBlock, biome.fillerBlock, biome.topBlock, biome.fillerBlock, (byte) 0, 1);
    }

    public class SurfaceVanillaBeach extends SurfaceBase {

        private IBlockState cliffBlock1;
        private IBlockState cliffBlock2;
        private byte sandMetadata;
        private int cliffType;

        public SurfaceVanillaBeach(BiomeConfig config, IBlockState top, IBlockState filler, IBlockState cliff1, IBlockState cliff2, byte metadata, int cliff) {

            super(config, Blocks.DIRT, Blocks.DIRT);

            cliffBlock1 = Blocks.DIRT.getDefaultState();
            cliffBlock2 = Blocks.STONE.getDefaultState();
            sandMetadata = metadata;
            cliffType = cliff;
        }

        @SuppressWarnings("unused")
        @Override
        public void paintTerrain(ChunkPrimer primer, int i, int j, int x, int z, int depth, RTGWorld rtgWorld, float[] noise, float river, Biome[] base) {

            Random rand = rtgWorld.rand;
            float c = CliffCalculator.calc(x, z, noise);
            boolean cliff = c > 1.3f ? true : false;
            boolean dirt = false;

            for (int k = 255; k > -1; k--) {
                Block b = primer.getBlockState(x, k, z).getBlock();
                if (b == Blocks.AIR) {
                    depth = -1;
                }
                else if (b == Blocks.STONE) {
                    depth++;

                    if (cliff) {
                        if (cliffType == 1) {
                            if (depth < 6) {
                                primer.setBlockState(x, k, z, cliffBlock1.getBlock().getDefaultState());
                            }
                        }
                        else {
                            if (depth > -1 && depth < 2) {
                                primer.setBlockState(x, k, z, rand.nextInt(3) == 0 ? cliffBlock2 : cliffBlock1);
                            }
                            else if (depth < 10) {
                                primer.setBlockState(x, k, z, cliffBlock1);
                            }
                        }
                    }
                    else if (depth < 6) {
                        if (depth == 0 && k > 61 && k < 64) {
                            //if(simplex.noise2(i / 12f, j / 12f) > -0.3f + ((k - 61f) / 15f))
                            if (false) {
                                dirt = true;
                                primer.setBlockState(x, k, z, topBlock);
                            }
                            else {
                                primer.setBlockState(x, k, z, BlockUtil.getStateSand(sandMetadata));
                            }
                        }
                        else if (depth < 4) {
                            if (dirt) {
                                primer.setBlockState(x, k, z, fillerBlock);
                            }
                            else {
                                if (k > 61 && k < 69) {
                                    primer.setBlockState(x, k, z, BlockUtil.getStateSand(sandMetadata));
                                }
                            }
                        }
                        else if (!dirt) {
                            if (k > 56 && k < 68) { // one lower for under sand and 4 deeper
                                primer.setBlockState(x, k, z, Blocks.SANDSTONE.getDefaultState());
                            }
                            else {
                                primer.setBlockState(x, k, z, Blocks.STONE.getDefaultState());
                            }
                        }
                    }
                }
            }
        }
    }

    @Override
    public void initDecos() {

        // Scattered palm trees.

        TreeRTG nuciferaTree = new TreeRTGCocosNucifera();
        nuciferaTree.setMinTrunkSize(7);
        nuciferaTree.setMaxTrunkSize(9);
        nuciferaTree.setMinCrownSize(6);
        nuciferaTree.setMaxCrownSize(8);
        nuciferaTree.getValidGroundBlocks().clear();
        nuciferaTree.getValidGroundBlocks().add(Blocks.SAND.getDefaultState());
        this.addTree(nuciferaTree);

        DecoTree palmTrees = new DecoTree(nuciferaTree);
        palmTrees.setTreeType(DecoTree.TreeType.RTG_TREE);
        palmTrees.setTreeCondition(DecoTree.TreeCondition.NOISE_GREATER_AND_RANDOM_CHANCE);
        palmTrees.setTreeConditionNoise(-0.2f);
        palmTrees.setTreeConditionChance(12);
        palmTrees.setMaxY(68);
        this.addDeco(palmTrees, this.getConfig().ALLOW_PALM_TREES.get());
    }
}
