package rtg.world.biome.realistic.vanilla;

import net.minecraft.init.Biomes;
import net.minecraft.init.Blocks;
import rtg.util.noise.CellNoise;
import rtg.util.noise.OpenSimplexNoise;
import rtg.world.biome.deco.DecoTree;
import rtg.world.biome.deco.DecoTree.TreeType;
import rtg.world.gen.surface.SurfaceBase;
import rtg.world.gen.surface.vanilla.SurfaceVanillaBeach;
import rtg.world.gen.terrain.TerrainBase;

public class RealisticBiomeVanillaBeach extends RealisticBiomeVanillaBase
{

    public RealisticBiomeVanillaBeach() {
        super(
                Biomes.beach,
                Biomes.river
        );
    }

    @Override
    protected SurfaceBase initSurface() {
        return new SurfaceVanillaBeach(this);
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

    @Override
    protected void initProperties()
    {
        config.addBlock(config.CLIFF_BLOCK_1).setDefault(Blocks.dirt.getDefaultState());
        config.addBlock(config.CLIFF_BLOCK_2).setDefault(Blocks.stone.getDefaultState());
    }

    @Override
    protected void initDecos()
    {
        // Scattered palm trees.
        DecoTree palmTrees = new DecoTree();
        palmTrees.loops = 1;
        palmTrees.treeType = TreeType.VANILLA_BEACH_PALM;
        palmTrees.maxY = 80;
        this.addDeco(palmTrees);
    }
}
