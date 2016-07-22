package teamrtg.rtg.modules.bop.biomes;

import biomesoplenty.api.biome.BOPBiomes;
import net.minecraft.init.Biomes;
import teamrtg.rtg.api.tools.surface.SurfaceBase;
import teamrtg.rtg.api.tools.terrain.*;
import teamrtg.rtg.api.world.RTGWorld;
import teamrtg.rtg.api.world.biome.TerrainBase;
import teamrtg.rtg.api.world.biome.deco.DecoBaseBiomeDecorations;
import teamrtg.rtg.api.world.biome.surface.part.SurfacePart;
import teamrtg.rtg.modules.bop.RTGBiomeBOP;

public class RTGBiomeBOPBambooForest extends RTGBiomeBOP {

    public RTGBiomeBOPBambooForest() {
        super(BOPBiomes.bamboo_forest.get(), Biomes.RIVER);
    }

    @Override
    public TerrainBase initTerrain() {
        return new TerrainBase() {

            /* Basic idea: High hilly terrain mixed with ground-noisy flats
             * using a transition that also generates the hills
             */

            private float hillockWavelength = 30;
            private float hillockBoost = 5;
            private float hillockVariance = 10;
            private float hillockSpikeWidth = 8;
            private float hillockSpikeHeight = 4;
            private float terrainBase = 68;

            private JitterEffect biomeHeight;// this includes the base

            {

                // bumpy hills on top
                BumpyHillsEffect onTop = new BumpyHillsEffect();
                onTop.hillHeight = hillockVariance;
                onTop.hillWavelength = hillockWavelength;
                onTop.spikeHeight = hillockSpikeHeight;
                onTop.spikeWavelength = hillockSpikeWidth;
                onTop.hillOctave = 1;// same octave as variableRuggedness

                // plus raised a bit
                HeightEffect hillLevel = onTop.plus(new RaiseEffect(hillockBoost + terrainBase));

                // but only
                VariableRuggednessEffect hills = new VariableRuggednessEffect();
                hills.ruggedTerrain = hillLevel;
                hills.smoothTerrain = new RaiseEffect(terrainBase);
                hills.octave = 1;// just to make it clear;
                hills.startTransition = 0.1f;
                hills.transitionWidth = 0.35f;
                hills.wavelength = hillockWavelength;

                HeightEffect unJittered = hills.plus(new GroundEffect(6f));

                // and lets scramble it a bit

                biomeHeight = new JitterEffect();
                biomeHeight.amplitude = 2;
                biomeHeight.wavelength = 5;
                biomeHeight.jittered = unJittered;

            }

            @Override
            public float generateNoise(RTGWorld rtgWorld, int x, int y, float biomeWeight, float border, float river) {
                float result = biomeHeight.added(rtgWorld.simplex, rtgWorld.cell, x, y);
                if (result < 60) throw new RuntimeException();
                return result;
                //return terrainPlains(x, y, simplex, river, 160f, 10f, 60f, 80f, 65f);
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
    }

    @Override
    public void initConfig() {

    }
}
