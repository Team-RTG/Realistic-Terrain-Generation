package teamrtg.rtg.modules.bop.biomes;

import biomesoplenty.api.biome.BOPBiomes;
import biomesoplenty.common.block.BlockBOPLeaves;
import biomesoplenty.common.block.BlockBOPLog;
import biomesoplenty.common.enums.BOPTrees;
import biomesoplenty.common.enums.BOPWoods;
import net.minecraft.init.Biomes;
import net.minecraft.init.Blocks;
import teamrtg.rtg.api.tools.deco.DecoBoulder;
import teamrtg.rtg.api.tools.deco.DecoFallenTree;
import teamrtg.rtg.api.tools.deco.helper.DecoHelperRandomSplit;
import teamrtg.rtg.api.tools.surface.SurfaceBase;
import teamrtg.rtg.api.world.RTGWorld;
import teamrtg.rtg.api.world.biome.TerrainBase;
import teamrtg.rtg.api.world.biome.deco.DecoBase;
import teamrtg.rtg.api.world.biome.deco.DecoBaseBiomeDecorations;
import teamrtg.rtg.api.world.biome.surface.part.SurfacePart;
import teamrtg.rtg.modules.bop.RTGBiomeBOP;

public class RTGBiomeBOPDeadForest extends RTGBiomeBOP {

    public RTGBiomeBOPDeadForest() {
        super(BOPBiomes.dead_forest.get(), Biomes.RIVER);
    }

    @Override
    public TerrainBase initTerrain() {
        return new TerrainBase() {

            private float minHeight = 58f;
            private float maxHeight = 80f;
            private float hillStrength = 30f;
            private float deadForestGroundAmplitude = 10f;

            {
                this.minHeight = minHeight;
                this.maxHeight = (maxHeight > rollingHillsMaxHeight) ? rollingHillsMaxHeight : ((maxHeight < this.minHeight) ? rollingHillsMaxHeight : maxHeight);
                this.hillStrength = hillStrength;
            }

            @Override
            public float generateNoise(RTGWorld rtgWorld, int x, int y, float biomeWeight, float border, float river) {
                return terrainRollingHills(x, y, rtgWorld.simplex, river, hillStrength, maxHeight, groundNoise, deadForestGroundAmplitude, 0f);
            }
        };
    }

    @Override
    public SurfacePart initSurface() {
        return SurfaceBase.surfaceGenericCliffs(this);
    }

    @Override
    public void initDecos() {

        DecoBoulder decoBoulder = new DecoBoulder();
        decoBoulder.boulderBlock = Blocks.COBBLESTONE.getDefaultState();
        decoBoulder.chance = 16;
        decoBoulder.maxY = 95;
        decoBoulder.strengthFactor = 1f;
        this.addDeco(decoBoulder);

        DecoFallenTree decoFallenTree1 = new DecoFallenTree();
        decoFallenTree1.loops = 1;
        decoFallenTree1.distribution.noiseDivisor = 100f;
        decoFallenTree1.distribution.noiseFactor = 6f;
        decoFallenTree1.distribution.noiseAddend = 0.8f;
        decoFallenTree1.logCondition = DecoFallenTree.LogCondition.NOISE_GREATER_AND_RANDOM_CHANCE;
        decoFallenTree1.logConditionNoise = 0f;
        decoFallenTree1.logConditionChance = 10;
        decoFallenTree1.maxY = 100;
        decoFallenTree1.logBlock = BlockBOPLog.paging.getVariantState(BOPWoods.DEAD);
        decoFallenTree1.leavesBlock = BlockBOPLeaves.paging.getVariantState(BOPTrees.DEAD);
        decoFallenTree1.minSize = 3;
        decoFallenTree1.maxSize = 5;

        DecoFallenTree decoFallenTree2 = new DecoFallenTree();
        decoFallenTree2.loops = 1;
        decoFallenTree2.distribution.noiseDivisor = 100f;
        decoFallenTree2.distribution.noiseFactor = 6f;
        decoFallenTree2.distribution.noiseAddend = 0.8f;
        decoFallenTree2.logCondition = DecoFallenTree.LogCondition.NOISE_GREATER_AND_RANDOM_CHANCE;
        decoFallenTree2.logConditionNoise = 0f;
        decoFallenTree2.logConditionChance = 10;
        decoFallenTree2.maxY = 100;
        decoFallenTree2.logBlock = Blocks.LOG.getStateFromMeta(1);
        decoFallenTree2.leavesBlock = Blocks.LEAVES.getStateFromMeta(1);
        decoFallenTree2.minSize = 3;
        decoFallenTree2.maxSize = 5;

        DecoHelperRandomSplit decoHelperRandomSplit = new DecoHelperRandomSplit();
        decoHelperRandomSplit.decos = new DecoBase[]{decoFallenTree2, decoFallenTree1};
        decoHelperRandomSplit.chances = new int[]{12, 1};
        this.addDeco(decoHelperRandomSplit);

        DecoBaseBiomeDecorations decoBaseBiomeDecorations = new DecoBaseBiomeDecorations();
        this.addDeco(decoBaseBiomeDecorations);
    }

    @Override
    public void initConfig() {

    }
}
