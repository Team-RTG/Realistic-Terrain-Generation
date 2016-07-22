package teamrtg.rtg.modules.bop.biomes;

import biomesoplenty.api.biome.BOPBiomes;
import net.minecraft.init.Biomes;
import net.minecraft.init.Blocks;
import teamrtg.rtg.api.tools.deco.DecoBoulder;
import teamrtg.rtg.api.tools.deco.DecoFallenTree;
import teamrtg.rtg.api.tools.deco.helper.DecoHelper5050;
import teamrtg.rtg.api.tools.surface.SurfaceBase;
import teamrtg.rtg.api.world.RTGWorld;
import teamrtg.rtg.api.world.biome.TerrainBase;
import teamrtg.rtg.api.world.biome.deco.DecoBaseBiomeDecorations;
import teamrtg.rtg.api.world.biome.surface.part.SurfacePart;
import teamrtg.rtg.modules.bop.RTGBiomeBOP;

public class RTGBiomeBOPEucalyptusForest extends RTGBiomeBOP {

    public RTGBiomeBOPEucalyptusForest() {
        super(BOPBiomes.eucalyptus_forest.get(), Biomes.RIVER);
    }

    @Override
    public TerrainBase initTerrain() {
        return new TerrainBase() {

            private float baseHeight = 76f;
            private float peakyHillWavelength = 40f;
            private float peakyHillStrength = 20f;
            private float smoothHillWavelength = 20f;
            private float smoothHillStrength = 10f;

            @Override
            public float generateNoise(RTGWorld rtgWorld, int x, int y, float biomeWeight, float border, float river) {

                groundNoise = groundNoise(x, y, groundNoiseAmplitudeHills, rtgWorld.simplex);
                float h = terrainGrasslandHills(x, y, rtgWorld.simplex, rtgWorld.cell, river, peakyHillWavelength, peakyHillStrength, smoothHillWavelength, smoothHillStrength, baseHeight);
                return riverized(groundNoise+h,river) ;
            }
        };
    }

    @Override
    public SurfacePart initSurface() {
        return SurfaceBase.surfaceGenericCliffs(this);
    }

    @Override
    public void initDecos() {

        DecoBoulder decoBoulder1 = new DecoBoulder();
        decoBoulder1.boulderBlock = Blocks.COBBLESTONE.getDefaultState();
        decoBoulder1.maxY = 80;
        decoBoulder1.chance = 12;
        decoBoulder1.strengthFactor = 1f;
        DecoBoulder decoBoulder2 = new DecoBoulder();
        decoBoulder2.boulderBlock = Blocks.COBBLESTONE.getDefaultState();
        decoBoulder2.maxY = 80;
        decoBoulder2.chance = 12;
        decoBoulder2.strengthFactor = 1f;
        DecoHelper5050 decoHelper5050 = new DecoHelper5050(decoBoulder1, decoBoulder2);
        this.addDeco(decoHelper5050);

        DecoFallenTree decoFallenTree = new DecoFallenTree();
        decoFallenTree.distribution.noiseDivisor = 100f;
        decoFallenTree.distribution.noiseFactor = 6f;
        decoFallenTree.distribution.noiseAddend = 0.8f;
        decoFallenTree.logCondition = DecoFallenTree.LogCondition.NOISE_GREATER_AND_RANDOM_CHANCE;
        decoFallenTree.logConditionNoise = 0f;
        decoFallenTree.logConditionChance = 16;
        decoFallenTree.logBlock = Blocks.LOG.getStateFromMeta(3);
        decoFallenTree.leavesBlock = Blocks.LEAVES.getStateFromMeta(3);
        decoFallenTree.minSize = 8;
        decoFallenTree.maxSize = 14;
        this.addDeco(decoFallenTree);

        DecoBaseBiomeDecorations decoBaseBiomeDecorations = new DecoBaseBiomeDecorations();
        this.addDeco(decoBaseBiomeDecorations);
    }

    @Override
    public void initConfig() {

    }
}
