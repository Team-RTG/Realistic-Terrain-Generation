package rtg.world.biome.realistic.biomesoplenty;

import java.util.Random;

import biomesoplenty.api.biome.BOPBiomes;
import biomesoplenty.api.block.BOPBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.BlockPlanks;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.ChunkPrimer;
import rtg.api.config.BiomeConfig;
import rtg.api.util.BlockUtil;
import rtg.api.util.WorldUtil;
import rtg.api.util.noise.SimplexNoise;
import rtg.api.world.RTGWorld;
import rtg.api.world.deco.DecoBaseBiomeDecorations;
import rtg.api.world.deco.DecoBoulder;
import rtg.api.world.deco.DecoFallenTree;
import rtg.api.world.deco.helper.DecoHelper5050;
import rtg.api.world.surface.SurfaceBase;
import rtg.api.world.terrain.TerrainBase;
import rtg.world.biome.realistic.RealisticBiomeBase;

import static rtg.api.world.deco.DecoFallenTree.LogCondition.RANDOM_CHANCE;


public class RealisticBiomeBOPShield extends RealisticBiomeBase {

    public static Biome biome = BOPBiomes.shield.get();

    public RealisticBiomeBOPShield() {

        super(biome, RiverType.NORMAL, BeachType.NORMAL);
    }

    @Override
    public void initConfig() {

        this.getConfig().addProperty(this.getConfig().ALLOW_LOGS).set(true);
    }

    @Override
    public TerrainBase initTerrain() {

        return new TerrainBOPShield(0f, 100f, 68f, 170f);
    }

    // this biome also changes the lake generation in RealisticBiomeBase
    public class TerrainBOPShield extends TerrainBase {

        private float start;
        private float height;
        private float base;
        private float width;

        public TerrainBOPShield(float hillStart, float landHeight, float baseHeight, float hillWidth) {

            start = hillStart;
            height = landHeight;
            base = baseHeight;
            width = hillWidth;
        }

        @Override
        public float generateNoise(RTGWorld rtgWorld, int x, int y, float border, float river) {

            return terrainPlains(x, y, rtgWorld, river, 160f, 10f, 60f, 200f, 64f);
        }
    }

    @Override
    public SurfaceBase initSurface() {

        return new SurfaceBOPShield(getConfig(), biome.topBlock, biome.fillerBlock);
    }

    public class SurfaceBOPShield extends SurfaceBase {

        public SurfaceBOPShield(BiomeConfig config, IBlockState top, IBlockState filler) {

            super(config, top, filler);
        }

        @Override
        public void paintTerrain(ChunkPrimer primer, int i, int j, int x, int z, int depth, RTGWorld rtgWorld, float[] noise, float river, Biome[] base) {

            Random rand = rtgWorld.rand();
            SimplexNoise simplex = rtgWorld.simplexInstance(0);
            float c = WorldUtil.Terrain.calcCliff(x, z, noise);
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

        DecoBaseBiomeDecorations decoBaseBiomeDecorations = new DecoBaseBiomeDecorations();
        this.addDeco(decoBaseBiomeDecorations);

        DecoBoulder decoBoulder = new DecoBoulder();
        decoBoulder.setBoulderBlock(Blocks.COBBLESTONE.getDefaultState());
        decoBoulder.setMaxY(80);
        decoBoulder.setChance(16);
        decoBoulder.setStrengthFactor(1f);
        this.addDeco(decoBoulder);

        DecoFallenTree decoFallenTree1 = new DecoFallenTree();
        decoFallenTree1.getDistribution().setNoiseDivisor(80f);
        decoFallenTree1.getDistribution().setNoiseFactor(60f);
        decoFallenTree1.getDistribution().setNoiseAddend(-15f);
        decoFallenTree1.setLogCondition(RANDOM_CHANCE);
        decoFallenTree1.setLogConditionNoise(0f);
        decoFallenTree1.setLogConditionChance(6);
        decoFallenTree1.setMaxY(100);
        decoFallenTree1.setLogBlock(BOPBlocks.log_2.getStateFromMeta(6));
        decoFallenTree1.setLeavesBlock(Blocks.LEAVES.getDefaultState());
        decoFallenTree1.setMinSize(3);
        decoFallenTree1.setMaxSize(4);

        DecoFallenTree decoFallenTree2 = new DecoFallenTree();
        decoFallenTree2.getDistribution().setNoiseDivisor(80f);
        decoFallenTree2.getDistribution().setNoiseFactor(60f);
        decoFallenTree2.getDistribution().setNoiseAddend(-15f);
        decoFallenTree2.setLogCondition(RANDOM_CHANCE);
        decoFallenTree2.setLogConditionNoise(0f);
        decoFallenTree2.setLogConditionChance(6);
        decoFallenTree2.setMaxY(100);
        decoFallenTree2.setLogBlock(BlockUtil.getStateLog(BlockPlanks.EnumType.SPRUCE));
        decoFallenTree2.setLeavesBlock(BlockUtil.getStateLeaf(BlockPlanks.EnumType.SPRUCE));
        decoFallenTree2.setMinSize(3);
        decoFallenTree2.setMaxSize(4);

        DecoHelper5050 decoHelperHelper5050 = new DecoHelper5050(decoFallenTree1, decoFallenTree2);
        this.addDeco(decoHelperHelper5050, this.getConfig().ALLOW_LOGS.get());
    }
}
