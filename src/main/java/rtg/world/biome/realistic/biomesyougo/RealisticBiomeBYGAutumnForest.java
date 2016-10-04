package rtg.world.biome.realistic.biomesyougo;

import net.minecraft.block.Block;
import net.minecraft.block.BlockDirt;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Biomes;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.Biome;

import rtg.api.biome.BiomeConfig;
import rtg.api.biome.biomesyougo.config.BiomeConfigBYGAutumnForest;
import rtg.world.biome.deco.*;
import rtg.world.gen.feature.tree.rtg.TreeRTG;
import rtg.world.gen.feature.tree.rtg.TreeRTGQuercusRobur;
import rtg.world.gen.surface.biomesyougo.SurfaceBYGAutumnForest;
import rtg.world.gen.terrain.biomesyougo.TerrainBYGAutumnForest;

public class RealisticBiomeBYGAutumnForest extends RealisticBiomeBYGBase {

    public static Biome river = Biomes.RIVER;

    private static IBlockState cikaLogBlock = Block.getBlockFromName("BiomesYouGo:CikaLog").getDefaultState();
    private static IBlockState cikaLeavesBlock = Block.getBlockFromName("BiomesYouGo:CikaLeaves").getDefaultState();

    public RealisticBiomeBYGAutumnForest(Biome biome, BiomeConfig config) {

        super(config, biome, river,
            new TerrainBYGAutumnForest(),
            new SurfaceBYGAutumnForest(config,
                biome.topBlock, //Block top
                biome.fillerBlock, //Block filler,
                Blocks.DIRT.getDefaultState().withProperty(BlockDirt.VARIANT, BlockDirt.DirtType.PODZOL), //IBlockState mixTop,
                biome.fillerBlock, //IBlockState mixFill,
                80f, //float mixWidth,
                0.35f, //float mixHeight,
                10f, //float smallWidth,
                0.65f //float smallStrength
            )
        );

        DecoFallenTree decoFallenTree = new DecoFallenTree();
        decoFallenTree.distribution.noiseDivisor = 100f;
        decoFallenTree.distribution.noiseFactor = 6f;
        decoFallenTree.distribution.noiseAddend = 0.8f;
        decoFallenTree.logCondition = DecoFallenTree.LogCondition.NOISE_GREATER_AND_RANDOM_CHANCE;
        decoFallenTree.logConditionNoise = 0f;
        decoFallenTree.logConditionChance = 24;
        decoFallenTree.logBlock = Blocks.LOG.getDefaultState();
        decoFallenTree.leavesBlock = Blocks.LEAVES.getDefaultState();
        decoFallenTree.minSize = 3;
        decoFallenTree.maxSize = 6;
        this.addDeco(decoFallenTree, this.config._boolean(BiomeConfigBYGAutumnForest.decorationLogsId));

        DecoShrub decoShrubCika = new DecoShrub();
        decoShrubCika.logBlock = cikaLogBlock;
        decoShrubCika.leavesBlock = cikaLeavesBlock;
        decoShrubCika.maxY = 90;
        decoShrubCika.strengthFactor = 4f;
        decoShrubCika.chance = 8;
        this.addDeco(decoShrubCika);

        DecoShrub decoShrubOak = new DecoShrub();
        decoShrubOak.maxY = 90;
        decoShrubOak.strengthFactor = 4f;
        decoShrubOak.chance = 4;
        this.addDeco(decoShrubOak);

        DecoBoulder decoBoulder = new DecoBoulder();
        decoBoulder.boulderBlock = Blocks.COBBLESTONE.getDefaultState();
        decoBoulder.chance = 24;
        decoBoulder.maxY = 80;
        decoBoulder.strengthFactor = 2f;
        this.addDeco(decoBoulder);

        TreeRTG quercusRoburOakTree = new TreeRTGQuercusRobur();
        quercusRoburOakTree.logBlock = Blocks.LOG.getDefaultState();
        quercusRoburOakTree.leavesBlock = Blocks.LEAVES.getDefaultState();
        quercusRoburOakTree.minTrunkSize = 3;
        quercusRoburOakTree.maxTrunkSize = 6;
        quercusRoburOakTree.minCrownSize = 5;
        quercusRoburOakTree.maxCrownSize = 9;
        quercusRoburOakTree.validGroundBlocks.clear();
        quercusRoburOakTree.validGroundBlocks.add(Blocks.DIRT.getDefaultState().withProperty(BlockDirt.VARIANT, BlockDirt.DirtType.PODZOL));
        this.addTree(quercusRoburOakTree);

        DecoTree bigOakTrees = new DecoTree(quercusRoburOakTree);
        bigOakTrees.strengthFactorForLoops = 2f;
        bigOakTrees.treeType = DecoTree.TreeType.RTG_TREE;
        bigOakTrees.distribution.noiseDivisor = 100f;
        bigOakTrees.distribution.noiseFactor = 6f;
        bigOakTrees.distribution.noiseAddend = 0.8f;
        bigOakTrees.treeCondition = DecoTree.TreeCondition.NOISE_GREATER_AND_RANDOM_CHANCE;
        bigOakTrees.treeConditionNoise = 0f;
        bigOakTrees.treeConditionChance = 6;
        bigOakTrees.maxY = 105;
        this.addDeco(bigOakTrees);

        DecoBaseBiomeDecorations decoBaseBiomeDecorations = new DecoBaseBiomeDecorations();
        decoBaseBiomeDecorations.maxY = 105;
        decoBaseBiomeDecorations.notEqualsZeroChance = 8;
        this.addDeco(decoBaseBiomeDecorations);

        // Grass filler.
        DecoGrass decoGrass = new DecoGrass();
        decoGrass.minY = 63;
        decoGrass.maxY = 105;
        decoGrass.loops = 1;
        this.addDeco(decoGrass);
    }
}
