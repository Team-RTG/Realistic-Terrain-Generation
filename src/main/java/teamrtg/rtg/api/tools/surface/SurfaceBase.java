package teamrtg.rtg.api.tools.surface;

import net.minecraft.block.BlockDirt;
import net.minecraft.init.Blocks;
import teamrtg.rtg.api.util.math.CanyonColour;
import teamrtg.rtg.api.util.noise.IFloatAt;
import teamrtg.rtg.api.world.biome.RTGBiome;
import teamrtg.rtg.api.world.biome.surface.part.*;

public abstract class SurfaceBase {

    public SurfaceBase() {

    }

    public static final SurfacePart surfaceBeach(RTGBiome biome) {
        SurfacePart surface = biome.PARTS.selectTopAndFill()
                .add(new CliffSelector(1.3f)
                        .add(new BlockPart(biome.getConfig().CLIFF_BLOCK_1.get())))
                .add(biome.PARTS.selectTop()
                        .add(new HeightSelector(61, 64)
                                .add(biome.PARTS.TOP_BLOCK)))
                .add(biome.PARTS.selectFill()
                        .add(new HeightSelector(61, 69)
                                .add(biome.PARTS.TOP_BLOCK)))
                .add(new HeightSelector(56, 68).setMaxNoise(biome.PARTS.DEPTH_NOISE)
                        .add(biome.PARTS.FILL_BLOCK))
                .add(biome.PARTS.surfaceGeneric());
        return surface;
    }

    public static final SurfacePart surfaceBirchForestHillsM(RTGBiome biome) {
        return new DepthSelector(0, 10)
                .add(new CliffSelector(1.4f)
                        .add(new DepthSelector(0, 1)
                                .add(biome.PARTS.rand(3)
                                        .add(biome.PARTS.COBBLE)))
                        .add(biome.PARTS.STONE))
                .add(biome.PARTS.surfaceGeneric());
    }

    public static final SurfacePart surfaceColdBeach(RTGBiome biome) {
        SurfacePart surface = new SurfacePart();
        surface.add(biome.PARTS.selectTopAndFill()
                .add(new CliffSelector(1.3f)
                        .add(new BlockPart(biome.getConfig().CLIFF_BLOCK_1.get())))
                .add(biome.PARTS.selectTop()
                        .add(new HeightSelector(61, 255)
                                .add(new Selector((x, y, z, rtgWorld) -> rtgWorld.simplex.noise2(x / 12f, z / 12f) > -0.3f + ((y - 61f) / 15f))
                                        .add(new BlockPart(biome.getConfig().TOP_BLOCK.get())))
                                .add(new BlockPart(Blocks.SAND.getDefaultState()))))
                .add(new DepthSelector(0, 4)
                        .add(new Selector((x, y, z, rtgWorld) -> rtgWorld.simplex.noise2(x / 12f, z / 12f) > -0.3f + ((y - 61f) / 15f))
                                .add(new BlockPart(biome.getConfig().FILL_BLOCK.get())))
                        .add(new HeightSelector(0, 69)
                                .add(new BlockPart(Blocks.SAND.getDefaultState()))))
                .add(new Selector((x, y, z, rtgWorld) -> rtgWorld.simplex.noise2(x / 12f, z / 12f) <= -0.3f + ((y - 61f) / 15f))
                        .add(new BlockPart(Blocks.SANDSTONE.getDefaultState()))));
        surface.add(biome.PARTS.surfaceGeneric());
        return surface;
    }

    public static final SurfacePart surfaceColdTaigaM(RTGBiome biome) {
        return new DepthSelector(0, 10)
                .add(new CliffSelector(1.4f)
                        .add(new DepthSelector(0, 1)
                                .add(biome.PARTS.STONE_OR_COBBLE))
                        .add(biome.PARTS.STONE))
                .add(biome.PARTS.surfaceGeneric());
    }

    public static final SurfacePart surfaceDesert(RTGBiome biome) {
        SurfacePart surface = new SurfacePart();
        surface.add(new SurfaceRiverOasis(biome));
        surface.add(biome.PARTS.selectTopAndFill()
                .add(new BlockPart(Blocks.SAND.getDefaultState())));
        surface.add(new HeightSelector(60, 255).setMaxNoise(biome.PARTS.DEPTH_NOISE2)
                .add(new BlockPart(Blocks.SANDSTONE.getDefaultState())));
        return surface;
    }

    public static final SurfacePart surfaceDesertHills(RTGBiome biome) {
        SurfacePart surface = biome.PARTS.selectTopAndFill();
        surface.add(new SurfaceRiverOasis(biome));
        surface.add(new OrSelector()
                .or(new CliffSelector((x, y, z, rtgWorld) -> 1.5f - ((y - 60f) / 65f) + rtgWorld.simplex.noise3(x / 8f, y / 8f, z / 8f) * 0.5f))
                .or(new CliffSelector(1.5f))
                .add(new BlockPart(Blocks.SANDSTONE.getDefaultState()))
        );
        surface.add(biome.PARTS.surfaceGeneric());
        return surface;
    }

    public static final SurfacePart surfaceDesertM(RTGBiome biome) {
        SurfacePart surface = biome.PARTS.selectTopAndFill();
        surface.add(new SurfaceRiverOasis(biome));
        surface.add(new CliffSelector(1.5f)
                .add(biome.PARTS.SHADOW_SAND)
        );
        surface.add(new CliffSelector((x, y, z, rtgWorld) -> 1.5f - ((y - 60f) / 65f) + rtgWorld.simplex.noise3(x / 8f, y / 8f, z / 8f) * 0.5f)
                .add(biome.PARTS.selectFill()
                        .add(biome.PARTS.rand(3)
                                .add(new BlockPart(Blocks.SANDSTONE.getDefaultState()))))
                .add(new BlockPart(Blocks.SAND.getDefaultState()))
        );
        surface.add(biome.PARTS.surfaceGeneric());
        return surface;
    }

    public static final SurfacePart surfaceExtremeHillsPlus(RTGBiome biome) {
        SurfacePart surface = biome.PARTS.selectTopAndFill();
        surface.add(new CliffSelector(1.5f)
                .add(biome.PARTS.selectTopAndFill()
                        .add(biome.PARTS.SHADOW_STONE)));
        surface.add(new CliffSelector((x, y, z, rtgWorld) -> 1.5f - ((y - 60f) / 65f) + rtgWorld.simplex.noise3(x / 8f, y / 8f, z / 8f) * 0.5f)
                .add(biome.PARTS.selectTop()
                        .add(biome.PARTS.STONE_OR_COBBLE)))
                .add(biome.PARTS.selectFill()
                        .add(biome.PARTS.STONE));
        surface.add(biome.PARTS.surfaceMix(biome.PARTS.MIX_NOISE_SMALL));
        surface.add(biome.PARTS.surfaceGeneric());
        return surface;
    }

    public static final SurfacePart surfaceExtremeHills(RTGBiome biome) {
        SurfacePart surface = biome.PARTS.selectTopAndFill();
        surface.add(new CliffSelector(1.4f)
                .add(new DepthSelector(0, 1)
                        .add(biome.PARTS.STONE_OR_COBBLE))
        );
        surface.add(biome.PARTS.surfaceMix((x, y, z, rtgWorld) -> rtgWorld.simplex.noise2(x / 60f, z / 60f) + rtgWorld.simplex.noise2(x / 14f, z / 14f) * 0.25f > -0.14f));
        surface.add(biome.PARTS.surfaceGeneric());
        return surface;
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

    public static final SurfacePart surfaceGeneric(RTGBiome biome) {
        return biome.PARTS.surfaceGeneric();
    }

    public static final SurfacePart surfaceGenericCliffs(RTGBiome biome) {
        SurfacePart surface = new SurfacePart();
        surface.add(new CliffSelector(1.5f)
                .add(biome.PARTS.STONE_OR_COBBLE));
        surface.add(biome.PARTS.surfaceGeneric());
        return surface;
    }

    public static final SurfacePart surfaceIceMix(RTGBiome biome) {
        SurfacePart surface = biome.PARTS.selectTopAndFill();
        surface.add(new CliffSelector(1.4f)
                .add(new DepthSelector(0, 1)
                        .add(biome.PARTS.rand(3)
                                .add(new BlockPart(biome.getConfig().CLIFF_BLOCK_2.get()))))
                .add(new BlockPart(biome.getConfig().CLIFF_BLOCK_1.get())));
        surface.add(biome.PARTS.surfaceMix((x, y, z, rtgWorld) -> rtgWorld.simplex.noise2(x / 60f, z / 60f) + rtgWorld.simplex.noise2(x / 14f, z / 14f) * 0.25f > -0.14f));
        surface.add(biome.PARTS.surfaceGeneric());
        return surface;
    }

    public static final SurfacePart surfaceJungle(RTGBiome biome) {
        SurfacePart surface = new SurfacePart();
        surface.add(new CliffSelector(1.4f)
                .add(new DepthSelector(0, 1)
                        .add(biome.PARTS.STONE_OR_COBBLE)))
                .add(biome.PARTS.STONE);
        surface.add(biome.PARTS.surfaceGeneric());
        return surface;
    }

    public static final SurfacePart surfaceJungleHills(RTGBiome biome) {
        SurfacePart surface = new SurfacePart();
        surface.add(new CliffSelector(1.5f)
                .add(biome.PARTS.selectTopAndFill()
                        .add(biome.PARTS.SHADOW_STONE)));
        surface.add(new CliffSelector((x, y, z, rtgWorld) -> 1.5f - ((y - 60f) / 65f) + rtgWorld.simplex.noise3(x / 8f, y / 8f, z / 8f) * 0.5f)
                .add(biome.PARTS.selectTop()
                        .add(biome.PARTS.STONE_OR_COBBLE)))
                .add(biome.PARTS.selectFill()
                        .add(biome.PARTS.STONE));
        surface.add(biome.PARTS.surfaceGeneric());
        return surface;
    }

    public static final SurfacePart surfaceMesa(RTGBiome biome, CanyonColour clayColour) {
        SurfacePart surface = new SurfacePart();
        surface.add(new SurfaceRiverOasis(biome));
        surface.add(
                new DepthSelector(0, 11)
                        .add(new OrSelector()
                                .or(new CliffSelector(1.3f))
                                .or(new DepthSelector(4, 255))
                                .add(new BlockPart(clayColour)))
                        .add(new HeightSelector(78, 255)
                                .add(biome.PARTS.rand(5)
                                        .add(new BlockPart(Blocks.DIRT.getDefaultState().withProperty(BlockDirt.VARIANT, BlockDirt.DirtType.COARSE_DIRT)))))
                        .add(new HeightSelector(0, 77)
                                .add(biome.PARTS.selectTop()
                                        .add(new HeightSelector(0, 71)
                                                .add(biome.PARTS.rand(5)
                                                        .add(new BlockPart(Blocks.DIRT.getDefaultState().withProperty(BlockDirt.VARIANT, BlockDirt.DirtType.COARSE_DIRT))))
                                        ))));
        surface.add(biome.PARTS.surfaceGeneric());
        surface.add(new HeightSelector(50, 255).setMinNoise(biome.PARTS.DEPTH_NOISE2)
                .add(new BlockPart(clayColour)));
        return surface;
    }

    public static final SurfacePart surfaceOcean(RTGBiome biome) {
        SurfacePart surface = new SurfacePart();
        surface.add(biome.PARTS.selectTop()
                .add(new HeightSelector(0, 63)
                        .add(new Selector((x, y, z, rtgWorld) -> rtgWorld.simplex.noise2(x / 20f, z / 20f) > 0.1f)
                                .add(new BlockPart(biome.getConfig().MIX_BLOCK_TOP.get())))));
        surface.add(biome.PARTS.surfaceGeneric());
        return surface;
    }

    public static final SurfacePart surfacePlateau1(RTGBiome biome, CanyonColour clayColour) {
        SurfacePart surface = new SurfacePart();
        surface.add(new SurfaceRiverOasis(biome));
        surface.add(new HeightSelector(50, 255).setMinNoise(biome.PARTS.DEPTH_NOISE)
                .add(new BlockPart(CanyonColour.MESA)));
        return surface;
    }

    public static final SurfacePart surfacePlateau2(RTGBiome biome, CanyonColour clayColour) {
        SurfacePart surface = new SurfacePart();
        surface.add(new SurfaceRiverOasis(biome));
        surface.add(new DepthSelector(0, 11)
                .add(new OrSelector()
                        .or(new CliffSelector(1.3f))
                        .or(new DepthSelector(4, 256))
                        .add(new BlockPart(clayColour)))
                .add(biome.PARTS.selectTop()
                        .add(biome.PARTS.rand(5)
                                .add(new BlockPart(Blocks.GRASS.getDefaultState())))
                        .add(biome.PARTS.rand(3)
                                .add(new BlockPart(Blocks.DIRT.getStateFromMeta(1)))))
        );
        surface.add(biome.PARTS.surfaceGeneric());
        surface.add(
                new HeightSelector(50, 256).setMinNoise(biome.PARTS.DEPTH_NOISE)
                        .add(new BlockPart(clayColour))
        );
        return surface;
    }

    public static final SurfacePart surfacePlateau3(RTGBiome biome, CanyonColour clayColour) {
        SurfacePart surface = new SurfacePart();
        surface.add(new SurfaceRiverOasis(biome));
        surface.add(new DepthSelector(0, 11)
                .add(new OrSelector()
                        .or(new CliffSelector(1.3f))
                        .or(new DepthSelector(4, 256))
                        .add(new BlockPart(clayColour)))
                .add(biome.PARTS.selectTop()
                        .add(biome.PARTS.rand(5)
                                .add(new BlockPart(Blocks.GRASS.getDefaultState())))
                        .add(biome.PARTS.rand(3)
                                .add(new BlockPart(Blocks.DIRT.getStateFromMeta(1)))))
        );
        surface.add(biome.PARTS.surfaceGeneric());
        surface.add(
                new HeightSelector(50, 256).setMinNoise(biome.PARTS.DEPTH_NOISE)
                        .add(new BlockPart(clayColour))
        );
        surface.add(biome.PARTS.surfaceGeneric());
        return surface;
    }

    public static final SurfacePart surfaceSavanna(RTGBiome biome) {
        SurfacePart surface = new SurfacePart();
        surface.add(new CliffSelector(1.5f)
                .add(biome.PARTS.selectTop()
                        .add(biome.PARTS.STONE_OR_COBBLE))
                .add(new DepthSelector(0, 10)
                        .add(biome.PARTS.STONE)));
        surface.add(biome.PARTS.surfaceMix(biome.PARTS.MIX_NOISE));
        surface.add(biome.PARTS.surfaceGeneric());
        return surface;
    }

    public static final SurfacePart surfaceStoneBeach(RTGBiome biome) {
        SurfacePart surface = new SurfacePart();
        surface.add(new CliffSelector(1.5f)
                .add(biome.PARTS.selectTopAndFill()
                        .add(biome.PARTS.SHADOW_STONE)));
        surface.add(new CliffSelector((x, y, z, rtgWorld) -> 1.5f - ((y - 60f) / 65f) + rtgWorld.simplex.noise3(x / 8f, y / 8f, z / 8f) * 0.5f)
                .add(biome.PARTS.selectTop()
                        .add(biome.PARTS.STONE_OR_COBBLE)))
                .add(biome.PARTS.selectFill()
                        .add(biome.PARTS.STONE));
        surface.add(biome.PARTS.selectTopAndFill()
                .add(new TopPosSelector(0, 63)
                        .add(new BlockPart(Blocks.GRAVEL.getDefaultState()))));
        surface.add(biome.PARTS.surfaceGeneric());
        return surface;
    }

    public static final SurfacePart surfaceSwamp(RTGBiome biome) {
        SurfacePart surface = new SurfacePart();
        surface.add(new CliffSelector(1.5f)
                .add(biome.PARTS.STONE_OR_COBBLE));
        surface.add(biome.PARTS.surfaceSwamp());
        return surface;
    }

    public static final SurfacePart surfaceTaiga(RTGBiome biome) {
        SurfacePart surface = new SurfacePart();

        IFloatAt cliffNoise = (x, y, z, rtgWorld) -> rtgWorld.simplex.noise3(x / 8f, y / 8f, z / 8f) * 0.5f;

        surface.add(biome.PARTS.selectTopAndFill()

                .add(new CliffSelector((x, y, z, rtgWorld) -> {
                    float n = 1.5f - ((y - 60f) / 65f) + cliffNoise.getAt(x, y, z, rtgWorld);
                    return (n > 0.2f) ? n : 0.2f;
                })
                        .add(biome.PARTS.selectTop()
                                .add(biome.PARTS.STONE_OR_COBBLE))
                        .add(biome.PARTS.STONE))

                .add(new CliffSelector(1.5f)
                        .add(biome.PARTS.SHADOW_STONE))

                .add(new CliffSelector((x, y, z, rtgWorld) -> 0.3f + ((y - 100f) / 50f) + cliffNoise.getAt(x, y, z, rtgWorld))
                        .add(new Selector((x, y, z, rtgWorld) -> y > 110 + (cliffNoise.getAt(x, y, z, rtgWorld) * 4))
                                .add(new BlockPart(Blocks.SNOW.getDefaultState()))))

                .add(biome.PARTS.selectTop()
                        .add(new Selector((x, y, z, rtgWorld) -> rtgWorld.simplex.noise2(x / 50f, z / 50f) + cliffNoise.getAt(x, y, z, rtgWorld) * 0.6f > 0.24f)
                                .add(new BlockPart(Blocks.DIRT.getStateFromMeta(2))))
                        .add(new BlockPart(Blocks.GRASS.getDefaultState())))
                .add(new TopPosSelector(0, 63)
                        .add(new BlockPart(Blocks.GRAVEL.getDefaultState())))
                .add(new BlockPart(Blocks.DIRT.getDefaultState()))
        );
        return surface;
    }
}