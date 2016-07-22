package teamrtg.rtg.modules.bop.biomes;

import biomesoplenty.api.biome.BOPBiomes;
import net.minecraft.init.Biomes;
import teamrtg.rtg.api.tools.deco.DecoPond;
import teamrtg.rtg.api.tools.deco.helper.DecoHelperBorder;
import teamrtg.rtg.api.tools.surface.SurfaceBase;
import teamrtg.rtg.api.util.noise.SimplexOctave;
import teamrtg.rtg.api.world.RTGWorld;
import teamrtg.rtg.api.world.biome.TerrainBase;
import teamrtg.rtg.api.world.biome.deco.DecoBaseBiomeDecorations;
import teamrtg.rtg.api.world.biome.surface.part.SurfacePart;
import teamrtg.rtg.modules.bop.RTGBiomeBOP;

public class RTGBiomeBOPCrag extends RTGBiomeBOP {

    public RTGBiomeBOPCrag() {
        super(BOPBiomes.crag.get(), Biomes.RIVER);
        this.noLakes = true;
        this.noWaterFeatures = true;
    }

    @Override
    public TerrainBase initTerrain() {
        return new TerrainBase(90f) {

            private float pointHeightVariation = 20f;
            private float pointHeightWavelength = 400f;// deep variation
            private float pointHeight = 50;
            private float pointWavelength = 50;

            @Override
            public float generateNoise(RTGWorld rtgWorld, int x, int y, float biomeWeight, float border, float river) {

                // need a little jitter to the points
                SimplexOctave.Derivative jitter = new SimplexOctave.Derivative();
                rtgWorld.simplex.riverJitter().evaluateNoise((float)x / 20.0, (float)y / 20.0, jitter);
                double pX = x + jitter.deltax() * 1f;
                double pY = y + jitter.deltay() * 1f;

                // restrict the points to in the biome.
                double multiplier = (border-0.5)*10.0;
                if (multiplier <0) multiplier = 0;
                if (multiplier >1) multiplier = 1;
                double [] points = rtgWorld.cell.octave(1).eval((float)pX/pointWavelength, (float)pY/pointWavelength);
                float raise = (float)((points[1]-points[0])/points[1]);
                raise = raise * 3f;
                raise -= 0.2f;
                if (raise<0) raise = 0;
                if (raise>1) raise = 1;
                float topHeight = (float)(pointHeight +
                        pointHeightVariation*rtgWorld.simplex.noise((float)x/pointHeightWavelength, (float)y/pointHeightWavelength));

                float p = raise*topHeight;
                if (border >= 1f) return base+p;
                if (border > 0.65) {
                    // we need to adjust for the border adjustments to the height to make the base work
                    // it actaully doesn't always help
                    float missingBase = (1f-border) *(base-70f);  // shortfall at the top
                    float pStretch = (topHeight+missingBase)/topHeight;
                    p = p*pStretch;
                    p = borderAdjusted(p, border, 0.75f, 0.65f);
                    return base + p;
                }
                return base;
                //return terrainCanyon(x, y, simplex, river, height, border, strength, heightLength, booRiver);
            }
        };
    }

    @Override
    public SurfacePart initSurface() {
        return SurfaceBase.surfaceGenericCliffs(this);
    }

    @Override
    public void initDecos() {

        DecoPond decoPond = new DecoPond();
        decoPond.chunksPerPond = 3;// very high because most are blocked by topography
        DecoHelperBorder borderedPond = new DecoHelperBorder(decoPond,0.8f,0.7f);
        this.addDeco(borderedPond);

        DecoBaseBiomeDecorations decoBaseBiomeDecorations = new DecoBaseBiomeDecorations();
        this.addDeco(decoBaseBiomeDecorations);
    }

    @Override
    public void initConfig() {

        config.GENERATE_EMERALDS.setDefault(true);
    }
}
