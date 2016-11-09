package rtg.world.biome.realistic.biomesoplenty;

import net.minecraft.init.Biomes;
import net.minecraft.world.biome.Biome;

import biomesoplenty.api.biome.BOPBiomes;

import rtg.api.biome.BiomeConfig;
import rtg.util.CellNoise;
import rtg.util.OpenSimplexNoise;
import rtg.util.SimplexOctave;
import rtg.world.biome.deco.DecoBaseBiomeDecorations;
import rtg.world.biome.deco.DecoPond;
import rtg.world.biome.deco.helper.DecoHelperBorder;
import rtg.world.gen.surface.biomesoplenty.SurfaceBOPCrag;
import rtg.world.gen.terrain.TerrainBase;

public class RealisticBiomeBOPCrag extends RealisticBiomeBOPBase {

    public static Biome biome = BOPBiomes.crag.get();
    public static Biome river = Biomes.RIVER;

    public RealisticBiomeBOPCrag(BiomeConfig config) {

        super(config, biome, river,
            new rtg.world.gen.terrain.biomesoplenty.TerrainBOPCrag(90f),
            new SurfaceBOPCrag(config, biome.topBlock, biome.fillerBlock, biome.topBlock)
        );

        this.generatesEmeralds = true;
        this.noLakes = true;
        this.noWaterFeatures = true;

        DecoPond decoPond = new DecoPond();
        decoPond.chunksPerPond = 3;// very high because most are blocked by topography
        DecoHelperBorder borderedPond = new DecoHelperBorder(decoPond, 0.8f, 0.7f);
        this.addDeco(borderedPond);

        DecoBaseBiomeDecorations decoBaseBiomeDecorations = new DecoBaseBiomeDecorations();
        this.addDeco(decoBaseBiomeDecorations);
    }

    @Override
    public TerrainBase initTerrain() {

        return new TerrainBOPCrag(90f);
    }

    public class TerrainBOPCrag extends TerrainBase {

        private float pointHeightVariation = 20f;
        private float pointHeightWavelength = 400f;// deep variation
        private float pointHeight = 50;
        private float pointWavelength = 50;

        public TerrainBOPCrag(float baseHeight) {

            base = baseHeight;
        }

        @Override
        public float generateNoise(OpenSimplexNoise simplex, CellNoise cell, int x, int y, float border, float river) {

            // need a little jitter to the points
            SimplexOctave.Derivative jitter = new SimplexOctave.Derivative();
            simplex.riverJitter().evaluateNoise((float) x / 20.0, (float) y / 20.0, jitter);
            double pX = x + jitter.deltax() * 1f;
            double pY = y + jitter.deltay() * 1f;

            // restrict the points to in the biome.
            double multiplier = (border - 0.5) * 10.0;
            if (multiplier < 0) {
                multiplier = 0;
            }
            if (multiplier > 1) {
                multiplier = 1;
            }
            double[] points = cell.octave(1).eval((float) pX / pointWavelength, (float) pY / pointWavelength);
            float raise = (float) ((points[1] - points[0]) / points[1]);
            raise = raise * 3f;
            raise -= 0.2f;
            if (raise < 0) {
                raise = 0;
            }
            if (raise > 1) {
                raise = 1;
            }
            float topHeight = (float) (pointHeight +
                pointHeightVariation * simplex.noise((float) x / pointHeightWavelength, (float) y / pointHeightWavelength));

            float p = raise * topHeight;
            if (border >= 1f) {
                return base + p;
            }
            if (border > 0.65) {
                // we need to adjust for the border adjustments to the height to make the base work
                // it actaully doesn't always help
                float missingBase = (1f - border) * (base - 70f);  // shortfall at the top
                float pStretch = (topHeight + missingBase) / topHeight;
                p = p * pStretch;
                p = borderAdjusted(p, border, 0.75f, 0.65f);
                return base + p;
            }
            return base;
            //return terrainCanyon(x, y, simplex, river, height, border, strength, heightLength, booRiver);
        }
    }
}
