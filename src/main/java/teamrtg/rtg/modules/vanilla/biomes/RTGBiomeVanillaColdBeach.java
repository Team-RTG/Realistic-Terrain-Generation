package teamrtg.rtg.modules.vanilla.biomes;

import net.minecraft.init.Biomes;
import net.minecraft.init.Blocks;
import teamrtg.rtg.api.tools.deco.DecoBoulder;
import teamrtg.rtg.api.world.RTGWorld;
import teamrtg.rtg.api.world.biome.TerrainBase;
import teamrtg.rtg.api.world.biome.surface.part.*;
import teamrtg.rtg.modules.vanilla.RTGBiomeVanilla;

public class RTGBiomeVanillaColdBeach extends RTGBiomeVanilla {

    public RTGBiomeVanillaColdBeach() {
        super(
                Biomes.COLD_BEACH,
            Biomes.RIVER

        );
    }

    @Override
    public TerrainBase initTerrain() {
        return new TerrainBase() {
            @Override
            public float generateNoise(RTGWorld rtgWorld, int x, int y, float biomeWeight, float border, float river) {
                return terrainBeach(x, y, rtgWorld.simplex, river, 180f, 35f, 63f);
            }
        };
    }

    @Override
    public SurfacePart initSurface() {
        SurfacePart surface = new SurfacePart();
        surface.add(PARTS.selectTopAndFill()
            .add(new CliffSelector(1.3f)
                .add(new BlockPart(config.CLIFF_BLOCK_1.get())))
            .add(PARTS.selectTop()
                .add(new HeightSelector(61, 255)
                    .add(new Selector((x, y, z, rtgWorld) -> rtgWorld.simplex.noise2(x / 12f, z / 12f) > -0.3f + ((y - 61f) / 15f))
                        .add(new BlockPart(config.TOP_BLOCK.get())))
                    .add(new BlockPart(Blocks.SAND.getDefaultState()))))
            .add(new DepthSelector(0, 4)
                .add(new Selector((x, y, z, rtgWorld) -> rtgWorld.simplex.noise2(x / 12f, z / 12f) > -0.3f + ((y - 61f) / 15f))
                    .add(new BlockPart(config.FILL_BLOCK.get())))
                .add(new HeightSelector(0, 69)
                    .add(new BlockPart(Blocks.SAND.getDefaultState()))))
            .add(new Selector((x, y, z, rtgWorld) -> rtgWorld.simplex.noise2(x / 12f, z / 12f) <= -0.3f + ((y - 61f) / 15f))
                .add(new BlockPart(Blocks.SANDSTONE.getDefaultState()))));
        surface.add(PARTS.surfaceGeneric());
        return surface;
    }

    @Override
    public void initDecos() {
        DecoBoulder decoBoulder = new DecoBoulder();
        decoBoulder.boulderBlock = Blocks.COBBLESTONE;
        decoBoulder.chance = 16;
        decoBoulder.maxY = 95;
        decoBoulder.strengthFactor = 3f;
        this.addDeco(decoBoulder);
    }

    @Override
    public void initConfig() {
        config.addBlock(config.CLIFF_BLOCK_1).setDefault(Blocks.SAND.getDefaultState());
        config.addBlock(config.CLIFF_BLOCK_2).setDefault(Blocks.SAND.getDefaultState());
    }
}
