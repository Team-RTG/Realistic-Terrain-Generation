package teamrtg.rtg.modules.vanilla.biomes;

import net.minecraft.init.Biomes;
import net.minecraft.init.Blocks;
import teamrtg.rtg.api.tools.deco.DecoTree;
import teamrtg.rtg.api.tools.deco.DecoTree.TreeType;
import teamrtg.rtg.api.world.RTGWorld;
import teamrtg.rtg.api.world.biome.TerrainBase;
import teamrtg.rtg.api.world.biome.surface.part.BlockPart;
import teamrtg.rtg.api.world.biome.surface.part.CliffSelector;
import teamrtg.rtg.api.world.biome.surface.part.HeightSelector;
import teamrtg.rtg.api.world.biome.surface.part.SurfacePart;
import teamrtg.rtg.modules.vanilla.RTGBiomeVanilla;

public class RTGBiomeVanillaBeach extends RTGBiomeVanilla {

    public RTGBiomeVanillaBeach() {
        super(
            Biomes.BEACH,
            Biomes.RIVER
        );
    }

    @Override
    public void initConfig() {
        config.addBlock(config.CLIFF_BLOCK_1).setDefault(Blocks.SAND.getDefaultState());
        config.FILL_BLOCK.setDefault(Blocks.SANDSTONE.getDefaultState());
    }

    @Override
    public void initDecos() {
        // Scattered palm trees.
        DecoTree palmTrees = new DecoTree();
        palmTrees.loops = 1;
        palmTrees.treeType = TreeType.VANILLA_BEACH_PALM;
        palmTrees.maxY = 80;
        this.addDeco(palmTrees);
    }

    @Override
    public SurfacePart initSurface() {
        return PARTS.selectTopAndFill()
            .add(new CliffSelector(1.3f)
                .add(new BlockPart(config.CLIFF_BLOCK_1.get())))
            .add(PARTS.selectTop()
                .add(new HeightSelector(61, 64)
                    .add(PARTS.TOP_BLOCK)))
            .add(PARTS.selectFill()
                .add(new HeightSelector(61, 69)
                    .add(PARTS.TOP_BLOCK)))
            .add(new HeightSelector(56, 68).setMaxNoise(PARTS.DEPTH_NOISE)
                .add(PARTS.FILL_BLOCK))
            .add(PARTS.surfaceGeneric());
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
}
