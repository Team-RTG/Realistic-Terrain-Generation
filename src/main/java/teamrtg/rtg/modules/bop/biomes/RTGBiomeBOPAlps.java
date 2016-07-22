package teamrtg.rtg.modules.bop.biomes;

import biomesoplenty.api.biome.BOPBiomes;
import net.minecraft.init.Biomes;
import net.minecraft.init.Blocks;
import teamrtg.rtg.api.util.noise.IFloatAt;
import teamrtg.rtg.api.world.RTGWorld;
import teamrtg.rtg.api.world.biome.TerrainBase;
import teamrtg.rtg.api.world.biome.deco.DecoBaseBiomeDecorations;
import teamrtg.rtg.api.world.biome.surface.part.*;
import teamrtg.rtg.modules.bop.RTGBiomeBOP;

public class RTGBiomeBOPAlps extends RTGBiomeBOP {

    public RTGBiomeBOPAlps() {
        super(BOPBiomes.alps.get(), Biomes.FROZEN_RIVER);
        this.noLakes = true;
    }

    @Override
    public TerrainBase initTerrain() {
        return new TerrainBase(120f) {

            // the BoP version has steep slopes and a flat area on top. The RTG version will
            private float start = 0f;// this puts a minimum on "ruggedness" on the top. We want to allow flats
            private float height = 40f; // sets the variability range
            private float width = 80f; // width of irregularity noise on top. We want low, for a lot of features.

            @Override
            public float generateNoise(RTGWorld rtgWorld, int x, int y, float biomeWeight, float border, float river) {
                return terrainHighland(x, y, rtgWorld.simplex, rtgWorld.cell, river, start, width, height, base - 62f);
            }
        };
    }

    @Override
    public SurfacePart initSurface() {
        SurfacePart surface = new SurfacePart();

        IFloatAt cliffNoise = (x, y, z, rtgWorld) -> rtgWorld.simplex.noise3(x / 8f, y / 8f, z / 8f) * 0.5f;

        surface.add(PARTS.selectTopAndFill()

                .add(new CliffSelector((x, y, z, rtgWorld) -> {
                    float n = 1.5f - ((y - 60f) / 65f) + cliffNoise.getAt(x, y, z, rtgWorld);
                    return (n > 0.2f) ? n : 0.2f;
                })
                        .add(PARTS.selectTop()
                                .add(PARTS.STONE_OR_COBBLE))
                        .add(PARTS.STONE))

                .add(new CliffSelector(1.5f)
                        .add(this.PARTS.SHADOW_STONE))

                .add(new CliffSelector((x, y, z, rtgWorld) -> 0.3f + ((y - 100f) / 50f) + cliffNoise.getAt(x, y, z, rtgWorld))
                        .add(new Selector((x, y, z, rtgWorld) -> y > 110 + (cliffNoise.getAt(x, y, z, rtgWorld) * 4))
                                .add(new BlockPart(Blocks.SNOW.getDefaultState()))))

                .add(PARTS.selectTop()
                        .add(new Selector((x, y, z, rtgWorld) -> rtgWorld.simplex.noise2(x / 50f, z / 50f) + cliffNoise.getAt(x, y, z, rtgWorld) * 0.6f > 0.24f)
                                .add(new BlockPart(Blocks.DIRT.getStateFromMeta(2))))
                        .add(new BlockPart(Blocks.GRASS.getDefaultState())))
                .add(new TopPosSelector(0, 63)
                        .add(new BlockPart(Blocks.GRAVEL.getDefaultState())))
                .add(new BlockPart(Blocks.DIRT.getDefaultState()))
        );
        return surface;
    }

    @Override
    public void initDecos() {
        DecoBaseBiomeDecorations decoBaseBiomeDecorations = new DecoBaseBiomeDecorations();
        this.addDeco(decoBaseBiomeDecorations);
    }

    @Override
    public void initConfig() {
        config.GENERATE_EMERALDS.setDefault(true);
    }
}
