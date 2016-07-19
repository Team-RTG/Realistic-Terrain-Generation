package teamrtg.rtg.modules.bop.biomes;

import net.minecraft.init.Biomes;
import net.minecraft.world.biome.Biome;
import teamrtg.rtg.api.util.BiomeUtils;
import teamrtg.rtg.api.util.noise.SimplexOctave;
import teamrtg.rtg.api.world.RTGWorld;
import teamrtg.rtg.api.world.biome.TerrainBase;
import teamrtg.rtg.api.world.biome.deco.DecoBaseBiomeDecorations;
import teamrtg.rtg.api.world.biome.surface.part.CliffSelector;
import teamrtg.rtg.api.world.biome.surface.part.SurfacePart;
import teamrtg.rtg.modules.vanilla.RTGBiomeVanilla;

public class RTGBiomeBOPCrag extends RTGBiomeVanilla {
    public static Biome standardBiome = Biomes.PLAINS;
    public static Biome mutationBiome = Biome.getBiome(BiomeUtils.getId(standardBiome) + MUTATION_ADDEND);

    public RTGBiomeBOPCrag() {
        super(
                mutationBiome,
            Biomes.RIVER
        );
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
        SurfacePart surface = new SurfacePart();
        surface.add(new CliffSelector(1.5f)
            .add(PARTS.STONE_OR_COBBLE));
        surface.add(PARTS.surfaceGeneric());
        return surface;
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
