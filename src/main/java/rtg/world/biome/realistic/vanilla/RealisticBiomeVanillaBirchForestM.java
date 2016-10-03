package rtg.world.biome.realistic.vanilla;

import net.minecraft.block.*;
import net.minecraft.init.Biomes;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.Biome;

import rtg.api.biome.BiomeConfig;
import rtg.api.biome.vanilla.config.BiomeConfigVanillaBirchForestM;
import rtg.world.biome.deco.*;
import rtg.world.gen.feature.tree.rtg.TreeRTG;
import rtg.world.gen.feature.tree.rtg.TreeRTGBetulaPapyrifera;
import rtg.world.gen.surface.vanilla.SurfaceVanillaBirchForestM;
import rtg.world.gen.terrain.vanilla.TerrainVanillaBirchForestM;

public class RealisticBiomeVanillaBirchForestM extends RealisticBiomeVanillaBase {

    public static Biome biome = Biomes.MUTATED_BIRCH_FOREST;
    public static Biome river = Biomes.RIVER;

    public RealisticBiomeVanillaBirchForestM(BiomeConfig config) {

        super(config, biome, river,
            new TerrainVanillaBirchForestM(),
            new SurfaceVanillaBirchForestM(config, biome.topBlock, biome.fillerBlock, 0f, 1.5f, 60f, 65f, 1.5f, Blocks.DIRT.getDefaultState(), 0.15f)
        );

        this.noLakes = true;

        TreeRTG tallBirch = new TreeRTGBetulaPapyrifera();
        tallBirch.logBlock = Blocks.LOG.getDefaultState().withProperty(BlockOldLog.VARIANT, BlockPlanks.EnumType.BIRCH);
        tallBirch.leavesBlock = Blocks.LEAVES.getDefaultState().withProperty(BlockOldLeaf.VARIANT, BlockPlanks.EnumType.BIRCH);
        tallBirch.minTrunkSize = 16;
        tallBirch.maxTrunkSize = 23;
        tallBirch.minCrownSize = 4;
        tallBirch.maxCrownSize = 11;
        this.addTree(tallBirch);

        DecoTree superTallBirch = new DecoTree(tallBirch);
        superTallBirch.strengthFactorForLoops = 16f;
        superTallBirch.strengthNoiseFactorForLoops = true;
        superTallBirch.treeType = DecoTree.TreeType.RTG_TREE;
        superTallBirch.distribution.noiseDivisor = 80f;
        superTallBirch.distribution.noiseFactor = 60f;
        superTallBirch.distribution.noiseAddend = -15f;
        superTallBirch.treeCondition = DecoTree.TreeCondition.ALWAYS_GENERATE;
        superTallBirch.maxY = 100;
        this.addDeco(superTallBirch);

        DecoLargeFernDoubleTallgrass decoDoublePlants = new DecoLargeFernDoubleTallgrass();
        decoDoublePlants.maxY = 128;
        decoDoublePlants.strengthFactor = 8f;
        this.addDeco(decoDoublePlants);

        DecoGrass decoGrass = new DecoGrass();
        decoGrass.maxY = 128;
        decoGrass.strengthFactor = 24f;
        this.addDeco(decoGrass);

        DecoFallenTree decoFallenTree = new DecoFallenTree();
        decoFallenTree.logCondition = DecoFallenTree.LogCondition.RANDOM_CHANCE;
        decoFallenTree.logConditionChance = 20;
        decoFallenTree.logBlock = Blocks.LOG.getDefaultState().withProperty(BlockOldLog.VARIANT, BlockPlanks.EnumType.BIRCH);
        decoFallenTree.leavesBlock = Blocks.LEAVES.getDefaultState().withProperty(BlockOldLeaf.VARIANT, BlockPlanks.EnumType.BIRCH);
        decoFallenTree.minSize = 3;
        decoFallenTree.maxSize = 6;
        this.addDeco(decoFallenTree, this.config._boolean(BiomeConfigVanillaBirchForestM.decorationLogsId));

        DecoShrub decoShrub = new DecoShrub();
        decoShrub.maxY = 110;
        decoShrub.strengthFactor = 2f;
        this.addDeco(decoShrub);
    }
}
