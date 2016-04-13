package teamrtg.rtg.world.biome.surface.part;

import net.minecraft.init.Blocks;
import teamrtg.rtg.api.biome.RealisticBiomeBase;
import teamrtg.rtg.util.noise.IFloatAt;

/**
 * @author topisani
 */
public class PresetParts {

    private RealisticBiomeBase biome;

    public PresetParts(RealisticBiomeBase biome) {
        this.biome = biome;
    }

    public final SurfacePart STONE = new BlockPart(Blocks.STONE.getDefaultState());
    public final SurfacePart COBBLE = new BlockPart(Blocks.COBBLESTONE.getDefaultState());
    public final SurfacePart STONE_OR_COBBLE = new SurfacePart();
    public final SurfacePart TOP_BLOCK = new BlockPart(biome.config.TOP_BLOCK.get());
    public final SurfacePart FILL_BLOCK = new BlockPart(biome.config.FILL_BLOCK.get());

    public final SurfacePart GENERIC_SURFACE = new SurfacePart();
    public final SurfacePart RIVER_OASIS = new SurfacePart() {
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

        {
            add(new RiverSelector(minRiver)
                .add(new GenericPart(Blocks.GRASS.getDefaultState(), Blocks.DIRT.getDefaultState())));
        }
    };

    {
        STONE_OR_COBBLE.add(new RandomSelector(biome.rand, 3)
            .add(COBBLE))
            .add(STONE);

        GENERIC_SURFACE.add(new DepthSelector(0, 0)
            .add(new HeightSelector(61, 255)
                .add(TOP_BLOCK)))
            .add(new DepthSelector(0, 4)
                .add(FILL_BLOCK)
            );
    }

    public final IFloatAt DEPTH_NOISE = (x, y, z) -> biome.simplex.noise2(x / 2f, z / 2f) * 3f;
    public final IFloatAt DEPTH_NOISE2 = (x, y, z) -> biome.simplex.noise2(x / 2.5f, z / 2.5f) * 3f;
}
