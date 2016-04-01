package rtg.world.biome.realistic.vanilla;

import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Biomes;
import rtg.util.noise.CellNoise;
import rtg.util.noise.OpenSimplexNoise;
import rtg.world.biome.deco.DecoTree;
import rtg.world.biome.deco.DecoTree.TreeType;
import rtg.world.gen.surface.SurfaceBase;
import rtg.world.gen.surface.vanilla.SurfaceVanillaBeach;
import rtg.world.gen.terrain.TerrainBase;

public class RealisticBiomeVanillaBeach extends RealisticBiomeVanillaBase {

    public static IBlockState topBlock = Biomes.beach.topBlock;
    public static IBlockState fillerBlock = Biomes.beach.fillerBlock;

    public RealisticBiomeVanillaBeach() {
        super(
                Biomes.beach,
                Biomes.river
        );

        /**
         * ##################################################
         * # DECORATIONS (ORDER MATTERS)
         * ##################################################
         */

        // Scattered palm trees.
        DecoTree palmTrees = new DecoTree();
        palmTrees.loops = 1;
        palmTrees.treeType = TreeType.VANILLA_BEACH_PALM;
        palmTrees.maxY = 80;
        this.addDeco(palmTrees, this.config._boolean(BiomeConfigProperty.DECORATION_TREE_PALM));
    }

    @Override
    protected SurfaceBase initSurface() {
        return new SurfaceVanillaBeach(config, topBlock, fillerBlock, topBlock, fillerBlock, (byte) 0, 1);
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
