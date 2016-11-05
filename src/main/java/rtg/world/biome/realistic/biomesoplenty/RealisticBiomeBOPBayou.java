package rtg.world.biome.realistic.biomesoplenty;

import net.minecraft.block.BlockLeaves;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Biomes;
import net.minecraft.world.biome.Biome;

import biomesoplenty.api.biome.BOPBiomes;
import biomesoplenty.api.block.BOPBlocks;

import rtg.api.biome.BiomeConfig;
import rtg.api.biome.biomesoplenty.config.BiomeConfigBOPBayou;
import rtg.world.biome.deco.*;
import rtg.world.gen.surface.biomesoplenty.SurfaceBOPBayou;
import rtg.world.gen.terrain.biomesoplenty.TerrainBOPBayou;

public class RealisticBiomeBOPBayou extends RealisticBiomeBOPBase {

    public static Biome biome = BOPBiomes.bayou.get();
    public static Biome river = Biomes.RIVER;

    private static IBlockState mudBlock = BOPBlocks.mud.getDefaultState();
    private static IBlockState logBlock = BOPBlocks.log_2.getStateFromMeta(5);

    private static IBlockState leavesBlock = BOPBlocks.leaves_4.getStateFromMeta(3)
        .withProperty(BlockLeaves.CHECK_DECAY, false)
        .withProperty(BlockLeaves.DECAYABLE, false);

    public RealisticBiomeBOPBayou(BiomeConfig config) {

        super(config, biome, river,
            new TerrainBOPBayou(),
            new SurfaceBOPBayou(config, biome.topBlock, biome.fillerBlock, 0f, 1.5f, 60f, 65f, 1.5f, biome.topBlock, 0.10f)
        );

        this.waterSurfaceLakeChance = 0; // We want RTG ponds, not Mojang lakes.

        DecoPond decoPond = new DecoPond();
        decoPond.chunksPerPond = 1;
        decoPond.minY = 63;
        decoPond.loops = 8;
        this.addDeco(decoPond);

//        TreeRTG myrtilloidesTree = new TreeRTGSalixMyrtilloides();
//        myrtilloidesTree.logBlock = logBlock;
//        myrtilloidesTree.leavesBlock = leavesBlock;
//        myrtilloidesTree.validGroundBlocks.add(mudBlock);
//        this.addTree(myrtilloidesTree);
//        DecoTree decoTrees = new DecoTree(myrtilloidesTree);
//        decoTrees.treeType = DecoTree.TreeType.RTG_TREE;
//        decoTrees.treeCondition = DecoTree.TreeCondition.RANDOM_CHANCE;
//        decoTrees.treeConditionChance = 4;
//        decoTrees.logBlock = logBlock;
//        decoTrees.leavesBlock = leavesBlock;
//        decoTrees.maxY = 90;
//        this.addDeco(decoTrees);

        /*
         * STOP! Don't add anymore trees! BOP seems to generate a batch of its trees every time RTG generates a batch
         * of its trees, even though we're not calling the BOP Bayou's decorate() method.
         */

//        TreeRTG roseaTree = new TreeRTGCeibaRosea(16f, 5, 0.32f, 0.1f);
//        roseaTree.logBlock = logBlock;
//        roseaTree.leavesBlock = leavesBlock;
//        roseaTree.validGroundBlocks.add(mudBlock);
//        roseaTree.minTrunkSize = 2;
//        roseaTree.maxTrunkSize = 3;
//        roseaTree.minCrownSize = 10;
//        roseaTree.maxCrownSize = 18;
//        roseaTree.noLeaves = false;
//        this.addTree(roseaTree);
//        DecoTree ceibaRoseaTree = new DecoTree(roseaTree);
//        ceibaRoseaTree.treeType = DecoTree.TreeType.RTG_TREE;
//        ceibaRoseaTree.treeCondition = DecoTree.TreeCondition.RANDOM_CHANCE;
//        ceibaRoseaTree.treeConditionChance = 4;
//        ceibaRoseaTree.maxY = 90;
//        ceibaRoseaTree.scatter = new DecoTree.Scatter(16, 0);
//        this.addDeco(ceibaRoseaTree);

        DecoBaseBiomeDecorations decoBaseBiomeDecorations = new DecoBaseBiomeDecorations();
        decoBaseBiomeDecorations.notEqualsZeroChance = 4;
        this.addDeco(decoBaseBiomeDecorations);

        // Shrubs to fill in the blanks.
        DecoShrub decoShrubOak = new DecoShrub();
        decoShrubOak.maxY = 90;
        decoShrubOak.strengthFactor = 4f;
        decoShrubOak.chance = 3;
        this.addDeco(decoShrubOak);

        DecoFallenTree decoFallenTree = new DecoFallenTree();
        decoFallenTree.distribution.noiseDivisor = 80f;
        decoFallenTree.distribution.noiseFactor = 60f;
        decoFallenTree.distribution.noiseAddend = -15f;
        decoFallenTree.logCondition = DecoFallenTree.LogCondition.NOISE_GREATER_AND_RANDOM_CHANCE;
        decoFallenTree.logConditionNoise = -0.2f;
        decoFallenTree.logConditionChance = 4;
        decoFallenTree.logBlock = logBlock;
        decoFallenTree.leavesBlock = leavesBlock;
        decoFallenTree.minSize = 3;
        decoFallenTree.maxSize = 6;
        this.addDeco(decoFallenTree, this.config._boolean(BiomeConfigBOPBayou.decorationLogsId));

        DecoJungleLilypadVines decoJungleLilypadVines = new DecoJungleLilypadVines();
        this.addDeco(decoJungleLilypadVines);

        DecoGrassDoubleTallgrass decoGrassDoubleTallgrass = new DecoGrassDoubleTallgrass();
        decoGrassDoubleTallgrass.maxY = 90;
        decoGrassDoubleTallgrass.strengthFactor = 4f;
        decoGrassDoubleTallgrass.doubleGrassChance = 8;
        this.addDeco(decoGrassDoubleTallgrass);

        DecoGrass decoGrass = new DecoGrass();
        decoGrass.maxY = 90;
        decoGrass.strengthFactor = 4f;
        decoGrass.chance = 2;
        this.addDeco(decoGrass);

        DecoMushrooms decoMushrooms = new DecoMushrooms();
        decoMushrooms.maxY = 90;
        decoMushrooms.randomType = rtg.world.biome.deco.DecoMushrooms.RandomType.ALWAYS_GENERATE;
        this.addDeco(decoMushrooms);
    }
}
