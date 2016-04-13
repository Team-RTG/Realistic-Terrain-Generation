package teamrtg.rtg.world.biome.surface.part;

import net.minecraft.init.Blocks;
import teamrtg.rtg.api.biome.RealisticBiomeBase;

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
}
