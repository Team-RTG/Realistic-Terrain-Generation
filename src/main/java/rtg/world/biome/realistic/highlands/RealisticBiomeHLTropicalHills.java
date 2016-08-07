package rtg.world.biome.realistic.highlands;

import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.BiomeGenBase;

import rtg.api.biome.BiomeConfig;
import rtg.api.biome.highlands.config.BiomeConfigHLTropicalHills;
import rtg.world.biome.deco.*;
import rtg.world.gen.surface.highlands.SurfaceHLTropicalHills;
import rtg.world.gen.terrain.highlands.TerrainHLTropicalHills;

public class RealisticBiomeHLTropicalHills extends RealisticBiomeHLBase {

    public RealisticBiomeHLTropicalHills(BiomeGenBase biome, BiomeConfig config) {

        super(config, biome, BiomeGenBase.river,
            new TerrainHLTropicalHills(),
            new SurfaceHLTropicalHills(config, biome.topBlock, biome.fillerBlock)
        );

        this.waterSurfaceLakeChance = 3;

        // First, a thick layer of shrubs to make sure the canopy trees are spaced out enough.
//        DecoHLTree highlandsShrubs = new DecoHLTree();
//        highlandsShrubs.logMeta = 3;
//        highlandsShrubs.leavesMeta = 0;
//        highlandsShrubs.strengthFactorForLoops = 4f;
//        highlandsShrubs.treeType = rtg.world.biome.deco.DecoHLTree.TreeType.HIGHLANDS_SHRUB;
//        highlandsShrubs.treeCondition = DecoTree.TreeCondition.ALWAYS_GENERATE;
//        highlandsShrubs.maxY = 90;
//        this.addDeco(highlandsShrubs);

        /**
         *  Fill in the gaps with sparse (2f), thin-trunked canopy trees.
         *  If you want more trees, try decreasing the density of shrubs above instead of increasing the density of trees here.
         */
        DecoHLTree thinCanopyTrees = new DecoHLTree();
        thinCanopyTrees.thickTrunk = false;
        thinCanopyTrees.minSize = 13;
        thinCanopyTrees.maxSize = 24;
        thinCanopyTrees.strengthFactorForLoops = 2f;
        thinCanopyTrees.treeType = rtg.world.biome.deco.DecoHLTree.TreeType.CANOPY;
        thinCanopyTrees.treeCondition = DecoTree.TreeCondition.ALWAYS_GENERATE;
        //thinCanopyTrees.treeConditionChance = 1;
        thinCanopyTrees.maxY = 120;
        this.addDeco(thinCanopyTrees);

//		DecoHLTree thickCanopyTrees = new DecoHLTree();
//		thickCanopyTrees.thickTrunk = true;
//		thickCanopyTrees.minSize = 13;
//		thickCanopyTrees.maxSize = 24;
//		thickCanopyTrees.loops = 1;
//		thickCanopyTrees.treeType = rtg.world.biome.deco.DecoHLTree.TreeType.CANOPY;
//		thickCanopyTrees.treeCondition = TreeCondition.RANDOM_CHANCE;
//		thickCanopyTrees.treeConditionChance = 4;
//		thickCanopyTrees.maxY = 120;
//		this.addDeco(thickCanopyTrees);

        // Canopy wood logs.
        DecoFallenTree decoFallenTree = new DecoFallenTree();
        decoFallenTree.loops = 1;
        decoFallenTree.distribution.noiseDivisor = 100f;
        decoFallenTree.distribution.noiseFactor = 5f;
        decoFallenTree.distribution.noiseAddend = 0.8f;
        decoFallenTree.logCondition = DecoFallenTree.LogCondition.NOISE_GREATER_AND_RANDOM_CHANCE;
        decoFallenTree.logConditionNoise = 0f;
        decoFallenTree.logConditionChance = 10;
        decoFallenTree.maxY = 90;
        decoFallenTree.logBlock = Blocks.log.getDefaultState();
        decoFallenTree.leavesBlock = Blocks.leaves.getDefaultState();
        decoFallenTree.minSize = 4;
        decoFallenTree.maxSize = 7;
        this.addDeco(decoFallenTree, this.config._boolean(BiomeConfigHLTropicalHills.decorationLogsId));

        // Sprinkle a few boulders around. No mossy cobble needed as vines will provide enough green in various places.
        DecoBoulder decoBoulder = new DecoBoulder();
        decoBoulder.boulderBlock = Blocks.cobblestone.getDefaultState();
        decoBoulder.chance = 16;
        decoBoulder.maxY = 95;
        decoBoulder.strengthFactor = 2f;
        this.addDeco(decoBoulder);

        /**
         *  Now that we've covered most of the chunk, let the base biome try to spawn in some thick-trunked canopy trees
         *  and other native decorations.
         */
        DecoBaseBiomeDecorations decoBaseBiomeDecorations = new DecoBaseBiomeDecorations();
        decoBaseBiomeDecorations.equalsZeroChance = 4;
        decoBaseBiomeDecorations.maxY = 210; // No trees at the top of mountains.
        this.addDeco(decoBaseBiomeDecorations);

        /**
         *  Vines should come after all full blocks have been placed for greatest effect.
         *  Throw in some lilypads to add contrast to the ponds.
         */
        DecoJungleLilypadVines decoJungleLilypadVines = new DecoJungleLilypadVines();
        this.addDeco(decoJungleLilypadVines);
        DecoJungleGrassVines decoJungleGrassVines = new DecoJungleGrassVines();
        this.addDeco(decoJungleLilypadVines);

        // White flowers to brighten up the place.
        DecoFlowersRTG decoFlowersRTG = new DecoFlowersRTG();
        decoFlowersRTG.flowers = new int[]{3, 6, 8};
        decoFlowersRTG.chance = 3;
        decoFlowersRTG.maxY = 120;
        decoFlowersRTG.strengthFactor = 2f;
        this.addDeco(decoFlowersRTG);

        // Grass & fern filler.
        DecoGrass decoGrass = new DecoGrass();
        decoGrass.maxY = 128;
        decoGrass.loops = 16;
        decoGrass.randomGrassBlocks = new IBlockState[]{Blocks.tallgrass.getStateFromMeta(2), Blocks.tallgrass.getStateFromMeta(2)};
        decoGrass.randomGrassMetas = new byte[]{(byte) 2, (byte) 2};
        this.addDeco(decoGrass);
    }
}
