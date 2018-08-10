package teamrtg.rtg.modules.bop.biomes;

import biomesoplenty.api.biome.BOPBiomes;
import biomesoplenty.common.block.BlockBOPLog;
import biomesoplenty.common.enums.BOPWoods;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Biomes;
import net.minecraft.init.Blocks;
import teamrtg.rtg.api.tools.deco.DecoBoulder;
import teamrtg.rtg.api.tools.deco.DecoFallenTree;
import teamrtg.rtg.api.tools.deco.DecoJungleCacti;
import teamrtg.rtg.api.tools.surface.SurfaceBase;
import teamrtg.rtg.api.tools.terrain.*;
import teamrtg.rtg.api.world.RTGWorld;
import teamrtg.rtg.api.world.biome.TerrainBase;
import teamrtg.rtg.api.world.biome.deco.DecoBaseBiomeDecorations;
import teamrtg.rtg.api.world.biome.surface.part.SurfacePart;
import teamrtg.rtg.modules.bop.RTGBiomeBOP;

public class RTGBiomeBOPLushDesert extends RTGBiomeBOP {

    public RTGBiomeBOPLushDesert() {

        super(BOPBiomes.lush_desert.get(), Biomes.RIVER);
    }

    @Override
    public void initConfig() {

    }

    @Override
    public TerrainBase initTerrain() {

        return new TerrainBase() {

            private float minHeight;
            private float mesaWavelength;
            private float hillStrength;
            private float topBumpinessHeight = 2;
            private float topBumpinessWavelength = 15;
            private HeightEffect height;
            private HeightEffect groundEffect;

            {
                this.minHeight = 65f;
                this.mesaWavelength = 40f;
                this.hillStrength = 10f;

                groundEffect = new GroundEffect(3f);

                // this is variation in what's added to the top. Set to vary with the "standard" ruggedness
                HeightVariation topVariation = new HeightVariation();
                topVariation.height = hillStrength;
                topVariation.octave = 1;
                topVariation.wavelength = VariableRuggednessEffect.STANDARD_RUGGEDNESS_WAVELENGTH;


                // create some bumpiness to disguise the cliff heights
                HeightVariation topBumpiness = new HeightVariation();
                topBumpiness.height = topBumpinessHeight;
                topBumpiness.wavelength = topBumpinessWavelength;
                topBumpiness.octave = 3;

                // now make the top only show up on mesa
                height = new VariableRuggednessEffect(new RaiseEffect(0f), topVariation.plus(topBumpiness).plus(new RaiseEffect(hillStrength)), 0.3f, 0.15f, mesaWavelength);

            }

            @Override
            public float generateNoise(RTGWorld rtgWorld, int x, int y, float biomeWeight, float border, float river) {

                return riverized(minHeight + groundEffect.added(rtgWorld.simplex, rtgWorld.cell, x, y), river) + height.added(rtgWorld.simplex, rtgWorld.cell, x, y);
                //return terrainRollingHills(x, y, simplex, river, hillStrength, maxHeight, groundNoise, groundNoiseAmplitudeHills, 4f);
            }
        };
    }

    @Override
    public SurfacePart initSurface() {

        return SurfaceBase.surfaceGenericCliffs(this);
    }

    @Override
    public void initDecos() {

        DecoBaseBiomeDecorations decoBaseBiomeDecorations = new DecoBaseBiomeDecorations();
        this.addDeco(decoBaseBiomeDecorations);

        DecoBoulder decoBoulder = new DecoBoulder();
        decoBoulder.boulderBlock = Blocks.COBBLESTONE.getDefaultState();
        decoBoulder.maxY = 80;
        decoBoulder.chance = 16;
        decoBoulder.strengthFactor = 1f;
        this.addDeco(decoBoulder);

        DecoFallenTree decoFallenTree = new DecoFallenTree();
        decoFallenTree.distribution.noiseDivisor = 80f;
        decoFallenTree.distribution.noiseFactor = 60f;
        decoFallenTree.distribution.noiseAddend = -15f;
        decoFallenTree.logCondition = DecoFallenTree.LogCondition.NOISE_GREATER_AND_RANDOM_CHANCE;
        decoFallenTree.logConditionNoise = 0f;
        decoFallenTree.logConditionChance = 12;
        decoFallenTree.randomLogBlocks = new IBlockState[]{Blocks.LOG2.getStateFromMeta(1), BlockBOPLog.paging.getVariantState(BOPWoods.DEAD), Blocks.LOG.getDefaultState()};
        decoFallenTree.minSize = 3;
        decoFallenTree.maxSize = 5;
        this.addDeco(decoFallenTree);

        DecoJungleCacti decoJungleCacti = new DecoJungleCacti();
        decoJungleCacti.strengthFactor = 8f;
        decoJungleCacti.maxY = 110;
        this.addDeco(decoJungleCacti);
    }
}
