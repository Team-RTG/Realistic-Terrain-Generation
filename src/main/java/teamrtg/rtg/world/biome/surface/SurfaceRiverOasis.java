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
        IFloatAt noiseNeg = (x, y, z) -> {
            float a = (biome.simplex.octave(3).noise2(x / cutOffSize, z / cutOffSize) * cutOffAmplitude);
            return (a < 0f) ? 0f : a;
        };

        IFloatAt noise = (x, y, z) -> {
            float noiseValue = (biome.simplex.octave(0).noise2(x / 21f, z / 21f) * amplitude / 1f);
            noiseValue += (biome.simplex.octave(1).noise2(x / 12f, z / 12f) * amplitude / 2f);
            noiseValue += (biome.simplex.octave(2).noise2(x / 5f, z / 5f) * amplitude / 3f);
            return noiseValue - noiseNeg.getFloatAt(x, y, z);
        };

        IFloatAt minRiver = (x, y, z) -> {
            float p = (float) (0.67f - Math.pow(Math.abs(noise.getFloatAt(x, y, z)), 1.2f) * (noise.getFloatAt(x, y, z) > 0f ? 1f : -1f));
            return (p > 0.05f) ? p : 0.05f;
        };
        add(new RiverSelector(minRiver)
            .add(new GenericPart(Blocks.GRASS.getDefaultState(), Blocks.DIRT.getDefaultState())));
    }
}
