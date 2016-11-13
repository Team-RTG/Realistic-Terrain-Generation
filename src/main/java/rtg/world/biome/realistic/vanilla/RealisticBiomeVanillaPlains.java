package rtg.world.biome.realistic.vanilla;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Biomes;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.ChunkPrimer;

import net.minecraftforge.common.config.Configuration;

import rtg.api.biome.BiomeConfig;
import rtg.api.biome.BiomeConfigProperty;
import rtg.util.BlockUtil;
import rtg.util.CellNoise;
import rtg.util.CliffCalculator;
import rtg.util.OpenSimplexNoise;
import rtg.world.biome.deco.*;
import rtg.world.biome.deco.helper.DecoHelperThisOrThat;
import rtg.world.gen.feature.tree.rtg.TreeRTG;
import rtg.world.gen.feature.tree.rtg.TreeRTGQuercusRobur;
import rtg.world.gen.surface.SurfaceBase;
import rtg.world.gen.terrain.GroundEffect;
import rtg.world.gen.terrain.TerrainBase;

public class RealisticBiomeVanillaPlains extends RealisticBiomeVanillaBase {

    public static Biome biome = Biomes.PLAINS;
    public static Biome river = Biomes.RIVER;

    public RealisticBiomeVanillaPlains() {

        super(biome, river);
    }

    @Override
    public void initConfig() {

        this.config.addProperty(new BiomeConfigProperty(BiomeConfig.decorationWheatId, BiomeConfigProperty.Type.BOOLEAN, BiomeConfig.decorationWheatName, "", true));

        this.config.addProperty(new BiomeConfigProperty(
            BiomeConfig.decorationWheatChanceId,
            BiomeConfigProperty.Type.INTEGER,
            BiomeConfig.decorationWheatChanceName,
            "1/x chance that wheat will generate."
                + Configuration.NEW_LINE +
                "0 = Never generate; 1 = Always generate if possible; 2 = 50% chance; 4 = 25% chance"
                + Configuration.NEW_LINE,
            50, 0, Integer.MAX_VALUE
        ));

        this.config.addProperty(new BiomeConfigProperty(
            BiomeConfig.decorationWheatMinYId,
            BiomeConfigProperty.Type.INTEGER,
            BiomeConfig.decorationWheatMinYName,
            "The lowest Y value at which wheat is allowed to generate in this biome.",
            63, 63, 255
        ));

        this.config.addProperty(new BiomeConfigProperty(
            BiomeConfig.decorationWheatMaxYId,
            BiomeConfigProperty.Type.INTEGER,
            BiomeConfig.decorationWheatMaxYName,
            "The highest Y value at which wheat is allowed to generate in this biome.",
            255, 63, 255
        ));
    }

    @Override
    public TerrainBase initTerrain() {

        return new TerrainVanillaPlains();
    }

    public class TerrainVanillaPlains extends TerrainBase {

        private GroundEffect groundEffect = new GroundEffect(4f);

        public TerrainVanillaPlains() {

        }

        @Override
        public float generateNoise(OpenSimplexNoise simplex, CellNoise cell, int x, int y, float border, float river) {
            //return terrainPlains(x, y, simplex, river, 160f, 10f, 60f, 200f, 66f);
            return riverized(65f + groundEffect.added(simplex, cell, x, y), river);
        }
    }

    @Override
    public SurfaceBase initSurface() {

        return new SurfaceVanillaPlains(config, biome.topBlock, biome.fillerBlock);
    }

    public class SurfaceVanillaPlains extends SurfaceBase {

        public SurfaceVanillaPlains(BiomeConfig config, IBlockState top, IBlockState filler) {

            super(config, top, filler);
        }

        @Override
        public void paintTerrain(ChunkPrimer primer, int i, int j, int x, int y, int depth, World world, Random rand, OpenSimplexNoise simplex, CellNoise cell, float[] noise, float river, Biome[] base) {

            float c = CliffCalculator.calc(x, y, noise);
            boolean cliff = c > 1.4f ? true : false;

            for (int k = 255; k > -1; k--) {
                Block b = primer.getBlockState(x, k, y).getBlock();
                if (b == Blocks.AIR) {
                    depth = -1;
                }
                else if (b == Blocks.STONE) {
                    depth++;

                    if (cliff) {
                        if (depth > -1 && depth < 2) {
                            if (rand.nextInt(3) == 0) {

                                primer.setBlockState(x, k, y, hcCobble(world, i, j, x, y, k));
                            }
                            else {

                                primer.setBlockState(x, k, y, hcStone(world, i, j, x, y, k));
                            }
                        }
                        else if (depth < 10) {
                            primer.setBlockState(x, k, y, hcStone(world, i, j, x, y, k));
                        }
                    }
                    else {
                        if (depth == 0 && k > 61) {
                            primer.setBlockState(x, k, y, topBlock);
                        }
                        else if (depth < 4) {
                            primer.setBlockState(x, k, y, fillerBlock);
                        }
                    }
                }
            }
        }
    }

    @Override
    public void initDecos() {

        //Sparse wheat
        DecoCrop decoCropWheat = new DecoCrop();
        decoCropWheat.size = 8;
        decoCropWheat.density = 5;
        decoCropWheat.chance = this.config._int(BiomeConfig.decorationWheatChanceId);
        decoCropWheat.type = 3;
        decoCropWheat.water = false;
        decoCropWheat.minY = this.config._int(BiomeConfig.decorationWheatMinYId);
        decoCropWheat.maxY = this.config._int(BiomeConfig.decorationWheatMaxYId);
        this.addDeco(decoCropWheat, this.config._boolean(BiomeConfig.decorationWheatId));

        // Very sparse shrubs.
        DecoShrub decoShrubOak = new DecoShrub();
        decoShrubOak.logBlock = Blocks.LOG.getDefaultState();
        decoShrubOak.leavesBlock = Blocks.LEAVES.getDefaultState();
        decoShrubOak.maxY = 110;
        decoShrubOak.loops = 1;
        decoShrubOak.chance = 36;
        this.addDeco(decoShrubOak);

        // The occasional flower.
        DecoFlowersRTG decoFlowersRTG = new DecoFlowersRTG();
        decoFlowersRTG.flowers = new int[]{0, 2, 3, 4, 5, 6, 7, 8, 9};
        decoFlowersRTG.maxY = 128;
        decoFlowersRTG.strengthFactor = 2f;
        this.addDeco(decoFlowersRTG);

        // Lots of grass, but not as much as vanilla.
        DecoGrass decoGrass = new DecoGrass();
        decoGrass.minY = 60;
        decoGrass.maxY = 128;
        decoGrass.loops = 6;
        this.addDeco(decoGrass);

        // Very rare fat oak/birch trees.

        TreeRTG roburTree1 = new TreeRTGQuercusRobur();
        roburTree1.logBlock = Blocks.LOG.getDefaultState();
        roburTree1.leavesBlock = Blocks.LEAVES.getDefaultState();
        roburTree1.minTrunkSize = 3;
        roburTree1.maxTrunkSize = 5;
        roburTree1.minCrownSize = 7;
        roburTree1.maxCrownSize = 9;
        this.addTree(roburTree1);

        DecoTree oakTrees = new DecoTree(roburTree1);
        oakTrees.treeType = DecoTree.TreeType.RTG_TREE;
        oakTrees.treeCondition = DecoTree.TreeCondition.NOISE_GREATER_AND_RANDOM_CHANCE;
        oakTrees.distribution = new DecoTree.Distribution(100f, 6f, 0.8f);
        oakTrees.treeConditionNoise = 0.4f;
        oakTrees.treeConditionChance = 48;

        TreeRTG roburTree2 = new TreeRTGQuercusRobur();
        roburTree2.logBlock = BlockUtil.getStateLog(2);
        roburTree2.leavesBlock = BlockUtil.getStateLeaf(2);
        roburTree2.minTrunkSize = 3;
        roburTree2.maxTrunkSize = 5;
        roburTree2.minCrownSize = 7;
        roburTree2.maxCrownSize = 9;
        this.addTree(roburTree2);

        DecoTree birchTrees = new DecoTree(roburTree2);
        birchTrees.treeType = DecoTree.TreeType.RTG_TREE;
        birchTrees.treeCondition = DecoTree.TreeCondition.NOISE_GREATER_AND_RANDOM_CHANCE;
        birchTrees.distribution = new DecoTree.Distribution(100f, 6f, 0.8f);
        birchTrees.treeConditionNoise = 0.4f;
        birchTrees.treeConditionChance = 48;

        this.addDeco(new DecoHelperThisOrThat(4, DecoHelperThisOrThat.ChanceType.NOT_EQUALS_ZERO, oakTrees, birchTrees));

        // Vanilla trees look awful in this biome, so let's make sure they don't generate.
        //DecoBaseBiomeDecorations decoBaseBiomeDecorations = new DecoBaseBiomeDecorations();
        //this.addDeco(decoBaseBiomeDecorations);
    }
}
