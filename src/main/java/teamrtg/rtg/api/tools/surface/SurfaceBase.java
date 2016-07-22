package teamrtg.rtg.api.tools.surface;

import teamrtg.rtg.api.world.biome.RTGBiome;
import teamrtg.rtg.api.world.biome.surface.part.CliffSelector;
import teamrtg.rtg.api.world.biome.surface.part.SurfacePart;

public abstract class SurfaceBase {

    public SurfaceBase() {

    }

    public static final SurfacePart surfaceForest(RTGBiome biome) {
        SurfacePart surface = new SurfacePart();
        surface.add(new CliffSelector(1.5f)
                .add(biome.PARTS.selectTopAndFill()
                        .add(biome.PARTS.SHADOW_STONE)));
        surface.add(new CliffSelector((x, y, z, rtgWorld) -> 1.5f - ((y - 60f) / 65f) + rtgWorld.simplex.noise3(x / 8f, y / 8f, z / 8f) * 0.5f)
                .add(biome.PARTS.selectTop()
                        .add(biome.PARTS.STONE_OR_COBBLE)))
                .add(biome.PARTS.selectFill()
                        .add(biome.PARTS.STONE));
        surface.add(biome.PARTS.surfaceMix(biome.PARTS.MIX_NOISE));
        surface.add(biome.PARTS.surfaceGeneric());
        return surface;
    }
}