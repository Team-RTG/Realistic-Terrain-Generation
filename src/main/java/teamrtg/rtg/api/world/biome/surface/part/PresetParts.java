package teamrtg.rtg.api.world.biome.surface.part;

import net.minecraft.init.Blocks;
import teamrtg.rtg.api.module.Mods;
import teamrtg.rtg.api.util.noise.IBlockAt;
import teamrtg.rtg.api.util.noise.IBoolAt;
import teamrtg.rtg.api.util.noise.IFloatAt;
import teamrtg.rtg.api.world.biome.IWorldFeature;

/**
 * @author topisani
 */
public class PresetParts {

    public final IWorldFeature biome;

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


    public PresetParts(IWorldFeature biome) {
        this.biome = biome;
        DEPTH_NOISE = (x, y, z, rtgWorld) -> rtgWorld.simplex.noise2(x / 2f, z / 2f) * 2f;
        DEPTH_NOISE2 = (x, y, z, rtgWorld) -> rtgWorld.simplex.noise2(x / 2.5f, z / 2.5f) * 3f;
        MIX_NOISE = jitter((IBoolAt) (x, y, z, rtgWorld) -> rtgWorld.simplex.noise2(x / 12f, z / 12f) + rtgWorld.simplex.noise2(x / 4f, z / 4f) * 0.1f > 0.15f);
        MIX_NOISE_SMALL = jitter((IBoolAt) (x, y, z, rtgWorld) -> rtgWorld.simplex.noise2(x / 12f, z / 12f) + rtgWorld.simplex.noise2(x / 4f, z / 4f) * 0.1f > 0.08f);
        STONE = new BlockPart(Blocks.STONE.getDefaultState());
        COBBLE = new BlockPart(Blocks.COBBLESTONE.getDefaultState());
        STONE_OR_COBBLE = new SurfacePart().add(STONE).add(new RandomSelector(3).add(COBBLE));
        SHADOW_STONE = new BlockPart(Mods.RTG.config.SHADOW_STONE_BLOCK.get());
        SHADOW_SAND = new BlockPart(Mods.RTG.config.SHADOW_DESERT_BLOCK.get());
        TOP_BLOCK = new BlockPart(biome.getConfig().TOP_BLOCK.get());
        FILL_BLOCK = new BlockPart(biome.getConfig().FILL_BLOCK.get());
    }

    public final IBoolAt jitter(IBoolAt in) {
        return (x, y, z, rtgWorld) -> {
            rtgWorld.simplex.evaluateNoise(x, z, rtgWorld.surfaceJitter);
            int jX = (int) Math.round(x + rtgWorld.surfaceJitter.deltax() * Mods.RTG.config.SURFACE_BLEED_RADIUS.get());
            int jZ = (int) Math.round(z + rtgWorld.surfaceJitter.deltay() * Mods.RTG.config.SURFACE_BLEED_RADIUS.get());
            return in.getAt(jX, y, jZ, rtgWorld);
        };
    }

    public final SurfacePart rand(int r) {
        return new RandomSelector(r);
    }

    public final SurfacePart selectTopAndFill() {
        return new DepthSelector(0, biome.getConfig().FILL_LAYERS.get()).setMaxNoise(DEPTH_NOISE);
    }

    public final SurfacePart surfaceGeneric() {
        return new SurfacePart() {{
            add(selectTop()
                .add(new HeightSelector(63, 255)
                    .add(TOP_BLOCK)));
            add(selectFill()
                .add(FILL_BLOCK));
        }};
    }

    public final SurfacePart selectTop() {
        return new DepthSelector(0, 0);
    }

    public final SurfacePart selectFill() {
        return new DepthSelector(1, biome.getConfig().FILL_LAYERS.get()).setMaxNoise(DEPTH_NOISE);
    }

    public final SurfacePart surfaceMix(IBoolAt mixNoise) {
        SurfacePart surf = new Selector(mixNoise);
        if (biome.getConfig().MIX_BLOCK_TOP.getDefault() != null) {
            surf.add(selectTop()
                .add(new BlockPart(biome.getConfig().MIX_BLOCK_TOP.get())));
        }
        if (biome.getConfig().MIX_BLOCK_FILL.getDefault() != null) {
            surf.add(selectFill()
                .add(new BlockPart(biome.getConfig().MIX_BLOCK_FILL.get())));
        }
        return surf;
    }

    public final IFloatAt jitter(IFloatAt in) {
        return (x, y, z, rtgWorld) -> {
            rtgWorld.simplex.evaluateNoise(x, z, rtgWorld.surfaceJitter);
            int jX = (int) Math.round(x + rtgWorld.surfaceJitter.deltax() * Mods.RTG.config.SURFACE_BLEED_RADIUS.get());
            int jZ = (int) Math.round(z + rtgWorld.surfaceJitter.deltay() * Mods.RTG.config.SURFACE_BLEED_RADIUS.get());
            return in.getAt(jX, y, jZ, rtgWorld);
        };
    }

    public final IBlockAt jitter(IBlockAt in) {
        return (x, y, z, rtgWorld) -> {
            rtgWorld.simplex.evaluateNoise(x, z, rtgWorld.surfaceJitter);
            int jX = (int) Math.round(x + rtgWorld.surfaceJitter.deltax() * Mods.RTG.config.SURFACE_BLEED_RADIUS.get());
            int jZ = (int) Math.round(z + rtgWorld.surfaceJitter.deltay() * Mods.RTG.config.SURFACE_BLEED_RADIUS.get());
            return in.getAt(jX, y, jZ, rtgWorld);
        };
    }
}
