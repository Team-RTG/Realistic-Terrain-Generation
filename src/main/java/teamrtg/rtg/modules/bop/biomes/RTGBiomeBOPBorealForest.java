package teamrtg.rtg.modules.bop.biomes;

import biomesoplenty.api.biome.BOPBiomes;
import net.minecraft.init.Biomes;
import teamrtg.rtg.api.tools.surface.SurfaceBase;
import teamrtg.rtg.api.tools.terrain.BumpyHillsEffect;
import teamrtg.rtg.api.world.RTGWorld;
import teamrtg.rtg.api.world.biome.TerrainBase;
import teamrtg.rtg.api.world.biome.deco.DecoBaseBiomeDecorations;
import teamrtg.rtg.api.world.biome.surface.part.SurfacePart;
import teamrtg.rtg.modules.bop.RTGBiomeBOP;

public class RTGBiomeBOPBorealForest extends RTGBiomeBOP {

    public RTGBiomeBOPBorealForest() {

        super(BOPBiomes.boreal_forest.get(), Biomes.RIVER);
    }

    @Override
    public void initConfig() {

    }

    @Override
    public TerrainBase initTerrain() {

        return new TerrainBase() {

            private float baseHeight = 64f;
            private float hillStrength = 50f;
            private BumpyHillsEffect hillEffect;
            private float hillWidth = 60f;
            private float hillBumpyness = 10f;
            private float hillBumpynessWidth = 20f;

            {
                hillEffect = new BumpyHillsEffect();
                hillEffect.hillHeight = hillStrength;
                hillEffect.hillWavelength = hillWidth;
                hillEffect.spikeHeight = hillBumpyness;
                hillEffect.spikeWavelength = this.hillBumpynessWidth;
            }

            @Override
            public float generateNoise(RTGWorld rtgWorld, int x, int y, float biomeWeight, float border, float river) {

                groundNoise = groundNoise(x, y, groundNoiseAmplitudeHills, rtgWorld.simplex);
                float m = hillEffect.added(rtgWorld.simplex, rtgWorld.cell, x, y);
                return riverized(baseHeight, river) + (groundNoise + m) * river;
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
}
