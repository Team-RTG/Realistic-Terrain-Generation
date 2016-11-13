package rtg.world.biome.realistic.abyssalcraft;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Biomes;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.ChunkPrimer;

import com.shinoow.abyssalcraft.api.biome.ACBiomes;
import com.shinoow.abyssalcraft.api.block.ACBlocks;

import rtg.api.biome.BiomeConfig;
import rtg.api.biome.BiomeConfigProperty;
import rtg.util.CellNoise;
import rtg.util.CliffCalculator;
import rtg.util.OpenSimplexNoise;
import rtg.world.biome.deco.DecoBaseBiomeDecorations;
import rtg.world.biome.deco.DecoFallenTree;
import rtg.world.biome.deco.DecoShrub;
import rtg.world.gen.surface.SurfaceBase;
import rtg.world.gen.terrain.TerrainBase;

public class RealisticBiomeACDarklands extends RealisticBiomeACBase {

    public static Biome biome = ACBiomes.darklands;
    public static Biome river = Biomes.RIVER;

    public RealisticBiomeACDarklands() {

        super(biome, river);
    }

    @Override
    public void initConfig() {

        this.config.addProperty(new BiomeConfigProperty(
            BiomeConfig.decorationLogsId, BiomeConfigProperty.Type.BOOLEAN, BiomeConfig.decorationLogsName, "", true
        ));
        this.config.addProperty(new BiomeConfigProperty(
            BiomeConfig.surfaceMixBlockId, BiomeConfigProperty.Type.STRING, BiomeConfig.surfaceMixBlockName, "", ""
        ));
        this.config.addProperty(new BiomeConfigProperty(
            BiomeConfig.surfaceMixBlockMetaId, BiomeConfigProperty.Type.STRING, BiomeConfig.surfaceMixBlockMetaName, "", ""
        ));
    }

    @Override
    public TerrainBase initTerrain() {

        return new TerrainACDarklands();
    }

    public class TerrainACDarklands extends TerrainBase {

        private float hillStrength = 40f;

        public TerrainACDarklands() {

            this(72f, 40f);
        }

        public TerrainACDarklands(float bh, float hs) {

            base = bh;
            hillStrength = hs;
        }

        @Override
        public float generateNoise(OpenSimplexNoise simplex, CellNoise cell, int x, int y, float border, float river) {

            return terrainHighland(x, y, simplex, cell, river, 10f, 68f, hillStrength, base - 62f);

        }
    }

    @Override
    public SurfaceBase initSurface() {

        return new SurfaceACDarklands(config, biome.topBlock, biome.fillerBlock, 0f, 1.5f, 60f, 65f, 1.5f, biome.topBlock, 0.15f);
    }

    public class SurfaceACDarklands extends SurfaceACBase {

        private float min;

        private float sCliff = 1.5f;
        private float sHeight = 60f;
        private float sStrength = 65f;
        private float cCliff = 1.5f;

        private IBlockState mixBlock;
        private float mixHeight;

        public SurfaceACDarklands(BiomeConfig config, IBlockState top, IBlockState fill, float minCliff, float stoneCliff,
                                  float stoneHeight, float stoneStrength, float clayCliff, IBlockState mix, float mixSize) {

            super(config, top, fill);
            min = minCliff;

            sCliff = stoneCliff;
            sHeight = stoneHeight;
            sStrength = stoneStrength;
            cCliff = clayCliff;

            mixBlock = this.getConfigBlock(config, BiomeConfig.surfaceMixBlockId, BiomeConfig.surfaceMixBlockMetaId, mix);
            mixHeight = mixSize;
        }

        @Override
        public void paintTerrain(ChunkPrimer primer, int i, int j, int x, int y, int depth, World world, Random rand,
                                 OpenSimplexNoise simplex, CellNoise cell, float[] noise, float river, Biome[] base) {

            float c = CliffCalculator.calc(x, y, noise);
            int cliff = 0;
            boolean m = false;

            Block b;
            for (int k = 255; k > -1; k--) {
                b = primer.getBlockState(x, k, y).getBlock();
                if (b == Blocks.AIR) {
                    depth = -1;
                }
                else if (b == Blocks.STONE) {
                    depth++;

                    if (depth == 0) {

                        float p = simplex.noise3(i / 8f, j / 8f, k / 8f) * 0.5f;
                        if (c > min && c > sCliff - ((k - sHeight) / sStrength) + p) {
                            cliff = 1;
                        }
                        if (c > cCliff) {
                            cliff = 2;
                        }

                        if (cliff == 1) {
                            if (rand.nextInt(3) == 0) {

                                primer.setBlockState(x, k, y, hcCobble(world, i, j, x, y, k));
                            }
                            else {

                                primer.setBlockState(x, k, y, hcStone(world, i, j, x, y, k));
                            }
                        }
                        else if (cliff == 2) {
                            primer.setBlockState(x, k, y, getShadowStoneBlock(world, i, j, x, y, k));
                        }
                        else if (k < 63) {
                            if (k < 62) {
                                primer.setBlockState(x, k, y, fillerBlock);
                            }
                            else {
                                primer.setBlockState(x, k, y, topBlock);
                            }
                        }
                        else if (simplex.noise2(i / 12f, j / 12f) > mixHeight) {
                            primer.setBlockState(x, k, y, mixBlock);
                            m = true;
                        }
                        else {
                            primer.setBlockState(x, k, y, topBlock);
                        }
                    }
                    else if (depth < 6) {
                        if (cliff == 1) {
                            primer.setBlockState(x, k, y, hcStone(world, i, j, x, y, k));
                        }
                        else if (cliff == 2) {
                            primer.setBlockState(x, k, y, getShadowStoneBlock(world, i, j, x, y, k));
                        }
                        else {
                            primer.setBlockState(x, k, y, fillerBlock);
                        }
                    }
                }
            }
        }
    }

    @Override
    public void initDecos() {

        DecoFallenTree decoFallenTree = new DecoFallenTree();
        decoFallenTree.logCondition = DecoFallenTree.LogCondition.RANDOM_CHANCE;
        decoFallenTree.logConditionChance = 8;
        decoFallenTree.logBlock = ACBlocks.darklands_oak_wood.getDefaultState();
        decoFallenTree.leavesBlock = ACBlocks.darklands_oak_leaves.getDefaultState();
        decoFallenTree.minSize = 2;
        decoFallenTree.maxSize = 3;
        this.addDeco(decoFallenTree, this.config._boolean(this.config.decorationLogsId));

        DecoShrub decoShrubCustom = new DecoShrub();
        decoShrubCustom.logBlock = ACBlocks.darklands_oak_wood.getDefaultState();
        decoShrubCustom.leavesBlock = ACBlocks.darklands_oak_leaves.getDefaultState();
        decoShrubCustom.maxY = 110;
        decoShrubCustom.notEqualsZerochance = 3;
        decoShrubCustom.strengthFactor = 2f;
        this.addDeco(decoShrubCustom);

        DecoBaseBiomeDecorations decoBaseBiomeDecorations = new DecoBaseBiomeDecorations();
        this.addDeco(decoBaseBiomeDecorations);
    }
}
