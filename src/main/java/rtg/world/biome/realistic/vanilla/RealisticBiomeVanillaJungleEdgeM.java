package rtg.world.biome.realistic.vanilla;

import net.minecraft.block.Block;
import net.minecraft.block.BlockPlanks.EnumType;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Biomes;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.ChunkPrimer;
import rtg.api.config.BiomeConfig;
import rtg.api.util.BlockUtil;
import rtg.api.world.RTGWorld;
import rtg.api.world.biome.RealisticBiomeBase;
import rtg.api.world.deco.DecoFallenTree;
import rtg.api.world.surface.SurfaceBase;
import rtg.api.world.terrain.TerrainBase;

import java.util.Random;


public class RealisticBiomeVanillaJungleEdgeM extends RealisticBiomeBase {

    public static Biome biome = Biomes.MUTATED_JUNGLE_EDGE;
    public static Biome river = Biomes.RIVER;

    public RealisticBiomeVanillaJungleEdgeM() {

        super(biome);
    }

    @Override
    public void initConfig() {
        this.getConfig().ALLOW_SCENIC_LAKES.set(false);
        this.getConfig().ALLOW_RIVERS.set(false);
        this.getConfig().addProperty(this.getConfig().ALLOW_LOGS).set(true);
        this.getConfig().addProperty(this.getConfig().FALLEN_LOG_DENSITY_MULTIPLIER);
    }

    @Override
    public TerrainBase initTerrain() {

        return new TerrainVanillaJungleEdgeM();
    }

    @Override
    public SurfaceBase initSurface() {

        return new SurfaceVanillaJungleEdgeM(getConfig(), biome.topBlock, biome.fillerBlock);
    }

    @Override
    public void initDecos() {

        DecoFallenTree decoFallenTree = new DecoFallenTree();
        decoFallenTree.setLoops(1);
        decoFallenTree.getDistribution().setNoiseDivisor(100f);
        decoFallenTree.getDistribution().setNoiseFactor(6f);
        decoFallenTree.getDistribution().setNoiseAddend(0.8f);
        decoFallenTree.setLogConditionChance(6);
        decoFallenTree.setLogBlock(BlockUtil.getStateLog(EnumType.JUNGLE));
        decoFallenTree.setLeavesBlock(BlockUtil.getStateLeaf(EnumType.JUNGLE));
        decoFallenTree.setMinSize(3);
        decoFallenTree.setMaxSize(6);
        this.addDeco(decoFallenTree, this.getConfig().ALLOW_LOGS.get());
    }

    @Override
    public void overrideDecorations() {
        baseBiome().decorator.grassPerChunk = 30; // Vanilla = 25
        baseBiome().decorator.flowersPerChunk = -999; // Vanilla = 4
        baseBiome().decorator.treesPerChunk = 2; // Vanilla = 2
    }

    public static class TerrainVanillaJungleEdgeM extends TerrainBase {

        public TerrainVanillaJungleEdgeM() {

        }

        @Override
        public float generateNoise(RTGWorld rtgWorld, int x, int y, float border, float river) {

            return terrainGrasslandMountains(x, y, rtgWorld, river, 4f, 80f, 68f);
        }
    }

    public static class SurfaceVanillaJungleEdgeM extends SurfaceBase {

        public SurfaceVanillaJungleEdgeM(BiomeConfig config, IBlockState top, IBlockState filler) {

            super(config, top, filler);
        }

        @Override
        public void paintTerrain(ChunkPrimer primer, int i, int j, int x, int z, int depth, RTGWorld rtgWorld, float[] noise, float river, Biome[] base) {

            Random rand = rtgWorld.rand();
            float c = TerrainBase.calcCliff(x, z, noise);
            boolean cliff = c > 1.4f;

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

                                primer.setBlockState(x, k, z, hcCobble());
                            }
                            else {

                                primer.setBlockState(x, k, z, hcStone());
                            }
                        }
                        else if (depth < 10) {
                            primer.setBlockState(x, k, z, hcStone());
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
}
