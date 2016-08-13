package rtg.world.biome.realistic.vanilla;

import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.world.gen.feature.WorldGenMegaJungle;

import rtg.api.biome.BiomeConfig;
import rtg.api.biome.vanilla.config.BiomeConfigVanillaJungleHills;
import rtg.world.biome.deco.*;
import rtg.world.biome.deco.helper.DecoHelperThisOrThat;
import rtg.world.gen.feature.tree.rtg.TreeRTG;
import rtg.world.gen.feature.tree.rtg.TreeRTGCocosNucifera;
import rtg.world.gen.feature.tree.rtg.TreeRTGRhizophoraMucronata;
import rtg.world.gen.surface.vanilla.SurfaceVanillaJungleHills;
import rtg.world.gen.terrain.vanilla.TerrainVanillaJungleHills;

public class RealisticBiomeVanillaJungleHills extends RealisticBiomeVanillaBase {

    public static IBlockState topBlock = Biome.jungleHills.topBlock;
    public static IBlockState fillerBlock = Biome.jungleHills.fillerBlock;

    public RealisticBiomeVanillaJungleHills(BiomeConfig config) {

        super(config,
            Biome.jungleHills,
            Biome.river,
            new TerrainVanillaJungleHills(72f, 40f),
            new SurfaceVanillaJungleHills(config, Blocks.grass.getDefaultState(), Blocks.dirt.getDefaultState(), false, null, 1f, 1.5f, 60f, 65f, 1.5f)
        );

        this.waterSurfaceLakeChance = 3;
        this.noLakes = true;

        // Blend of the WorldGenMegaJungle collection and some tall RTG Mangrove trees.

        TreeRTG mucronataTree = new TreeRTGRhizophoraMucronata(4, 5, 13f, 0.32f, 0.2f);
        mucronataTree.logBlock = Blocks.log.getStateFromMeta(3);
        mucronataTree.leavesBlock = Blocks.leaves.getStateFromMeta(3);
        mucronataTree.minTrunkSize = 3;
        mucronataTree.maxTrunkSize = 4;
        mucronataTree.minCrownSize = 10;
        mucronataTree.maxCrownSize = 27;
        this.addTree(mucronataTree);

        DecoTree mangroves = new DecoTree(mucronataTree);
        mangroves.loops = 3;
        mangroves.treeType = DecoTree.TreeType.RTG_TREE;
        mangroves.treeCondition = DecoTree.TreeCondition.RANDOM_CHANCE;
        mangroves.treeConditionChance = 2;
        mangroves.maxY = 160;

        DecoTree megaJungle = new DecoTree(new WorldGenMegaJungle(false, 10, 27, Blocks.log.getStateFromMeta(3), Blocks.leaves.getStateFromMeta(3)));
        megaJungle.logBlock = Blocks.log.getStateFromMeta(3);
        megaJungle.leavesBlock = Blocks.leaves.getStateFromMeta(3);
        megaJungle.minTrunkSize = 3;
        megaJungle.maxTrunkSize = 4;
        megaJungle.minCrownSize = 10;
        megaJungle.maxCrownSize = 27;
        megaJungle.loops = 3;
        megaJungle.treeType = DecoTree.TreeType.WORLDGEN;
        megaJungle.treeCondition = DecoTree.TreeCondition.RANDOM_CHANCE;
        megaJungle.treeConditionChance = 2;
        megaJungle.maxY = 160;

        DecoHelperThisOrThat decoHelperThisOrThat = new DecoHelperThisOrThat(3, DecoHelperThisOrThat.ChanceType.NOT_EQUALS_ZERO, megaJungle, mangroves);
        this.addDeco(decoHelperThisOrThat);

        // Add some palm trees for variety.

        TreeRTG nuciferaTree = new TreeRTGCocosNucifera();
        nuciferaTree.minTrunkSize = 7;
        nuciferaTree.maxTrunkSize = 9;
        nuciferaTree.minCrownSize = 6;
        nuciferaTree.maxCrownSize = 8;
        this.addTree(nuciferaTree);

        DecoTree palmCustom = new DecoTree(nuciferaTree);
        palmCustom.loops = 1;
        palmCustom.treeType = DecoTree.TreeType.RTG_TREE;
        palmCustom.treeCondition = DecoTree.TreeCondition.RANDOM_CHANCE;
        palmCustom.treeConditionChance = 3;
        palmCustom.maxY = 160;
        this.addDeco(palmCustom);

        // Another pass of the WorldGenMegaJungle collection for extra jungleness.
        this.addDeco(decoHelperThisOrThat);

        // Jungle logs.
        DecoFallenTree decoFallenTree = new DecoFallenTree();
        decoFallenTree.loops = 1;
        decoFallenTree.distribution.noiseDivisor = 100f;
        decoFallenTree.distribution.noiseFactor = 5f;
        decoFallenTree.distribution.noiseAddend = 0.8f;
        decoFallenTree.logCondition = DecoFallenTree.LogCondition.NOISE_GREATER_AND_RANDOM_CHANCE;
        decoFallenTree.logConditionNoise = 0f;
        decoFallenTree.logConditionChance = 3;
        decoFallenTree.logBlock = Blocks.log.getStateFromMeta(3);
        decoFallenTree.leavesBlock = Blocks.leaves.getStateFromMeta(3);
        decoFallenTree.minSize = 4;
        decoFallenTree.maxSize = 9;
        this.addDeco(decoFallenTree, this.config._boolean(BiomeConfigVanillaJungleHills.decorationLogsId));

        // At this point, let's hand over some of the decoration to the base biome, but only about 85% of the time.
        DecoBaseBiomeDecorations decoBaseBiomeDecorations = new DecoBaseBiomeDecorations();
        decoBaseBiomeDecorations.notEqualsZeroChance = 6;
        decoBaseBiomeDecorations.loops = 1;
        this.addDeco(decoBaseBiomeDecorations);

        // A combo-deal of lilypads and vines. (This could probably be pulled out into individual decos.)
        DecoJungleLilypadVines decoJungleLilypadVines = new DecoJungleLilypadVines();
        this.addDeco(decoJungleLilypadVines);

        // A combo-deal of grass and vines. (This could probably be pulled out into individual decos.)
        DecoJungleGrassVines decoJungleGrassVines = new DecoJungleGrassVines();
        this.addDeco(decoJungleLilypadVines);

        // Flowers.
        DecoFlowersRTG decoFlowersRTG = new DecoFlowersRTG();
        decoFlowersRTG.flowers = new int[]{5}; // Only orange tulips fit in with the colour scheme.
        decoFlowersRTG.chance = 4;
        decoFlowersRTG.maxY = 120;
        decoFlowersRTG.strengthFactor = 2f;
        this.addDeco(decoFlowersRTG);

        // Tall cacti on red sand - matches the colour scheme nicely.
        DecoJungleCacti decoJungleCacti = new DecoJungleCacti();
        decoJungleCacti.strengthFactor = 8f;
        decoJungleCacti.maxY = 120;
        decoJungleCacti.sandOnly = false;
        decoJungleCacti.extraHeight = 7;
        decoJungleCacti.sandMeta = (byte) 1;
        this.addDeco(decoJungleCacti, this.config._boolean(BiomeConfigVanillaJungleHills.decorationCactusId));

        // Mossy boulders for the green.
        DecoBoulder decoBoulder = new DecoBoulder();
        decoBoulder.boulderBlock = Blocks.mossy_cobblestone.getDefaultState();
        decoBoulder.chance = 16;
        decoBoulder.maxY = 95;
        decoBoulder.strengthFactor = 2f;
        this.addDeco(decoBoulder);

        // Grass filler.
        DecoGrass decoGrass = new DecoGrass();
        decoGrass.maxY = 128;
        decoGrass.strengthFactor = 12f;
        this.addDeco(decoGrass);
    }
}
