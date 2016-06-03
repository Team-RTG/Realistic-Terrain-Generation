package teamrtg.rtg.api.tools.surface;

import net.minecraft.init.Blocks;
import teamrtg.rtg.api.util.noise.IFloatAt;
import teamrtg.rtg.api.world.biome.RTGBiome;
import teamrtg.rtg.api.world.biome.surface.part.GenericPart;
import teamrtg.rtg.api.world.biome.surface.part.RiverSelector;
import teamrtg.rtg.api.world.biome.surface.part.SurfacePart;

/**
 * @author topisani
 */
public class SurfaceRiverOasis extends SurfacePart {

    public SurfaceRiverOasis(RTGBiome biome) {

        float amplitude = 0.25f;

        // Cut-off noise size. The bigger, the larger the cut-off scale is
        float cutOffSize = 142f;
        // Cut-off noise amplitude. The bigger, the more effect cut-off is going to have in the grass
        float cutOffAmplitude = .75f;

        // Large scale cut-offs
        IFloatAt noiseNeg = (x, y, z, rtgWorld) -> {
            float a = (rtgWorld.simplex.octave(3).noise2(x / cutOffSize, z / cutOffSize) * cutOffAmplitude);
            return (a < 0f) ? 0f : a;
        };

        IFloatAt noise = (x, y, z, rtgWorld) -> {
            float noiseValue = (rtgWorld.simplex.octave(0).noise2(x / 21f, z / 21f) * amplitude / 1f);
            noiseValue += (rtgWorld.simplex.octave(1).noise2(x / 12f, z / 12f) * amplitude / 2f);
            noiseValue += (rtgWorld.simplex.octave(2).noise2(x / 5f, z / 5f) * amplitude / 3f);
            return noiseValue - noiseNeg.getAt(x, y, z, rtgWorld);
        };

        IFloatAt minRiver = (x, y, z, rtgWorld) -> {
            float p = (float) (0.67f - Math.pow(Math.abs(noise.getAt(x, y, z, rtgWorld)), 1.2f) * (noise.getAt(x, y, z, rtgWorld) > 0f ? 1f : -1f));
            return (p > 0.05f) ? p : 0.05f;
        };
        add(new RiverSelector(minRiver)
            .add(new GenericPart(Blocks.GRASS.getDefaultState(), Blocks.DIRT.getDefaultState())));
    }
}
