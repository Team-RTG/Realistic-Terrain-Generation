package rtg.world.biome.realistic.vanilla;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Biomes;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.ChunkPrimer;

import rtg.api.biome.BiomeConfig;
import rtg.api.biome.BiomeConfigProperty;
import rtg.util.BlockUtil;
import rtg.util.CellNoise;
import rtg.util.CliffCalculator;
import rtg.util.OpenSimplexNoise;
import rtg.world.biome.deco.*;
import rtg.world.biome.deco.collection.DecoCollectionMegaTaiga;
import rtg.world.gen.surface.SurfaceBase;
import rtg.world.gen.terrain.TerrainBase;
import static rtg.api.biome.BiomeConfig.allowVolcanoesId;
import static rtg.api.biome.BiomeConfig.volcanoChanceId;

public class RealisticBiomeVanillaMegaTaiga extends RealisticBiomeVanillaBase {

    public static Biome biome = Biomes.REDWOOD_TAIGA;
    public static Biome river = Biomes.RIVER;

    public RealisticBiomeVanillaMegaTaiga() {

        super(biome, river);
    }

    @Override
    public void initConfig() {

        this.config.addProperty(new BiomeConfigProperty(BiomeConfig.decorationLogsId, BiomeConfigProperty.Type.BOOLEAN, BiomeConfig.decorationLogsName, "", true));

        this.config.setPropertyValueById(allowVolcanoesId, true);
        this.config.setPropertyValueById(volcanoChanceId, -1);
    }

    @Override
    public TerrainBase initTerrain() {

        return new TerrainVanillaMegaTaiga();
    }

    public class TerrainVanillaMegaTaiga extends TerrainBase {

        public TerrainVanillaMegaTaiga() {

        }

        public float generateNoise(OpenSimplexNoise simplex, CellNoise cell, int x, int y, float border, float river) {

            return terrainFlatLakes(x, y, simplex, river, 13f, 66f);
        }
    }

    @Override
    public SurfaceBase initSurface() {

        return new SurfaceVanillaMegaTaiga(config, biome.topBlock, biome.fillerBlock);
    }

    public class SurfaceVanillaMegaTaiga extends SurfaceBase {

        public SurfaceVanillaMegaTaiga(BiomeConfig config, IBlockState top, IBlockState fill) {

            super(config, top, fill);
        }

        @Override
        public void paintTerrain(ChunkPrimer primer, int i, int j, int x, int z, int depth, World world, Random rand,
                                 OpenSimplexNoise simplex, CellNoise cell, float[] noise, float river, Biome[] base) {

            float p = simplex.noise2(i / 8f, j / 8f) * 0.5f;
            float c = CliffCalculator.calc(x, z, noise);
            int cliff = 0;

            Block b;
            for (int k = 255; k > -1; k--) {
                b = primer.getBlockState(x, k, z).getBlock();
                if (b == Blocks.AIR) {
                    depth = -1;
                }
                else if (b == Blocks.STONE) {
                    depth++;

                    if (depth == 0) {

                        if (c > 0.45f && c > 1.5f - ((k - 60f) / 65f) + p) {
                            cliff = 1;
                        }
                        if (c > 1.5f) {
                            cliff = 2;
                        }
                        if (k > 110 + (p * 4) && c < 0.3f + ((k - 100f) / 50f) + p) {
                            cliff = 3;
                        }

                        if (cliff == 1) {
                            if (rand.nextInt(3) == 0) {

                                primer.setBlockState(x, k, z, hcCobble(world, i, j, x, z, k));
                            }
                            else {

                                primer.setBlockState(x, k, z, hcStone(world, i, j, x, z, k));
                            }
                        }
                        else if (cliff == 2) {
                            primer.setBlockState(x, k, z, getShadowStoneBlock(world, i, j, x, z, k));
                        }
                        else if (cliff == 3) {
                            primer.setBlockState(x, k, z, Blocks.SNOW.getDefaultState());
                        }
                        else if (simplex.noise2(i / 50f, j / 50f) + p * 0.6f > 0.24f) {
                            primer.setBlockState(x, k, z, BlockUtil.getStateDirt(2));
                        }
                        else {
                            primer.setBlockState(x, k, z, Blocks.GRASS.getDefaultState());
                        }
                    }
                    else if (depth < 6) {
                        if (cliff == 1) {
                            primer.setBlockState(x, k, z, hcStone(world, i, j, x, z, k));
                        }
                        else if (cliff == 2) {
                            primer.setBlockState(x, k, z, getShadowStoneBlock(world, i, j, x, z, k));
                        }
                        else if (cliff == 3) {
                            primer.setBlockState(x, k, z, Blocks.SNOW.getDefaultState());
                        }
                        else {
                            primer.setBlockState(x, k, z, Blocks.DIRT.getDefaultState());
                        }
                    }
                }
            }
        }
    }

    @Override
    public void initDecos() {

        DecoBoulder decoBoulder = new DecoBoulder();
        decoBoulder.boulderBlock = Blocks.MOSSY_COBBLESTONE.getDefaultState();
        decoBoulder.chance = 16;
        decoBoulder.maxY = 95;
        decoBoulder.strengthFactor = 3f;
        this.addDeco(decoBoulder);

        this.addDecoCollection(new DecoCollectionMegaTaiga());

        DecoFallenTree decoFallenTree = new DecoFallenTree();
        decoFallenTree.distribution.noiseDivisor = 100f;
        decoFallenTree.distribution.noiseFactor = 6f;
        decoFallenTree.distribution.noiseAddend = 0.8f;
        decoFallenTree.logCondition = DecoFallenTree.LogCondition.NOISE_GREATER_AND_RANDOM_CHANCE;
        decoFallenTree.logConditionNoise = 0f;
        decoFallenTree.logConditionChance = 6;
        decoFallenTree.logBlock = BlockUtil.getStateLog(1);
        decoFallenTree.leavesBlock = BlockUtil.getStateLeaf(1);
        decoFallenTree.minSize = 3;
        decoFallenTree.maxSize = 6;
        this.addDeco(decoFallenTree, this.config._boolean(BiomeConfig.decorationLogsId));

        DecoShrub decoShrub = new DecoShrub();
        decoShrub.maxY = 100;
        decoShrub.strengthFactor = 2f;
        decoShrub.chance = 10;
        this.addDeco(decoShrub);

        DecoBaseBiomeDecorations decoBaseBiomeDecorations = new DecoBaseBiomeDecorations();
        decoBaseBiomeDecorations.equalsZeroChance = 3;
        this.addDeco(decoBaseBiomeDecorations);

        DecoMushrooms decoMushrooms = new DecoMushrooms();
        decoMushrooms.maxY = 90;
        decoMushrooms.randomType = rtg.world.biome.deco.DecoMushrooms.RandomType.X_DIVIDED_BY_STRENGTH;
        decoMushrooms.randomFloat = 3f;
        this.addDeco(decoMushrooms);

        DecoPumpkin decoPumpkin = new DecoPumpkin();
        decoPumpkin.maxY = 90;
        decoPumpkin.randomType = rtg.world.biome.deco.DecoPumpkin.RandomType.X_DIVIDED_BY_STRENGTH;
        decoPumpkin.randomFloat = 20f;
        this.addDeco(decoPumpkin);

        DecoGrass decoGrass = new DecoGrass();
        decoGrass.maxY = 128;
        decoGrass.strengthFactor = 10f;
        this.addDeco(decoGrass);
    }
}
