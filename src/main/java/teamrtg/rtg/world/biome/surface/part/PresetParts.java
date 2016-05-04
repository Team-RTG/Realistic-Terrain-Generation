package teamrtg.rtg.world.biome.surface.part;

import net.minecraft.init.Blocks;
import teamrtg.rtg.api.biome.RealisticBiomeBase;
import teamrtg.rtg.api.mods.Mods;
import teamrtg.rtg.util.IBlockAt;
import teamrtg.rtg.util.noise.IBoolAt;
import teamrtg.rtg.util.noise.IFloatAt;

/**
 * @author topisani
 */
public class PresetParts {

    public final RealisticBiomeBase biome;

    public final IFloatAt DEPTH_NOISE;
    public final IFloatAt DEPTH_NOISE2;

    public final IBoolAt MIX_NOISE;
    public final IBoolAt MIX_NOISE_SMALL;

    public final SurfacePart STONE;
    public final SurfacePart COBBLE;
    public final SurfacePart STONE_OR_COBBLE;
    public final SurfacePart SHADOW_STONE;
    public final SurfacePart SHADOW_SAND;
    public final SurfacePart TOP_BLOCK;
    public final SurfacePart FILL_BLOCK;


    public PresetParts(RealisticBiomeBase biome) {
        this.biome = biome;
        DEPTH_NOISE = (x, y, z) -> biome.simplex.noise2(x / 2f, z / 2f) * 2f;
        DEPTH_NOISE2 = (x, y, z) -> biome.simplex.noise2(x / 2.5f, z / 2.5f) * 3f;
        MIX_NOISE = jitter((IBoolAt) (x, y, z) -> biome.simplex.noise2(x / 12f, z / 12f) + biome.simplex.noise2(x / 4f, z / 4f) * 0.1f > 0.15f);
        MIX_NOISE_SMALL = jitter((IBoolAt) (x, y, z) -> biome.simplex.noise2(x / 12f, z / 12f) + biome.simplex.noise2(x / 4f, z / 4f) * 0.1f > 0.08f);
        STONE = new BlockPart(Blocks.STONE.getDefaultState());
        COBBLE = new BlockPart(Blocks.COBBLESTONE.getDefaultState());
        STONE_OR_COBBLE = new SurfacePart().add(STONE).add(new RandomSelector(biome.rand, 3).add(COBBLE));
        SHADOW_STONE = new BlockPart(Mods.RTG.config.SHADOW_STONE_BLOCK.get());
        SHADOW_SAND = new BlockPart(Mods.RTG.config.SHADOW_DESERT_BLOCK.get());
        TOP_BLOCK = new BlockPart(biome.config.TOP_BLOCK.get());
        FILL_BLOCK = new BlockPart(biome.config.FILL_BLOCK.get());
    }

    public final SurfacePart rand(int r) {
        return new RandomSelector(biome.rand, r);
    }

    public final SurfacePart selectTop() {
        return new DepthSelector(0, 0);
    }

    public final SurfacePart selectFill() {
        return new DepthSelector(1, biome.config.FILL_LAYERS.get()).setMaxNoise(DEPTH_NOISE);
    }

    public final SurfacePart selectTopAndFill() {
        return new DepthSelector(0, biome.config.FILL_LAYERS.get()).setMaxNoise(DEPTH_NOISE);
    }

    public final SurfacePart surfaceGeneric() {
        return new SurfacePart() {{
            add(selectTop()
                .add(TOP_BLOCK));
            add(selectFill()
                .add(FILL_BLOCK));
        }};
    }

    public final SurfacePart surfaceMix(IBoolAt mixNoise) {
        return new Selector(mixNoise)
            .add(selectTop()
                .add(new BlockPart(biome.config.MIX_BLOCK_TOP.get())))
            .add(selectFill()
                .add(new BlockPart(biome.config.MIX_BLOCK_FILL.get())));
    }

    public final IFloatAt jitter(IFloatAt in) {
        return (x, y, z) -> {
            biome.simplex.evaluateNoise(x, z, biome.chunkProvider.surfaceJitter);
            int jX = (int) Math.round(x + biome.chunkProvider.surfaceJitter.deltax() * Mods.RTG.config.SURFACE_BLEED_RADIUS.get());
            int jZ = (int) Math.round(z + biome.chunkProvider.surfaceJitter.deltay() * Mods.RTG.config.SURFACE_BLEED_RADIUS.get());
            return in.getAt(jX, y, jZ);
        };
    }

    public final IBoolAt jitter(IBoolAt in) {
        return (x, y, z) -> {
            biome.simplex.evaluateNoise(x, z, biome.chunkProvider.surfaceJitter);
            int jX = (int) Math.round(x + biome.chunkProvider.surfaceJitter.deltax() * Mods.RTG.config.SURFACE_BLEED_RADIUS.get());
            int jZ = (int) Math.round(z + biome.chunkProvider.surfaceJitter.deltay() * Mods.RTG.config.SURFACE_BLEED_RADIUS.get());
            return in.getAt(jX, y, jZ);
        };
    }

    public final IBlockAt jitter(IBlockAt in) {
        return (x, y, z) -> {
            biome.simplex.evaluateNoise(x, z, biome.chunkProvider.surfaceJitter);
            int jX = (int) Math.round(x + biome.chunkProvider.surfaceJitter.deltax() * Mods.RTG.config.SURFACE_BLEED_RADIUS.get());
            int jZ = (int) Math.round(z + biome.chunkProvider.surfaceJitter.deltay() * Mods.RTG.config.SURFACE_BLEED_RADIUS.get());
            return in.getAt(jX, y, jZ);
        };
    }
}
