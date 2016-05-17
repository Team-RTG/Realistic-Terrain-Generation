package teamrtg.rtg.world.biome.surface;

import net.minecraft.init.Blocks;
import teamrtg.rtg.api.biome.RealisticBiomeBase;
import teamrtg.rtg.util.noise.IFloatAt;
import teamrtg.rtg.world.biome.surface.part.GenericPart;
import teamrtg.rtg.world.biome.surface.part.RiverSelector;
import teamrtg.rtg.world.biome.surface.part.SurfacePart;

/**
 * @author topisani
 */
public class SurfaceRiverOasis extends SurfacePart {

    public SurfaceRiverOasis(RealisticBiomeBase biome) {

        float amplitude = 0.25f;

        // Cut-off noise size. The bigger, the larger the cut-off scale is
        float cutOffSize = 142f;
        // Cut-off noise amplitude. The bigger, the more effect cut-off is going to have in the grass
        float cutOffAmplitude = .75f;

        // Large scale cut-offs
        IFloatAt noiseNeg = (x, y, z, provider) -> {
            float a = (provider.simplex.octave(3).noise2(x / cutOffSize, z / cutOffSize) * cutOffAmplitude);
            return (a < 0f) ? 0f : a;
        };

        IFloatAt noise = (x, y, z, provider) -> {
            float noiseValue = (provider.simplex.octave(0).noise2(x / 21f, z / 21f) * amplitude / 1f);
            noiseValue += (provider.simplex.octave(1).noise2(x / 12f, z / 12f) * amplitude / 2f);
            noiseValue += (provider.simplex.octave(2).noise2(x / 5f, z / 5f) * amplitude / 3f);
            return noiseValue - noiseNeg.getAt(x, y, z, provider);
        };

        IFloatAt minRiver = (x, y, z, provider) -> {
            float p = (float) (0.67f - Math.pow(Math.abs(noise.getAt(x, y, z, provider)), 1.2f) * (noise.getAt(x, y, z, provider) > 0f ? 1f : -1f));
            return (p > 0.05f) ? p : 0.05f;
        };
        add(new RiverSelector(minRiver)
            .add(new GenericPart(Blocks.GRASS.getDefaultState(), Blocks.DIRT.getDefaultState())));
    }
}
