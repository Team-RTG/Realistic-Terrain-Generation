package teamrtg.rtg.modules.bop.biomes;

import biomesoplenty.api.biome.BOPBiomes;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Biomes;
import net.minecraft.init.Blocks;
import teamrtg.rtg.api.tools.deco.DecoBoulder;
import teamrtg.rtg.api.tools.deco.DecoFallenTree;
import teamrtg.rtg.api.tools.deco.DecoShrub;
import teamrtg.rtg.api.tools.deco.DecoTree;
import teamrtg.rtg.api.tools.deco.helper.DecoHelper5050;
import teamrtg.rtg.api.tools.feature.tree.rtg.TreeRTG;
import teamrtg.rtg.api.tools.feature.tree.rtg.TreeRTGBetulaPapyrifera;
import teamrtg.rtg.api.tools.feature.tree.rtg.TreeRTGPiceaSitchensis;
import teamrtg.rtg.api.tools.surface.SurfaceBase;
import teamrtg.rtg.api.world.RTGWorld;
import teamrtg.rtg.api.world.biome.TerrainBase;
import teamrtg.rtg.api.world.biome.deco.DecoBaseBiomeDecorations;
import teamrtg.rtg.api.world.biome.surface.part.SurfacePart;
import teamrtg.rtg.modules.bop.RTGBiomeBOP;

public class RTGBiomeBOPLandOfLakes extends RTGBiomeBOP {

    public RTGBiomeBOPLandOfLakes() {
        super(BOPBiomes.land_of_lakes.get(), Biomes.RIVER);
    }

    @Override
    public TerrainBase initTerrain() {
        return new TerrainBase() {

            private float minHeight = 58f;
            private float maxHeight = 76f;
            private float hillStrength = 36f;

            {
                this.minHeight = minHeight;
                this.maxHeight = (maxHeight > rollingHillsMaxHeight) ? rollingHillsMaxHeight : ((maxHeight < this.minHeight) ? rollingHillsMaxHeight : maxHeight);
                this.hillStrength = hillStrength;
            }

            @Override
            public float generateNoise(RTGWorld rtgWorld, int x, int y, float biomeWeight, float border, float river) {
                return terrainRollingHills(x, y, rtgWorld.simplex, river, hillStrength, maxHeight, groundNoise, groundNoiseAmplitudeHills, 0f);
            }
        };
    }

    @Override
    public SurfacePart initSurface() {
        return SurfaceBase.surfaceGenericCliffs(this);
    }

    @Override
    public void initDecos() {

        TreeRTG birchTree = new TreeRTGBetulaPapyrifera();
        birchTree.logBlock = Blocks.LOG.getStateFromMeta(2);
        birchTree.leavesBlock = Blocks.LEAVES.getStateFromMeta(2);
        birchTree.minTrunkSize = 4;
        birchTree.maxTrunkSize = 10;
        birchTree.minCrownSize = 8;
        birchTree.maxCrownSize = 19;
        this.addTree(birchTree);

        DecoTree birchTrees = new DecoTree(birchTree);
        birchTrees.strengthFactorForLoops = 9f;
        birchTrees.treeType = DecoTree.TreeType.RTG_TREE;
        birchTrees.distribution.noiseDivisor = 100f;
        birchTrees.distribution.noiseFactor = 6f;
        birchTrees.distribution.noiseAddend = 0.8f;
        birchTrees.treeCondition = DecoTree.TreeCondition.NOISE_GREATER_AND_RANDOM_CHANCE;
        birchTrees.treeConditionChance = 1;
        birchTrees.treeConditionNoise = 0f;
        birchTrees.maxY = 120;

        TreeRTG sitchensisTree = new TreeRTGPiceaSitchensis();
        sitchensisTree.logBlock = Blocks.LOG.getStateFromMeta(1);
        sitchensisTree.leavesBlock = Blocks.LEAVES.getStateFromMeta(1);
        sitchensisTree.minTrunkSize = 4;
        sitchensisTree.maxTrunkSize = 9;
        sitchensisTree.minCrownSize = 5;
        sitchensisTree.maxCrownSize = 14;
        this.addTree(sitchensisTree);

        DecoTree smallPine = new DecoTree(sitchensisTree);
        smallPine.strengthFactorForLoops = 9f;
        smallPine.treeType = DecoTree.TreeType.RTG_TREE;
        smallPine.distribution.noiseDivisor = 100f;
        smallPine.distribution.noiseFactor = 6f;
        smallPine.distribution.noiseAddend = 0.8f;
        smallPine.treeCondition = DecoTree.TreeCondition.NOISE_GREATER_AND_RANDOM_CHANCE;
        smallPine.treeConditionChance = 1;
        smallPine.treeConditionNoise = 0f;
        smallPine.maxY = 120;

        DecoHelper5050 decoHelper5050 = new DecoHelper5050(birchTrees, smallPine);

        DecoFallenTree decoFallenTree = new DecoFallenTree();
        decoFallenTree.distribution.noiseDivisor = 100f;
        decoFallenTree.distribution.noiseFactor = 6f;
        decoFallenTree.distribution.noiseAddend = 0.8f;
        decoFallenTree.logCondition = DecoFallenTree.LogCondition.NOISE_GREATER_AND_RANDOM_CHANCE;
        decoFallenTree.logConditionNoise = 0f;
        decoFallenTree.logConditionChance = 12;
        decoFallenTree.randomLogBlocks = new IBlockState[]{Blocks.LOG.getDefaultState(), Blocks.LOG.getStateFromMeta(1), Blocks.LOG.getStateFromMeta(2)};
        decoFallenTree.minSize = 8;
        decoFallenTree.maxSize = 12;
        this.addDeco(decoFallenTree);

        DecoShrub decoShrub = new DecoShrub();
        decoShrub.maxY = 110;
        decoShrub.strengthFactor = 3f;
        this.addDeco(decoShrub);

        DecoBoulder decoBoulder = new DecoBoulder();
        decoBoulder.boulderBlock = Blocks.COBBLESTONE.getDefaultState();
        decoBoulder.maxY = 80;
        decoBoulder.chance = 12;
        decoBoulder.strengthFactor = 1f;
        this.addDeco(decoBoulder);

        DecoBaseBiomeDecorations decoBaseBiomeDecorations = new DecoBaseBiomeDecorations();
        this.addDeco(decoBaseBiomeDecorations);
    }

    @Override
    public void initConfig() {

    }
}
