package teamrtg.rtg.modules.rtg.terrainfeature;

import teamrtg.rtg.api.module.Mods;
import teamrtg.rtg.api.util.noise.SimplexOctave;
import teamrtg.rtg.api.world.RTGWorld;
import teamrtg.rtg.api.world.biome.RTGBiome;
import teamrtg.rtg.api.world.biome.WorldFeature;

import static teamrtg.rtg.api.world.biome.RTGBiome.actualRiverProportion;

/**
 * @author topisani
 */
public class WaterFeatures extends WorldFeature {

    public WaterFeatures() {
        super(Mods.RTG, "waterFeatures");
    }

    @Override
    public float modifyTerrain(RTGWorld rtgWorld, RTGBiome biome, float heightIn, int x, int z, float border, float river) {
        // we now have both lakes and rivers lowering land
        if (biome.noWaterFeatures) {
            return heightIn;
        }
        float lakeStrength = lakePressure(rtgWorld, biome, x, z, border);
        float lakeFlattening = (float) lakeFlattening(lakeStrength, biome.lakeShoreLevel, biome.lakeDepressionLevel);
        // we add some flattening to the rivers. The lakes are pre-flattened.
        float riverFlattening = river * 1.25f - 0.25f;
        if (riverFlattening < 0) riverFlattening = 0;
        if ((river < 1) && (lakeFlattening < 1)) {
            riverFlattening = (float) ((1f - riverFlattening) / riverFlattening + (1f - lakeFlattening) / lakeFlattening);
            riverFlattening = (1f / (riverFlattening + 1f));
        } else {
            if (lakeFlattening < riverFlattening) riverFlattening = (float) lakeFlattening;
        }
        // the lakes have to have a little less flattening to avoid the rocky edges
        lakeFlattening = lakeFlattening(lakeStrength, biome.lakeWaterLevel, biome.lakeDepressionLevel);

        if ((river < 1) && (lakeFlattening < 1)) {
            river = (float) ((1f - river) / river + (1f - lakeFlattening) / lakeFlattening);
            river = (1f / (river + 1f));
        } else {
            if (lakeFlattening < river) river = (float) lakeFlattening;
        }
        // place water features
        return this.erodedNoise(rtgWorld, x, z, river, border, heightIn, lakeFlattening);
    }

    public float lakePressure(RTGWorld rtgWorld, RTGBiome biome, int x, int z, float border) {
        if (biome.noLakes) return 1f;
        SimplexOctave.Disk jitter = new SimplexOctave.Disk();
        rtgWorld.simplex.riverJitter().evaluateNoise((float) x / 240.0, (float) z / 240.0, jitter);
        double pX = x + jitter.deltax() * biome.largeBendSize;
        double pY = z + jitter.deltay() * biome.largeBendSize;
        rtgWorld.simplex.mountain().evaluateNoise((float) x / 80.0, (float) z / 80.0, jitter);
        pX += jitter.deltax() * biome.mediumBendSize;
        pY += jitter.deltay() * biome.mediumBendSize;
        rtgWorld.simplex.octave(4).evaluateNoise((float) x / 30.0, (float) z / 30.0, jitter);
        pX += jitter.deltax() * biome.smallBendSize;
        pY += jitter.deltay() * biome.smallBendSize;
        //double results =simplexCell.river().noise(pX / lakeInterval, pY / lakeInterval,1.0);
        double[] lakeResults = rtgWorld.cell.river().eval((float) pX / biome.lakeInterval, (float) pY / biome.lakeInterval);
        float results = 1f - (float) ((lakeResults[1] - lakeResults[0]) / lakeResults[1]);
        if (results > 1.01) throw new RuntimeException("" + lakeResults[0] + " , " + lakeResults[1]);
        if (results < -.01) throw new RuntimeException("" + lakeResults[0] + " , " + lakeResults[1]);
        //return simplexCell.river().noise((float)x/ lakeInterval, (float)z/ lakeInterval,1.0);
        return results;
    }

    public float lakeFlattening(float pressure, float bottomLevel, float topLevel) {
        // this number indicates a multiplier to height
        if (pressure > topLevel) return 1;
        if (pressure < bottomLevel) return 0;
        return (float) Math.pow((pressure - bottomLevel) / (topLevel - bottomLevel), 1.0);
    }

    public float erodedNoise(RTGWorld rtgWorld, int x, int z, float river, float border, float biomeHeight, double lakeFlattening) {

        float r = 1f;

        // put a flat spot in the middle of the river
        float riverFlattening = river; // moved the flattening to terrain stage
        if (riverFlattening < 0) riverFlattening = 0;

        r = riverFlattening / actualRiverProportion;
        if ((r < 1f && biomeHeight > 57f)) {
            return (biomeHeight * (r))
                + ((57f + rtgWorld.simplex.noise2(x / 12f, z / 12f) * 2f + rtgWorld.simplex.noise2(x / 8f, z / 8f) * 1.5f) * (1f - r));
        } else {
            return biomeHeight;
        }
    }
}
