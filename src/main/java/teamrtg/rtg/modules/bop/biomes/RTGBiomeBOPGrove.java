package teamrtg.rtg.modules.bop.biomes;

import biomesoplenty.api.biome.BOPBiomes;
import net.minecraft.init.Biomes;
import net.minecraft.init.Blocks;
import teamrtg.rtg.api.tools.deco.DecoFallenTree;
import teamrtg.rtg.api.tools.deco.DecoFlowersRTG;
import teamrtg.rtg.api.tools.deco.DecoGrass;
import teamrtg.rtg.api.tools.deco.DecoShrub;
import teamrtg.rtg.api.tools.deco.helper.DecoHelper5050;
import teamrtg.rtg.api.tools.surface.SurfaceBase;
import teamrtg.rtg.api.world.RTGWorld;
import teamrtg.rtg.api.world.biome.TerrainBase;
import teamrtg.rtg.api.world.biome.deco.DecoBaseBiomeDecorations;
import teamrtg.rtg.api.world.biome.surface.part.SurfacePart;
import teamrtg.rtg.modules.bop.RTGBiomeBOP;

public class RTGBiomeBOPGrove extends RTGBiomeBOP {

    public RTGBiomeBOPGrove() {
        super(BOPBiomes.grove.get(), Biomes.RIVER);
    }

    @Override
    public TerrainBase initTerrain() {
        return new TerrainBase() {

            private float baseHeight = 64f;
            private float peakyHillWavelength = 40f;
            private float peakyHillStrength = 5f;
            private float smoothHillWavelength = 20f;
            private float smoothHillStrength = 10f;

            @Override
            public float generateNoise(RTGWorld rtgWorld, int x, int y, float biomeWeight, float border, float river) {

                // no ground noise

                float h = this.terrainGrasslandHills(x, y, rtgWorld.simplex, rtgWorld.cell, river,  smoothHillWavelength, smoothHillStrength, peakyHillWavelength, peakyHillStrength,baseHeight);

                return h;
            }
        };
    }

    @Override
    public SurfacePart initSurface() {
        return SurfaceBase.surfaceGenericCliffs(this);
    }

    @Override
    public void initDecos() {

        DecoFallenTree decoFallenTree1 = new DecoFallenTree();
        decoFallenTree1.distribution.noiseDivisor = 80f;
        decoFallenTree1.distribution.noiseFactor = 60f;
        decoFallenTree1.distribution.noiseAddend = -15f;
        decoFallenTree1.logCondition = DecoFallenTree.LogCondition.X_DIVIDED_BY_STRENGTH;
        decoFallenTree1.logConditionNoise = 8f;
        decoFallenTree1.logConditionChance = 1;
        decoFallenTree1.maxY = 100;
        decoFallenTree1.logBlock = Blocks.LOG.getStateFromMeta(2);
        decoFallenTree1.leavesBlock = Blocks.LEAVES.getStateFromMeta(2);
        decoFallenTree1.minSize = 3;
        decoFallenTree1.maxSize = 6;

        DecoFallenTree decoFallenTree2 = new DecoFallenTree();
        decoFallenTree2.distribution.noiseDivisor = 80f;
        decoFallenTree2.distribution.noiseFactor = 60f;
        decoFallenTree2.distribution.noiseAddend = -15f;
        decoFallenTree2.logCondition = DecoFallenTree.LogCondition.X_DIVIDED_BY_STRENGTH;
        decoFallenTree2.logConditionNoise = 8f;
        decoFallenTree2.logConditionChance = 1;
        decoFallenTree2.maxY = 100;
        decoFallenTree2.logBlock = Blocks.LOG2.getStateFromMeta(1);
        decoFallenTree2.leavesBlock = Blocks.LEAVES.getStateFromMeta(1);
        decoFallenTree2.minSize = 3;
        decoFallenTree2.maxSize = 6;

        DecoHelper5050 decoHelperHelper5050 = new DecoHelper5050(decoFallenTree1, decoFallenTree2);
        this.addDeco(decoHelperHelper5050);

        DecoShrub decoShrubCustom = new DecoShrub();
        decoShrubCustom.logBlock = Blocks.LOG.getStateFromMeta(2);
        decoShrubCustom.leavesBlock = Blocks.LEAVES.getStateFromMeta(2);
        decoShrubCustom.maxY = 110;
        decoShrubCustom.strengthFactor = 2f;
        DecoShrub decoShrubCustom2 = new DecoShrub();
        decoShrubCustom2.logBlock = Blocks.LOG2.getStateFromMeta(1);
        decoShrubCustom2.leavesBlock = Blocks.LEAVES2.getStateFromMeta(1);
        decoShrubCustom2.maxY = 110;
        decoShrubCustom2.strengthFactor = 2f;
        DecoHelper5050 decoHelperHelper50502 = new DecoHelper5050(decoShrubCustom, decoShrubCustom2);
        this.addDeco(decoHelperHelper50502);

        DecoFlowersRTG decoFlowersRTG = new DecoFlowersRTG();
        decoFlowersRTG.flowers = new int[] {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11};
        decoFlowersRTG.maxY = 128;
        decoFlowersRTG.strengthFactor = 2f;
        this.addDeco(decoFlowersRTG);

        DecoGrass decoGrass = new DecoGrass();
        decoGrass.maxY = 128;
        decoGrass.strengthFactor = 12f;
        this.addDeco(decoGrass);

        DecoBaseBiomeDecorations decoBaseBiomeDecorations = new DecoBaseBiomeDecorations();
        this.addDeco(decoBaseBiomeDecorations);
    }

    @Override
    public void initConfig() {

    }
}
