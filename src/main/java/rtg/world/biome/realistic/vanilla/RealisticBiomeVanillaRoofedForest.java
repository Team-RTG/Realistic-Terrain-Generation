package rtg.world.biome.realistic.vanilla;

import net.minecraft.block.BlockDirt;
import net.minecraft.block.BlockNewLeaf;
import net.minecraft.block.BlockNewLog;
import net.minecraft.block.BlockPlanks;
import net.minecraft.init.Biomes;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.Biome;

import rtg.api.biome.BiomeConfig;
import rtg.api.biome.vanilla.config.BiomeConfigVanillaRoofedForest;
import rtg.world.biome.deco.*;
import rtg.world.biome.deco.helper.DecoHelperThisOrThat;
import rtg.world.gen.feature.tree.rtg.TreeRTG;
import rtg.world.gen.feature.tree.rtg.TreeRTGCeibaPentandra;
import rtg.world.gen.feature.tree.rtg.TreeRTGCeibaRosea;
import rtg.world.gen.feature.tree.rtg.TreeRTGRhizophoraMucronata;
import rtg.world.gen.surface.vanilla.SurfaceVanillaRoofedForest;
import rtg.world.gen.terrain.vanilla.TerrainVanillaRoofedForest;

public class RealisticBiomeVanillaRoofedForest extends RealisticBiomeVanillaBase {

    public static Biome biome = Biomes.ROOFED_FOREST;
    public static Biome river = Biomes.RIVER;

    public RealisticBiomeVanillaRoofedForest(BiomeConfig config) {

        super(config, biome, river,
            new TerrainVanillaRoofedForest(),
            new SurfaceVanillaRoofedForest(config, Blocks.GRASS.getDefaultState(), Blocks.DIRT.getDefaultState(), 0f, 1.5f, 60f, 65f, 1.5f, Blocks.DIRT.getDefaultState().withProperty(BlockDirt.VARIANT, BlockDirt.DirtType.PODZOL), 0.08f)
        );

        this.waterSurfaceLakeChance = 3;

        DecoMushrooms decoMushrooms = new DecoMushrooms();
        decoMushrooms.chance = 4;
        decoMushrooms.maxY = 90;
        decoMushrooms.randomType = rtg.world.biome.deco.DecoMushrooms.RandomType.ALWAYS_GENERATE;
        this.addDeco(decoMushrooms);

        TreeRTG mucronataTree = new TreeRTGRhizophoraMucronata(3, 4, 13f, 0.32f, 0.1f);
        mucronataTree.logBlock = Blocks.LOG2.getDefaultState().withProperty(BlockNewLog.VARIANT, BlockPlanks.EnumType.DARK_OAK);
        mucronataTree.leavesBlock = Blocks.LEAVES2.getDefaultState().withProperty(BlockNewLeaf.VARIANT, BlockPlanks.EnumType.DARK_OAK);
        mucronataTree.minTrunkSize = 2;
        mucronataTree.maxTrunkSize = 3;
        mucronataTree.minCrownSize = 10;
        mucronataTree.maxCrownSize = 18;
        mucronataTree.noLeaves = false;
        this.addTree(mucronataTree);

        DecoTree mangroveTree = new DecoTree(mucronataTree);
        mangroveTree.treeType = DecoTree.TreeType.RTG_TREE;
        mangroveTree.treeCondition = DecoTree.TreeCondition.RANDOM_CHANCE;
        mangroveTree.treeConditionChance = 1;
        mangroveTree.strengthFactorForLoops = 12f;
        mangroveTree.maxY = 110;
        mangroveTree.scatter = new DecoTree.Scatter(16, 0);
        this.addDeco(mangroveTree);

        TreeRTG pentandraTree = new TreeRTGCeibaPentandra(13f, 3, 0.32f, 0.1f);
        pentandraTree.logBlock = Blocks.LOG2.getDefaultState().withProperty(BlockNewLog.VARIANT, BlockPlanks.EnumType.DARK_OAK);
        pentandraTree.leavesBlock = Blocks.LEAVES2.getDefaultState().withProperty(BlockNewLeaf.VARIANT, BlockPlanks.EnumType.DARK_OAK);
        pentandraTree.minTrunkSize = 2;
        pentandraTree.maxTrunkSize = 3;
        pentandraTree.minCrownSize = 10;
        pentandraTree.maxCrownSize = 18;
        pentandraTree.noLeaves = false;
        this.addTree(pentandraTree);

        DecoTree ceibaPentandraTree = new DecoTree(pentandraTree);
        ceibaPentandraTree.treeType = DecoTree.TreeType.RTG_TREE;
        ceibaPentandraTree.treeCondition = DecoTree.TreeCondition.RANDOM_CHANCE;
        ceibaPentandraTree.treeConditionChance = 1;
        ceibaPentandraTree.strengthFactorForLoops = 12f;
        ceibaPentandraTree.maxY = 110;
        ceibaPentandraTree.scatter = new DecoTree.Scatter(16, 0);
        this.addDeco(ceibaPentandraTree);

        TreeRTG roseaTree = new TreeRTGCeibaRosea(16f, 5, 0.32f, 0.1f);
        roseaTree.logBlock = Blocks.LOG2.getDefaultState().withProperty(BlockNewLog.VARIANT, BlockPlanks.EnumType.DARK_OAK);
        roseaTree.leavesBlock = Blocks.LEAVES2.getDefaultState().withProperty(BlockNewLeaf.VARIANT, BlockPlanks.EnumType.DARK_OAK);
        roseaTree.minTrunkSize = 2;
        roseaTree.maxTrunkSize = 3;
        roseaTree.minCrownSize = 10;
        roseaTree.maxCrownSize = 18;
        roseaTree.noLeaves = false;
        this.addTree(roseaTree);

        DecoTree ceibaRoseaTree = new DecoTree(roseaTree);
        ceibaRoseaTree.treeType = DecoTree.TreeType.RTG_TREE;
        ceibaRoseaTree.treeCondition = DecoTree.TreeCondition.RANDOM_CHANCE;
        ceibaRoseaTree.treeConditionChance = 1;
        ceibaRoseaTree.strengthFactorForLoops = 12f;
        ceibaRoseaTree.maxY = 110;
        ceibaRoseaTree.scatter = new DecoTree.Scatter(16, 0);
        this.addDeco(ceibaRoseaTree);

        DecoFallenTree decoFallenTree = new DecoFallenTree();
        decoFallenTree.distribution.noiseDivisor = 80f;
        decoFallenTree.distribution.noiseFactor = 60f;
        decoFallenTree.distribution.noiseAddend = -15f;
        decoFallenTree.logCondition = DecoFallenTree.LogCondition.NOISE_GREATER_AND_RANDOM_CHANCE;
        decoFallenTree.logConditionChance = 16;
        decoFallenTree.logConditionNoise = 0f;
        decoFallenTree.logBlock = Blocks.LOG2.getDefaultState().withProperty(BlockNewLog.VARIANT, BlockPlanks.EnumType.DARK_OAK);
        decoFallenTree.leavesBlock = Blocks.LEAVES2.getDefaultState().withProperty(BlockNewLeaf.VARIANT, BlockPlanks.EnumType.DARK_OAK);
        decoFallenTree.minSize = 4;
        decoFallenTree.maxSize = 9;
        this.addDeco(decoFallenTree, this.config._boolean(BiomeConfigVanillaRoofedForest.decorationLogsId));

        DecoShrub darkOakShrub = new DecoShrub();
        darkOakShrub.logBlock = Blocks.LOG2.getDefaultState().withProperty(BlockNewLog.VARIANT, BlockPlanks.EnumType.DARK_OAK);
        darkOakShrub.leavesBlock = Blocks.LEAVES2.getDefaultState().withProperty(BlockNewLeaf.VARIANT, BlockPlanks.EnumType.DARK_OAK);
        darkOakShrub.maxY = 100;
        darkOakShrub.strengthFactor = 8f;

        DecoShrub oakShrub = new DecoShrub();
        oakShrub.logBlock = Blocks.LOG.getDefaultState();
        oakShrub.leavesBlock = Blocks.LEAVES.getDefaultState();
        oakShrub.maxY = 100;
        oakShrub.strengthFactor = 8f;

        this.addDeco(new DecoHelperThisOrThat(4, DecoHelperThisOrThat.ChanceType.NOT_EQUALS_ZERO, darkOakShrub, oakShrub));

        DecoBoulder decoBoulder = new DecoBoulder();
        decoBoulder.boulderBlock = Blocks.MOSSY_COBBLESTONE.getDefaultState();
        decoBoulder.chance = 16;
        decoBoulder.maxY = 80;
        decoBoulder.strengthFactor = 2f;
        this.addDeco(decoBoulder);

        DecoCobwebs decoCobwebs = new DecoCobwebs();
        decoCobwebs.chance = 1;
        decoCobwebs.minY = 63;
        decoCobwebs.maxY = 76;
        decoCobwebs.strengthFactor = 24f;
        decoCobwebs.adjacentBlock = Blocks.LOG2.getDefaultState().withProperty(BlockNewLog.VARIANT, BlockPlanks.EnumType.DARK_OAK);
        decoCobwebs.minAdjacents = 2;
        this.addDeco(decoCobwebs, this.config._boolean(BiomeConfigVanillaRoofedForest.decorationCobwebsId));

        DecoBaseBiomeDecorations decoBaseBiomeDecorations = new DecoBaseBiomeDecorations();
        decoBaseBiomeDecorations.notEqualsZeroChance = 2;
        decoBaseBiomeDecorations.maxY = 100;
        this.addDeco(decoBaseBiomeDecorations);

        DecoGrass decoGrass = new DecoGrass();
        decoGrass.maxY = 100;
        decoGrass.strengthFactor = 20f;
        this.addDeco(decoGrass);

        DecoDeadBush decoDeadBush = new DecoDeadBush();
        decoDeadBush.maxY = 100;
        decoDeadBush.chance = 2;
        decoDeadBush.strengthFactor = 2f;
        this.addDeco(decoDeadBush);
    }
}
