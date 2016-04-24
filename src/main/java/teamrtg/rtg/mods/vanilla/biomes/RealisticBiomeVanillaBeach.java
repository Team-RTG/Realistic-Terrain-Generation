package teamrtg.rtg.mods.vanilla.biomes;

import net.minecraft.init.Biomes;
import net.minecraft.init.Blocks;
import teamrtg.rtg.util.noise.CellNoise;
import teamrtg.rtg.util.noise.OpenSimplexNoise;
import teamrtg.rtg.world.biome.surface.part.*;
import teamrtg.rtg.world.biome.terrain.TerrainBase;
import teamrtg.rtg.world.gen.ChunkProviderRTG;
import teamrtg.rtg.world.gen.deco.DecoTree;
import teamrtg.rtg.world.gen.deco.DecoTree.TreeType;

public class RealisticBiomeVanillaBeach extends RealisticBiomeVanillaBase {

    public RealisticBiomeVanillaBeach(ChunkProviderRTG chunkProvider) {
        super(
            Biomes.BEACH,
            Biomes.RIVER,
            chunkProvider
        );
    }

    @Override
    protected void initProperties() {
        config.addBlock(config.CLIFF_BLOCK_1).setDefault(Blocks.SAND.getDefaultState());
        config.FILL_BLOCK.setDefault(Blocks.SANDSTONE.getDefaultState());
    }

    @Override
    protected void initDecos() {
        // Scattered palm trees.
        DecoTree palmTrees = new DecoTree();
        palmTrees.loops = 1;
        palmTrees.treeType = TreeType.VANILLA_BEACH_PALM;
        palmTrees.maxY = 80;
        this.addDeco(palmTrees);
    }

    @Override
    protected SurfacePart initSurface() {
        return PARTS.TOP_AND_FILL_SELECTOR
            .add(new CliffSelector(1.3f)
                .add(new BlockPart(config.CLIFF_BLOCK_1.get())))
            .add(PARTS.TOP_SELECTOR
                .add(new HeightSelector(61, 64)
                    .add(PARTS.TOP_BLOCK)))
            .add(PARTS.FILL_SELECTOR
                .add(new HeightSelector(61, 69)
                    .add(PARTS.TOP_BLOCK)))
            .add(new HeightSelector(56, 68).setMaxNoise(PARTS.DEPTH_NOISE)
                .add(PARTS.FILL_BLOCK))
            .add(PARTS.GENERIC_SURFACE);
    }

    @Override
    protected TerrainBase initTerrain() {
        return new TerrainBase() {
            @Override
            public float generateNoise(OpenSimplexNoise simplex, CellNoise cell, int x, int y, float border, float river) {
                return terrainBeach(x, y, simplex, river, 180f, 35f, 63f);
            }
        };
    }
}
