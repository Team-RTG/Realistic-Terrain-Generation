package rtg.world.biome.realistic.biomesyougo;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Biomes;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.Biome;

import rtg.api.biome.BiomeConfig;
import rtg.api.biome.biomesyougo.config.BiomeConfigBYGWillowSwamps;
import rtg.util.BlockUtil;
import rtg.util.CellNoise;
import rtg.util.OpenSimplexNoise;
import rtg.world.biome.deco.DecoBaseBiomeDecorations;
import rtg.world.biome.deco.DecoFallenTree;
import rtg.world.biome.deco.DecoShrub;
import rtg.world.biome.deco.DecoTree;
import rtg.world.gen.feature.tree.rtg.TreeRTG;
import rtg.world.gen.feature.tree.rtg.TreeRTGSalixMyrtilloides;
import rtg.world.gen.surface.biomesyougo.SurfaceBYGWillowSwamps;
import rtg.world.gen.terrain.TerrainBase;

public class RealisticBiomeBYGWillowSwamps extends RealisticBiomeBYGBase {

    public static Biome river = Biomes.RIVER;

    private static IBlockState willowLogBlock = Block.getBlockFromName("BiomesYouGo:WillowLog").getDefaultState();
    private static IBlockState willowLeavesBlock = Block.getBlockFromName("BiomesYouGo:WillowLeaves").getDefaultState();

    public RealisticBiomeBYGWillowSwamps(Biome biome, BiomeConfig config) {

        super(config, biome, river,
            new rtg.world.gen.terrain.biomesyougo.TerrainBYGWillowSwamps(),
            new SurfaceBYGWillowSwamps(config, biome.topBlock, biome.fillerBlock)
        );

        DecoBaseBiomeDecorations decoBaseBiomeDecorations = new DecoBaseBiomeDecorations();
        decoBaseBiomeDecorations.maxY = 82;
        this.addDeco(decoBaseBiomeDecorations);

        TreeRTG myrtilloidesTree = new TreeRTGSalixMyrtilloides();
        myrtilloidesTree.logBlock = Blocks.LOG.getDefaultState();
        myrtilloidesTree.leavesBlock = Blocks.LEAVES.getDefaultState();
        this.addTree(myrtilloidesTree);

        DecoTree decoTrees = new DecoTree(myrtilloidesTree);
        decoTrees.distribution.noiseDivisor = 80f;
        decoTrees.distribution.noiseFactor = 60f;
        decoTrees.distribution.noiseAddend = -15f;
        decoTrees.treeType = DecoTree.TreeType.RTG_TREE;
        decoTrees.treeCondition = DecoTree.TreeCondition.NOISE_GREATER_AND_RANDOM_CHANCE;
        decoTrees.treeConditionNoise = 0f;
        decoTrees.treeConditionChance = 16;
        decoTrees.maxY = 70;
        this.addDeco(decoTrees);

        DecoFallenTree decoFallenTree = new DecoFallenTree();
        decoFallenTree.distribution.noiseDivisor = 80f;
        decoFallenTree.distribution.noiseFactor = 60f;
        decoFallenTree.distribution.noiseAddend = -15f;
        decoFallenTree.logCondition = DecoFallenTree.LogCondition.NOISE_GREATER_AND_RANDOM_CHANCE;
        decoFallenTree.logConditionNoise = 0f;
        decoFallenTree.logConditionChance = 6;
        decoFallenTree.logBlock = BlockUtil.getStateLog2(1);
        decoFallenTree.leavesBlock = BlockUtil.getStateLeaf2(1);
        decoFallenTree.minSize = 3;
        decoFallenTree.maxSize = 6;
        decoFallenTree.maxY = 76;
        this.addDeco(decoFallenTree, this.config._boolean(BiomeConfigBYGWillowSwamps.decorationLogsId));

        TreeRTG deadWillowTree = new TreeRTGSalixMyrtilloides();
        deadWillowTree.logBlock = Blocks.LOG.getDefaultState();
        deadWillowTree.leavesBlock = Blocks.LEAVES.getDefaultState();
        deadWillowTree.noLeaves = true;
        this.addTree(deadWillowTree);

        DecoTree deadWillow = new DecoTree(deadWillowTree);
        deadWillow.treeType = DecoTree.TreeType.RTG_TREE;
        deadWillow.treeCondition = DecoTree.TreeCondition.RANDOM_CHANCE;
        deadWillow.treeConditionChance = 18;
        deadWillow.maxY = 84;
        this.addDeco(deadWillow);

        DecoShrub decoShrub = new DecoShrub();
        decoShrub.maxY = 88;
        decoShrub.strengthFactor = 6f;
        this.addDeco(decoShrub);

        DecoShrub decoShrubBYG = new DecoShrub();
        decoShrubBYG.logBlock = willowLogBlock;
        decoShrubBYG.leavesBlock = willowLeavesBlock;
        decoShrubBYG.maxY = 88;
        decoShrubBYG.strengthFactor = 3f;
        this.addDeco(decoShrubBYG);
    }

    @Override
    public TerrainBase initTerrain() {

        return new TerrainBYGWillowSwamps();
    }

    public class TerrainBYGWillowSwamps extends TerrainBase {

        public TerrainBYGWillowSwamps() {

        }

        @Override
        public float generateNoise(OpenSimplexNoise simplex, CellNoise cell, int x, int y, float border, float river) {

            return terrainMarsh(x, y, simplex, 61.5f);
        }
    }
}
