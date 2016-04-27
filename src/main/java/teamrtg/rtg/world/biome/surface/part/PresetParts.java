package teamrtg.rtg.world.biome.surface.part;

import net.minecraft.init.Blocks;
import teamrtg.rtg.api.biome.RealisticBiomeBase;
import teamrtg.rtg.api.mods.Mods;
import teamrtg.rtg.util.noise.IFloatAt;

/**
 * @author topisani
 */
public class PresetParts {

    public final RealisticBiomeBase biome;

    public final IFloatAt DEPTH_NOISE;
    public final IFloatAt DEPTH_NOISE2;

    public final IFloatAt MIX_NOISE;

    public final SurfacePart STONE;
    public final SurfacePart COBBLE;
    public final SurfacePart STONE_OR_COBBLE;
    public final SurfacePart SHADOW_STONE;
    public final SurfacePart SHADOW_SAND;
    public final SurfacePart TOP_BLOCK;
    public final SurfacePart FILL_BLOCK;

    public final SurfacePart TOP_SELECTOR;
    public final SurfacePart FILL_SELECTOR;
    public final SurfacePart TOP_AND_FILL_SELECTOR;
    public final SurfacePart MIX_SELECTOR;

    public final SurfacePart GENERIC_SURFACE;
    public final SurfacePart MIX_SURFACE;


    public PresetParts(RealisticBiomeBase biome) {
        this.biome = biome;
        DEPTH_NOISE = (x, y, z) -> biome.simplex.noise2(x / 2f, z / 2f) * 2f;
        DEPTH_NOISE2 = (x, y, z) -> biome.simplex.noise2(x / 2.5f, z / 2.5f) * 3f;
        MIX_NOISE = (x, y, z) -> biome.simplex.noise2(x / 12f, z / 12f) + biome.simplex.noise2(x / 4f, z / 4f) * 0.1f;
        STONE = new BlockPart(Blocks.STONE.getDefaultState());
        COBBLE = new BlockPart(Blocks.COBBLESTONE.getDefaultState());
        STONE_OR_COBBLE = new SurfacePart().add(STONE).add(new RandomSelector(biome.rand, 3).add(COBBLE));
        SHADOW_STONE = new BlockPart(Mods.RTG.config.SHADOW_STONE_BLOCK.get());
        SHADOW_SAND = new BlockPart(Mods.RTG.config.SHADOW_DESERT_BLOCK.get());
        TOP_BLOCK = new BlockPart(biome.config.TOP_BLOCK.get());
        FILL_BLOCK = new BlockPart(biome.config.FILL_BLOCK.get());

        TOP_SELECTOR = new DepthSelector(0, 0);
        FILL_SELECTOR = new DepthSelector(1, 5).setMaxNoise(DEPTH_NOISE);
        TOP_AND_FILL_SELECTOR = new DepthSelector(0, 5).setMaxNoise(DEPTH_NOISE);
        MIX_SELECTOR = new Selector((x, y, z) -> MIX_NOISE.getFloatAt(x, y, z) > 0.15);

        GENERIC_SURFACE = new SurfacePart();
        GENERIC_SURFACE.add(TOP_SELECTOR
            .add(TOP_BLOCK));
        GENERIC_SURFACE.add(FILL_SELECTOR
            .add(FILL_BLOCK));
        MIX_SURFACE = MIX_SELECTOR.add(TOP_SELECTOR.add(new BlockPart(biome.config.MIX_BLOCK_TOP.get()))).add(FILL_SELECTOR.add(new BlockPart(biome.config.MIX_BLOCK_FILL.get())));
    }
}
